package switchtwentytwenty.project.autentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Component
public class SetupDataLoader implements //Script, quando corres a app ele cria o admin e o user
        ApplicationListener<ContextRefreshedEvent> {

    private static final String ADMIN = "admin";
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        RoleJPA admin = new RoleJPA(ERole.ROLE_ADMIN);
        RoleJPA user = new RoleJPA(ERole.ROLE_USER);
        if(!roleRepository.findByName(ERole.ROLE_ADMIN).isPresent()){
            roleRepository.save(admin);
        }
        if(!roleRepository.findByName(ERole.ROLE_USER).isPresent()){
            roleRepository.save(user);
        }
        Set<RoleJPA> roles = new HashSet<>();
        roles.add(admin);
        UserJPA userJPA = new UserJPA();
        userJPA.setUsername(ADMIN);
        userJPA.setEmail("admin@gmail.com");
        userJPA.setPassword(passwordEncoder.encode(ADMIN));
        userJPA.setRoles(roles);
        if (!userRepository.existsByUsername(ADMIN)) {
            userRepository.save(userJPA);
        }
    }
}