package br.com.tw.lorena.GraphProblem;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.tw.lorena.resource.ResourseProperty;

public class ResoursePropertyTest {
	
	@Test 
	public void findPropertyFileTest(){
		String waited = "";
		String returned = ResourseProperty.getProperty("nanananna");
		assertEquals(waited, returned);
	}

}
