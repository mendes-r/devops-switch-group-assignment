package switchtwentytwenty.project.autentication;

import lombok.Getter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class JwtResponseDTOTest {

    @Test

    void getAttributesFromJwtResponseDTO() {
        //arrange
        String jwt = "jdbfufdfouwhf";
        Long id = 122334443L;
        String username = "admin";
        String email = "admin@gmail.com";
        List<String> roles = new ArrayList<>();
        roles.add("admin");
        JwtResponseDTO jwtResponseDTO = new JwtResponseDTO(jwt,id,username,email,roles);
        //act & assert
        assertEquals(jwt, jwtResponseDTO.getJwt());
        assertEquals(id, jwtResponseDTO.getId());
        assertEquals(username, jwtResponseDTO.getUsername());
        assertEquals(email, jwtResponseDTO.getEmail());
        assertEquals(roles, jwtResponseDTO.getRoles());
    }
}