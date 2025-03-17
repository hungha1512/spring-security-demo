package vn.aptech.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import vn.aptech.security.user.Role;
import vn.aptech.security.user.RoleRepository;
import vn.aptech.security.user.User;
import vn.aptech.security.user.UserRepository;

import java.util.Set;

@SpringBootTest
public class CreateUserTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    public void createUser() {
        Role role = new Role();
        role.setName("ROLE_USER");
        role.setDescription("Role for user");

        roleRepository.save(role);

        User user = new User();
        user.setName("User01");
        user.setPassword(passwordEncoder.encode("123abc@A"));
        user.setUsername("user01");
        user.setRoles(Set.of(role));
        userRepository.save(user);
    }

}
