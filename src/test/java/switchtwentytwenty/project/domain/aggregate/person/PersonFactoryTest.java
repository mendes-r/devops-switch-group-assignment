package switchtwentytwenty.project.domain.aggregate.person;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.share.id.*;
import switchtwentytwenty.project.domain.share.list.EmailList;
import switchtwentytwenty.project.domain.share.persondata.BirthDate;
import switchtwentytwenty.project.domain.share.persondata.PersonName;
import switchtwentytwenty.project.domain.share.persondata.TelephoneNumberList;
import switchtwentytwenty.project.domain.share.persondata.VAT;
import switchtwentytwenty.project.domain.share.persondata.address.Address;
import switchtwentytwenty.project.dto.todomaindto.JpaToDomainPersonDTO;
import switchtwentytwenty.project.dto.todomaindto.VOPersonDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PersonFactoryTest {

    @Test
    @DisplayName("Test creation of person with an VOPersonDTO")
    void createPerson_WithVOPersonDTO() throws Exception {
        //arrange
        Email email = new Email("email@gmail.com");
        PersonName personName = new PersonName("Joaquina");
        BirthDate birthDate = new BirthDate("2020-03-02");
        VAT vat = new VAT("123456789");
        Address address = new Address("Rua Escura", "25", "2156-956", "Porto", "Portugal");
        List<String> list = new ArrayList<>();
        list.add("225498653");
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList(list);
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        VOPersonDTO voPersonDTO = new VOPersonDTO(personName, birthDate, vat, address, telephoneNumberList, email, familyID, ledgerID);
        //act
        Person person = PersonFactory.create(voPersonDTO);
        //assert
        assertNotNull(person);
        assertEquals(person.getID(), email);
        assertEquals(person.getName(), personName);
        assertEquals(person.getBirthDate(), birthDate);
        assertEquals(person.getVat(), vat);
        assertEquals(person.getAddress(), address);
        assertEquals(person.getTelephoneNumbers(), telephoneNumberList);
        assertEquals(person.getFamilyID(), familyID);
        assertEquals(person.getLedgerID(), ledgerID);
    }

    @Test
    @DisplayName("Test creation of person with an JpaToDomainPersonDTO")
    void createPerson_WithJpaToDomainPersonDTO() throws Exception {
        //arrange
        Email email = new Email("email@gmail.com");
        PersonName personName = new PersonName("Joaquina");
        BirthDate birthDate = new BirthDate("2020-03-02");
        VAT vat = new VAT("123456789");
        Address address = new Address("Rua Escura", "25", "2156-956", "Porto", "Portugal");
        List<String> list = new ArrayList<>();
        list.add("225498653");
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList(list);
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        AccountIDList accountIDList = new AccountIDList();
        accountIDList.add(new AccountID(UUID.randomUUID()));
        EmailList otherEmails = new EmailList();
        otherEmails.add(new Email("anotherOne@gmail.com"));
        JpaToDomainPersonDTO jpaToDomainPersonDTO = new JpaToDomainPersonDTO(personName, birthDate, vat, address, telephoneNumberList, email,
                familyID, ledgerID, otherEmails, accountIDList);
        //act
        Person person = PersonFactory.create(jpaToDomainPersonDTO);
        //assert
        assertNotNull(person);
        assertEquals(person.getID(), email);
        assertEquals(person.getName(), personName);
        assertEquals(person.getBirthDate(), birthDate);
        assertEquals(person.getVat(), vat);
        assertEquals(person.getAddress(), address);
        assertEquals(person.getTelephoneNumbers(), telephoneNumberList);
        assertEquals(person.getFamilyID(), familyID);
        assertEquals(person.getLedgerID(), ledgerID);
        assertEquals(person.getAccountIDList(), accountIDList);
        assertEquals(person.getOtherEmails(), otherEmails);
    }

    @Test
    @DisplayName("Failure create person with an VOPersonDTO: empty")
    void failureCreatePerson_WithEmptyVOPersonDTO(){
        //arrange
        VOPersonDTO voPersonDTO = new VOPersonDTO();
        //act - assert
        assertThrows(NullPointerException.class, ()-> PersonFactory.create(voPersonDTO));
    }
}