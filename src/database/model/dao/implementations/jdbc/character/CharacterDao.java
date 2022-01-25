package database.model.dao.implementations.jdbc.character;

import java.util.List;

import database.model.dao.GenericDao;
import database.model.entities.Character;

public interface CharacterDao extends GenericDao<Character>{
	
	List<Character> findByUserId(int id);
	
}
