package me.lucasgsalmeida.gestao10x;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class Gestao10xApplication {

	public static void main(String[] args) {
		SpringApplication.run(Gestao10xApplication.class, args);
	}

}
