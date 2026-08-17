package com.giovani.park.config;

import java.util.TimeZone;

import jakarta.annotation.PostConstruct;

public class SpringTimezoneConfig {
	
	
	@PostConstruct
    public void timezoneConfig() {
        // Define o fuso horário oficial da aplicação Java para GMT-3
        TimeZone.setDefault(TimeZone.getTimeZone("America/Bahia"));
    }

}
