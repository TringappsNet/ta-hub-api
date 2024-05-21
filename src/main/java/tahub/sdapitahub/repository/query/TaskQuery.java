package tahub.sdapitahub.repository.query;

public enum TaskQuery {
    FIND_ALL("SELECT * FROM ta_tasks"),
    FIND_BY_ID("SELECT * FROM ta_tasks WHERE task_id = ?"),
    SAVE("INSERT INTO ta_tasks (job_id, job_title, role_type, mode_of_work, work_location, client_budget, assigned_budget, primary_assignee, secondary_assignee, task_status, backlogs, description, created_at, last_updated) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"),
    DELETE_BY_ID("DELETE FROM ta_tasks WHERE task_id = ?");

    private final String query;

    TaskQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}

