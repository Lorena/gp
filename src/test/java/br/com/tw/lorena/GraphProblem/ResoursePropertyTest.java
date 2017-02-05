package br.com.tw.lorena.GraphProblem;

import br.com.tw.lorena.resource.ResourseProperty;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ResoursePropertyTest {
	
	@Test 
	public void shouldFindPropertyInPropertyFileTest() throws IOException {
		String waitedProperty = "Enter with tow towns.";
		String returnedProperty = ResourseProperty.getProperty("enter_with_two_towns");
		assertEquals(waitedProperty, returnedProperty);
	}

}
