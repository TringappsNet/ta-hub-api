package tahub.sdapitahub.repository.query;

public enum JobRequirementQuery {
    FIND_ALL("SELECT * FROM ta_client_requirements"),
    FIND_BY_ID("SELECT * FROM ta_client_requirements WHERE job_id = ?"),
    SAVE("INSERT INTO ta_client_requirements (requirement_start_date, client_name, client_spoc_name, client_spoc_contact, account_manager, account_manager_email, total_no_of_openings, salary_budget, mode_of_interviews, tentative_start_date, tentative_duration, approved_by, years_of_experience_required, primary_skill_set, secondary_skill_set, created_at, last_updated) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"),
    FIND_BY_CLIENT_NAME("SELECT * FROM ta_client_requirements WHERE client_name = ?");
    private final String query;

    JobRequirementQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
