import { useState } from "react";
import Quiz from "./Quiz";
import Admin from "./Admin";

function Login() {

    const [userName, setUserName] = useState("");
    const [userPassword, setUserPassword] = useState("");
    const [authencatedUserNamePassword, setAuthencatedUserNamePassword] = useState(false);
    var [showQuiz, setShowQuiz] = useState(false);
    var [showAdmin, setShowAdmin] = useState(false);
    var [showButtons, setShowButtons] = useState(false);

    const updateUserName = (event) => {
        setUserName(event.target.value);
    }

    const updatePassword = (event) => {
        setUserPassword(event.target.value);
    }

    const authenticateUserName = (event) => {
        event.preventDefault();
        fetch("http://localhost:8080/profile/authenticate", {
            method: "post",
            body: JSON.stringify({
                userName,
                userPassword
            })
        })
            .then(response => response.json())
            .then(response => {
                setShowButtons(response);
                setAuthencatedUserNamePassword(response);
            });
        flushFields();
    }

    var flushFields = () => {
        setUserName("");
        setUserPassword("");
    }

    return (
        <div>
            <div hidden={authencatedUserNamePassword === true}>
                <form className="grid_form" onSubmit={authenticateUserName} onReset={flushFields}>
                    <label>
                        User Name:
                    </label>
                    <input className="grid_form.input" type="text" onChange={updateUserName} value={userName} minLength={3} required />
                    <label>
                        Password:
                    </label>
                    <input className="grid_form.input" type="password" onChange={updatePassword} value={userPassword} minLength={3} required />
                    <input type="reset" />
                    <input type="submit" />
                </form>
            </div>
            <div hidden={!showButtons}>
                <button value={showQuiz} onClick={() => {
                    setShowQuiz(true);
                    setAuthencatedUserNamePassword(true);
                    setShowButtons(false);
                }}>Click To Start Quiz</button>
                <br />
                <button value={showAdmin} onClick={() => {
                    setShowAdmin(true);
                    setAuthencatedUserNamePassword(true);
                    setShowButtons(false);
                }}>Click To Open Admin Page</button>
            </div>
            <div hidden={!showQuiz}>
                <Quiz />
            </div>
            <div hidden={!showAdmin}>
                <Admin />
            </div>
        </div>
    );
}

export default Login;