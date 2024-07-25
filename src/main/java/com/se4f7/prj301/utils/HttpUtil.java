package com.se4f7.prj301.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;

import javax.servlet.http.Part;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.se4f7.prj301.constants.ErrorMessage;

public class HttpUtil {

	private String value;

	public HttpUtil(String value) {
		this.value = value;
	}

	public <T> T toModel(Class<T> tClass) {
		try {
			return new ObjectMapper().readValue(value, tClass);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		return null;
	}

	public static HttpUtil of(BufferedReader reader) {
		StringBuilder sb = new StringBuilder();
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			System.out.print(e.getMessage());
		}
		return new HttpUtil(sb.toString());
	}

	public static <T> T ofFormData(Collection<Part> parts, Class<T> tClass) {
		try {
			StringBuilder sb = new StringBuilder();
			for (Part part : parts) {
				if ("application/json".equals(part.getContentType())) {
					InputStream inputStream = part.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
					sb.append(HttpUtil.of(reader).value);
				}
			}
			return new ObjectMapper().readValue(sb.toString(), tClass);
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.MISSING_BODY_PAYLOAD);
		}
	}
	public static HttpUtil ofFormData(Part part) {
		try {
			InputStream inputStream = part.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			return of(reader);
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.MISSING_BODY_PAYLOAD);
		}

	}
}
