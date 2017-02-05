package br.com.tw.lorena.controller;

import br.com.tw.lorena.resource.ResourseProperty;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ResoursePropertyTest {
	
	@Test 
	public void shouldFindPropertyInPropertyFileTest() throws IOException {
		String waitedProperty = "Enter with tow towns.";
		String returnedProperty = new ResourseProperty().getProperty("enter_with_two_towns");
		assertEquals(waitedProperty, returnedProperty);
	}

	@Test
	public void shouldGiveNullWhenMissProperty() throws IOException {
		String property = new ResourseProperty().getProperty("nanana");
		assertNull(property);
	}

}
