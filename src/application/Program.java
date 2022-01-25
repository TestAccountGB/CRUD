package application;

import java.util.List;
import java.util.Scanner;

import database.model.dao.Database;
import database.model.entities.Character;
import database.model.entities.Class;
import database.model.entities.User;

public class Program {

	private static boolean logged;

	public static void program(User user, Scanner scan) {

		logged = true;

		// TODO: A better implematation - (logged)
		while (logged) {
			System.out.println("1) Play");
			System.out.println("2) Characters");
			System.out.println("3) Options");
			System.out.println("4) Logout");
			String answer = scan.next();

			switch (answer) {
			case "1":
				System.out.println("Maybe? ");
				break;
			case "2":
				characters(user, scan);// - Mostrar todos os personagens da conta
				break;
			case "3":
				options(scan, user);
				break;
			case "4":
				return;
			default:
				break;
			}
		}
	}

	private static void characters(User user, Scanner scan) {

		System.out.println("1) Show characters");
		System.out.println("2) Create a new character");
		System.out.println("3) Delete a character");
		System.out.println("4) Update a character");

		String answer = scan.next();

		switch (answer) {
		case "1":
			showCharacters(user.getCharacterList(), user);
			break;
		case "2":
			createCharacter(scan, user);
			break;
		case "3":
			deleteCharacter(scan, user);
			break;
		case "4":
			updateCharacter(scan, user);
			break;
		default:
			break;
		}

	}

	private static void showCharacters(List<Character> characterList, User user) {
		if (!characterList.isEmpty()) {
			characterList.forEach(System.out::println);
			return;
		}

		System.out.println("User don't have any character");
	}

	private static void createCharacter(Scanner scan, User user) {
		System.out.print("Name: ");
		String name = scan.next();

		System.out.println("Class: ");
		Class characterClass = chooseCharacterClass(scan);

		System.out.print("Level? "); // I know, this is dumb. Too bad!
		int level = scan.nextInt();

		Database.create(Database.characterJdbc, new Character(name, characterClass, level, user.getId()), true, true);

		refreshCharacterList(user);
	}

	private static void deleteCharacter(Scanner scan, User user) {
		int i = 1;
		for (Character character : user.getCharacterList()) {
			System.out.println(i + ") " + character);
			i++;
		}

		System.out.print("Choose one to delete: ");
		int id = scan.nextInt();

		if (id > 0) {
			id -= 1;
		}

		Database.deleteById(Database.characterJdbc, user.getCharacterList().get(id).getId(), true, true);

		refreshCharacterList(user);
	}

	private static void updateCharacter(Scanner scan, User user) {
		int i = 1;
		for (Character character : user.getCharacterList()) {
			System.out.println(i + ") " + character);
			i++;
		}

		System.out.print("Choose one to delete: ");
		int id = scan.nextInt();

		if (id > 0) {
			id -= 1;
		}

		System.out.print("Name: ");
		String name = scan.next();

		System.out.println("Class: ");
		Class characterClass = chooseCharacterClass(scan);

		System.out.print("Level? ");
		int level = scan.nextInt();

		Database.update(Database.characterJdbc, new Character(name, characterClass, level, user.getId()),
				user.getCharacterList().get(id).getId(), true, true);

		refreshCharacterList(user);
	}

	private static void refreshCharacterList(User user) {
		user.setCharacterList(Database.findByUserId(Database.characterJdbc, user.getId(), true, true));
	}

	private static void options(Scanner scan, User user) {
		System.out.println("1) Change the name/password");
		System.out.println("2) Delete account");

		String answer = scan.next();

		switch (answer) {
		case "1":
			updateAccount(scan, user);
			break;
		case "2":
			deleteAccount(scan, user);
			break;
		default:
			break;
		}
	}

	private static void updateAccount(Scanner scan, User user) {

		System.out.print("Name: ");
		String newName = scan.next();

		user.setName(newName);
		Database.update(Database.userJdbc, user, user.getId(), true, true);
	}

	private static void deleteAccount(Scanner scan, User user) {

		System.out.println("ARE YOU SURE ABOUT THAT? y/n");

		char answer = scan.next().charAt(0);

		if (answer == 'y') {
			System.out.println(":(");
			Database.deleteById(Database.userJdbc, user.getId(), true, true);
			logged = false;
		} else {
			System.out.println("Ok");
		}
	}

	private static Class chooseCharacterClass(Scanner scan) {

		while (true) {
			System.out.println("1) Knight");
			System.out.println("2) Healer");
			System.out.println("3) Tank");
			System.out.println("4) Hunter");
			System.out.println("5) Sorcerer");

			String answer = scan.next();

			switch (answer) {
			case "1":
				return Class.KNIGHT;
			case "2":
				return Class.HEALER;
			case "3":
				return Class.TANK;
			case "4":
				return Class.HUNTER;
			case "5":
				return Class.SORCERER;
			default:
				break;
			}
		}
	}

}
