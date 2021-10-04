package switchtwentytwenty.project.interfaceadaptor.implcontroller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IFamilyAndMemberService;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IPersonService;
import switchtwentytwenty.project.applicationservice.irepository.IPersonRepository;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.dto.toservicedto.FamilyAndAdminDTO;
import switchtwentytwenty.project.dto.indto.InAddEmailDTO;
import switchtwentytwenty.project.dto.outdto.OutAddEmailDTO;
import switchtwentytwenty.project.interfaceadaptor.icontroller.IAddEmailToProfileController;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AddEmailToProfileControllerIT {

    @Autowired
    IAddEmailToProfileController controller;
    @Autowired
    IPersonService personService;
    @Autowired
    IPersonRepository personRepository;
    @Autowired
    IFamilyAndMemberService familyAndMemberService;

    @BeforeEach
    public void before(){
        personRepository.deleteAll();
    }

    @Test
    void addEmailToProfile() throws Exception {

        //arrange
        String personId = "bones@gmail.com";
        String emailToAdd = "bonesTwo@gmail.com";

        InAddEmailDTO info = new InAddEmailDTO(emailToAdd);

        List<String> johnPhoneNumbers = new ArrayList<>();
        johnPhoneNumbers.add("912343546");
        FamilyAndAdminDTO dto = new FamilyAndAdminDTO("Margaret Hamilton","1904-12-01","213025086","14","Logical Street",
                "Porto","Portugal","2100-345",johnPhoneNumbers,personId,"Hamilton");
        familyAndMemberService.startFamily(dto);
        Person marty = personRepository.findByID(new Email(personId));

        OutAddEmailDTO expectedOutAddEmailDTO = new OutAddEmailDTO(marty.getID().toString(),emailToAdd);
        ResponseEntity expected = new ResponseEntity(expectedOutAddEmailDTO,HttpStatus.OK);

        //act
        ResponseEntity<Object> result = controller.addEmailToProfile(personId, info);
        personRepository.save(marty);

        //assert
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(expected,result);
    }

    @Test
    void addNoneEmailToProfile()  {
        // arrange
        String personId = "bones@gmail.com";
        String emailToAdd = " ";
        InAddEmailDTO info = new InAddEmailDTO(emailToAdd);

        List<String> johnPhoneNumbers = new ArrayList<>();
        johnPhoneNumbers.add("912343546");
        FamilyAndAdminDTO dto = new FamilyAndAdminDTO("Margaret Hamilton","1904-12-01","213025086","14","Logical Street",
                "Porto","Portugal","2100-345",johnPhoneNumbers,personId,"Hamilton");

        //act
        ResponseEntity<Object> result = controller.addEmailToProfile(personId, info);

        //assert
        assertEquals(400, result.getStatusCodeValue());
    }

    @Test
    void addInvalidEmailToProfile() {

        //arrange
        String personId = "bones@gmail.com";
        String invalidEmailToAdd = "bonesTwogmail.com";
        InAddEmailDTO info = new InAddEmailDTO(invalidEmailToAdd);


        List<String> johnPhoneNumbers = new ArrayList<>();
        johnPhoneNumbers.add("912343546");
        FamilyAndAdminDTO dto = new FamilyAndAdminDTO("Margaret Hamilton","1904-12-01","213025086","14","Logical Street",
                "Porto","Portugal","2100-345",johnPhoneNumbers,personId,"Hamilton");
        //act
        ResponseEntity<Object> result = controller.addEmailToProfile(personId, info);

        //assert
        assertEquals(400, result.getStatusCodeValue());
    }


    @Test
    void addEmailToInvalidProfile() {

        //arrange
        String personId = null;
        String invalidEmailToAdd = "bonesTwogmail.com";

        InAddEmailDTO info = new InAddEmailDTO(invalidEmailToAdd);

        //act
        ResponseEntity<Object> result = controller.addEmailToProfile(personId, info);

        //assert
        assertEquals(400, result.getStatusCodeValue());

    }

    @Test
    void addRepeatedEmailToProfile() {

        //arrange
        String personId = "bonesTwo@gmail.com";
        String invalidEmailToAdd = "bonesTwo@gmail.com";
        InAddEmailDTO info = new InAddEmailDTO(invalidEmailToAdd);

        List<String> johnPhoneNumbers = new ArrayList<>();
        johnPhoneNumbers.add("912343546");
        FamilyAndAdminDTO dto = new FamilyAndAdminDTO("Margaret Hamilton","1904-12-01","213025086","14","Logical Street",
                "Porto","Portugal","2100-345",johnPhoneNumbers,personId,"Hamilton");


        //act
        ResponseEntity<Object> result = controller.addEmailToProfile(personId, info);
        //assert
        assertEquals(400, result.getStatusCodeValue());

    }
}
