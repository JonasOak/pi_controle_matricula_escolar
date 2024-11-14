package com.sollares.controller.resources;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sollares.controller.services.DisciplinaService;
import com.sollares.controller.services.MatriculaService;
import com.sollares.controller.services.PessoaService;
import com.sollares.exception.LimiteException;
import com.sollares.model.entities.Disciplina;
import com.sollares.model.entities.Matricula;
import com.sollares.model.entities.Pessoa;
import com.sollares.model.entities.Usuario;
import com.sollares.model.repositories.MatriculaRepository;
import com.sollares.model.repositories.PessoaRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes("usuarioLogado")
public class MatriculaResources {

	@Autowired
	private MatriculaService servico;

	@Autowired
	private DisciplinaService servicoDisciplina;

	@Autowired
	private PessoaService servicoPessoa;

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private MatriculaRepository matriculaRepository;

	@ModelAttribute("usuarioLogado")
	public Usuario getUsuarioLogado(HttpSession session) {
		return (Usuario) session.getAttribute("usuarioLogado");
	}

	@GetMapping("/faturamento")
	public String getFaturamento(HttpSession session, Model model) {
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		if (usuarioLogado == null) {
			model.addAttribute("msgFaltaLogin", "Por favor, faça login para acessar o portal.");
			return "redirect:/index";
		}
		
		List<Disciplina> listaDisciplinas = servicoDisciplina.buscarTodos();
	    model.addAttribute("listaDisciplinas", listaDisciplinas);

		return "faturamento";
	}

	@GetMapping("/cursoProfessor")
	public String getCursoProfessor(HttpSession session, Model model) {
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		if (usuarioLogado == null) {
			model.addAttribute("msgFaltaLogin", "Por favor, faça login para acessar o portal.");
			return "redirect:/index";
		}
		List<Pessoa> professores = pessoaRepository.findDistinctProfessores();
		model.addAttribute("professores", professores);
		return "cursoProfessor";
	}

	@PostMapping("/consultar/cursoProfessor")
	public String consultar(@RequestParam("professorId") Integer professorId, Model model) {
		Optional<Pessoa> professorOpt = pessoaRepository.findById(professorId);
		Pessoa professor = professorOpt.orElse(null);
		List<Disciplina> disciplinas = matriculaRepository.buscarDisciplinasPorProfessor(professorId);

		model.addAttribute("disciplinas", disciplinas);
		model.addAttribute("professor", professor);
		return "listarCursoProfessor";
	}

	@GetMapping("/cursoAluno")
	public String getCursoAluno(HttpSession session, Model model) {
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		if (usuarioLogado == null) {
			model.addAttribute("msgFaltaLogin", "Por favor, faça login para acessar o portal.");
			return "redirect:/index";
		}
		List<Pessoa> alunos = pessoaRepository.findDistinctAlunos();
		model.addAttribute("alunos", alunos);
		return "cursoAluno";
	}

	@PostMapping("/consultar/cursoAluno")
	public String consultarCursosAluno(@RequestParam("alunoId") Integer alunoId, Model model) {
		Optional<Pessoa> alunoOpt = pessoaRepository.findById(alunoId);
		Pessoa aluno = alunoOpt.orElse(null);
		List<Disciplina> disciplinas = matriculaRepository.buscarDisciplinasPorAluno(alunoId);

		model.addAttribute("disciplinas", disciplinas);
		model.addAttribute("aluno", aluno);
		return "listarCursoAluno";
	}

	@GetMapping("/matriculas")
	public String listarTodos(HttpSession session, Model model) {
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		if (usuarioLogado == null) {
			model.addAttribute("msgFaltaLogin", "Por favor, faça login para acessar o portal.");
			return "redirect:/index";
		}
		List<Matricula> listaAlunos = servico.buscarMatriculaComAlunos();
		List<Matricula> listaDisciplinas = servico.buscarDisciplinasComProfessores();
		model.addAttribute("listaAlunos", listaAlunos);
		model.addAttribute("listaMatriculas", listaDisciplinas);
		return "listarMatriculas";
	}

	@GetMapping("/matricula/buscar")
	public String buscarMatriculasPorNome(@RequestParam(value = "nome", required = false) String nome, Model model) {
		List<Matricula> matricula = servico.buscarPorNome(nome);
		model.addAttribute("listaMatriculas", matricula);
		return "listarMatriculas";
	}

	@GetMapping("/matriculaCadastrar")
	public String cadastrarMatricula(HttpSession session, Model model) {
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		if (usuarioLogado == null) {
			model.addAttribute("msgFaltaLogin", "Por favor, faça login para acessar o portal.");
			return "redirect:/index";
		}
		model.addAttribute("matricula", new Matricula());
		List<Disciplina> listaDisciplinas = servicoDisciplina.buscarTodos();
		model.addAttribute("listaDisciplinas", listaDisciplinas);
		List<Pessoa> listaPessoas = matriculaRepository.buscarPessoas();
		model.addAttribute("listaPessoas", listaPessoas);

		return "matriculaCadastrar";
	}

	@PostMapping("/matricula")
	public String inserir(@ModelAttribute("matricula") Matricula matricula, RedirectAttributes redirectAttributes,
			Model model) {
		try {
			servico.inserir(matricula);
			redirectAttributes.addFlashAttribute("sucessoCadastro", "Matrícula cadastrada com sucesso.");
			return "redirect:/matriculas";
		} catch (LimiteException e) {
			redirectAttributes.addFlashAttribute("errorInserir", "Erro ao cadastrar matrícula: " + e.getMessage());
			return "redirect:/matriculas";
		}

	}

	@GetMapping("/matriculaAtualizar/{id}")
	public String mostrarAtualizarMatricula(@PathVariable("id") Integer id, Model model) {
		Matricula matricula = servico.buscarPorId(id);
		List<Disciplina> listaDisciplinas = servicoDisciplina.buscarTodos();
		List<Pessoa> listaPessoas = matriculaRepository.buscarPessoas();

		model.addAttribute("matricula", matricula);
		model.addAttribute("listaDisciplinas", listaDisciplinas);
		model.addAttribute("listaPessoas", listaPessoas);

		return "matriculaAtualizar";
	}

	@PostMapping("/confirmarAtualizarMatricula/{id}")
	public String atualizarMatricula(@PathVariable("id") Integer id,
			@ModelAttribute("matricula") Matricula matriculaAtualizada, RedirectAttributes redirectAttributes,
			Model model) {
		try {
			servico.atualizar(id, matriculaAtualizada);
			redirectAttributes.addFlashAttribute("sucessoAtualizar", "Matrícula atualizada com sucesso.");
			return "redirect:/matriculas";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("erroAtualizar", "Erro ao atualizar matrícula: " + e.getMessage());
			return "matriculaAtualizar";
		}
	}

	@GetMapping("/matriculaDeletar/{id}")
	public String mostrarDeletarMatricula(@PathVariable("id") int codigo, Model model) {
		Matricula matricula = servico.buscarPorId(codigo);
		List<Disciplina> listaDisciplinas = servicoDisciplina.buscarTodos();
		List<Pessoa> listaPessoas = matriculaRepository.buscarPessoas();

		model.addAttribute("matricula", matricula);
		model.addAttribute("listaDisciplinas", listaDisciplinas);
		model.addAttribute("listaPessoas", listaPessoas);
		return "matriculaDeletar";
	}

	@GetMapping("/confirmarDeletarMatricula/{codigo}")
	public String deletar(@PathVariable("codigo") int codigo, RedirectAttributes redirectAttributes) {
		try {
			servico.deletar(codigo);
			redirectAttributes.addFlashAttribute("sucessoDeletar", "Matricula deletada com sucesso.");
		} catch (Exception e) {
			System.out.println("Erro ao deletar disciplina: " + e.getMessage());
			redirectAttributes.addFlashAttribute("erroDeletar", "Erro ao deletar matrícula.");
		}
		return "redirect:/matriculas";
	}

	@PostMapping("/faturamentoTotal")
	public String calcularFaturamento(@RequestParam("disciplina") Integer disciplinaCodigo,
			@RequestParam("dataInicial") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
			@RequestParam("dataFinal") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal,
			Model model) {

		List<Disciplina> listaDisciplinas = servicoDisciplina.buscarTodos();
		Disciplina disciplina = servicoDisciplina.buscarPorId(disciplinaCodigo);

		BigDecimal faturamentoTotal = servico.consultarFaturamento(disciplina, dataInicial, dataFinal);

		model.addAttribute("faturamentoTotal", faturamentoTotal);
		model.addAttribute("listaDisciplinas", listaDisciplinas);

		return "faturamento";
	}

}
