package tahub.sdapitahub.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tahub.sdapitahub.constants.AuthMessages;
import tahub.sdapitahub.constants.BoardContMsgs;
import tahub.sdapitahub.constants.CandidateMsgs;
import tahub.sdapitahub.entity.Board;
import tahub.sdapitahub.dto.BoardDTO;
import tahub.sdapitahub.entity.Candidate;
import tahub.sdapitahub.service.BoardService;

import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "Board", description = "Operations related to Board")
@RequestMapping("/api/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/columns")
    public ResponseEntity<List<Board>> getAllBoards() {
        List<Board> boards = boardService.getAllBoards();
        return ResponseEntity.ok(boards);
    }

    @GetMapping("/column/{id}")
    public ResponseEntity<Board> getBoardById(@PathVariable Long id) {
        Optional<Board> board = boardService.getBoardById(id);
        return board.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/column")
    public ResponseEntity<String> createBoard(@RequestBody BoardDTO boardDTO) {
        Board createdBoard = boardService.createBoard(boardDTO);
        if (createdBoard != null) {
            return ResponseEntity.status(200).body(BoardContMsgs.BOARD_CREATED.getMessage());
        } else {
            return ResponseEntity.status(400).body(BoardContMsgs.BOARD_NOT_CREATED.getMessage());
        }
    }

    @PutMapping("/column/{id}")
    public ResponseEntity<String> updateBoard(@PathVariable Long id, @RequestBody BoardDTO boardDTO) {
        Board updatedBoard = boardService.updateBoard(id, boardDTO);
        if (updatedBoard != null) {
            return ResponseEntity.status(HttpStatus.OK).body(BoardContMsgs.BOARD_UPDATED.getMessage());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BoardContMsgs.BOARD_NOT_UPDATED.getMessage());
        }

    }

    @DeleteMapping("/column/{id}")
    public ResponseEntity<String> deleteBoard(@PathVariable Long id) {
            boardService.deleteBoard(id);
            return ResponseEntity.status(HttpStatus.OK).body(BoardContMsgs.BOARD_DELETED.getMessage());
    }
}
