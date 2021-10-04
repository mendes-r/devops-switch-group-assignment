package switchtwentytwenty.project.applicationservice.appservice.implappservice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import switchtwentytwenty.project.applicationservice.irepository.IPersonRepository;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.aggregate.person.PersonFactory;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.domain.share.list.PersonList;
import switchtwentytwenty.project.domain.share.persondata.BirthDate;
import switchtwentytwenty.project.domain.share.persondata.PersonName;
import switchtwentytwenty.project.domain.share.persondata.TelephoneNumberList;
import switchtwentytwenty.project.domain.share.persondata.VAT;
import switchtwentytwenty.project.domain.share.persondata.address.Address;
import switchtwentytwenty.project.dto.outdto.OutAddEmailDTO;
import switchtwentytwenty.project.dto.outdto.OutPersonDTO;
import switchtwentytwenty.project.dto.todomaindto.VOPersonDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {


    @InjectMocks
    PersonService personService;

    @Mock
    IPersonRepository personRepository;

    @Test
    @DisplayName("Add Email - successful case")
    void addEmailSuccessfully() throws Exception {
        //arrange
        String emailToAdd = "tiagoA@gmail.com";
        String personId = "tiago@gmail.com";
        Person person = Mockito.mock(Person.class);
        Email email = Mockito.mock(Email.class);
        when(personRepository.findByID(Mockito.any(Email.class))).thenReturn(person);
        doNothing().when(person).addEmail(Mockito.any(Email.class));
        doNothing().when(personRepository).save(Mockito.any(Person.class));
        when(person.getID()).thenReturn(email);
        when(email.toString()).thenReturn(Mockito.anyString());
        //act
        OutAddEmailDTO outAddEmailDTO = personService.addEmailToProfile(personId, emailToAdd);
        //assert
        assertNotNull(outAddEmailDTO);
    }

    @Test
    @DisplayName("Add Email - Unsuccessful case")
    void addEmailUnSuccessfully() throws Exception {
        //arrange
        String emailToAdd = "tiago@gmail.com";
        String personId = "tiago@gmail.com";
        Person person = Mockito.mock(Person.class);
        when(personRepository.findByID(Mockito.any(Email.class))).thenReturn(person);
        doThrow(IllegalArgumentException.class).when(person).addEmail(Mockito.any(Email.class));
        //act and assert
        assertThrows(IllegalArgumentException.class, () -> personService.addEmailToProfile(personId, emailToAdd));
    }


    @Test
    @DisplayName("Get List Of Family Members: empty")
    void getListOfFamilyMembers_Empty() throws Exception {
        //arrange
        String familyID = UUID.randomUUID().toString();
        when(personRepository.findByFamilyID(Mockito.any(FamilyID.class))).thenReturn(new PersonList());
        //act
        List<OutPersonDTO> result = personService.getListOfFamilyMembers(familyID);
        //assert
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    @DisplayName("Get List Of Family Members: one element")
    void getListOfFamilyMembers_OneElement() throws Exception {
        //arrange
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        Email email = new Email("email@gmail.com");
        PersonName personName = new PersonName("Joaquina");
        BirthDate birthDate = new BirthDate("2020-03-02");
        VAT vat = new VAT("123456789");
        Address address = new Address("Rua Escura", "25", "2156-956", "Porto", "Portugal");
        List<String> list = new ArrayList<>();
        list.add("225498653");
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList(list);
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        VOPersonDTO voPersonDTO = new VOPersonDTO(personName, birthDate, vat, address, telephoneNumberList, email, familyID, ledgerID);
        Person person = PersonFactory.create(voPersonDTO);

        PersonList personList = new PersonList();
        personList.add(person);

        List<OutPersonDTO> expected = new ArrayList<>();
        OutPersonDTO outPersonDTO = new OutPersonDTO(person.getName().toString(), person.getMainEmail().toString(), person.getFamilyID().toString());
        expected.add(outPersonDTO);

        when(personRepository.findByFamilyID(Mockito.any(FamilyID.class))).thenReturn(personList);
        //act
        List<OutPersonDTO> result = personService.getListOfFamilyMembers(familyID.toString());
        //assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Get List Of Family Members: several elements")
    void getListOfFamilyMembers_SeveralElements() throws Exception {
        //arrange
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        Email email = new Email("email@gmail.com");
        Email otherEmail = new Email("other@gmail.com");
        PersonName personName = new PersonName("Joaquina");
        PersonName otherPersonName = new PersonName("Ricardina");
        BirthDate birthDate = new BirthDate("2020-03-02");
        VAT vat = new VAT("123456789");
        Address address = new Address("Rua Escura", "25", "2156-956", "Porto", "Portugal");
        List<String> list = new ArrayList<>();
        list.add("225498653");
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList(list);
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        VOPersonDTO voPersonDTO = new VOPersonDTO(personName, birthDate, vat, address, telephoneNumberList, email, familyID, ledgerID);
        VOPersonDTO otherVoPersonDTO = new VOPersonDTO(otherPersonName, birthDate, vat, address, telephoneNumberList, otherEmail, familyID, ledgerID);
        Person person = PersonFactory.create(voPersonDTO);
        Person otherPerson = PersonFactory.create(otherVoPersonDTO);

        PersonList personList = new PersonList();
        personList.add(person);
        personList.add(otherPerson);

        List<OutPersonDTO> expected = new ArrayList<>();
        OutPersonDTO outPersonDTO = new OutPersonDTO(person.getName().toString(), person.getMainEmail().toString(), person.getFamilyID().toString());
        OutPersonDTO otherOutPersonDTO = new OutPersonDTO(otherPerson.getName().toString(), otherPerson.getMainEmail().toString(), otherPerson.getFamilyID().toString());
        expected.add(outPersonDTO);
        expected.add(otherOutPersonDTO);

        when(personRepository.findByFamilyID(Mockito.any(FamilyID.class))).thenReturn(personList);
        //act
        List<OutPersonDTO> result = personService.getListOfFamilyMembers(familyID.toString());
        //assert
        assertEquals(expected, result);
    }

}
