package org.mycompany.controller;

import javax.inject.Inject;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mycompany.service.Bird;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;

public class BirdAmbiguousTest extends TestConfig{

	@Autowired Bird bird;
	@Inject Bird bird;
	
	@Rule
	public ExpectedException expectedExcetption = ExpectedException.none();

	@Test
	public void autowiredInterface() {
		expectedExcetption.expect(BeanCreationException.class);
		
		bird1.whoAmI();
		
	}
}
