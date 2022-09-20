package com.mtech.services.dao;
import java.sql.Connection;
import java.sql.DriverManager;

public class Service {
	//Variaveis de conexao
	private Connection connection;


    private final String host = "localhost";
    private final String database = "dbinfox";
    private final int port = 5432;
    private final String user = "MeuUserNoBanco";
    private final String pass = "MinhaSenhaDoBanco";


    private String url = "jdbc:postgresql://%s:%d/%s";
    
    public Service() {
    	this.url = String.format(this.url, this.host, this.port, this.database);
    	
    }
	
	
	//Metodo responsavel por estabelecer a conexao com o banco
	public Connection conector() {
		
		try {
			//Criar instancia de conexao com o postgreeSql
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(url,user,pass);
			
			System.out.println("SUCESSO conectar************"+connection);
			return connection;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Erro conectar************"+e);
			return null;
		}
		
		
		
	}
		
}
