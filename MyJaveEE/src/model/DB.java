package model;

import java.sql.DriverManager;

public class DB {
	private static DB instance;
	
	public static void getInstance() {
		if(instance == null) {
			System.out.println("instance");
			instance = new DB();
		}
	}
	
	public DB () {
		System.out.println("DB");
	}
	
	public void doConnect() {
		
	}
}
