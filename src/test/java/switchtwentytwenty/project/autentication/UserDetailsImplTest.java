package switchtwentytwenty.project.autentication;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class UserDetailsImplTest {

    @Test
    void testEquals_true() {
        //arrange
        Long id = 23556654L;
        String username = "admin";
        String email = "admin@gmail.com";
        String password = "22494459";
        Collection<? extends GrantedAuthority> authorities = null;
        UserDetailsImpl userDetails = new UserDetailsImpl(id, username,email,password,authorities);
        UserDetailsImpl otherDetails = new UserDetailsImpl(id, username, email,password,authorities);
        //act & assert
        assertEquals(userDetails, otherDetails);
    }

    @Test
    void testEquals_false() {
        //arrange
        Long id = 23556654L;
        Long otherid = 2843273857L;
        String username = "admin";
        String email = "admin@gmail.com";
        String password = "22494459";
        Collection<? extends GrantedAuthority> authorities = null;
        UserDetailsImpl userDetails = new UserDetailsImpl(id, username,email,password,authorities);
        UserDetailsImpl otherDetails = new UserDetailsImpl(otherid, username, email,password,authorities);
        //act & assert
        assertNotEquals(userDetails, otherDetails);
    }
}