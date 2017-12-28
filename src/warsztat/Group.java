package warsztat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

public class Group {
	
	private int id=0;
	private String name;
	
	public Group(String name) {
		super();
		setName(name);	
	}
	
	public Group() {}
	
	

	public String getName() {
		return name;
	}

	public Group setName(String name) {
		this.name = name;
		return this;
	}
	
	public int getId() {
		return id;
	}
	
	private Group setId(int id) {
		this.id = id;
		return this;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getName());
		return sb.toString();
	}
	
	
	
	public Group saveToDb(Connection conn ) throws SQLException{
		
		
		if(this.getId()==0){
			
			String[] generatedCoulmns= {"id"};
			
			PreparedStatement prep = conn.prepareStatement("insert into user_group (name) value (?)", generatedCoulmns);
			prep.setString(1, this.getName());
			prep.executeUpdate();
			
			ResultSet rs = prep.getGeneratedKeys();
			
			if(rs.next()){
				this.setId(rs.getInt(1));
			}
			
		}else {
			PreparedStatement prep = conn.prepareStatement("update user_group set name=? where id=?");
			prep.setString(1, this.getName());
			prep.setInt(2, this.getId());
			
			prep.executeUpdate();
		}
		return this;
		
	}
	
	
	static public Group[] loadAll (Connection conn) throws SQLException{
		
		ArrayList<Group> groupList = new ArrayList<>();
		
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery("select * from user_group");
		
		while(res.next()){
			
			Group tmpGroup = new Group();
			tmpGroup.setName(res.getString("name"));
			tmpGroup.setId(res.getInt("id"));
			
			groupList.add(tmpGroup);
		}
		
		Group[] groupArr = new Group[groupList.size()];
		groupList.toArray(groupArr);
		return groupArr;
		
	}
	
	static public Group loadById(Connection conn, int id) throws SQLException{
		
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery("select * from user_group where id="+id);
		
		Group loaded = new Group();
		
		while(res.next()){
			
			loaded.setId(res.getInt("id"));
			loaded.setName(res.getString("name"));
			
		}
		return loaded;
		
	}
	
	public void deleteFromDb(Connection conn, int id) throws SQLException{
		
		Group loaded = loadById(conn, id);
		
		if(loaded.id!=0){
			PreparedStatement prep = conn.prepareStatement("delete from user_group where id=?");
			prep.setInt(1, id);
			prep.executeUpdate();
			this.id=0;
		}
		
	}
	

}
