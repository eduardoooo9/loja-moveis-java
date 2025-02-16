package br.ifpe.jaboatao.loja;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Banco {

    // Metodo para fazer a conexão
    public static Connection getConnection() throws SQLException {
        try {

            // Carrega o driver do JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Encontrado!");
            // faz a conexao
            Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/loja", "root", "");
            System.out.println("Conectado Com Sucesso!");
            return conexao;

        } catch (ClassNotFoundException e) {
            throw new SQLException("ERRO: ", e);
        }
    }

}
