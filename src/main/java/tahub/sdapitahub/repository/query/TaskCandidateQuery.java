package tahub.sdapitahub.repository.query;

public enum TaskCandidateQuery {
    FIND_ALL("SELECT * FROM task_candidates"),
    FIND_BY_ID("SELECT * FROM task_candidates WHERE task_candidates_id = ?"),
    SAVE("INSERT INTO task_candidates (task_id, candidate_id, task_candidate_status, task_candidate_comments, created_at, last_updated) " +
            "VALUES (?, ?, ?, ?, ?, ?)"),
    DELETE_BY_ID("DELETE FROM task_candidates WHERE task_candidates_id = ?");

    private final String query;

    TaskCandidateQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
