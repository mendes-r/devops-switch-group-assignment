package switchtwentytwenty.project.interfaceadaptor.implcontroller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switchtwentytwenty.project.exception.BusinessErrorMessage;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IPersonService;
import switchtwentytwenty.project.dto.indto.InAddEmailDTO;
import switchtwentytwenty.project.dto.outdto.OutAddEmailDTO;
import switchtwentytwenty.project.exception.*;
import switchtwentytwenty.project.interfaceadaptor.icontroller.IAddEmailToProfileController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class AddEmailToProfileController implements IAddEmailToProfileController {

    // Attributes

    @Autowired
    private final IPersonService personService;


    // Business Methods

    /**
     * Allows to add an email address to a Person Profile
     *
     * @param personId - id of person used to identify the person that is going to add an email to his profile
     * @param info     - Dto with person data
     * @return true if the email is added with success
     */
    @PostMapping(value = "/users/{personId}/email/")
    public ResponseEntity<Object> addEmailToProfile(@PathVariable String personId, @RequestBody InAddEmailDTO info) {
        try {
            OutAddEmailDTO result = personService.addEmailToProfile(personId, info.getEmailToAdd());
            Link linkToProfile = linkTo(methodOn(ViewProfileController.class)
                    .getUserProfile(result.getPersonID()))
                    .withRel("View User Profile");

            result.add(linkToProfile);

            return new ResponseEntity<>(result, HttpStatus.OK);

        } catch (IllegalArgumentException | NullPointerException | InvalidDateException | InvalidPersonNameException | InvalidVATException | InvalidEmailException | ElementNotFoundException exception) {
            BusinessErrorMessage msg = new BusinessErrorMessage(exception.getMessage(), BusinessErrorMessage.GROUP_DESCRIPTION_NOT_FOUND);
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }

    }
}
