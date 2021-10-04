package switchtwentytwenty.project.dto.outdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

@AllArgsConstructor
public class OutPersonalCashAccountDTO extends RepresentationModel<OutPersonalCashAccountDTO> {

    @Getter
    String accountID;
    @Getter
    String accountDesignation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OutPersonalCashAccountDTO)) return false;
        OutPersonalCashAccountDTO that = (OutPersonalCashAccountDTO) o;
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