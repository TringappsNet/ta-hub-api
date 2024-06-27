package tahub.sdapitahub.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tahub.sdapitahub.constants.ClientMsgs;
import tahub.sdapitahub.constants.RoleMsgs;
import tahub.sdapitahub.dto.Role.RoleDTO;
import tahub.sdapitahub.dto.Role.RoleGetDTO;
import tahub.sdapitahub.entity.Client;
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
    public ResponseEntity<String> createRole(@RequestBody RoleDTO roleCreateDTO) {
            Role createdRole = roleService.createRole(roleCreateDTO);
            if(createdRole!=null){
            return new ResponseEntity<String>(RoleMsgs.ROLE_CREATED.getMessage(), HttpStatus.CREATED);
        }else {
            return new ResponseEntity<String>(RoleMsgs.ERROR_CREATE.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/role/{id}")
    public ResponseEntity<String> updateRole(@PathVariable("id") Long id, @RequestBody RoleDTO roleCreateDTO) {
       try{
            Role updatedRole = roleService.updateRole(id, roleCreateDTO);
            if (updatedRole != null) {
                return new ResponseEntity<String>(RoleMsgs.ROLE_UPDATED.getMessage(), HttpStatus.OK);
            }
       } catch (EmptyResultDataAccessException ex) {
           return new ResponseEntity<>(RoleMsgs.ROLE_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
       }
        return new ResponseEntity<>(RoleMsgs.ERROR_UPDATE.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);


    }
    @DeleteMapping("/role/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable("id") Long id) {
    try {
        Optional<Role> existingRole = roleService.getRoleById(id);
        if (existingRole.isPresent()) {
            roleService.deleteRole(id);
            return new ResponseEntity<String>(RoleMsgs.ROLE_DELETED.getMessage(), HttpStatus.OK);
        }
    } catch (EmptyResultDataAccessException ex) {
        return new ResponseEntity<>(RoleMsgs.ROLE_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
    }
        return new ResponseEntity<>(RoleMsgs.ERROR_DELETE.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
