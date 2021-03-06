package switchtwentytwenty.project.datamodel.assembler;

import org.springframework.stereotype.Service;
import switchtwentytwenty.project.datamodel.AccountJPA;
import switchtwentytwenty.project.domain.aggregate.account.Account;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.AccountDesignation;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.dto.todomaindto.JpaToDomainAccountDTO;
import switchtwentytwenty.project.exception.AccountNotCreatedException;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class AccountDomainDataAssembler {

    /**
     * Translate domain to data model.
     *
     * @param account entity
     * @return data model
     */
    public AccountJPA toData(Account account) {
        return new AccountJPA(account.getID().toString(), account.getBalance().toString(), account.getDesignation().toString(), account.getAccountType());
    }

    /**
     * Translate data model to dto.
     *
     * @param accountJPA - data model
     * @return dto
     * @throws AccountNotCreatedException
     * @throws InstantiationException
     */
    public JpaToDomainAccountDTO toDomain(AccountJPA accountJPA) {

        AccountID accountID = new AccountID(UUID.fromString(accountJPA.getId()));
        AccountDesignation designation = new AccountDesignation(accountJPA.getDesignation());
        String accountType = accountJPA.getType();

        if (accountType.equals(Constants.CASH_ACCOUNT_TYPE)) {
            BigDecimal value = new BigDecimal(accountJPA.getBalance());
            MoneyValue moneyValue = new MoneyValue(value);
            return new JpaToDomainAccountDTO(accountID, designation,accountType, moneyValue);
        } else {
            return  new JpaToDomainAccountDTO(accountID, designation, accountType);
        }
    }

}
