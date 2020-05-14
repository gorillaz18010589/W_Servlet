package beans;

public class User {
	private String name;
	private String account;
	private String password;
	private String hash;
	private int active;
	
	
	
	public User() {
		
	}
	
	public User(String name, String account, String password, String hash, int active) {
		this.name = name;
		this.account = account;
		this.password = password;
		this.hash = hash;
		this.active = active;
	}
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}

	
	
}
