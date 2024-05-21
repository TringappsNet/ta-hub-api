package tahub.sdapitahub.repository.query;

public enum BoardQuery {
    FIND_ALL("SELECT * FROM ta_board"),
    FIND_BY_ID("SELECT * FROM ta_board WHERE id = ?"),
    SAVE("INSERT INTO ta_board (columns) VALUES (?)"),
    DELETE_BY_ID("DELETE FROM ta_board WHERE id = ?");

    private final String query;

    BoardQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
