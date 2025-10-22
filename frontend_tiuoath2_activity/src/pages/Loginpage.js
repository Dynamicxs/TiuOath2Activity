import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

export default function Loginpage() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();
    const BACKEND = "http://localhost:8080";

    const handleLogin = async (e) => {
        e.preventDefault();

        try {
            // Adjust the endpoint to your backend login endpoint
            const response = await axios.post("http://localhost:8080/api/auth/login", {
                email,
                password,
            });

            localStorage.setItem("token", response.data.token);
            navigate("/profile");
        } catch (err) {
            alert("Login failed. Please check credentials.");
        }
    };

    const handleOAuthLogin = (provider) => {
        // navigate browser to Spring Security OAuth2 authorization endpoint
        window.location.href = `${BACKEND}/oauth2/authorization/${provider}`;
    };

    return (
        <div className="card p-4 mx-auto" style={{ maxWidth: "400px" }}>
            <h3 className="text-center mb-3">Login</h3>
            <form onSubmit={handleLogin}>
                <div className="mb-3">
                    <label>Email</label>
                    <input
                        type="email"
                        className="form-control"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                </div>

                <div className="mb-3">
                    <label>Password</label>
                    <input
                        type="password"
                        className="form-control"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>

                <button type="submit" className="btn btn-primary w-100">
                    Login
                </button>
            </form>

            <div className="d-flex justify-content-center align-items-center" style={{height: "40vh"}}>
                <div className="card p-4" style={{width: 360}}>
                    <h4 className="text-center mb-3">Sign in</h4>

                    <button
                        className="btn btn-outline-primary w-100 mb-2"
                        onClick={() => handleOAuthLogin("google")}
                    >
                        <i className="bi bi-google me-2" /> Sign in with Google
                    </button>

                    <button
                        className="btn btn-outline-dark w-100 mb-2"
                        onClick={() => handleOAuthLogin("github")}
                    >
                        <i className="bi bi-github me-2" /> Sign in with GitHub
                    </button>

                    <hr />
                    <p className="text-muted small text-center">
                        The backend handles OAuth2 — you will be redirected to the provider.
                    </p>
                </div>
            </div>
        </div>

    );
}
