package org.mycompany.service;

import org.springframework.stereotype.Component;

@Component
public class BirdChicken implements Bird{

	@Override
	public String whoAmI() {
		return new String("chicken");
	}

}
