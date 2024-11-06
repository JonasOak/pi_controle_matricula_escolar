package com.sollares.model.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ConverterData implements Converter<String, Date> {

	private static final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public Date convert(String source) {
		try {
			return fmt.parse(source);
		} catch (Exception e) {
			return null;
		}
	}

}
