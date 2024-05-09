package tahub.sdapitahub.repository.query;

public enum User {
    FIND_ALL("SELECT * FROM users"),
    FIND_BY_ID("SELECT * FROM users WHERE user_id = ?"),
    SAVE("INSERT INTO users (first_name, last_name, username, email, phone, reset_token, password_hash, is_active, current_session_id, last_login_time, created_at) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"),
    UPDATE("UPDATE users SET first_name = ?, last_name = ?, username = ?, email = ?, phone = ?, reset_token = ?, " +
            "password_hash = ?, is_active = ?, current_session_id = ?, last_login_time = ?, created_at = ? WHERE user_id = ?"),
    DELETE_BY_ID("DELETE FROM users WHERE user_id = ?"),
    FIND_BY_EMAIL("SELECT * FROM users WHERE email = ?"),
    FIND_BY_RESET_TOKEN("SELECT * FROM users WHERE reset_token = ?");

    private final String query;

    User(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
