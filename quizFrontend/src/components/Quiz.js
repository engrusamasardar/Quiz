
import { useEffect, useState } from 'react' //hooks (built in functions)
// import Login from './components/Login';

function Quiz() {

  useEffect(() => {

    //Change "localhost:8080" if your backend running on different ip and port
    fetch("http://localhost:8080/quiz/getquestions", {
      method: "get"
    })
      .then((response) => response.json())
      .then((response) => {

        //for-each loop to iterate through "response" and store values in "question" variable
        response.forEach((element, index) => {
          question[index] = element;
        });

        //Initialized "setCurrentQuestionIndex" and "setTotalQuestions"
        setCurrentQuestionIndex(question[0].questionId);
        setTotalQuestions(question.length);
      })
  }, []);

  //"question" is initialized via useState
  const [question, setQuestion] = useState([]);
  const [currentQuestionIndex, setCurrentQuestionIndex] = useState();
  const [showScore, setShowScore] = useState(false);
  const [score, setScore] = useState(0);
  const [totalQuestions, setTotalQuestions] = useState();

  const resetQuestion = () => {
    console.log("Answer===>resetQuestion:");
    setCurrentQuestionIndex(question[0].questionId);
    setShowScore(false);
    setScore(0);
  }
  const handleAnswerOptionClick = (userAnwer) => {
    console.log("Answer===>isCorrect:", userAnwer.isCorrect);
    console.log("Answer===>answerText:", userAnwer.answerText);
    if (userAnwer.isCorrect === true) {
      console.log("Answer===>score:", score);
      setScore(score + 1);
    }

    const countQuestion = (currentQuestionIndex + 1);
    if (countQuestion <= totalQuestions) {
      const nextQuestion = (currentQuestionIndex + 1);
      console.log("Answer===>nextQuestion:", nextQuestion);
      console.log("Answer===>question.length:", totalQuestions);
      console.log("Answer===>setCurrentQuestionIndex(nextQuestion):", nextQuestion);
      setCurrentQuestionIndex(nextQuestion);
    } else {
      setShowScore(true);
      console.log("Answer===>setShowScore:true");
    }
  };



  return (
    <div className='app'>
      {
        showScore
          ?

          (
            <div className='score-section'>
              Thank you for your Quiz <br />You scored {score} out of {question.length}
              <br />

              <button onClick={resetQuestion}>Start Again</button>
            </div>
          )
          : (
            <>
              {question.title}
              <div className='question-section'>
                <div className='question-count'>Question#({(currentQuestionIndex)}/{totalQuestions})</div>
                <div className='question-text'>{question[(currentQuestionIndex - 1)]?.title} </div>

              </div>
              <br />
              <div className='answer-section'>
                {
                  question[(currentQuestionIndex - 1)]?.option.map(item => {
                    return (<button onClick={() => handleAnswerOptionClick(item)}>{item.answerText}</button>)
                  }

                  )}
              </div>
            </>
          )
      }

    </div>
  );
}

export default Quiz;
