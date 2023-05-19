package com.service.role;

import com.model.Role;
import com.repository.jwt.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return null;
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role findByName(String name) {
        return null;
    }

    @Override
    public Boolean existById(Long id) {
        return roleRepository.existsById(id);
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void delete(Role role) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
