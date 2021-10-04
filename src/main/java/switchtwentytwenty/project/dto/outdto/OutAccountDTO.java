package switchtwentytwenty.project.dto.outdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

@AllArgsConstructor
public class OutAccountDTO extends RepresentationModel<OutAccountDTO> {

    @Getter
    String accountID;

    @Getter
    String designation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OutAccountDTO)) return false;
        OutAccountDTO that = (OutAccountDTO) o;
        return Objects.equals(accountID, that.accountID) &&
                Objects.equals(designation, that.designation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountID, designation);
    }
}
