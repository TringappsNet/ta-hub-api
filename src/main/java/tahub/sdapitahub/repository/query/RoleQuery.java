package tahub.sdapitahub.repository.query;

    public enum RoleQuery {
        FIND_ALL("SELECT * FROM roles"),
        FIND_BY_ID("SELECT * FROM roles WHERE role_id = ?"),
        SAVE("INSERT INTO roles (role) " +
                "VALUES (?)"),
        DELETE_BY_ID("DELETE FROM roles WHERE role_id = ?");

        private final String query;

        RoleQuery(String query) {
            this.query = query;
        }

        public String getQuery() {
            return query;
        }
    }





