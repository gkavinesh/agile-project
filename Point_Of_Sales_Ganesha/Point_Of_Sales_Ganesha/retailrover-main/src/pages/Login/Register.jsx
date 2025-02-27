import React, { useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";
import { FaUser, FaEnvelope, FaLock } from "react-icons/fa"; // Importing icons from react-icons
import "./Register.scss"; // Make sure to update the styles in Register.scss

function Register() {
    const [employeename, setEmployeename] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    async function save(event) {
        event.preventDefault();
        try {
            await axios.post("http://localhost:8080/api/v1/employee/save", {
                employeename: employeename,
                email: email,
                password: password,
            });
            alert("Employee Registration Successfully");
        } catch (err) {
            alert(err);
        }
    }

    return (
        <div className="register-container">
            <div className="register-form">
                <h2>POS by Maven Tek</h2>
                <p>Sign up to start your journey with us!</p>
                <form onSubmit={save}>
                    <div className="form-group">
                        <FaUser className="input-icon" />
                        <input
                            type="text"
                            className="form-control"
                            placeholder="Employee Name"
                            value={employeename}
                            onChange={(event) => setEmployeename(event.target.value)}
                        />
                    </div>

                    <div className="form-group">
                        <FaEnvelope className="input-icon" />
                        <input
                            type="email"
                            className="form-control"
                            placeholder="Email"
                            value={email}
                            onChange={(event) => setEmail(event.target.value)}
                        />
                    </div>

                    <div className="form-group">
                        <FaLock className="input-icon" />
                        <input
                            type="password"
                            className="form-control"
                            placeholder="PIN"
                            value={password}
                            onChange={(event) => setPassword(event.target.value)}
                        />
                    </div>

                    <button type="submit" className="btn-register">
                        Sign Up
                    </button>
                </form>

                <div className="login-link">
                    <p>Already have an account? <Link to="/">Login</Link></p>
                </div>
            </div>
        </div>
    );
}

export default Register;

