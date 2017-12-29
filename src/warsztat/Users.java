package warsztat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
/**
 * @author karolpat
 * 
 */
public class Users {

	private int id = 0;
	private String username;
	private String password;
	private String email;
	private int person_group;

	// Wczytywanie z bazy
	public Users() {
	}

	// Tworzenie nowego
	public Users(String username, String password, String email, int person_group) {
		super();
		setUsername(username);
		setPassword(password);
		setEmail(email);
		setPerson_group(person_group);
	}

	public int getPerson_group() {
		return person_group;
	}

	public Users setPerson_group(int person_group) {
		this.person_group = person_group;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public Users setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public Users setPassword(String password) {
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());
		return this;
	}

	public String getEmail() {
		return email;
	}

	public Users setEmail(String email) {
		this.email = email;
		return this;
	}

	public int getId() {
		return id;
	}

	private Users setId(int id) {
		this.id = id;
		return this;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getUsername()).append(" | ").append(this.getEmail()).append(" | ").append(this.getId());
		return sb.toString();
	}

	
	/**
	 * @param conn
	 * @return - an array of all Users 
	 * @throws SQLException
	 */
	static public Users[] loadAll(Connection conn) throws SQLException {

		ArrayList<Users> users = new ArrayList<Users>();

		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery("Select * from users");

		while (res.next()) {

			Users tmpUsr = new Users();
			tmpUsr.setUsername(res.getString("username"));
			tmpUsr.setEmail(res.getString("email"));
			tmpUsr.password = res.getString("password");
			tmpUsr.setId(res.getInt("id"));

			users.add(tmpUsr);
		}

		Users[] userArr = new Users[users.size()];
		users.toArray(userArr);
		return userArr;
	}

	/**
	 * @param conn 
	 * @param id - id of User to load
	 * @return - an instance of the object with given id
	 * @throws SQLException
	 */
	static public Users loadById(Connection conn, int id) throws SQLException {

		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery("Select * from users where id=" + id);

		Users loaded = new Users();

		while (res.next()) {
			loaded.setId(res.getInt("id"));
			loaded.setUsername(res.getString("username"));
			loaded.setEmail(res.getString("email"));
			loaded.password = res.getString("password");
		}
		return loaded;

	}
	
	/** To load an array of users of group by given id group
	 * @param conn
	 * @param person_group -> id of the group
	 * @return -> An array of users
	 * @throws SQLException
	 */
	static public Users[] loadByGroupId(Connection conn, int person_group) throws SQLException {
		
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery("select * from users where person_group="+person_group);
		
		
		ArrayList<Users> tmpList = new ArrayList<>();
		
		while(res.next()) {
			
			Users loaded = new Users();
			
			loaded.setId(res.getInt("id"));
			loaded.setUsername(res.getString("username"));
			loaded.setEmail(res.getString("email"));
			
			tmpList.add(loaded);
		}
		
		Users[] usersArr = new Users[tmpList.size()];
		tmpList.toArray(usersArr);
		return usersArr;
	}

	public Users saveToDb(Connection conn) throws SQLException {

		if (this.getId() == 0) {
			// save to db

			String[] generatedColumns = { "id" };

			PreparedStatement pst = conn.prepareStatement(
					"insert into users (username, email, password, person_group) value (?, ?, ?, ?)", generatedColumns);
			pst.setString(1, this.getUsername());
			pst.setString(2, this.getEmail());
			pst.setString(3, this.getPassword());
			pst.setInt(4, this.getPerson_group());
			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();

			if (rs.next()) {
				this.setId(rs.getInt(1));
			}

		} else {
			// update to ddb

			PreparedStatement pst = conn
					.prepareStatement("update users set username=?, email=?, password=?, person_group=? where id=?");
			pst.setString(1, this.getUsername());
			pst.setString(2, this.getEmail());
			pst.setString(3, this.getPassword());
			pst.setInt(4, this.getPerson_group());
			pst.setInt(5, this.getId());
			pst.executeUpdate();
		}
		return this;

	}

	/**
	 * @param conn
	 * @param id - id of the user to be deleted
	 * @throws SQLException
	 */
	public void deleteFromDb(Connection conn) throws SQLException {

		if (this.id != 0) {
			PreparedStatement prep = conn.prepareStatement("delete from users where id=?");
			prep.setInt(1, id);
			prep.executeUpdate();
			this.id=0;
		}

	}

}
