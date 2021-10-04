/*
package switchtwentytwenty.project.interfaceadaptor.implcontroller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import switchtwentytwenty.project.Application;
import switchtwentytwenty.project.applicationservice.irepository.IFamilyRepository;
import switchtwentytwenty.project.applicationservice.irepository.IPersonRepository;
import switchtwentytwenty.project.domain.aggregate.family.Family;
import switchtwentytwenty.project.domain.aggregate.family.FamilyFactory;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.aggregate.person.PersonFactory;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.familydata.FamilyName;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.domain.share.persondata.BirthDate;
import switchtwentytwenty.project.domain.share.persondata.PersonName;
import switchtwentytwenty.project.domain.share.persondata.TelephoneNumberList;
import switchtwentytwenty.project.domain.share.persondata.VAT;
import switchtwentytwenty.project.domain.share.persondata.address.Address;
import switchtwentytwenty.project.dto.todomaindto.VOFamilyDTO;
import switchtwentytwenty.project.dto.todomaindto.VOPersonDTO;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc(addFilters = false)
public class AddFamilyMemberControllerIT {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    IFamilyRepository familyRepository;
    @Autowired
    IPersonRepository personRepository;

    MediaType mediaType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            StandardCharsets.UTF_8
    );

    @BeforeEach
    public void before(){
        personRepository.deleteAll();
    }

    @Test
    @DisplayName("Add family member successfully")
    void testAddMemberSuccessfully()
            throws Exception {
        //arrange
        VOFamilyDTO familyDTO = new VOFamilyDTO(new FamilyID(UUID.randomUUID()),new LedgerID(UUID.randomUUID()),new Email("admin@gmail.com"),new FamilyName("Costa"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);
        String familyID = family.getID().toString();

        String data = "{\n" +
                "    \"email\": \"yeah@gmail.com\",\n" +
                "    \"name\": \"Constantino\",\n" +
                "    \"birthDate\": \"2020-01-01\",\n" +
                "    \"vat\": \"123456789\",\n" +
                "    \"houseNumber\": \"25\",\n" +
                "    \"street\": \"Rua Nova\",\n" +
                "    \"city\": \"Porto\",\n" +
                "    \"country\": \"Portugal\",\n" +
                "    \"zipCode\": \"2156-958\",\n" +
                "    \"phoneNumbers\": [\"912654986\"],\n" +
                "    \"familyID\": \"" + familyID + "\" \n" +
                "}";

        //act-assert
        this.mockMvc.perform(post(Constants.URI_USERS)
                .contentType(mediaType)
                .content(data))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("Constantino")))
                .andExpect(content().string(containsString("yeah@gmail.com")));
    }

    @Test
    @DisplayName("Add family member successfully: without telephone numbers")
    void testAddMemberSuccessfully_WithoutTelephoneNumbers()
            throws Exception {
        //arrange
        VOFamilyDTO familyDTO = new VOFamilyDTO(new FamilyID(UUID.randomUUID()),new LedgerID(UUID.randomUUID()),new Email("admin@gmail.com"),new FamilyName("Costa"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);
        String familyID = family.getID().toString();

        String data = "{\n" +
                "    \"email\": \"yeah@gmail.com\",\n" +
                "    \"name\": \"Constantino\",\n" +
                "    \"birthDate\": \"2020-01-01\",\n" +
                "    \"vat\": \"123456789\",\n" +
                "    \"houseNumber\": \"25\",\n" +
                "    \"street\": \"Rua Nova\",\n" +
                "    \"city\": \"Porto\",\n" +
                "    \"country\": \"Portugal\",\n" +
                "    \"zipCode\": \"2156-958\",\n" +
                "    \"phoneNumbers\": [\"912654986\"],\n" +
                "    \"familyID\": \"" + familyID + "\" \n" +
                "}";

        //act-assert
        this.mockMvc.perform(post(Constants.URI_USERS)
                .contentType(mediaType)
                .content(data))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("Constantino")))
                .andExpect(content().string(containsString("yeah@gmail.com")));
    }

    @Test
    @DisplayName("Failure add family member: family not found")
    void failureAddFamilyMember_FamilyNotFound() throws Exception {
        //arrange
        String familyID = UUID.randomUUID().toString();
        String data = "{\n" +
                "    \"email\": \"yeah@gmail.com\",\n" +
                "    \"name\": \"Constantino\",\n" +
                "    \"birthDate\": \"2020-01-01\",\n" +
                "    \"vat\": \"123456789\",\n" +
                "    \"houseNumber\": \"25\",\n" +
                "    \"street\": \"Rua Nova\",\n" +
                "    \"city\": \"Porto\",\n" +
                "    \"country\": \"Portugal\",\n" +
                "    \"zipCode\": \"2156-958\",\n" +
                "    \"phoneNumbers\": [\"912654986\"],\n" +
                "    \"familyID\": \"" + familyID + "\" \n" +
                "}";

        //act - assert
        this.mockMvc.perform(post(Constants.URI_USERS)
                .contentType(mediaType)
                .content(data))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString(Constants.FAMILY_NOT_FOUND)));
    }

    @Test
    @DisplayName("Failure add family member: invalid person name")
    void failureAddFamilyMember_InvalidPersonName()
            throws Exception {
        //arrange
        VOFamilyDTO familyDTO = new VOFamilyDTO(new FamilyID(UUID.randomUUID()),new LedgerID(UUID.randomUUID()),new Email("admin@gmail.com"),new FamilyName("Costa"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);
        String familyID = family.getID().toString();

        String data = "{\n" +
                "    \"email\": \"yeah@gmail.com\",\n" +
                "    \"name\": \"123\",\n" +
                "    \"birthDate\": \"2020-01-01\",\n" +
                "    \"vat\": \"123456789\",\n" +
                "    \"houseNumber\": \"25\",\n" +
                "    \"street\": \"Rua Nova\",\n" +
                "    \"city\": \"Porto\",\n" +
                "    \"country\": \"Portugal\",\n" +
                "    \"zipCode\": \"2156-958\",\n" +
                "    \"phoneNumbers\": [\"912654986\"],\n" +
                "    \"familyID\": \"" + familyID + "\" \n" +
                "}";

        //act - assert
        this.mockMvc.perform(post(Constants.URI_USERS)
                .contentType(mediaType)
                .content(data))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Person name invalid")));
    }


    @Test
    @DisplayName("Failure add family member: person already in system")
    void failureAddFamilyMember_PersonAlreadyInSystem() throws Exception {
        //arrange
        VOFamilyDTO familyDTO = new VOFamilyDTO(new FamilyID(UUID.randomUUID()), new LedgerID(UUID.randomUUID()), new Email("admin@gmail.com"),
                new FamilyName("Costa"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);
        String familyID = family.getID().toString();
        String email = "yeah@gmail.com";
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();

        VOPersonDTO voPersonDTO = new VOPersonDTO(
                new PersonName("Joaquina"),
                new BirthDate("2020-03-02"),
                new VAT("123456789"),
                new Address("Rua Escura", "25", "2156-956", "Porto", "Portugal"),
                telephoneNumberList,
                new Email(email),
                new FamilyID(UUID.fromString(familyID)),
                new LedgerID(UUID.randomUUID()));
        Person member = PersonFactory.create(voPersonDTO);
        personRepository.save(member);

        String data = "{\n" +
                "    \"email\": \"yeah@gmail.com\",\n" +
                "    \"name\": \"Joaquim\",\n" +
                "    \"birthDate\": \"2020-01-01\",\n" +
                "    \"vat\": \"123456789\",\n" +
                "    \"houseNumber\": \"25\",\n" +
                "    \"street\": \"Rua Nova\",\n" +
                "    \"city\": \"Porto\",\n" +
                "    \"country\": \"Portugal\",\n" +
                "    \"zipCode\": \"2156-958\",\n" +
                "    \"phoneNumbers\": [\"912654986\"],\n" +
                "    \"familyID\": \"" + familyID + "\" \n" +
                "}";

        //act - assert
        this.mockMvc.perform(post(Constants.URI_USERS)
                .contentType(mediaType)
                .content(data))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Email Already Used")));
    }

    @Test
    @DisplayName("Failure add family member: vat already used in family")
    void failureAddFamilyMember_VatAlreadyUsedInFamily()
            throws Exception {
        //arrange
        VOFamilyDTO familyDTO = new VOFamilyDTO(new FamilyID(UUID.randomUUID()), new LedgerID(UUID.randomUUID()), new Email("admin@gmail.com"),
                new FamilyName("Costa"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);
        String familyID = family.getID().toString();
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();

        VOPersonDTO voPersonDTO = new VOPersonDTO(
                new PersonName("Joaquina"),
                new BirthDate("2020-03-02"),
                new VAT("123456789"),
                new Address("Rua Escura", "25", "2156-956", "Porto", "Portugal"),
                telephoneNumberList,
                new Email("email@gmail.com"),
                new FamilyID(UUID.fromString(familyID)),
                new LedgerID(UUID.randomUUID()));
        Person member = PersonFactory.create(voPersonDTO);
        personRepository.save(member);

        String data = "{\n" +
                "    \"email\": \"yeah@gmail.com\",\n" +
                "    \"name\": \"Joaquim\",\n" +
                "    \"birthDate\": \"2020-01-01\",\n" +
                "    \"vat\": \"123456789\",\n" +
                "    \"houseNumber\": \"25\",\n" +
                "    \"street\": \"Rua Nova\",\n" +
                "    \"city\": \"Porto\",\n" +
                "    \"country\": \"Portugal\",\n" +
                "    \"zipCode\": \"2156-958\",\n" +
                "    \"phoneNumbers\": [\"912654986\"],\n" +
                "    \"familyID\": \"" + familyID + "\" \n" +
                "}";

        //act - assert
        this.mockMvc.perform(post(Constants.URI_USERS)
                .contentType(mediaType)
                .content(data))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Vat Already Used In This Family")));
    }
}
*/
