package tahub.sdapitahub.repository.query;

public enum UserQuery {
    FIND_ALL("SELECT * FROM ta_users"),
    FIND_BY_ID("SELECT * FROM ta_users WHERE user_id = ?"),
    SAVE("INSERT INTO ta_users (role_id, first_name, last_name, username, email, phone, reset_token, password_hash, is_active, g_access_token, g_refresh_token, current_session_id, last_login_time, created_at, last_updated) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"),
    DELETE_BY_ID("DELETE FROM ta_users WHERE user_id = ?"),
    FIND_BY_EMAIL("SELECT * FROM ta_users WHERE email = ?"),
    FIND_BY_RESET_TOKEN("SELECT * FROM ta_users WHERE reset_token = ?");

    private final String query;

    UserQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
