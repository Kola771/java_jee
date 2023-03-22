package bj.highfive.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bj.highfive.usermanagement.bean.User;

public class UserDao {

	// Connexion avec la base de données
	public Connection getConnection() {
		Connection conn = null;

		try {
			// Je veux me connecter avec la BDD mais j'ai besoin d'un pilote (driver)
			Class.forName("com.mysql.jdbc.Driver");

			// Récupération de l'objet
			// getConnection() est la méthode qui se connecte à la BDD au travers de
			// l'interface DriverManager
			/*
			 * get(dsn, username,password) dsn = data source name ex php: $dsn =
			 * "mysql:host=localhost;dbname=user_app;port=3306;charset=UTF-8"; $conn = new
			 * PDO($dsn, "root", "")
			 * 
			 * dns mysql java ==> "jdbc:mysql://hébergeur:port/nom_de_la_BDD
			 */
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_app", "root", "");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return conn;
	}

	/**
	 * Create => créé une ressource (createUser) Read => lire une ressource
	 * (getUserById | getAllUsers) Update => maj une ressource (updateuser) Delete
	 * => suppr une ressource (deleteUser)
	 */
	
	public boolean createUser(User user)
	{
		boolean result = false;
		// Etape: Effectuée avec succcès (récupération de l'objet de connection)
		Connection connection = getConnection();
		try {
			// Etape: Créer la requête
			PreparedStatement ps = connection
					.prepareStatement("INSERT INTO `user_app`.users (name, email, country) VALUES(?,?,?);");
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getCountry());
			// Etape: Exécuter la requête
			result = ps.execute();
			// Etape: Fermer la requête
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
