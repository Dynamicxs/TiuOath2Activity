import React, { useEffect, useState } from "react";
import {useNavigate} from "react-router-dom";

function Profile() {
    const [user, setUser] = useState(null);
    const [isEditing, setIsEditing] = useState(false);
    const [formData, setFormData] = useState({ displayName: "", bio: "" });
    const navigate = useNavigate();

    useEffect(() => {
        fetch("http://localhost:8080/api/users/user", {
            method: "GET",
            credentials: "include", // ✅ required for cookies/session
        })
            .then((res) => {
                if (!res.ok) throw new Error("Unauthorized");
                return res.json();
            })
            .then((data) => setUser(data))
            .catch((err) => {
                console.error("Profile fetch error:", err);
                navigate("/login");
            });
    }, [navigate]);

    const handleEditToggle = () => setIsEditing(!isEditing);

    const handleChange = (e) =>
        setFormData({ ...formData, [e.target.name]: e.target.value });

    const handleSave = (e) => {
        e.preventDefault();
        fetch("http://localhost:8080/api/users", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            credentials: "include",
            body: JSON.stringify({
                ...user,
                displayName: formData.displayName,
                bio: formData.bio,
            }),
        })
            .then((res) => res.json())
            .then((data) => {
                setUser(data);
                setIsEditing(false);
            })
            .catch((err) => console.error("Update failed:", err));
    };

    const handleLogout = () => {
        window.location.href = "http://localhost:8080/logout";
    };

    if (!user) return <div className="text-center mt-5">Loading profile...</div>;

    return (
        <div className="container mt-5">
            <div className="card shadow-lg p-4" style={{ maxWidth: "600px", margin: "0 auto" }}>
                <h2 className="text-center mb-4">👤 User Profile</h2>
                <div className="text-center mb-3">
                    <img
                        src={user.picture || "https://via.placeholder.com/100"}
                        alt="Profile"
                        className="rounded-circle"
                        width="100"
                        height="100"
                    />
                </div>

                {!isEditing ? (
                    <>
                        <h4 className="text-center">{user.displayName || "No display name"}</h4>
                        <p className="text-center text-muted">{user.email || "No email"}</p>
                        <p className="text-center">{user.bio || "No bio provided"}</p>

                        <div className="d-flex justify-content-center gap-2 mt-3">
                            <button className="btn btn-primary" onClick={handleEditToggle}>
                                ✏️ Edit Profile
                            </button>
                            <button className="btn btn-danger" onClick={handleLogout}>
                                🚪 Logout
                            </button>
                        </div>
                    </>
                ) : (
                    <form onSubmit={handleSave}>
                        <div className="form-group mb-3">
                            <label>Display Name</label>
                            <input
                                type="text"
                                className="form-control"
                                name="displayName"
                                value={formData.displayName}
                                onChange={handleChange}
                            />
                        </div>
                        <div className="form-group mb-3">
                            <label>Bio</label>
                            <textarea
                                className="form-control"
                                name="bio"
                                rows="3"
                                value={formData.bio}
                                onChange={handleChange}
                            />
                        </div>

                        <div className="d-flex justify-content-center gap-2">
                            <button type="submit" className="btn btn-success">
                                💾 Save
                            </button>
                            <button type="button" className="btn btn-secondary" onClick={handleEditToggle}>
                                ❌ Cancel
                            </button>
                        </div>
                    </form>
                )}
            </div>
        </div>
    );
}

export default Profile;
