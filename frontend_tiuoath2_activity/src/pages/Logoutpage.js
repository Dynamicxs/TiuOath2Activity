import React from "react";

function LogoutButton() {
    const handleLogout = async () => {
        try {
            await fetch("http://localhost:8080/logout", {
                method: "POST",
                credentials: "include",
                headers: { "Content-Type": "application/x-www-form-urlencoded" }
            });
            // redirect to frontend home
            window.location.href = "/";
        } catch (err) {
            console.error(err);
        }
    };

    return <button className="btn btn-danger" onClick={handleLogout}>Logout</button>;
}

export default LogoutButton;
