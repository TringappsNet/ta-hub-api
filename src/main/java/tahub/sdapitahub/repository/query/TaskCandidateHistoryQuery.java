package tahub.sdapitahub.repository.query;


public enum TaskCandidateHistoryQuery {
    FIND_ALL("SELECT * FROM ta_task_candidates_status_history"),
    FIND_BY_ID("SELECT * FROM ta_task_candidates_status_history WHERE status_id = ?"),
    FIND_COMMENTS("SELECT * FROM ta_task_candidates_status_history WHERE task_id = ? AND candidate_id = ?"),
    SAVE("INSERT INTO ta_task_candidates_status_history (task_id, candidate_id, task_candidate_status, task_candidate_comments, task_status, modified_by, created_at, last_updated) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)"),
    DELETE_BY_ID("DELETE FROM ta_task_candidates_status_history WHERE status_id = ?");

    private final String query;

    TaskCandidateHistoryQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
