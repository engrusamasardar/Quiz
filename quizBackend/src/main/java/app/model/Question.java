package app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;


/**
 * @implNote Entity Class of Question
 * @author Usama
 *
 */
@Component
@Entity(name = "questions")
public class Question {

	@Id
	private int qid;
	
	@Column(name = "question")
	private String question;
	
	@Column(name = "optA")
	private String optA;
	
	@Column(name = "optB")
	private String optB;
	
	@Column(name = "optC")
	private String optC;
	
	@Column(name = "answer")
	private String answer;

	public int getQid() {
		return qid;
	}

	public void setQid(int qid) {
		this.qid = qid;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getOptA() {
		return optA;
	}

	public void setOptA(String optA) {
		this.optA = optA;
	}

	public String getOptB() {
		return optB;
	}

	public void setOptB(String optB) {
		this.optB = optB;
	}

	public String getOptC() {
		return optC;
	}

	public void setOptC(String optC) {
		this.optC = optC;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "Question [qid=" + qid + ", question=" + question + ", optA=" + optA + ", optB=" + optB + ", optC="
				+ optC + ", answer=" + answer + "]";
	}
}
