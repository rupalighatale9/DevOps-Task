package com.usermanagement.config;

import com.usermanagement.entity.Role;
import com.usermanagement.entity.User;
import com.usermanagement.repository.RoleRepository;
import com.usermanagement.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        seedRoles();
        seedUsers();
    }

    private void seedRoles() {
        if (roleRepository.count() == 0) {
            System.out.println("Seeding default roles...");
            
            Role adminRole = new Role("ROLE_ADMIN");
            Role managerRole = new Role("ROLE_MANAGER");
            Role userRole = new Role("ROLE_USER");

            roleRepository.save(adminRole);
            roleRepository.save(managerRole);
            roleRepository.save(userRole);

            System.out.println("Default roles seeded successfully");
        } else {
            System.out.println("Roles already exist, skipping seeding");
        }
    }

    private void seedUsers() {
        if (userRepository.count() == 0) {
            System.out.println("Seeding sample users...");

            Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                    .orElseThrow(() -> new RuntimeException("ROLE_ADMIN not found"));
            Role managerRole = roleRepository.findByName("ROLE_MANAGER")
                    .orElseThrow(() -> new RuntimeException("ROLE_MANAGER not found"));
            Role userRole = roleRepository.findByName("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("ROLE_USER not found"));

            // Admin User
            User admin = new User();
            admin.setName("Admin User");
            admin.setEmail("admin@usermanagement.com");
            admin.setPassword(passwordEncoder.encode("Admin@123"));
            admin.setEnabled(true);
            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(adminRole);
            admin.setRoles(adminRoles);
            userRepository.save(admin);

            // Manager User
            User manager = new User();
            manager.setName("Manager User");
            manager.setEmail("manager@usermanagement.com");
            manager.setPassword(passwordEncoder.encode("Manager@123"));
            manager.setEnabled(true);
            Set<Role> managerRoles = new HashSet<>();
            managerRoles.add(managerRole);
            managerRoles.add(userRole);
            manager.setRoles(managerRoles);
            userRepository.save(manager);

            // Normal User
            User normalUser = new User();
            normalUser.setName("John Doe");
            normalUser.setEmail("john@usermanagement.com");
            normalUser.setPassword(passwordEncoder.encode("User@123"));
            normalUser.setEnabled(true);
            Set<Role> userRoles = new HashSet<>();
            userRoles.add(userRole);
            normalUser.setRoles(userRoles);
            userRepository.save(normalUser);

            System.out.println("Sample users seeded successfully");
            System.out.println("Admin credentials: admin@usermanagement.com / Admin@123");
            System.out.println("Manager credentials: manager@usermanagement.com / Manager@123");
            System.out.println("User credentials: john@usermanagement.com / User@123");
        } else {
            System.out.println("Users already exist, skipping seeding");
        }
    }
}
