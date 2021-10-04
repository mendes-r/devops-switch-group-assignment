/*package switchtwentytwenty.project.autentication;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SetupDataLoaderTest {

    @Autowired
    SetupDataLoader setupDataLoader;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @MockBean
    ApplicationContext applicationContext;

    @Test
    @DisplayName("Create roles and admin")
    void createRolesAndAdmin() {
        //arrange - act
        setupDataLoader.onApplicationEvent(new ContextRefreshedEvent(applicationContext));
        Optional<RoleJPA> adminRole = roleRepository.findByName(ERole.ROLE_ADMIN);
        Optional<RoleJPA> userRole = roleRepository.findByName(ERole.ROLE_USER);
        boolean adminRolePresent = adminRole.isPresent();
        boolean userRolePresent = userRole.isPresent();

        Optional<UserJPA> user = userRepository.findByUsername("admin");
        boolean userPresent = user.isPresent();

        if (userPresent) {
            String username = user.get().getUsername();
            String email = user.get().getEmail();
            Set<RoleJPA> roles = user.get().getRoles();
            Set<RoleJPA> expectedRoles = new HashSet<>();
            Optional<RoleJPA> expectedRole = roleRepository.findByName(ERole.ROLE_ADMIN);
            if (!expectedRole.isPresent()) {
                fail();
            }
            expectedRoles.add(expectedRole.get());

            assertEquals("admin", username);
            assertEquals("admin@gmail.com", email);
            assertEquals(expectedRoles, roles);
        }

        //assert
        assertTrue(userPresent);
        assertTrue(adminRolePresent);
        assertTrue(userRolePresent);
    }
}
*/