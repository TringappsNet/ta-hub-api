package tahub.sdapitahub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tahub.sdapitahub.dto.RoleDTO;
import tahub.sdapitahub.entity.Role;
import tahub.sdapitahub.repository.RoleRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<RoleDTO> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
                .map(role -> new RoleDTO(role.getRoleId(), role.getRole()))
                .collect(Collectors.toList());
    }

    public Optional<Role> getRoleById(Long id) {
        return Optional.ofNullable(roleRepository.findById(id));
    }
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public Role updateRole(Long id, Role role) {
        role.setRoleId(id);
        return roleRepository.update(role);
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
