package database.model.entities;

import java.util.List;
import java.util.Objects;

import database.model.dao.Database;

public class User implements Database {
	private int id;
	private String name;
	private String password;
	private List<Character> characterList;

	public User(int id, String name, String password, List<Character> characterList) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.characterList = characterList;
	}

	public User(int id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
	}

	public User(String name, String password, List<Character> characterList) {
		this.name = name;
		this.password = password;
		this.characterList = characterList;
	}

	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Id: " + id + ", Name: " + name + ", Password: " + password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Character> getCharacterList() {
		return characterList;
	}

	public void setCharacterList(List<Character> characterList) {
		this.characterList = characterList;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return id == other.id;
	}
}
