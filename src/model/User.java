package model;

/**
 * User
 * @author fxiangyi@gmail.com
 * @version 2.0
 */

public class User {
	
	private String name;
	private IO io;
	/**
	 * constructor
	 * @param name name of user
	 */
	public User(String name) {
		super();
		this.name = name;
		io = new TXTIO();
		io.newUserLexicon(name);
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
