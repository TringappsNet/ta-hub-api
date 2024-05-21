package tahub.sdapitahub.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tahub.sdapitahub.entity.Board;
import tahub.sdapitahub.repository.mapper.BoardMapper;
import tahub.sdapitahub.repository.query.BoardQuery;

import java.util.List;
import java.util.Optional;

@Repository
public class BoardRepository {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BoardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Board> findAll() {
        return jdbcTemplate.query(BoardQuery.FIND_ALL.getQuery(), new BoardMapper());
    }

    public Optional<Board> findById(Long id) {
        return jdbcTemplate.query(BoardQuery.FIND_BY_ID.getQuery(), new Object[]{id}, new BoardMapper())
                .stream().findFirst();
    }

    public Board save(Board board) {
        jdbcTemplate.update(BoardQuery.SAVE.getQuery(), board.getColumn());
        return board;
    }

    public void deleteById(Long id) {
        jdbcTemplate.update(BoardQuery.DELETE_BY_ID.getQuery(), id);
    }

    public Board update(Board board) {
        String query = "UPDATE ta_board SET columns = ? WHERE id = ?";
        jdbcTemplate.update(query, board.getColumn(), board.getId());
        return board;
    }
}
