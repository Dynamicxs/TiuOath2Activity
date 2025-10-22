import React from "react";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import LoginPage from "./pages/Loginpage";
import ProfilePage from "./pages/Profilepage";
import HomePage from "./pages/Homepage";
import "bootstrap/dist/css/bootstrap.min.css";

function App() {

    return (
        <Router>
            <div className="container mt-5">
                <Routes>
                    <Route path="/" element={<HomePage />} />
                    <Route path="/login" element={<LoginPage />} />
                    <Route path="/profile" element={<ProfilePage />} />
                    <Route path="*" element={<Navigate to="/" />} />
                </Routes>
            </div>
        </Router>
    );
}

export default App;
