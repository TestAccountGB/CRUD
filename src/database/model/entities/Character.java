package database.model.entities;

import java.util.Objects;

import database.model.dao.Database;

public class Character implements Database{
	private int id;
	private String name;
	private Class characterClass;
	private int level;
	private int userId;

	public Character(int id, String name, Class characterClass, int level, int userId) {
		this.id = id;
		this.name = name;
		this.characterClass = characterClass;
		this.level = level;
		this.userId = userId;
	}

	public Character(String name, Class characterClass, int level, int userId) {
		this.name = name;
		this.characterClass = characterClass;
		this.level = level;
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Name: " + name + ", Class: " + getCharacterClassInString() + ", Level: " + level;
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

	public Class getCharacterClass() {
		return characterClass;
	}
	
	public String getCharacterClassInString() {
		String characterClassString = characterClass.toString().substring(1).toLowerCase();
		char firstLetter = characterClass.toString().charAt(0);
		return firstLetter + characterClassString;
	}

	public void setCharacterClass(Class characterClass) {
		this.characterClass = characterClass;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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
		Character other = (Character) obj;
		return id == other.id;
	}

}
