package switchtwentytwenty.project.dto.indto;


import lombok.AllArgsConstructor;
import lombok.Getter;


import java.util.Objects;

@AllArgsConstructor
public class InAddEmailDTO {


    @Getter
    private final String emailToAdd;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InAddEmailDTO)) return false;
        InAddEmailDTO that = (InAddEmailDTO) o;
        return
                Objects.equals(emailToAdd, that.emailToAdd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailToAdd);
    }

}