package controller;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {
	
	private ArrayList<Connection> pool;
	private static final int poolsize = 15;
	
	public ConnectionPool() {
		pool = new ArrayList<Connection>();
		
		for (int i=0; i < poolsize; i++) {
			pool.add(createConnection());
		}
	}
	
	private Connection createConnection() {
		
		Connection con = null;
		
		try {
			con = DriverManager.getConnection("jdbc:mysql:///Palantir", "Sauron", "1ring2r00lthem@ll");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
	
	protected Connection getConnection() {
		if(this.pool.isEmpty()) {
			this.pool.add(createConnection());
		}
		Connection con = this.pool.remove(0);
        
        return con;
	}
	
	protected void returnConnection(Connection con) {
		
		if (this.pool.size() < poolsize) {
			this.pool.add(con);
		}
	}

}