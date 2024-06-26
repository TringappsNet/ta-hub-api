package tahub.sdapitahub.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tahub.sdapitahub.dto.User.UserDTO;
import tahub.sdapitahub.entity.TaUser;
import tahub.sdapitahub.service.UserService;
import jakarta.validation.Valid;

import java.util.List;

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
    public ResponseEntity<TaUser> getUserById(@PathVariable("id") Long id) {
        TaUser user = userService.getUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/user")
    public ResponseEntity<TaUser> createUser(@Valid @RequestBody UserDTO userCreateDTO) {
        TaUser createdUser = userService.createUser(userCreateDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<TaUser> updateUser(@PathVariable("id") Long id, @RequestBody UserDTO userCreateDTO) {
        TaUser updatedUser = userService.updateUser(id, userCreateDTO);
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
