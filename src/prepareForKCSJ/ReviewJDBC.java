package prepareForKCSJ;

import java.sql.*;

public class ReviewJDBC {

	private Statement stmt = null;
	private Connection conn = null;
	public ReviewJDBC() {
		this.init();
	}
	public void init() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager
					.getConnection("jdbc:mysql://localhost/mynews?user=root&password=root");
			stmt = conn.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} 
	}
	
	public void executeRequestString() {
		try {
			ResultSet rs = this.stmt.executeQuery("select * from test");
			while (rs.next()) {
				System.out.format("%s ", rs.getString("id"));
				System.out.format("%s ", rs.getString("content"));
				System.out.format("%s \n", rs.getString("age"));
			}
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ReviewJDBC instance = new ReviewJDBC();
		instance.executeRequestString();

	}

}
