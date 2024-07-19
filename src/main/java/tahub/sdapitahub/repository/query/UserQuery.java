package tahub.sdapitahub.repository.query;

public enum UserQuery {
    FIND_ALL("SELECT * FROM ta_users"),
    FIND_BY_ID("SELECT * FROM ta_users WHERE user_id = ?"),
    FIND_BY_INVITE_TOKEN("SELECT * FROM ta_users WHERE invite_token = ?"),
    SAVE("INSERT INTO ta_users (role_id, first_name, last_name, username, email, phone, reset_token, password_hash, is_active, invite_token, g_access_token, g_access_token_created_at, g_token_expires_in, g_id_token, current_session_id, last_login_time, created_at, last_updated) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"),
    CREATE("INSERT INTO ta_users (role_id, email, invite_token) " +
            "VALUES (?, ?, ?)"),
    DELETE_BY_ID("DELETE FROM ta_users WHERE user_id = ?"),
    FIND_BY_EMAIL("SELECT * FROM ta_users WHERE email = ?"),
    FIND_BY_ACCESS_TOKEN("SELECT * FROM ta_users WHERE g_access_token = ?"),
    FIND_BY_RESET_TOKEN("SELECT * FROM ta_users WHERE reset_token = ?"),
    UPDATE_GOOGLE_ACCESS_TOKEN("INSERT INTO ta_users (gAccessToken , userId) " +
            "VALUES ( ? ,?)"),
    UPDATE_EMAIL("INSERT INTO ta_users (email  , userId) " +
            "VALUES (? , ?)");

    private final String query;

    UserQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
