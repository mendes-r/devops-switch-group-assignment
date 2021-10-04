package switchtwentytwenty.project.dto.outdto;

import lombok.Getter;


import org.springframework.hateoas.RepresentationModel;


import java.util.Objects;


public class OutCashAccountBalanceDTO extends RepresentationModel<OutCashAccountBalanceDTO> {


    @Getter
    private double balance;

    public OutCashAccountBalanceDTO(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OutCashAccountBalanceDTO)) return false;
        OutCashAccountBalanceDTO that = (OutCashAccountBalanceDTO) o;
        return Objects.equals(balance, that.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), balance);
    }
}
