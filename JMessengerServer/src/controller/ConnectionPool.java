package controller;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {
	
	private ArrayList<Connection> pool;
	private static final int poolsize = 15;
	private String username;
	private String password;
	private int port;
	
	
	public ConnectionPool(String uname, String pword, int dbport) {
		username = uname;
		password = pword;
		port = dbport;
		
		pool = new ArrayList<Connection>();
		
		for (int i=0; i < poolsize; i++) {
			pool.add(createConnection());
		}
	}
	
	private Connection createConnection() {
		
		Connection con = null;
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:"+port+"/Palantir", this.username, this.password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		
		return con;
	}
	
	public Connection getConnection() {
		if(this.pool.isEmpty()) {
			this.pool.add(createConnection());
		}
		Connection con = this.pool.remove(0);
        
        return con;
	}
	
	public void returnConnection(Connection con) {
		
		if (this.pool.size() < poolsize) {
			this.pool.add(con);
		}
	}

}
