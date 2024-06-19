package tahub.sdapitahub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tahub.sdapitahub.dto.Role.RoleDTO;
import tahub.sdapitahub.dto.Role.RoleGetDTO;
import tahub.sdapitahub.entity.Role;
import tahub.sdapitahub.repository.RoleRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<RoleGetDTO> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
                .map(role -> new RoleGetDTO(role.getRoleId(), role.getRole()))
                .collect(Collectors.toList());
    }

    public Optional<Role> getRoleById(Long id) {
        Role role = roleRepository.findById(id);
        return Optional.ofNullable(role);
    }

    public Role createRole(RoleDTO roleCreateDTO) {
        Role role = new Role.Builder()
                .role(roleCreateDTO.getRole())
                .build();
        return roleRepository.save(role);
    }

    public Role updateRole(Long id, RoleDTO roleCreateDTO) {
        Optional<Role> roleOptional = getRoleById(id);
        if (roleOptional.isPresent()) {
            Role role = roleOptional.get();
            role.setRole(roleCreateDTO.getRole());
            return roleRepository.update(role);
        } else {
            return null; // Or throw an exception, depending on your requirements
        }
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
