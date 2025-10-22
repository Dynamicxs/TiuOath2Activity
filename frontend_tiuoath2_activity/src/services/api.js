export async function fetchUser() {
    try {
        const response = await fetch("http://localhost:8080/api/users/current", {
            method: "GET",
            credentials: "include",
        });

        if (!response.ok) {
            throw new Error("Not authenticated");
        }

        return await response.json();
    } catch (err) {
        console.error("Error fetching user:", err);
        throw err;
    }
}
