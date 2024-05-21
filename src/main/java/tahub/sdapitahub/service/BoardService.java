package tahub.sdapitahub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tahub.sdapitahub.entity.Board;
import tahub.sdapitahub.repository.BoardRepository;
import tahub.sdapitahub.dto.BoardDTO;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    public Optional<Board> getBoardById(Long id) {
        return boardRepository.findById(id);
    }

    public Board createBoard(BoardDTO boardDTO) {
        Board board = new Board.Builder()
                .column(boardDTO.getColumn())
                .build();
        return boardRepository.save(board);
    }

    public Board updateBoard(Long id, BoardDTO boardDTO) {
        Board board = new Board.Builder()
                .id(id)
                .column(boardDTO.getColumn())
                .build();
        return boardRepository.update(board);
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }
}
