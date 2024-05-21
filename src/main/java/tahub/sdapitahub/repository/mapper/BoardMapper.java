package tahub.sdapitahub.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import tahub.sdapitahub.entity.Board;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardMapper implements RowMapper<Board> {
    @Override
    public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Board.Builder()
                .id(rs.getLong("id"))
                .column(rs.getString("columns"))
                .build();
    }
}
