package database.model.dao;

import database.connection.Connector;
import database.model.dao.implementations.jdbc.character.CharacterDaoJDBC;
import database.model.dao.implementations.jdbc.user.UserDaoJDBC;

public class DaoFactory {
	
	public static <T extends Database> CharacterDaoJDBC createCharacterDao(boolean connection) {
		if(!connection)
			return new CharacterDaoJDBC();
		return new CharacterDaoJDBC(Connector.getConnetion());
	}
	
	public static UserDaoJDBC createUserDao(boolean connection) {
		if(!connection)
			return new UserDaoJDBC();
		return new UserDaoJDBC(Connector.getConnetion());
	}
	
}
