package switchtwentytwenty.project.dto.todomaindto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.AccountDesignation;
import switchtwentytwenty.project.domain.share.id.AccountID;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class JpaToDomainAccountDTOTest {

    @Test
    @DisplayName("Equals: same instance")
    void testEquals_SameInstance() {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountDesignation accountDesignation = new AccountDesignation("Account");
        String accountType = Constants.CASH_ACCOUNT_TYPE;
        MoneyValue amount = new MoneyValue(BigDecimal.valueOf(25.26));
        JpaToDomainAccountDTO dto = new JpaToDomainAccountDTO(accountID, accountDesignation, accountType, amount);
        //act - assert
        assertEquals(dto, dto);
    }

    @Test
    @DisplayName("Equals: null")
    void testEquals_Null() {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountDesignation accountDesignation = new AccountDesignation("Account");
        String accountType = Constants.CASH_ACCOUNT_TYPE;
        MoneyValue amount = new MoneyValue(BigDecimal.valueOf(25.26));
        JpaToDomainAccountDTO dto = new JpaToDomainAccountDTO(accountID, accountDesignation, accountType, amount);
        //act
        assertNotEquals(null, dto);
    }

    @Test
    @DisplayName("Equals: other class type")
    void testEquals_OtherClassType() {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountDesignation accountDesignation = new AccountDesignation("Account");
        String accountType = Constants.CASH_ACCOUNT_TYPE;
        MoneyValue amount = new MoneyValue(BigDecimal.valueOf(25.26));
        JpaToDomainAccountDTO dto = new JpaToDomainAccountDTO(accountID, accountDesignation, accountType, amount);
        //act
        assertNotEquals("", dto);
    }

    @Test
    @DisplayName("Equals: true")
    void testEquals_True() {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountDesignation accountDesignation = new AccountDesignation("Account");
        String accountType = Constants.CASH_ACCOUNT_TYPE;
        MoneyValue amount = new MoneyValue(BigDecimal.valueOf(25.26));
        JpaToDomainAccountDTO dto = new JpaToDomainAccountDTO(accountID, accountDesignation, accountType, amount);
        JpaToDomainAccountDTO other = new JpaToDomainAccountDTO(accountID, accountDesignation, accountType, amount);
        //act
        boolean result = dto.equals(other);
        //arrange
        assertTrue(result);
    }

    @Test
    @DisplayName("Equals: false")
    void testEquals_False() {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountDesignation accountDesignation = new AccountDesignation("Account");
        String accountType = Constants.CASH_ACCOUNT_TYPE;
        String otherAccountType = Constants.CREDIT_ACCOUNT_TYPE;
        MoneyValue amount = new MoneyValue(BigDecimal.valueOf(25.26));
        JpaToDomainAccountDTO dto = new JpaToDomainAccountDTO(accountID, accountDesignation, accountType, amount);
        JpaToDomainAccountDTO other = new JpaToDomainAccountDTO(accountID, accountDesignation, otherAccountType, amount);
        //act
        boolean result = dto.equals(other);
        //arrange
        assertFalse(result);
    }

    @Test
    @DisplayName("HashCode: true")
    void testHashCode_True() {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountDesignation accountDesignation = new AccountDesignation("Account");
        String accountType = Constants.CASH_ACCOUNT_TYPE;
        MoneyValue amount = new MoneyValue(BigDecimal.valueOf(25.26));
        JpaToDomainAccountDTO dto = new JpaToDomainAccountDTO(accountID, accountDesignation, accountType, amount);
        JpaToDomainAccountDTO other = new JpaToDomainAccountDTO(accountID, accountDesignation, accountType, amount);
        //act
        int hashCode = dto.hashCode();
        int otherHashCode = other.hashCode();
        //arrange
        assertEquals(otherHashCode, hashCode);
    }

    @Test
    @DisplayName("HashCode: false")
    void testHashCode_False() {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountDesignation accountDesignation = new AccountDesignation("Account");
        String accountType = Constants.CASH_ACCOUNT_TYPE;
        String otherAccountType = Constants.CREDIT_ACCOUNT_TYPE;
        MoneyValue amount = new MoneyValue(BigDecimal.valueOf(25.26));
        JpaToDomainAccountDTO dto = new JpaToDomainAccountDTO(accountID, accountDesignation, accountType, amount);
        JpaToDomainAccountDTO other = new JpaToDomainAccountDTO(accountID, accountDesignation, otherAccountType, amount);
        //act
        int hashCode = dto.hashCode();
        int otherHashCode = other.hashCode();
        //arrange
        assertNotEquals(otherHashCode, hashCode);
    }

    @Test
    @DisplayName("Failure get cash amount")
    void failureGetCashAmount() {
        //arrange
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountDesignation accountDesignation = new AccountDesignation("Account");
        String accountType = Constants.CASH_ACCOUNT_TYPE;
        JpaToDomainAccountDTO dto = new JpaToDomainAccountDTO(accountID, accountDesignation, accountType);
        //act - assert
        assertThrows(NullPointerException.class, dto::getCashAmount);
    }
}