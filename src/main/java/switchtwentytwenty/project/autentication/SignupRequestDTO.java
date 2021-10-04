package switchtwentytwenty.project.autentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Set;

@AllArgsConstructor
public class SignupRequestDTO {

    @Getter
    String username;

    @Getter
    String email;

    @Getter
    String password;

    @Getter
    Set<String> role;
}
