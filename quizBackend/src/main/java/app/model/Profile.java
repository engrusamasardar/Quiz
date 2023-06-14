package app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

/**
 * @implNote Entity class for User
 * @author Usama
 *
 */
@Component
@Entity(name = "user")
public class Profile {

	@Id
	@Column(name = "uId")
	private int uId;

	@Column(name = "uName")
	private String uName;

	@Column(name = "uPassword")
	private String uPassword;

	public int getuId() {
		return uId;
	}

	public void setuId(int uId) {
		this.uId = uId;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getuPassword() {
		return uPassword;
	}

	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}

	@Override
	public String toString() {
		return "User [uId=" + uId + ", uName=" + uName + ", uPassword=" + uPassword + "]";
	}
}
