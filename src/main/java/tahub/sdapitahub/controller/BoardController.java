package tahub.sdapitahub.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tahub.sdapitahub.constants.BoardContMsgs;
import tahub.sdapitahub.entity.Board;
import tahub.sdapitahub.dto.Board.BoardDTO;
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
            return ResponseEntity.status(HttpStatus.OK).body("Board created successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create Board");
        }    }

    @PutMapping("/column/{id}")
    public ResponseEntity<String> updateBoard(@PathVariable Long id, @RequestBody BoardDTO boardDTO) {
        Optional<Board> existingBoardOptional = boardService.getBoardById(id);
        if (existingBoardOptional.isPresent()) {
            Board existingBoard = existingBoardOptional.get();
            Board updatedBoard = boardService.updateBoard(id, boardDTO);
            if (updatedBoard != null) {
                return ResponseEntity.ok("Board updated successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update board.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Board not found.");
        }
    }

    @DeleteMapping("/column/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.noContent().build();
    }
}
