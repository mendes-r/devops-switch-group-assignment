package switchtwentytwenty.project.applicationservice.appservice.implappservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentytwenty.project.applicationservice.irepository.IFamilyRepository;
import switchtwentytwenty.project.domain.aggregate.family.Family;
import switchtwentytwenty.project.domain.aggregate.family.FamilyFactory;
import switchtwentytwenty.project.domain.share.familydata.FamilyName;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.dto.outdto.OutFamilyDTO;
import switchtwentytwenty.project.dto.todomaindto.VOFamilyDTO;



import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class FamilyServiceIT {

    @Autowired
    FamilyService familyService;
    @Autowired
    IFamilyRepository familyRepository;

    @BeforeEach
    public void before(){
        familyRepository.deleteAll();
    }


    @Test
    @DisplayName("Get list of families")
    void getListOfFamilies() throws Exception {
        //arrange
        List<OutFamilyDTO> familiesDTO = new ArrayList<>();

        //Creating first family
        UUID id = UUID.randomUUID();
        FamilyID familyID = new FamilyID(id);
        FamilyName familyName = new FamilyName("Turing");
        Email adminEmail = new Email("alan_turing@hotmail.com");
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        VOFamilyDTO familyDTO = new VOFamilyDTO(familyID, ledgerID, adminEmail, familyName);
        Family family = FamilyFactory.create(familyDTO);

        //Creating second family
        UUID secondId = UUID.randomUUID();
        FamilyID secondFamilyID = new FamilyID(secondId);
        FamilyName secondFamilyName = new FamilyName("Turing");
        Email secondAdminEmail = new Email("alan_turing@hotmail.com");
        LedgerID secondLedgerID = new LedgerID(UUID.randomUUID());
        VOFamilyDTO secondFamilyDTO = new VOFamilyDTO(secondFamilyID, secondLedgerID, secondAdminEmail, secondFamilyName);
        Family secondFamily = FamilyFactory.create(secondFamilyDTO);

        //Creating third family
        UUID thirdId = UUID.randomUUID();
        FamilyID thirdFamilyID = new FamilyID(thirdId);
        FamilyName thirdFamilyName = new FamilyName("Turing");
        Email thirdAdminEmail = new Email("alan_turing@hotmail.com");
        LedgerID thirdLedgerID = new LedgerID(UUID.randomUUID());
        VOFamilyDTO thirdFamilyDTO = new VOFamilyDTO(thirdFamilyID, thirdLedgerID, thirdAdminEmail, thirdFamilyName);
        Family thirdFamily = FamilyFactory.create(thirdFamilyDTO);

        //act
        familyRepository.save(family);
        familyRepository.save(secondFamily);
        familyRepository.save(thirdFamily);
        OutFamilyDTO outFamilyDTO = new OutFamilyDTO(family.getName().toString(),
                family.getID().toString());
        OutFamilyDTO secondOutFamilyDTO = new OutFamilyDTO(secondFamily.getName().toString(),
                secondFamily.getID().toString());
        OutFamilyDTO thirdOutFamilyDTO = new OutFamilyDTO(thirdFamily.getName().toString(),
                thirdFamily.getID().toString());

        familiesDTO.add(outFamilyDTO);
        familiesDTO.add(secondOutFamilyDTO);
        familiesDTO.add(thirdOutFamilyDTO);
        //assert

        List<OutFamilyDTO> result = familyService.getListOfFamilies();

        assertEquals(result, familiesDTO);

    }

    @Test
    @DisplayName("Get list of families: no families added to the list")
    void getListOfFamilies_NullCase() throws Exception {
        //arrange
        List<OutFamilyDTO> familiesDTO = new ArrayList<>();

        //Creating first family
        UUID id = UUID.randomUUID();
        FamilyID familyID = new FamilyID(id);
        FamilyName familyName = new FamilyName("Turing");
        Email adminEmail = new Email("alan_turing@hotmail.com");
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        VOFamilyDTO familyDTO = new VOFamilyDTO(familyID,ledgerID,adminEmail,familyName);
        Family family = FamilyFactory.create(familyDTO);

        //act
        familyRepository.save(family);
        List<OutFamilyDTO> result = familyService.getListOfFamilies();
        //assert

        assertNotEquals(familiesDTO, result);

    }

}