package database.model.dao.implementations.jdbc.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.connection.Connector;
import database.model.dao.Database;
import database.model.entities.Character;
import database.model.entities.User;

public class UserDaoJDBC implements UserDao {

	private Connection connection;

	public UserDaoJDBC(Connection connection) {
		this.connection = connection;
	}

	public UserDaoJDBC() {
	}

	@Override
	public Connection getConnection() {
		return connection;
	}

	@Override
	public void openConnection() {
		if (Connector.isClosed(this.connection))
			this.connection = Connector.getConnetion();
	}

	@Override
	public void create(User user) {
		if (user == null || user.getName() == null || user.getPassword() == null) {
			throw new RuntimeException("Comprador invalido");
		}

		String query = "INSERT INTO user(name, password) values (?, ?);";

		try (PreparedStatement ps = connection.prepareStatement(query)) {

			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<User> getAll() {

		String query = "SELECT * FROM user;";
		List<User> userList = new ArrayList<>();

		try (PreparedStatement ps = connection.prepareStatement(query)) {

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String password = rs.getString("password");
				List<Character> characterList = Database.findByUserId(Database.characterJdbc, id, false, false);

				userList.add(new User(id, name, password, characterList));
			}

			return userList;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void update(User user, int id) {
		String query = "UPDATE user SET name = ?, password = ? WHERE (id = ?);";

		try (PreparedStatement ps = connection.prepareStatement(query)) {

			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());
			ps.setInt(3, user.getId());
			ps.setInt(4, id);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(User user) {
		String query = "DELETE FROM user WHERE (id = ?)";

		try (PreparedStatement ps = connection.prepareStatement(query)) {

			ps.setInt(1, user.getId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteById(int id) {
		String query = "DELETE FROM user WHERE (id = ?)";

		try (PreparedStatement ps = connection.prepareStatement(query)) {

			ps.setInt(1, id);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public User find(User user) {
		String query = "SELECT * FROM user WHERE (id = ?)";

		try (PreparedStatement ps = connection.prepareStatement(query)) {

			ps.setInt(1, user.getId());
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("Id");
				String name = rs.getString("name");
				String password = rs.getString("password");
				List<Character> characterList = Database.findByUserId(Database.characterJdbc, id, false, false);
				
				return new User(id, name, password, characterList);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User findById(int idArgs) {
		String query = "SELECT * FROM user WHERE (id = ?)";

		try (PreparedStatement ps = connection.prepareStatement(query)) {

			ps.setInt(1, idArgs);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("Id");
				String name = rs.getString("name");
				String password = rs.getString("password");
				List<Character> characterList = Database.findByUserId(Database.characterJdbc, id, false, false);
				
				return new User(id, name, password, characterList);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public User login(User user) {
		ResultSet rs = null;
		String query = "SELECT * FROM user WHERE (name = ? AND password = ?);";

		try (PreparedStatement ps = connection.prepareStatement(query)) {

			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());
			rs = ps.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("Id");
				String name = rs.getString("name");
				String password = rs.getString("password");
				List<Character> characterList = Database.findByUserId(Database.characterJdbc, id, true, true);
				
				return new User(id, name, password, characterList);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connector.close(rs);
		}
		return null;
	}

}
