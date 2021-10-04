package switchtwentytwenty.project.interfaceadaptor.icontroller;

import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.dto.indto.InAddEmailDTO;

public interface IAddEmailToProfileController {

     /**
      * * add email to person email's list
      * @param personID - personID
      * @param emailToInput - email added
      * @return response entity
      */
     ResponseEntity<Object> addEmailToProfile(String personID, InAddEmailDTO emailToInput);
}
