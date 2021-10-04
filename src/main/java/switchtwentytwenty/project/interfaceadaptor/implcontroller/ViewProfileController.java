package switchtwentytwenty.project.interfaceadaptor.implcontroller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import switchtwentytwenty.project.exception.BusinessErrorMessage;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IPersonService;
import switchtwentytwenty.project.dto.outdto.OutUserProfileDTO;
import switchtwentytwenty.project.exception.*;
import switchtwentytwenty.project.interfaceadaptor.icontroller.IViewProfileController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class ViewProfileController implements IViewProfileController {

    //Attributes
    @Autowired
    private final IPersonService personService;

    //Business Methods

    /**
     * Method that adds all the information about a user to a DTO.
     *
     * @param personId - person's email
     * @return a DTO with the user's profile if successfully, or a message error otherwise
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<Object> getUserProfile(@PathVariable("id") String personId) {
        try {
            OutUserProfileDTO profile = personService.getUserProfile(personId);

            Link linkToUsers = linkTo(methodOn(ViewProfileController.class).getUserProfile(personId)).withRel("view profile");
            Link linkToRelations = linkTo(methodOn(ViewFamilyRelationsFromAPersonController.class).getFamilyRelationByPersonID(personId)).withRel("view family relations");

            profile.add(linkToUsers);
            profile.add(linkToRelations);

            return new ResponseEntity<>(profile, HttpStatus.OK);

        } catch (InvalidEmailException | InvalidDateException | InvalidPersonNameException | InvalidVATException | ElementNotFoundException exception) {
            BusinessErrorMessage msg = new BusinessErrorMessage(exception.getMessage(), BusinessErrorMessage.GROUP_DESCRIPTION_NOT_FOUND);
            return new ResponseEntity<>(msg, HttpStatus.UNPROCESSABLE_ENTITY);
        }


    }


}


