package tahub.sdapitahub.repository.query;

public enum CandidateQuery {
    FIND_ALL("SELECT * FROM candidate_details"),
    FIND_BY_ID("SELECT * FROM candidate_details WHERE candidate_id = ?"),
    SAVE("INSERT INTO candidate_details (candidate_name, candidate_email, candidate_contact, technology, total_experience, " +
            "current_ctc, expected_ctc, notice_period, mode_of_work, current_location, candidate_status, comments, " +
            "remarks, recruiter, recruited_source, created_date, last_updated) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"),
    DELETE_BY_ID("DELETE FROM candidate_details WHERE candidate_id = ?");

    private final String query;

    CandidateQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
