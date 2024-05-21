package tahub.sdapitahub.repository.query;

public enum ClientQuery {
    FIND_ALL("SELECT * FROM ta_client_details"),
    FIND_BY_ID("SELECT * FROM ta_client_details WHERE client_id = ?"),
    SAVE("INSERT INTO ta_client_details (client_name, client_spoc_name, client_spoc_contact, client_location, created_at, last_updated) " +
            "VALUES (?, ?, ?, ?, ?, ?)"),
    DELETE_BY_ID("DELETE FROM ta_client_details WHERE client_id = ?");

    private final String query;

    ClientQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}

