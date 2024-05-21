package tahub.sdapitahub.repository.query;

public enum TaskCandidateQuery {
    FIND_ALL("SELECT * FROM ta_task_candidates"),
    FIND_BY_ID("SELECT * FROM ta_task_candidates WHERE task_candidates_id = ?"),
    FIND_BY_TASK_ID("SELECT * FROM ta_task_candidates WHERE task_id = ?"),
    SAVE("INSERT INTO ta_task_candidates (task_id, candidate_id, task_candidate_status, task_candidate_comments, created_at, last_updated) " +
            "VALUES (?, ?, ?, ?, ?, ?)"),
    DELETE_BY_ID("DELETE FROM ta_task_candidates WHERE task_candidates_id = ?");

    private final String query;

    TaskCandidateQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
