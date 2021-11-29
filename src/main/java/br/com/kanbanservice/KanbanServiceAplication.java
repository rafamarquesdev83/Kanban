package br.com.kanbanservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.kanbanservice.util.AES;

@SpringBootApplication
public class KanbanServiceAplication {

	public static void main(String[] args) {
		SpringApplication.run(KanbanServiceAplication.class, args);
		
		AES.setKey("teste");
	}
}


