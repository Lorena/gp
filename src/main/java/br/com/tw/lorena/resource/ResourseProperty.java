package br.com.tw.lorena.resource;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ResourseProperty {

	private static Properties properties;
	private static ResourseProperty instancia;

	public static synchronized ResourseProperty getInstancia() throws IOException {
		if (instancia == null)
			instancia = new ResourseProperty();
		return instancia;
	}

	public static Properties getProperties() {
		return properties;
	}

	public ResourseProperty() throws IOException {
		properties = new Properties();
		FileInputStream file = new FileInputStream("src/main/java/br/com/tw/lorena/resource/graphProblem.properties");
		properties.load(file);
	}

	public static String getProperty(String name) throws IOException {
		String field = getInstancia().getProperties().getProperty(name);
		return field;
	}
}