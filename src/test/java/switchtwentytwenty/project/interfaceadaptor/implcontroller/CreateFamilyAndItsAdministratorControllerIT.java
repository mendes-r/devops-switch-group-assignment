package switchtwentytwenty.project.interfaceadaptor.implcontroller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.applicationservice.appservice.implappservice.FamilyAndMemberService;
import switchtwentytwenty.project.applicationservice.irepository.IFamilyRepository;
import switchtwentytwenty.project.applicationservice.irepository.ILedgerRepository;
import switchtwentytwenty.project.applicationservice.irepository.IPersonRepository;
import switchtwentytwenty.project.domain.aggregate.family.Family;
import switchtwentytwenty.project.domain.aggregate.ledger.Ledger;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.dto.indto.InFamilyAndAdminDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CreateFamilyAndItsAdministratorControllerIT {

    @Autowired      //for integration tests
    FamilyAndMemberService service;
    @Autowired
    CreateFamilyAndItsAdministratorController controller;
    @Autowired
    IFamilyRepository familyRepository;
    @Autowired
    IPersonRepository personRepository;
    @Autowired
    ILedgerRepository ledgerRepository;

    @BeforeEach
    public void before(){
        personRepository.deleteAll();
    }

    @Test
    @DisplayName("Create family and administrator successfully")
    void startFamilySuccessfully1() throws Exception{
        //arrange
        InFamilyAndAdminDTO dto = new InFamilyAndAdminDTO();
        dto.setBirthDate("1998-12-13");
        dto.setCity("Porto");
        dto.setCountry("Portugal");
        dto.setPersonName("Indy");
        dto.setEmail("indy@gmail.com");
        dto.setFamilyName("Jones");
        dto.setHouseNumber("23");
        dto.setStreet("Dragons steet");
        dto.setVat("257069313");
        dto.setZipCode("2345-987");
        dto.setDescription("addfamily");
        Email adminID = new Email(dto.getEmail());
        //act
        ResponseEntity<Object> result = controller.startFamily(dto);
        //assert
        assertEquals(201, result.getStatusCodeValue());
        Person person = personRepository.findByID(adminID);
        FamilyID familyID = person.getFamilyID();
        Family family = familyRepository.findByID(familyID);
        LedgerID personLedgerID = person.getLedgerID();
        LedgerID familyLedgerID = family.getLedgerID();
        Ledger personLedger = ledgerRepository.findByID(personLedgerID);
        Ledger familyLedger = ledgerRepository.findByID(familyLedgerID);
        assertNotNull(personLedger);
        assertNotNull(familyLedger);
    }

    @Test
    @DisplayName("Create family and administrator successfully")
    void startFamilySuccessfully2() throws Exception{
        //arrange
        InFamilyAndAdminDTO dto = new InFamilyAndAdminDTO();
        dto.setBirthDate("1998-12-13");
        dto.setCity("Porto");
        dto.setCountry("Portugal");
        dto.setPersonName("Indy");
        dto.setEmail("indy@gmail.com");
        dto.setFamilyName("Jones");
        dto.setHouseNumber("23");
        dto.setStreet("Dragons steet");
        dto.setVat("257069313");
        dto.setZipCode("2345-987");
        dto.setDescription("addfamily");
        Email adminID = new Email(dto.getEmail());
        //act
        ResponseEntity<Object> result = controller.startFamily(dto);
        //assert
        assertEquals(201, result.getStatusCodeValue());
        Person person = personRepository.findByID(adminID);
        FamilyID familyID = person.getFamilyID();
        Family family = familyRepository.findByID(familyID);
        LedgerID personLedgerID = person.getLedgerID();
        LedgerID familyLedgerID = family.getLedgerID();
        Ledger personLedger = ledgerRepository.findByID(personLedgerID);
        Ledger familyLedger = ledgerRepository.findByID(familyLedgerID);
        assertNotNull(personLedger);
        assertNotNull(familyLedger);
    }

    @Test
    @DisplayName("Unsuccessful method: invalid family name")
    void startFamily_invalidEmail() {
        //arrange
        InFamilyAndAdminDTO dto = new InFamilyAndAdminDTO();
        dto.setBirthDate("1998-12-13");
        dto.setCity("Porto");
        dto.setCountry("Portugal");
        dto.setPersonName("Indy");
        dto.setEmail("indygmail.com");
        dto.setFamilyName("Jones");
        dto.setHouseNumber("23");
        dto.setStreet("Dragons steet");
        dto.setVat("257069313");
        dto.setZipCode("2345-987");
        dto.setDescription("addfamily");
        //act
        ResponseEntity<Object> result = controller.startFamily(dto);
        //assert
        assertEquals(400, result.getStatusCodeValue());
    }

    @Test
    @DisplayName("Unsuccessful method: invalid person birthDate")
    void startFamily_administratorInvalidBirthDate() {
        //arrange
        InFamilyAndAdminDTO dto = new InFamilyAndAdminDTO();
        dto.setBirthDate("1998-14-13");
        dto.setCity("Porto");
        dto.setCountry("Portugal");
        dto.setPersonName("Indy");
        dto.setEmail("indy@gmail.com");
        dto.setFamilyName("Jones");
        dto.setHouseNumber("23");
        dto.setStreet("Dragons steet");
        dto.setVat("257069313");
        dto.setZipCode("2345-987");
        dto.setDescription("addfamily");
        //act
        ResponseEntity<Object> result = controller.startFamily(dto);
        //assert
        assertEquals(400, result.getStatusCodeValue());
    }

    @Test
    @DisplayName("Unsuccessful method: invalid vat")
    void startFamily_administratorInvalidVat() {
        //arrange
        InFamilyAndAdminDTO dto = new InFamilyAndAdminDTO();
        dto.setBirthDate("1998-10-13");
        dto.setCity("Porto");
        dto.setCountry("Portugal");
        dto.setPersonName("Indy");
        dto.setEmail("indy@gmail.com");
        dto.setFamilyName("Jones");
        dto.setHouseNumber("23");
        dto.setStreet("Dragons steet");
        dto.setVat("25706931");
        dto.setZipCode("2345-987");
        dto.setDescription("addfamily");
        //act
        ResponseEntity<Object> result = controller.startFamily(dto);
        //assert
        assertEquals(400, result.getStatusCodeValue());
    }

    @Test
    @DisplayName("Unsuccessful method: invalid person email - already used")
    void startFamily_administratorInvalidID() {
        //arrange
            //family bones
        InFamilyAndAdminDTO jones = new InFamilyAndAdminDTO();
        jones.setBirthDate("1965-11-13");
        jones.setCity("Porto");
        jones.setPersonName("Indy");
        jones.setCountry("Portugal");
        jones.setEmail("indy@gmail.com");
        jones.setFamilyName("Jones");
        jones.setHouseNumber("23");
        jones.setStreet("Dragons steet");
        jones.setVat("257069313");
        jones.setZipCode("2345-987");
        jones.setDescription("addfamily");
            //family adams
        InFamilyAndAdminDTO adam = new InFamilyAndAdminDTO();
        adam.setBirthDate("1965-11-13");
        adam.setCity("Porto");
        adam.setPersonName("Indy");
        adam.setCountry("Portugal");
        adam.setEmail("indy@gmail.com");
        adam.setFamilyName("Jones");
        adam.setHouseNumber("23");
        adam.setStreet("Dragons steet");
        adam.setVat("257069313");
        adam.setZipCode("2345-987");
        adam.setDescription("addfamily");
        //act
        ResponseEntity<Object> jonesResult = controller.startFamily(jones);
        ResponseEntity<Object> adamResult = controller.startFamily(adam);
        //assert
        assertEquals(201, jonesResult.getStatusCodeValue());
        assertEquals(400, adamResult.getStatusCodeValue());
    }

    @Test
    @DisplayName("Add second family with the same name")
    void startSecondFamilySuccessfully() {
        //arrange
            //family bones
        List<String> jonesNumber = new ArrayList<>();
        jonesNumber.add("912343546");
        //family bones
        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("912343546");
        InFamilyAndAdminDTO jones = new InFamilyAndAdminDTO();
        jones.setBirthDate("1965-11-13");
        jones.setCity("Porto");
        jones.setPersonName("Indy");
        jones.setCountry("Portugal");
        jones.setEmail("indy@gmail.com");
        jones.setFamilyName("Jones");
        jones.setHouseNumber("23");
        jones.setStreet("Dragons steet");
        jones.setVat("257069313");
        jones.setZipCode("2345-987");
        jones.setDescription("addfamily");
        jones.setPhoneNumbers(jonesNumber);
            //family adams
        InFamilyAndAdminDTO adam = new InFamilyAndAdminDTO();
        adam.setBirthDate("1965-11-13");
        adam.setCity("Porto");
        adam.setPersonName("Indy");
        adam.setCountry("Portugal");
        adam.setEmail("adams@gmail.com");
        adam.setFamilyName("Adams");
        adam.setHouseNumber("23");
        adam.setStreet("Dragons steet");
        adam.setVat("257069313");
        adam.setZipCode("2345-987");
        adam.setDescription("addfamily");
        //act
        ResponseEntity<Object> jonesResult = controller.startFamily(jones);
        ResponseEntity<Object> adamResult = controller.startFamily(adam);
        //assert
        assertEquals(201, jonesResult.getStatusCodeValue());
        assertEquals(201, adamResult.getStatusCodeValue());
    }

    @Test
    @DisplayName("Unsuccessful method: invalid person city (null)")
    void startFamily_administratorInvalidCity() {
        //arrange
        InFamilyAndAdminDTO jones = new InFamilyAndAdminDTO();
        jones.setBirthDate("1965-11-13");
        jones.setPersonName("Indy");
        jones.setCountry("Portugal");
        jones.setEmail("indy@gmail.com");
        jones.setFamilyName("Jones");
        jones.setHouseNumber("23");
        jones.setStreet("Dragons steet");
        jones.setVat("257069313");
        jones.setZipCode("2345-987");
        jones.setDescription("addfamily");
        //act
        ResponseEntity<Object> jonesResult = controller.startFamily(jones);
        //assert
        assertEquals(400, jonesResult.getStatusCodeValue());
    }
}
