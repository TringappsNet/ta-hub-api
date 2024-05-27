package tahub.sdapitahub.repository.query;

public enum TaskQuery {
    FIND_ALL("SELECT * FROM ta_tasks"),
    FIND_BY_ID("SELECT * FROM ta_tasks WHERE task_id = ?"),
    FIND_BY_JOB_ID("SELECT * FROM ta_tasks WHERE job_id = ?"),
    SAVE("INSERT INTO ta_tasks (job_id, job_title, role_type, mode_of_work, work_location, years_of_experience_required, primary_skill_set, secondary_skill_set, client_budget, assigned_budget, primary_assignee, secondary_assignee, task_status, backlogs, approval_status, description, created_at, last_updated) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"),
    DELETE_BY_ID("DELETE FROM ta_tasks WHERE task_id = ?"),

    TASK_VIEW_ALL("SELECT * FROM ta_task_view");

    private final String query;

    TaskQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}

