package database.model.dao;

import java.util.List;

import database.connection.Connector;
import database.model.dao.implementations.jdbc.character.CharacterDao;
import database.model.dao.implementations.jdbc.character.CharacterDaoJDBC;
import database.model.dao.implementations.jdbc.user.UserDao;
import database.model.dao.implementations.jdbc.user.UserDaoJDBC;
import database.model.entities.Character;
import database.model.entities.User;

public interface Database {

	static CharacterDaoJDBC characterJdbc = DaoFactory.createCharacterDao(false);
	static UserDaoJDBC userJdbc = DaoFactory.createUserDao(false);

	static <T extends Database> void create(GenericDao<T> dao, T t, boolean openConnection, boolean closeConnection) {
		if (openConnection)
			dao.openConnection();
		dao.create(t);
		if (closeConnection)
			Connector.close(dao.getConnection());
	}

	static <T extends Database> List<T> read(GenericDao<T> dao, boolean openConnection, boolean closeConnection) {
		if (openConnection)
			dao.openConnection();
		List<T> list = dao.getAll();
		if (closeConnection)
			Connector.close(dao.getConnection());
		return list;
	}

	static <T extends Database> void update(GenericDao<T> dao, T t, int id, boolean openConnection, boolean closeConnection) {
		if (openConnection)
			dao.openConnection();
		dao.update(t, id);
		if (closeConnection)
			Connector.close(dao.getConnection());
	}

	static <T extends Database> void delete(GenericDao<T> dao, T t, boolean openConnection, boolean closeConnection) {
		if (openConnection)
			dao.openConnection();
		dao.delete(t);
		if (closeConnection)
			Connector.close(dao.getConnection());
	}

	static <T extends Database> void deleteById(GenericDao<T> dao, int id, boolean openConnection,
			boolean closeConnection) {
		if (openConnection)
			dao.openConnection();
		dao.deleteById(id);
		if (closeConnection)
			Connector.close(dao.getConnection());
	}

	static <T extends Database> T find(GenericDao<T> dao, T t, boolean openConnection, boolean closeConnection) {
		if (openConnection)
			dao.openConnection();
		T result = dao.find(t);
		if (closeConnection)
			Connector.close(dao.getConnection());
		return result;
	}

	static <T extends Database> T findById(GenericDao<T> dao, int id, boolean openConnection, boolean closeConnection) {
		if (openConnection)
			dao.openConnection();
		T result = dao.findById(id);
		if (closeConnection)
			Connector.close(dao.getConnection());
		return result;
	}

	static List<Character> findByUserId(CharacterDao dao, int id, boolean openConnection, boolean closeConnection) {
		if (openConnection)
			dao.openConnection();
		List<Character> characterList = dao.findByUserId(id);
		if (closeConnection)
			Connector.close(dao.getConnection());
		return characterList;
	}

	static User login(UserDao dao, User user, boolean openConnection, boolean closeConnection) {
		if (openConnection)
			dao.openConnection();
		User login = dao.login(user);
		if (closeConnection)
			Connector.close(dao.getConnection());
		return login;
	}
	
	
}
