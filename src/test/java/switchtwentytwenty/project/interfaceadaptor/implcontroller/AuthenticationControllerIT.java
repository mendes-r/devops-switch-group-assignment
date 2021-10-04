package switchtwentytwenty.project.interfaceadaptor.implcontroller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import switchtwentytwenty.project.autentication.*;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthenticationControllerIT {

    @Autowired
    AuthenticationController authenticationController;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @BeforeEach
    void cleanRepository(){
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("Create user successfully: one role")
    void registerUserSuccessfully_oneRole() {
        //arrange
            //in dto
            String username = "constantino";
            String email = "constantino@gmail.com";
            String password = "123456";
            Set<String> role = new HashSet<>();
            role.add("admin");
            SignupRequestDTO signupRequestDTO = new SignupRequestDTO(username,email,password,role);
        //act
        ResponseEntity<?> result = authenticationController.registerUser(signupRequestDTO);
        //assert
        assertEquals(200, result.getStatusCodeValue());
        assertTrue(userRepository.existsByUsername(username));
    }

    @Test
    @DisplayName("Create user successfully: two role")
    void registerUserSuccessfully_twoRole() {
        //arrange
        //in dto
        String username = "constantino";
        String email = "constantino@gmail.com";
        String password = "123456";
        Set<String> role = new HashSet<>();
        role.add("admin");
        role.add("user");
        SignupRequestDTO signupRequestDTO = new SignupRequestDTO(username,email,password,role);
        //act
        ResponseEntity<?> result = authenticationController.registerUser(signupRequestDTO);
        //assert
        assertEquals(200, result.getStatusCodeValue());
        assertTrue(userRepository.existsByUsername(username));
    }

    @Test
    @DisplayName("Create user successfully: none role")
    void registerUserSuccessfully_noneRole() {
        //arrange
        //in dto
        String username = "constantino";
        String email = "constantino@gmail.com";
        String password = "123456";
        SignupRequestDTO signupRequestDTO = new SignupRequestDTO(username,email,password,null);
        //act
        ResponseEntity<?> result = authenticationController.registerUser(signupRequestDTO);
        //assert
        assertEquals(200, result.getStatusCodeValue());
        assertTrue(userRepository.existsByUsername(username));
    }

    @Test
    @DisplayName("Create user unsuccessfully: username already used")
    void registerUserUnSuccessfully_usernameAlreadyExists() {
        //arrange
        //1st user
        String username = "constantino";
        String email = "constantino@gmail.com";
        String password = "123456";
        Set<String> role = new HashSet<>();
        role.add("admin");
        SignupRequestDTO signupRequestDTO = new SignupRequestDTO(username,email,password,role);
        authenticationController.registerUser(signupRequestDTO);
        //2st user
        String otheremail = "constantino2@gmail.com";
        String otherpassword = "123456";
        SignupRequestDTO othersignupRequestDTO = new SignupRequestDTO(username,otheremail,otherpassword,role);
        //act
        ResponseEntity<?> result = authenticationController.registerUser(othersignupRequestDTO);
        //assert
        assertEquals(400, result.getStatusCodeValue());
        assertFalse(userRepository.existsByEmail(otheremail));
    }

    @Test
    @DisplayName("Create user successfully: empty role")
    void registerUserUnSuccessfully_emptyRole() {
        //arrange
        //in dto
        String username = "constantino";
        String email = "constantino@gmail.com";
        String password = "123456";
        Set<String> role = new HashSet<>();
        SignupRequestDTO signupRequestDTO = new SignupRequestDTO(username,email,password,role);
        //act
        ResponseEntity<?> result = authenticationController.registerUser(signupRequestDTO);
        //assert
        assertEquals(200, result.getStatusCodeValue());
        assertTrue(userRepository.existsByUsername(username));
    }

    @Test
    @DisplayName("Create user unsuccessfully: email already used")
    void registerUserUnSuccessfully_emailAlreadyExists() {
        //arrange
        //1st user
        String username = "constantino";
        String email = "constantino@gmail.com";
        String password = "123456";
        Set<String> role = new HashSet<>();
        role.add("admin");
        SignupRequestDTO signupRequestDTO = new SignupRequestDTO(username,email,password,role);
        authenticationController.registerUser(signupRequestDTO);
        //2st user
        String otherusername = "otherconstantino";
        String otherpassword = "123456";
        SignupRequestDTO othersignupRequestDTO = new SignupRequestDTO(otherusername, email,otherpassword,role);
        //act
        ResponseEntity<?> result = authenticationController.registerUser(othersignupRequestDTO);
        //assert
        assertEquals(400, result.getStatusCodeValue());
        assertFalse(userRepository.existsByUsername(otherusername));
    }

    @Test
    @DisplayName("Create user successfully: one role")
    void authenticateUserSuccessfully() {
        //arrange
        //in dto
        String username = "constantino";
        String email = "constantino@gmail.com";
        String password = "123456";
        Set<String> role = new HashSet<>();
        role.add("admin");
        SignupRequestDTO signupRequestDTO = new SignupRequestDTO(username,email,password,role);
        authenticationController.registerUser(signupRequestDTO);
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO(username,password);
        //act
        ResponseEntity<?> result = authenticationController.authenticateUser(loginRequestDTO);
        //assert
        assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    @DisplayName("Create user successfully: one role")
    void authenticateUserUnSuccessfully() {
        //arrange
        //in dto
        String username = "constantino";
        String password = "123456";
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO(username,password);
        //act & assert
        assertThrows(BadCredentialsException.class, () -> authenticationController.authenticateUser(loginRequestDTO));
    }
}