package database.model.dao.implementations.jdbc.user;

import database.model.dao.GenericDao;
import database.model.entities.User;

public interface UserDao extends GenericDao<User>{
	
	User login(User user);
	
}
