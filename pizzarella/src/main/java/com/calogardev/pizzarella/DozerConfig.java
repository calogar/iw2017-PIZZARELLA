package com.calogardev.pizzarella;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DozerConfig {

	// Encapsulating the instance so we can change parameters later
	@Bean
	public DozerBeanMapper dozer() {
		return new DozerBeanMapper();
	}
}
