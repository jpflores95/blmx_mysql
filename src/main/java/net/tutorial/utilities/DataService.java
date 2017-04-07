package net.tutorial.utilities;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataService {
	
	public static final int INSERT_RECORD = 1;
	public static final int UPDATE_RECORD = 2;
	
	DBService dbService = null;
	private Connection dbConnection = null;
	private PreparedStatement ps = null;
	
	public DataService() {
		this.dbService = DBService.getInstance();
	}
	
	public ArrayList<Map<String, Object>> allRecords() {

		this.dbConnection = this.dbService.getConnection();

		ArrayList<Map<String, Object>> records = new ArrayList<Map<String, Object>>();
		Map<String, Object> record = null;
		String sSQL = "SELECT _id, name, email, mobile " + "FROM `contacts`";

		ResultSet rs = null;

		try {
			this.ps = this.dbConnection.prepareStatement(sSQL);
			rs = this.ps.executeQuery(sSQL);

			while (rs.next()) {
				record = new HashMap<String, Object>();
				record.put("_id", rs.getInt("_id"));
				record.put("name", rs.getString("name"));
				record.put("email", rs.getString("email"));
				record.put("mobile", rs.getString("mobile"));
				records.add(record);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			this.dbService.cleanUp();
		}

		return records;
	}
	
	public void deleteRecord(int id) {
		this.dbConnection = this.dbService.getConnection();

		try {
			String sSQL = "DELETE FROM `contacts` WHERE _id=?";
			this.ps = this.dbConnection.prepareStatement(sSQL);
			this.ps.setInt(1, id);
			this.ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			this.dbService.cleanUp();
		}
	}
	
	public Map<String, Object> findRecord(int id) { 
		this.dbConnection = this.dbService.getConnection();
		
		Map<String, Object> record = new HashMap<String, Object>();
		ResultSet rs = null;
		
		try {
			String sSQL = "SELECT * FROM `contacts` WHERE _id=?";
			this.ps = this.dbConnection.prepareStatement(sSQL);
			this.ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				record.put("_id", rs.getInt("_id"));
				record.put("name", rs.getString("name"));
				record.put("email", rs.getString("email"));
				record.put("mobile", rs.getString("mobile"));
			}

			rs.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			this.dbService.cleanUp();
		}
		return record;
	}
	
	public void updateRecord(int transaction, Map<String, Object> record) {
		this.dbConnection = this.dbService.getConnection();

		String sSQL = null;

		if (transaction == UPDATE_RECORD) {
			sSQL = "UPDATE `contacts` " + "SET name = ? , email = ? , mobile = ? " + "WHERE _id = ?";
		} else {
			sSQL = "INSERT INTO `contacts`" + "(`name`, `email`, `mobile`) VALUES" + "(?,?,?)";
		}

		try {
			this.ps = dbConnection.prepareStatement(sSQL);
			this.ps.setString(1, (String) record.get("name"));
			this.ps.setString(2, (String) record.get("email"));
			this.ps.setString(3, (String) record.get("mobile"));
			if (transaction == UPDATE_RECORD) {
				this.ps.setInt(4, (int) record.get("_id"));
			}
			this.ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			this.dbService.cleanUp();
		}
	}
	
}
