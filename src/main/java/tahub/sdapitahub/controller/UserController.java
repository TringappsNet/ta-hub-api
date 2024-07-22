package tahub.sdapitahub.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tahub.sdapitahub.constants.TaskMsgs;
import tahub.sdapitahub.constants.UserMsgs;
import tahub.sdapitahub.dto.User.UserDTO;
import tahub.sdapitahub.entity.TaUser;
import tahub.sdapitahub.service.UserService;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "User", description = "Operations related to User")
@RequestMapping("/api/users")

public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<TaUser>> getAllUsers() {
        List<TaUser> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
        TaUser user = userService.getUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(UserMsgs.USER_NOT_FOUND.getMessage());
        }
    }

    @PostMapping("/user")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDTO userCreateDTO) {
        try {
            TaUser createdUser = userService.createUser(userCreateDTO);
            return new ResponseEntity<>(UserMsgs.USER_CREATED.getMessage(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(UserMsgs.ERROR_USER_CREATE.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/user/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") Long id, @RequestBody UserDTO userCreateDTO) {
        try {
            TaUser updatedUser = userService.updateUser(id, userCreateDTO);
            if (updatedUser != null) {
                return new ResponseEntity<>(UserMsgs.USER_UPDATED.getMessage(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(UserMsgs.USER_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(UserMsgs.ERROR_USER_UPDATE.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        Optional<TaUser> userOptional = Optional.ofNullable(userService.getUserById(id));
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(UserMsgs.USER_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);

        } else {
            userService.deleteUser(id);
            return new ResponseEntity<>(UserMsgs.USER_DELETED.getMessage(),HttpStatus.OK);
        }
    }
}
