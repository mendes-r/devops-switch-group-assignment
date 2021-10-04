package switchtwentytwenty.project.interfaceadaptor.implcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import switchtwentytwenty.project.exception.BusinessErrorMessage;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IFamilyService;
import switchtwentytwenty.project.dto.outdto.OutFamilyProfileDTO;
import switchtwentytwenty.project.exception.ElementNotFoundException;
import switchtwentytwenty.project.exception.InvalidEmailException;
import switchtwentytwenty.project.exception.InvalidRelationTypeException;
import switchtwentytwenty.project.interfaceadaptor.icontroller.IGetFamilyProfileController;
import java.io.IOException;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class GetFamilyProfileController implements IGetFamilyProfileController {

    //Attributes
    @Autowired
    private IFamilyService familyService;

    /**
     * Get Family Profile information
     *
     * @param familyID - family ID
     * @return family profile: name, registration date, administratorID, administrator name.
     */
    @GetMapping("/families/{familyID}")
    public ResponseEntity<Object> getFamilyProfile(@PathVariable("familyID") String familyID) {
        try {
            OutFamilyProfileDTO familyProfileDTO = familyService.getFamilyProfile(familyID);
            String adminID = familyProfileDTO.getAdministratorID();
            Link adminProfile = linkTo(methodOn(ViewProfileController.class).getUserProfile(adminID)).withRel("admin profile");
            familyProfileDTO.add(adminProfile);
            return new ResponseEntity<>(familyProfileDTO, HttpStatus.OK);
        } catch (IOException | ElementNotFoundException | InvalidEmailException | InvalidRelationTypeException e) {

            BusinessErrorMessage msg = new BusinessErrorMessage(e.getMessage(), BusinessErrorMessage.GROUP_DESCRIPTION_NOT_FOUND);

            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
    }
}
