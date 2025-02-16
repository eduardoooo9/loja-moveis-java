package br.ifpe.jaboatao.loja;

import java.sql.Connection;
import java.sql.SQLException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaApplication {


	public static void main(String[] args) {
		try {
			// Conecta ao banco de dados atráves da classe Banco
			Connection conexao = Banco.getConnection();
		} catch (SQLException e) {
			// printa o erro do SQL no terminal
			System.out.println("Erro: " + e);
		}
		SpringApplication.run(JavaApplication.class, args);
	}

}
