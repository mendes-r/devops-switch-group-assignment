package switchtwentytwenty.project.dto.indto;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class InFamilyCashAccountDTOTest {

    @Test
    void createDTOSucessfully(){
        //arrange
        String familyID = UUID.randomUUID().toString();
        double amount = 200;
        String  designation = "my cahs account";
        String description = "createCashAccount";
        InFamilyCashAccountDTO dto = new InFamilyCashAccountDTO(familyID,amount, designation, description);
        //act
        String result1 = dto.getDescription();
        String result2 = dto.getDesignation();
        String result3 = dto.getFamilyAdministratorID();
        double result4 = dto.getInitialAmount();
        //assert
        assertEquals(description,result1);
        assertEquals(designation,result2);
        assertEquals(familyID,result3);
        assertEquals(amount,result4);
    }
}