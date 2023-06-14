package app.model;

/**
 * @implNote Store the "ID" of user to insert in database
 * @author Usama
 *
 */
public class UserSession {
	
	private static UserSession userSession = null;
	
	private UserSession () {
	}
	
	public static UserSession getInstance() {
		if(userSession == null) {
			return userSession = new UserSession();
		}
		return userSession;
	}
	
	public int id;

}
