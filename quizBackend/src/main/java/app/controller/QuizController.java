package app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.model.Question;
import app.model.UserSession;
import app.repository.QuestionRepository;
import app.repository.QuizRepository;

/**
 * @implNote Controller Class to manage Quiz http operations
 * @author usardar
 *
 */
@RestController
@RequestMapping("/quiz")
@CrossOrigin
public class QuizController {

	@Autowired
	private JSONObject json;

	@Autowired
	private QuizRepository quizRepository;

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private Question question;

	private UserSession userSession = null;

	private int questionNo = 1;
	private int countCorrect = 0;
	private int countFalse = 0;

	
	/*
	 * Usage: Example: http://localhost:8080/quiz/getquestions 
	 * Output: [ { questionId: 3, title: 'Who is the founder of Dawn news?', option: [{ answerText: 'Nawaz Sharif', isCorrect: false }, { answerText: 'Quaid-E-Azam', isCorrect: true }, { answerText: 'Bilawal Bhutto', isCorrect: false }] } ]
	 */
	@GetMapping("/getquestions")
	public List<Object> getQuestion(HttpServletResponse response) throws IOException {
		List<Question> questionsList = questionRepository.findAll();
		if(questionsList != null) {
			JSONArray outerJsonArray = new JSONArray();
			for (Question question : questionsList) {
				JSONObject innerJson = new JSONObject();
				innerJson.put("questionId", question.getQid());
				innerJson.put("title", question.getQuestion());
				JSONArray innerJsonArray = new JSONArray();
				JSONObject ans1 = new JSONObject();
				JSONObject ans2 = new JSONObject();
				JSONObject ans3 = new JSONObject();
				ans1.put("answerText", question.getOptA());
				if (question.getOptA().equals(question.getAnswer())) {
					ans1.put("isCorrect", true);
				} else {
					ans1.put("isCorrect", false);
				}
				innerJsonArray.put(ans1);
				ans2.put("answerText", question.getOptB());
				if (question.getOptB().equals(question.getAnswer())) {
					ans2.put("isCorrect", true);
				} else {
					ans2.put("isCorrect", false);
				}
				innerJsonArray.put(ans2);
				ans3.put("answerText", question.getOptC());
				if (question.getOptC().equals(question.getAnswer())) {
					ans3.put("isCorrect", true);
				} else {
					ans3.put("isCorrect", false);
				}
				innerJsonArray.put(ans3);
				innerJson.put("option", innerJsonArray);
				outerJsonArray.put(innerJson);
			}
			return outerJsonArray.toList();
		}
		return (new ArrayList<Object>());
	}

//	@PostMapping("/updatequestion")
//	public void updateInformation(@RequestBody String questionAnswer) {
//		userSession = UserSession.getInstance();
//		json = new JSONObject(questionAnswer);
//		String userAnswer = json.getString("userAnswer");
//		quizRepository.insertInToAttempQuiz(userSession.id, questionNo, userAnswer, question.getAnswer());
//		if (userAnswer.equals(question.getAnswer())) {
//			++countCorrect;
//		} else {
//			++countFalse;
//		}
//		++questionNo;
//		json.clear();
//	}
//
//	@PostMapping("/quizresult")
//	public String getResults() {
//		json = new JSONObject();
//		json.put("correct", countCorrect);
//		json.put("false", countFalse);
//		return json.toString();
//	}

}
