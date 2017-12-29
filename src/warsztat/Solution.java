package warsztat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Solution {
	
	private int id;
	private String created;
	private String update;
	private String description;
	private int excercise_id;
	private int users_id;
	
	
	public Solution() {	}


	public Solution(String created, String update, String description, int excercise_id, int users_id) {
		
		setCreated(created);
		setUpdate(update);
		setDescription(description);
		setExcercise_id(excercise_id);
		setUsers_id(users_id);
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCreated() {
		return created;
	}


	public void setCreated(String created) {
		this.created = created;
	}


	public String getUpdate() {
		return update;
	}


	public void setUpdate(String update) {
		this.update = update;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getExcercise_id() {
		return excercise_id;
	}


	public void setExcercise_id(int excercise_id) {
		this.excercise_id = excercise_id;
	}


	public int getUsers_id() {
		return users_id;
	}


	public void setUsers_id(int users_id) {
		this.users_id = users_id;
	}
	
	 @Override
	public String toString() {
		 StringBuilder sb = new StringBuilder();
		 sb.append(this.getUpdate()).append(" ").append(this.getDescription());
		 return sb.toString();
	}
	 
	 
	static public Solution[] loadAll (Connection conn) throws SQLException{
			
			ArrayList<Solution> solutList = new ArrayList<>();
			
			Statement st = conn.createStatement();
			ResultSet res = st.executeQuery("select * from solution order by updated asc");
			
			while(res.next()){
				
				Solution tmpSolution = new Solution();
				tmpSolution.setCreated(res.getString("created"));
				tmpSolution.setUpdate(res.getString("updated"));
				tmpSolution.setDescription(res.getString("description")); 
				tmpSolution.setExcercise_id(res.getInt("excercise_id"));
				tmpSolution.setUsers_id(res.getInt("users_id"));
				tmpSolution.setId(res.getInt("id"));
				
				solutList.add(tmpSolution);
			}
			
			Solution[] solutArr = new Solution[solutList.size()];
			solutList.toArray(solutArr);
			return solutArr;
			
		}
	
	static public Solution[] loadAllByUserId (Connection conn, int user_id) throws SQLException {
		
		ArrayList<Solution> solutsList = new ArrayList<>();
		
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery("select * from solution where users_id="+user_id);
		
		while(res.next()){
			
			Solution tmpSolut = new Solution();
			
			tmpSolut.setCreated(res.getString("created"));
			tmpSolut.setUpdate(res.getString("updated"));
			tmpSolut.setDescription(res.getString("description"));
			tmpSolut.setExcercise_id(res.getInt("excercise_id"));
			tmpSolut.setUsers_id(res.getInt("users_id"));
			tmpSolut.setId(res.getInt("id"));
			
			solutsList.add(tmpSolut);
			
		}
		Solution[] solutsByUser = new Solution [solutsList.size()];
		solutsList.toArray(solutsByUser);
		return solutsByUser;
		
	}
		
		static public Solution loadById(Connection conn, int id) throws SQLException{
			
			Statement st = conn.createStatement();
			ResultSet res = st.executeQuery("select * from solution where id="+id);
			
			Solution loaded = new Solution();
			
			while(res.next()){
				
				loaded.setCreated(res.getString("created"));
				loaded.setUpdate(res.getString("updated"));
				loaded.setDescription(res.getString("description"));
				loaded.setExcercise_id(res.getInt("excercise_id"));
				loaded.setUsers_id(res.getInt("users_id"));
				loaded.setId(res.getInt("id"));
				
			}
			return loaded;
			
		}
		
		static public Solution[] loadAllbyExcerciseId(Connection conn, int ex_id) throws SQLException{
			
			ArrayList<Solution> nextSolut = new ArrayList<>();
			
			Statement st = conn.createStatement();
			ResultSet res = st.executeQuery("select * from solution join excercise on solution.excercise_id=excercise.id where excercise.id="+ex_id+ "order by created asc;");
			
			while(res.next()){
				
				Solution tmpSolut = new Solution();
				
				tmpSolut.setCreated(res.getString("created"));
				tmpSolut.setUpdate(res.getString("updated"));
				tmpSolut.setDescription(res.getString("description"));
				tmpSolut.setExcercise_id(res.getInt("excercise_id"));
				tmpSolut.setUsers_id(res.getInt("users_id"));
				tmpSolut.setId(res.getInt("id"));
				
				nextSolut.add(tmpSolut);
			}
			
			Solution[] solutsArr = new Solution [nextSolut.size()];
			nextSolut.toArray(solutsArr);
			return solutsArr;
			
			
		}
		
		public void deleteFromDb(Connection conn, int id) throws SQLException{
			
			Solution loaded = loadById(conn, id);
			
			if(loaded.id!=0){
				PreparedStatement prep = conn.prepareStatement("delete from solution where id=?");
				prep.setInt(1, id);
				prep.executeUpdate();
				this.id=0;
			}
			
		}
		public Solution saveToDb(Connection conn ) throws SQLException{
			
			if(this.getId()==0){
				
				String[] generatedCoulmns= {"id"};
				
				PreparedStatement prep = conn.prepareStatement("insert into solution (created, updated, description, excercise_id, users_id) values (?, ?, ?, ?, ?)", generatedCoulmns);
				prep.setString(1, this.getCreated());
				prep.setString(2, this.getUpdate());
				prep.setString(3, this.getDescription());
				prep.setInt(4, this.getExcercise_id());
				prep.setInt(5, this.getUsers_id());
				prep.executeUpdate();
				
				ResultSet rs = prep.getGeneratedKeys();
				
				if(rs.next()){
					this.setId(rs.getInt(1));
				}
				
			}else {
				PreparedStatement prep = conn.prepareStatement("update solution set created=? updated=? excercise_id=? users_id=? where id=?");
				prep.setString(1, this.getCreated());
				prep.setString(2, this.getUpdate());
				prep.setString(3, this.getDescription());
				prep.setInt(4, this.getExcercise_id());
				prep.setInt(5, this.getUsers_id());
				prep.setInt(6, this.getId());
				
				prep.executeUpdate();
			}
			return this;
			
		}
	
	
}
