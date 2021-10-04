package switchtwentytwenty.project.interfaceadaptor.implcontroller;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IPersonService;
import switchtwentytwenty.project.dto.outdto.OutPersonDTO;
import switchtwentytwenty.project.interfaceadaptor.icontroller.IGetListOfFamilyMembersController;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class GetListOfFamilyMembersController implements IGetListOfFamilyMembersController {

    //Attributes
    @Autowired
    private final IPersonService personService;


    //Business Methods

    /**
     * Get list of the family members of a certain family.
     *
     * @param familyID - family ID
     * @return a list with the family members
     */
    @GetMapping(value = "/users")
    public ResponseEntity<Object> getListOfFamilyMembers(@RequestParam(name = "familyID") String familyID) {
        try {
            List<OutPersonDTO> familyMembers = personService.getListOfFamilyMembers(familyID);

            return new ResponseEntity<>(familyMembers, HttpStatus.OK);
        } catch (Exception exception) {
            String msg = exception.getMessage();
            return new ResponseEntity<>(msg, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
