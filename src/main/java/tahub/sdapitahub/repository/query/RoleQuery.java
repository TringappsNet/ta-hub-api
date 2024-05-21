package tahub.sdapitahub.repository.query;

    public enum RoleQuery {
        FIND_ALL("SELECT * FROM ta_roles"),
        FIND_BY_ID("SELECT * FROM ta_roles WHERE role_id = ?"),
        SAVE("INSERT INTO ta_roles (role) " +
                "VALUES (?)"),
        DELETE_BY_ID("DELETE FROM ta_roles WHERE role_id = ?");

        private final String query;

        RoleQuery(String query) {
            this.query = query;
        }

        public String getQuery() {
            return query;
        }
    }





