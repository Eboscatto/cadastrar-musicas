package br.com.everaldoboscatto.regitersongs;

import br.com.everaldoboscatto.regitersongs.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RegitersongsApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(RegitersongsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.exibeMenu();
	}
}
