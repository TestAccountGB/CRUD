package application;

import java.util.Scanner;

import database.model.dao.Database;
import database.model.entities.User;

public class Home {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		scan.useDelimiter(System.lineSeparator());
		
		try {
			while (true) {

				System.out.println("1) Login");
				System.out.println("2) Signup");
				String answer = scan.next();

				switch (answer) {
				case "1":
					login(scan);
					break;
				case "2":
					signup(scan);
					break;
				default:
					break;
				}
			}
		} finally {
			scan.close();
		}
	}

	private static void login(Scanner scan) {
		System.out.print("Username: ");
		String username = scan.next();

		System.out.print("Password: ");
		String password = scan.next();

		User user = Database.login(Database.userJdbc, new User(username, password), true , true);

		if (user != null) {
			Program.program(user, scan);
			return;
		}
		System.out.println("Usuario ou senha incorreto");
	}

	private static void signup(Scanner scan) {
		System.out.print("Username: ");
		String username = scan.next();
		//TODO: verificarion.username(username);

		System.out.print("Password: ");
		String password = scan.next();
		//TODO: verification.password(password);

		Database.create(Database.userJdbc, new User(username, password), true , true);
		System.out.println("Usuario criado com sucesso");
	}

}
