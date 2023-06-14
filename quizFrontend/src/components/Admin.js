import { useEffect, useState } from "react";

function Admin() {

    var [question, setQuestion] = useState("");
    var [optionA, setOptionA] = useState("");
    var [optionB, setOptionB] = useState("");
    var [optionC, setOptionC] = useState("");
    var [realAnswer, setRealAnswer] = useState("");
    var [message, setMessage] = useState("");
    var [isAlertVisible, setIsAlertVisible] = useState(false);
    var [questionsList, setQuestionsList] = useState([]);

    const updateQuestion = (event) => {
        setQuestion(event.target.value);
    }

    const updateOptionA = (event) => {
        setOptionA(event.target.value);
    }

    const updateOptionB = (event) => {
        setOptionB(event.target.value);
    }

    const updateOptionC = (event) => {
        setOptionC(event.target.value);
    }

    const updateRealAnswer = (event) => {
        setRealAnswer(event.target.value);
    }

    useEffect(() => {
        retreiveQuestionsListFunction();
    }, []);

    var insertNewQuestion = (event) => {
        event.preventDefault();
        fetch("http://localhost:8080/question/addquestion", {
            method: "post",
            body: JSON.stringify({
                question,
                optionA,
                optionB,
                optionC,
                realAnswer
            })
        })
            .then(response => response.json())
            .then(response => {
                setMessage(response);
                flushFields();
                setTimeout(() => {
                    flushMessageRelatedVariables();
                }, 3000);
                retreiveQuestionsListFunction();
            });
    }

    var deleteQuestion = (qId) => {
        fetch("http://localhost:8080/question/removequestion", {
            method: "post",
            body: JSON.stringify({
                qId
            })
        })
        .then(response => response.json())
        .then(response => {
            setMessage(response);
            setTimeout(() => {
                flushMessageRelatedVariables();
            }, 3000);
            retreiveQuestionsListFunction();
        });
    }

    var retreiveQuestionsListFunction = () => {
        fetch("http://localhost:8080/question/retrievequestions", {
            method: "get"
        }).then(response => response.json()).then(response => {
            setQuestionsList(response);
        })
    }

    var flushFields = () => {
        setQuestion("");
        setOptionA("");
        setOptionB("");
        setOptionC("");
        setRealAnswer("");
    }

    var flushMessageRelatedVariables = () => {
        setIsAlertVisible(false);
        setMessage("");
    }

    return (
        <div className="questionForm">
            <form className="grid_form" onSubmit={insertNewQuestion} onReset={flushFields}>
                <label>
                    Question:
                </label>
                <input type="text" value={question} onChange={updateQuestion} required minLength={3} />
                <label>
                    Option A:
                </label>
                <input type="text" value={optionA} onChange={updateOptionA} required minLength={3} />
                <label>
                    Option B:
                </label>
                <input type="text" value={optionB} onChange={updateOptionB} required minLength={3} />
                <label>
                    Option C:
                </label>
                <input type="text" value={optionC} onChange={updateOptionC} required minLength={3} />
                <label>
                    Real Answer:
                </label>
                <input type="text" value={realAnswer} onChange={updateRealAnswer} required minLength={3} />
                <input type="reset" />
                <input type="submit" />
            </form>
            <div hidden={isAlertVisible}>
                <h4>{message.msg}</h4>
            </div>
            <div className="questionsDiv">
                <table>
                    <thead>
                        <tr>
                            <th>S/N</th>
                            <th>Question</th>
                            <th>Option A</th>
                            <th>Option B</th>
                            <th>Option C</th>
                            <th>Real Answer</th>
                            <th>Delete Question</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            questionsList.map((item, index) => (
                                <tr key={item.qid}>
                                    <td>{(index + 1)}</td>
                                    <td>{item.question}</td>
                                    <td>{item.optA}</td>
                                    <td>{item.optB}</td>
                                    <td>{item.optC}</td>
                                    <td>{item.answer}</td>
                                    <td><button onClick={()=>{
                                        deleteQuestion(item.qid);
                                    }}>Delete</button></td>
                                </tr>
                            ))
                        }
                    </tbody>
                </table>
            </div>
        </div>
    );
}

export default Admin;