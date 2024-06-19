package tahub.sdapitahub.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tahub.sdapitahub.dto.Role.RoleDTO;
import tahub.sdapitahub.dto.Role.RoleGetDTO;
import tahub.sdapitahub.entity.Role;
import tahub.sdapitahub.service.RoleService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
@Tag(name = "Role", description = "Operations related to Role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/")
    public ResponseEntity<List<RoleGetDTO>> getAllRoles() {
        List<RoleGetDTO> roles = roleService.getAllRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/role/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable("id") Long id) {
        Optional<Role> roleOptional = roleService.getRoleById(id);
        if (roleOptional.isPresent()) {
            return new ResponseEntity<>(roleOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/role")
    public ResponseEntity<Role> createRole(@RequestBody RoleDTO roleCreateDTO) {
        Role createdRole = roleService.createRole(roleCreateDTO);
        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }

    @PutMapping("/role/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable("id") Long id, @RequestBody RoleDTO roleCreateDTO) {
        Role updatedRole = roleService.updateRole(id, roleCreateDTO);
        if (updatedRole != null) {
            return new ResponseEntity<>(updatedRole, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/role/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable("id") Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.status(HttpStatus.OK).body(RoleMessages.ROLE_DELETED.getMessage());
    }
}
