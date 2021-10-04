package switchtwentytwenty.project.dto.todomaindto;

import lombok.Getter;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.AccountDesignation;
import switchtwentytwenty.project.domain.share.id.AccountID;

import java.util.Objects;

public class JpaToDomainAccountDTO {

    //Attributes
    @Getter
    private final AccountID accountID;
    @Getter
    private final AccountDesignation accountDesignation;
    @Getter
    private final String accountType;

    private MoneyValue cashAmount;

    public JpaToDomainAccountDTO (AccountID accountID, AccountDesignation accountDesignation, String accountType) {
        this.accountID = accountID;
        this.accountDesignation = accountDesignation;
        this.accountType = accountType;
    }

    public JpaToDomainAccountDTO (AccountID accountID, AccountDesignation accountDesignation, String accountType, MoneyValue cashAmount){
        this.accountID = accountID;
        this.accountDesignation = accountDesignation;
        this.accountType = accountType;
        this.cashAmount = cashAmount;
    }

    public MoneyValue getCashAmount() {
        if (this.cashAmount == null){
            throw new NullPointerException();
        }
        return this.cashAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JpaToDomainAccountDTO that = (JpaToDomainAccountDTO) o;
        return Objects.equals(accountID, that.accountID) &&
                Objects.equals(accountDesignation, that.accountDesignation) &&
                Objects.equals(accountType, that.accountType) &&
                Objects.equals(cashAmount, that.cashAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountID, accountDesignation, accountType, cashAmount);
    }
}
