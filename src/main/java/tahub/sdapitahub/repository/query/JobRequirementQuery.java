package tahub.sdapitahub.repository.query;

public enum JobRequirementQuery {
    FIND_ALL("SELECT * FROM ta_client_requirements"),
    FIND_BY_ID("SELECT * FROM ta_client_requirements WHERE job_id = ?"),
    FIND_BY_APPROVAL_TOKEN("SELECT * FROM ta_client_requirements WHERE approval_token = ?"),
    FIND_BY_APPROVED_BY("SELECT * FROM ta_client_requirements WHERE approved_by = ?"),
    SAVE("INSERT INTO ta_client_requirements (requirement_start_date,client_id, client_name, client_spoc_name, client_spoc_contact, account_manager, account_manager_email, total_no_of_openings, salary_budget, mode_of_interviews, tentative_start_date, tentative_duration, approved_by, approval_status, approval_token, created_at, last_updated) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"),
    FIND_BY_CLIENT_NAME("SELECT * FROM ta_client_requirements WHERE client_name = ?"),
    DELETE_BY_ID("DELETE FROM ta_client_requirements WHERE job_id = ?");

    private final String query;

    JobRequirementQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
