package switchtwentytwenty.project.dto.outdto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OutPersonalCashAccountDTOTest {

    @Test
    void getAccountID() {
        //arrange
        String designation = "football match";
        String accountID = UUID.randomUUID().toString();
        OutPersonalCashAccountDTO dto = new OutPersonalCashAccountDTO(accountID, designation);
        //act
        String result = dto.getAccountDesignation();
        //assert
        assertEquals(designation, result);

    }


    @Test
    void getAccountDesignation() {
        //arrange
        String designation = "football match";
        String accountID = UUID.randomUUID().toString();
        OutPersonalCashAccountDTO dto = new OutPersonalCashAccountDTO(accountID, designation);
        //act
        String result = dto.getAccountID();
        //assert
        assertEquals(accountID, result);


    }

    @Test
    @DisplayName("Same object")
    void sameObject() {
        //arrange
        String designation = "football match";
        String accountID = UUID.randomUUID().toString();
        OutPersonalCashAccountDTO dto = new OutPersonalCashAccountDTO(accountID, designation);

        //act
        OutPersonalCashAccountDTO sameDto = dto;

        //assert
        assertEquals(dto, sameDto);
    }

    @Test
    @DisplayName("Identical object")
    void identicalObject() {
        //arrange
        String designation = "football match";
        String accountID = UUID.randomUUID().toString();
        OutPersonalCashAccountDTO dto = new OutPersonalCashAccountDTO(accountID, designation);

        //act
        OutPersonalCashAccountDTO sameDto = new OutPersonalCashAccountDTO(accountID, designation);

        //assert
        assertEquals(dto, sameDto);
    }

    @Test
    @DisplayName("Identical object")
    void differentObject() {
        //arrange
        String designation = "football match";
        String designation2 = "football match";
        String accountID = UUID.randomUUID().toString();
        String accountID2 = UUID.randomUUID().toString();
        OutPersonalCashAccountDTO dto = new OutPersonalCashAccountDTO(accountID, designation);

        //act
        OutPersonalCashAccountDTO differentDto = new OutPersonalCashAccountDTO(accountID2, designation2);

        //assert
        assertNotEquals(dto, differentDto);
    }

    @Test
    @DisplayName("Test equals with different dtos - successfully")
    void equalsDifferentTest() {

        //arrange
        String designation = "football match";
        String designation2 = "football match";
        String accountID = UUID.randomUUID().toString();
        String accountID2 = UUID.randomUUID().toString();

        OutPersonalCashAccountDTO dto = new OutPersonalCashAccountDTO(accountID, designation);
        OutPersonalCashAccountDTO differentDto = new OutPersonalCashAccountDTO(accountID2, designation2);
        //act
        boolean result = dto.equals(differentDto);
        //assert
        assertFalse(result);
    }
    @Test
    @DisplayName("Test equals with same dtos - successfully")
    void equalsSameDTOsTest() {

        //arrange
        String designation = "football match";
        String accountID = UUID.randomUUID().toString();

        OutPersonalCashAccountDTO dto = new OutPersonalCashAccountDTO(accountID, designation);

        //act
        boolean result = dto.equals(dto);
        //assert
        assertTrue(result);
    }
    @Test
    @DisplayName("Test equals with different dtos - successfully")
    void equalsNullTest() {

        //arrange
        String designation = "football match";
        String accountID = UUID.randomUUID().toString();

        OutPersonalCashAccountDTO dto = new OutPersonalCashAccountDTO(accountID, designation);
        //act
        boolean result = dto.equals(null);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Same hash code")
    void sameHashCode() {
        //arrange
        String designation = "football match";
        String accountID = UUID.randomUUID().toString();
        OutPersonalCashAccountDTO dto = new OutPersonalCashAccountDTO(accountID, designation);
        OutPersonalCashAccountDTO sameDto = new OutPersonalCashAccountDTO(accountID, designation);

        //act
        int hashCode1 = dto.hashCode();
        int hashCode2 = sameDto.hashCode();

        //assert
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    @DisplayName("Different hash codes")
    void differentHashCodes() {
        //arrange
        String designation = "football match";
        String designation2 = "football match";
        String accountID = UUID.randomUUID().toString();
        String accountID2 = UUID.randomUUID().toString();
        OutPersonalCashAccountDTO dto = new OutPersonalCashAccountDTO(accountID, designation);
        OutPersonalCashAccountDTO otherDto = new OutPersonalCashAccountDTO(accountID2, designation2);

        //act
        int hash1 = dto.hashCode();
        int hash2 = otherDto.hashCode();

        //assert
        assertNotEquals(hash1, hash2);
    }
}