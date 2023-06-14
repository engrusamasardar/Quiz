package app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @implNote Entity class for store answers in database
 * @author Usama
 *
 */
@Entity
@Table(name = "attempquiz")
public class AttemtsQuiz {
	
	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "uId")
	private int uId;
	
	@Column(name = "qId")
	private int qId;
	
	@Column(name = "realAnswer")
	private String answer;
	
	@Column(name = "userAnswer")
	private String userAnswer;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getuId() {
		return uId;
	}

	public void setuId(int uId) {
		this.uId = uId;
	}

	public int getqId() {
		return qId;
	}

	public void setqId(int qId) {
		this.qId = qId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}

	@Override
	public String toString() {
		return "AttemtsQuiz [id=" + id + ", uId=" + uId + ", qId=" + qId + ", answer=" + answer + ", userAnswer="
				+ userAnswer + "]";
	}
}
