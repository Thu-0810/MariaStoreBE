package com.example.artwebsitebe.config;


import com.example.artwebsitebe.entity.Role;
import com.example.artwebsitebe.repository.role.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final RoleRepository roleRepository;

    @PostConstruct
    public void initRoles() {

        if (roleRepository.findByName("USER").isEmpty()) {
            roleRepository.save(new Role(null, "USER", "Default user", null));
        }

        if (roleRepository.findByName("ADMIN").isEmpty()) {
            roleRepository.save(new Role(null, "ADMIN", "Administrator", null));
        }

        if (roleRepository.findByName("SELLER").isEmpty()) {
            roleRepository.save(new Role(null, "SELLER", "Seller", null));
        }
    }
}