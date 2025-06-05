package com.hernandezedwin.LiterBook;

import com.hernandezedwin.LiterBook.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterBookApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiterBookApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		//principal.mostrarPrincipal();
		principal.menu();
	}
}
