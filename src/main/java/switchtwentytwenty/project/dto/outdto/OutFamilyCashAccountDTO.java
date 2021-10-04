package switchtwentytwenty.project.dto.outdto;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

public class OutFamilyCashAccountDTO extends RepresentationModel<OutFamilyCashAccountDTO> {

    @Getter
    String accountID;
    @Getter
    String accountDesignation;


    public OutFamilyCashAccountDTO (String accountID, String accountDesignation) {
        this.accountID = accountID;
        this.accountDesignation = accountDesignation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OutFamilyCashAccountDTO)) return false;
        OutFamilyCashAccountDTO that = (OutFamilyCashAccountDTO) o;
        return Objects.equals(accountID, that.accountID) && Objects.equals(accountDesignation, that.accountDesignation);
    }

    /**
     * Override hashCode.
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), accountID, accountDesignation);
    }

}
