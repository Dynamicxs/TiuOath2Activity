import React from "react";
import { Link } from "react-router-dom";

export default function Homepage() {
    return (
        <div className="text-center">
            <h1>Welcome to my Page!</h1>
            <p>Simple demo for React + Spring Boot OAuth2</p>
            <Link to="/login" className="btn btn-primary m-2">
                Go to Login
            </Link>
            <Link to="/profile" className="btn btn-success m-2">
                View Profile
            </Link>
        </div>
    );
}
