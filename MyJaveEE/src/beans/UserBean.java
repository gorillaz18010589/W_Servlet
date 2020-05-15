package beans;

public class UserBean {
	private String name;
	private String account;
	private String password;
	private String hash;
	private int active;
	
	public UserBean() {
		
	}
	
	@Override
	public String toString() {
		return "name:" + name +",account=" +account +",passwod:" + password +",hash:" + hash +",active:" + active;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

}
