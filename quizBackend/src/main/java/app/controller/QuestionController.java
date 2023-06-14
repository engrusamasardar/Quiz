package app.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.model.Question;
import app.repository.QuestionRepository;

/**
 * @implNote Controller Class to manage Questions http operations
 * @author usardar
 *
 */
@RestController
@RequestMapping("/question")
@CrossOrigin
public class QuestionController {

	@Autowired
	private JSONObject json;

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private Question question;

	/*
	 * Usage: Example: http://localhost:8080/question/retrievequestions 
	 * Output: [ {"qId":3, "title":"Who is the founder of Dawn news?", "optA":"Quaid-E-Azam", "optB":"Usama", "optC":"Ali Hassan", "answer":"Quaid-E-Azam"} ]
	 */
	@GetMapping("/retrievequestions")
	public List<Question> getQuestionsList() {
		List<Question> questionsList = questionRepository.findAll();
		if (questionsList != null) {
			return questionsList;
		}
		return questionsList;
	}

	/*
	 * Usage: 
	 * Example: http://localhost:8080/question/addquestion
	 * Post Request : {"title":"Who is the founder of Pakistan?", "optA":"Quaid-E-Azam", "optB":"Usama", "optC":"Ali Hassan", "answer":"Quaid-E-Azam"}
	 * Output: [ {"success", "Question Inserted Successfuly"} ]
	 */
	@PostMapping("/addquestion")
	public String addQuestion(@RequestBody String newQuestionObj) {
		System.out.println(newQuestionObj);
//		json = new JSONObject(newQuestionObj);
		question.setQuestion(json.getString("newQuestion"));
		question.setOptA(json.getString("newOptA"));
		question.setOptB(json.getString("newOptB"));
		question.setOptC(json.getString("newOptC"));
		question.setAnswer(json.getString("newAnswer"));
		questionRepository.save(question);
		json.clear();
		json.put("success", "Question Inserted Successfuly");
		return json.toString();
	}

}