package database.model.dao.implementations.jdbc.character;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.connection.Connector;
import database.model.entities.Character;
import database.model.entities.Class;

public class CharacterDaoJDBC implements CharacterDao {

	private Connection connection;

	public CharacterDaoJDBC(Connection connection) {
		this.connection = connection;
	}

	public CharacterDaoJDBC() {
	}

	@Override
	public Connection getConnection() {
		return connection;
	}
	
	@Override
	public void openConnection() {
		if(Connector.isClosed(this.connection))
			this.connection = Connector.getConnetion();
	}

	@Override
	public void create(Character character) {
		String query = "INSERT INTO `character`(name, class, level, userId) values (?, ?, ?, ?);";

		try (PreparedStatement ps = connection.prepareStatement(query)) {

			ps.setString(1, character.getName());
			ps.setString(2, character.getCharacterClass().toString());
			ps.setInt(3, character.getLevel());
			ps.setInt(4, character.getUserId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Character> getAll() {

		String query = "SELECT * FROM `character`;";
		List<Character> characterList = new ArrayList<>();

		try (PreparedStatement ps = connection.prepareStatement(query)) {

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("Id");
				String name = rs.getString("name");
				Class characterClass = Class.valueOf(rs.getString("class"));
				int level = rs.getInt("level");
				int userId = rs.getInt("userId");

				characterList.add(new Character(id, name, characterClass, level, userId));
			}

			return characterList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(Character character, int id) {

		String query = "UPDATE `character` SET name = ?, class = ?, level = ? WHERE (id = ?);";

		try (PreparedStatement ps = connection.prepareStatement(query)) {

			ps.setString(1, character.getName());
			ps.setString(2, character.getCharacterClass().toString());
			ps.setInt(3, character.getLevel());
			ps.setInt(4, id);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Character character) {
		String query = "DELETE FROM `character` WHERE (id = ?);";

		try (PreparedStatement ps = connection.prepareStatement(query)) {

			ps.setInt(1, character.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteById(int idArgs) {
		String query = "DELETE FROM `character` WHERE (id = ?);";

		try (PreparedStatement ps = connection.prepareStatement(query)) {

			ps.setInt(1, idArgs);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Character find(Character character) {
		String query = "SELECT * FROM `character` WHERE (id = ?);";

		try (PreparedStatement ps = connection.prepareStatement(query)) {

			ps.setInt(1, character.getId());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("Id");
				String name = rs.getString("name");
				Class characterClass = Class.valueOf(rs.getString("class"));
				int level = rs.getInt("level");
				int userId = rs.getInt("userId");

				return new Character(id, name, characterClass, level, userId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Character findById(int idArgs) {
		String query = "SELECT * FROM `character` WHERE (id = ?);";

		try (PreparedStatement ps = connection.prepareStatement(query)) {

			ps.setInt(1, idArgs);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("Id");
				String name = rs.getString("name");
				Class characterClass = Class.valueOf(rs.getString("class"));
				int level = rs.getInt("level");
				int userId = rs.getInt("userId");

				return new Character(id, name, characterClass, level, userId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Character> findByUserId(int idArgs) {
		String query = "SELECT * FROM `character` WHERE (userId = ?);";
		List<Character> characterList = new ArrayList<>();

		try (PreparedStatement ps = connection.prepareStatement(query)) {

			ps.setInt(1, idArgs);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("Id");
				String name = rs.getString("name");
				Class characterClass = Class.valueOf(rs.getString("class"));
				int level = rs.getInt("level");
				int userId = rs.getInt("userId");

				characterList.add(new Character(id, name, characterClass, level, userId));
			}

			return characterList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
