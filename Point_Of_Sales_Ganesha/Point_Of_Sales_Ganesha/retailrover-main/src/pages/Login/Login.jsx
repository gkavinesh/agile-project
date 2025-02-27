import { useState } from "react";
import { Link, useNavigate } from 'react-router-dom'; // Import Link
import axios from "axios";
import { FaEnvelope, FaLock, FaSignInAlt } from 'react-icons/fa'; // Importing icons from react-icons
import "./Login.scss";

function Login() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    async function login(event) {
        event.preventDefault();
        try {
            const response = await axios.post("http://localhost:8080/api/v1/employee/login", {
                email: email,
                password: password,
            });
    
            const data = response.data;
            console.log(data);
    
            if (data.message === "Email not exists") {
                alert("Email does not exist");
            } else if (data.message === "Login Success") {
                await updateLoginTime(email); // Call updateLoginTime after successful login
                navigate('/home');
            } else {
                alert("Incorrect email or password");
            }
        } catch (err) {
            console.error(err);
            alert("An error occurred while logging in");
        }
    }

    async function updateLoginTime(email) {
        try {
            await axios.patch("http://localhost:8080/api/v1/employee/time", {
                email: email // Pass the user's email to identify the user
            });
            console.log("Login time updated successfully");
        } catch (err) {
            console.error("Error updating login time:", err);
        }
    }

    return (
        <div className="login-container">
            <div className="login-form">
                <h2>POS by Maven Tek</h2>
                <p>Quick sign-in with your pin</p>
                <form onSubmit={login}>
                    <div className="form-group">
                        <FaEnvelope className="input-icon" /> {/* Email Icon */}
                        <input
                            type="email"
                            className="form-control"
                            placeholder="Email"
                            value={email}
                            onChange={(event) => setEmail(event.target.value)}
                        />
                    </div>

                    <div className="form-group">
                        <FaLock className="input-icon" /> {/* Password Icon */}
                        <input
                            type="password"
                            className="form-control"
                            placeholder="PIN"
                            value={password}
                            onChange={(event) => setPassword(event.target.value)}
                        />
                    </div>

                    <button type="submit" className="btn-login">
                        <FaSignInAlt className="button-icon" /> Login {/* Login Icon */}
                    </button>
                </form>

                <div className="signup-link">
                    <p>Don't have an account? <Link to="/register">Sign Up</Link></p>
                </div>
            </div>
        </div>
    );
}

export default Login;



