package switchtwentytwenty.project.dto.outdto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.constant.Constants;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OutFamilyDTOTest {

    @Test
    void getName() {
        //arrange
        String familyID = UUID.randomUUID().toString();
        String name = "constantino";

        OutFamilyDTO dto = new OutFamilyDTO(name, familyID );
        //act

        String result = dto.getName();

        //assert
        assertEquals(name,result);


    }

    @Test
    void getFamilyID() {
        //arrange
        String familyID = UUID.randomUUID().toString();
        String name = "constantino";

        OutFamilyDTO dto = new OutFamilyDTO(name, familyID );
        //act

        String result = dto.getFamilyID();

        //assert
        assertEquals(familyID,result);

    }

    @Test
    void testEqualsTrueTwoIdenticalDTOS() {
        //arrange
        String familyID = UUID.randomUUID().toString();
        String name = "constantino";

        OutFamilyDTO dto = new OutFamilyDTO(name, familyID );
        OutFamilyDTO dto2 = new OutFamilyDTO(name, familyID );
        //act

        boolean result = dto.equals(dto2);

        //assert
        assertTrue(result);

    }

    @Test
    void testEqualsTrueTwoDifferentDTOS() {
        //arrange
        String familyID = UUID.randomUUID().toString();
        String name = "constantino";

        String familyID2 = UUID.randomUUID().toString();
        String name2 = "fonseca";

        OutFamilyDTO dto = new OutFamilyDTO(name, familyID );
        OutFamilyDTO dto2 = new OutFamilyDTO(name2, familyID2 );
        //act

        boolean result = dto.equals(dto2);

        //assert
        assertFalse(result);

    }

    @Test
    @DisplayName("Test null - successfully")
    void equalsNullTest() {

        //arrange
        String familyID = UUID.randomUUID().toString();
        String name = "constantino";

        OutFamilyDTO dto = new OutFamilyDTO(name, familyID);
        //act
        boolean result = dto.equals(null);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Same hash code")
    void sameHashCode() {
        //arrange
        String familyID = UUID.randomUUID().toString();
        String name = "constantino";
        OutFamilyDTO dto = new OutFamilyDTO(name, familyID);
        OutFamilyDTO sameDto = new OutFamilyDTO(name, familyID);

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
        String familyID = UUID.randomUUID().toString();
        String name = "constantino";
        String familyID2 = UUID.randomUUID().toString();
        String name2 = "constantino";
        OutFamilyDTO dto = new OutFamilyDTO(name, familyID);
        OutFamilyDTO differentDto = new OutFamilyDTO(name2, familyID2);

        //act
        int hash1 = dto.hashCode();
        int hash2 = differentDto.hashCode();

        //assert
        assertNotEquals(hash1, hash2);
    }
}

