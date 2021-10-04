
package switchtwentytwenty.project.interfaceadaptor.implcontroller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.applicationservice.appservice.implappservice.PersonService;
import switchtwentytwenty.project.dto.indto.InAddEmailDTO;
import switchtwentytwenty.project.dto.outdto.OutAddEmailDTO;
import switchtwentytwenty.project.exception.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddEmailToProfileControllerTest {

    @InjectMocks
    AddEmailToProfileController controller;
    @Mock
    PersonService personService;


    @Test
    void addEmailToProfile() throws Exception {

        String personIDString = "bones@gmail.com";
        String emailToAdd = "bonesTwo@gmail.com";

        InAddEmailDTO info = new InAddEmailDTO(emailToAdd);

        when(personService.addEmailToProfile(personIDString,emailToAdd)).thenReturn(Mockito.mock(OutAddEmailDTO.class));
        ResponseEntity<Object> result = controller.addEmailToProfile(personIDString,info);
        assertEquals(200,result.getStatusCodeValue());
    }

    @Test
    void addNoneEmailToProfile() throws Exception {

        String personIDString = "bones@gmail.com";
        String emailToAdd = " ";

        InAddEmailDTO info = new InAddEmailDTO(emailToAdd);

        doThrow(InvalidEmailException.class).when(personService).addEmailToProfile(personIDString,emailToAdd);
        ResponseEntity<Object> result = controller.addEmailToProfile(personIDString,info);
        assertEquals(400,result.getStatusCodeValue());
    }

    @Test
    void addInvalidEmailToProfile() throws Exception {

        String personIDString = "bones@gmail.com";
        String invalidEmailToAdd = "bonesTwogmail.com";
        InAddEmailDTO info = new InAddEmailDTO(invalidEmailToAdd);

        doThrow(InvalidEmailException.class).when(personService).addEmailToProfile(personIDString,invalidEmailToAdd);
        ResponseEntity<Object> result = controller.addEmailToProfile(personIDString,info);
        assertEquals(400,result.getStatusCodeValue());
    }


    @Test
    void addEmailToInvalidProfile() throws Exception {

        String personIDString = null;
        String invalidEmailToAdd = "bonesTwogmail.com";

        InAddEmailDTO info = new InAddEmailDTO(invalidEmailToAdd);

        doThrow(InvalidEmailException.class).when(personService).addEmailToProfile(personIDString,invalidEmailToAdd);
        ResponseEntity<Object> result = controller.addEmailToProfile(personIDString,info);
        assertEquals(400,result.getStatusCodeValue());

    }

    @Test
    void addRepeteadEmailToProfile() throws Exception {

        String personIDString = "bonesTwo@gmail.com";
        String invalidEmailToAdd = "bonesTwo@gmail.com";
        InAddEmailDTO info = new InAddEmailDTO(invalidEmailToAdd);
        doThrow(IllegalArgumentException.class).when(personService).addEmailToProfile(personIDString,invalidEmailToAdd);
        ResponseEntity<Object> result = controller.addEmailToProfile(personIDString,info);
        assertEquals(400,result.getStatusCodeValue());

    }
}
