import { useState } from "react";

// put some id in question and use id as index

function offlineApp() {
  const question = [
    { questionId: 1, title: 'Pakistan First Prime Minister?', option: [{ answerText: 'Nawaz Sharif', isCorrect: false }, { answerText: 'Liaqat Ali Khan', isCorrect: true }, { answerText: 'Bilawal Bhutto', isCorrect: false }] },
    { questionId: 2, title: 'Germany best Car Manufacturer ?', option: [{ answerText: 'Audi', isCorrect: false }, { answerText: 'BMW', isCorrect: true }, { answerText: 'Tesla', isCorrect: false }] },
    { questionId: 3, title: 'PS5 Lanuch on ?', option: [{ answerText: '1993', isCorrect: false }, { answerText: '2019', isCorrect: true }, { answerText: '2010', isCorrect: false }] },
    { questionId: 4, title: 'World best Airline?', option: [{ answerText: 'Turkish', isCorrect: true }, { answerText: 'Pegasus', isCorrect: false }, { answerText: 'Emirates', isCorrect: false }] },
    { questionId: 5, title: 'Sweden Best University ?', option: [{ answerText: 'Stockholm University', isCorrect: true }, { answerText: 'Lulea University of Technology', isCorrect: false }, { answerText: 'Uppsala University', isCorrect: false }] },
  ]

  const [currentQuestionIndex, setCurrentQuestionIndex] = useState(question[0].questionId);
  const [showScore, setShowScore] = useState(false);
  const [score, setScore] = useState(0);
  const totalQuestions = question[4].questionId;

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
              <div className='question-section'>
                <div className='question-count'>Question#({(currentQuestionIndex)}/{totalQuestions})</div>
                <div className='question-text'>{question[(currentQuestionIndex - 1)].title} </div>
              </div>
              <br />
              <div className='answer-section'>
                {
                  question[(currentQuestionIndex - 1)].option.map(item => {
                    return (<button onClick={() => handleAnswerOptionClick(item)}>{item.answerText}</button>)
                  }

                  )}
              </div>
            </>
          )}

    </div>
  );
}

export default offlineApp;
