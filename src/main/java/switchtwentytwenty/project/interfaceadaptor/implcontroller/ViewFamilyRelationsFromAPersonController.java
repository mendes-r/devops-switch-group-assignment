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
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IFamilyAndMemberService;
import switchtwentytwenty.project.dto.outdto.OutViewRelationDTO;
import switchtwentytwenty.project.exception.*;
import switchtwentytwenty.project.interfaceadaptor.icontroller.IViewFamilyRelationsFromAPerson;

import java.io.IOException;
import java.text.ParseException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class ViewFamilyRelationsFromAPersonController implements IViewFamilyRelationsFromAPerson {

    //Attributes

    @Autowired
    private final IFamilyAndMemberService familyAndMemberService;


    //Business Methods

    /**
     * Get family relation's from a person given his/her ID
     *
     * @param personID - person ID
     * @return a family relation list
     */
    @GetMapping("/families/{familyID}/relations/{id}")
    public ResponseEntity<Object> getFamilyRelationByPersonID(@PathVariable("id") String personID) {

        try {
            OutViewRelationDTO familyRelationsFromAPerson = familyAndMemberService.getFamilyRelationByPersonID(personID);

            Link linkToRelations = linkTo(methodOn(ViewFamilyRelationsFromAPersonController.class).getFamilyRelationByPersonID(personID)).withRel("view family relations");
            Link linkToViewProfile = linkTo(methodOn(ViewProfileController.class).getUserProfile(personID)).withRel("view profile");

            familyRelationsFromAPerson.add(linkToRelations);
            familyRelationsFromAPerson.add(linkToViewProfile);

            return new ResponseEntity<>(familyRelationsFromAPerson, HttpStatus.OK);

        } catch (IllegalArgumentException | AccountNotCreatedException | InstantiationException |InvalidMovementTypeException | NullPointerException | InvalidEmailException | ParseException | ElementNotFoundException | InvalidDateException | InvalidVATException | InvalidPersonNameException | IOException | InvalidRelationTypeException exception) {
            BusinessErrorMessage msg = new BusinessErrorMessage(exception.getMessage(), BusinessErrorMessage.GROUP_DESCRIPTION_NOT_FOUND);

            return new ResponseEntity<>(msg, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
