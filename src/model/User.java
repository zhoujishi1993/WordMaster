package model;

/**
 * User
 * @author fxiangyi@gmail.com
 * @version 2.0
 */

public class User {
	
	private String name;

	/**
	 * constructor
	 * @param name name of user
	 */
	public User(String name) {
		super();
		this.name = name;
	}
	
	/**
	 * get name
	 * @return name of user
	 */
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "User [name=" + name + "]";
	}

}
