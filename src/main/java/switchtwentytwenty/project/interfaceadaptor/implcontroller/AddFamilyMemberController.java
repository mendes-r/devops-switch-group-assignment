package switchtwentytwenty.project.interfaceadaptor.implcontroller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IFamilyAndMemberService;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.dto.outdto.OutPersonDTO;
import switchtwentytwenty.project.dto.toservicedto.PersonDTO;
import switchtwentytwenty.project.dto.indto.InPersonDTO;
import switchtwentytwenty.project.dto.toservicedto.PersonDTOMapper;
import switchtwentytwenty.project.exception.*;
import switchtwentytwenty.project.interfaceadaptor.icontroller.IAddFamilyMemberController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class AddFamilyMemberController implements IAddFamilyMemberController {

    // Attributes
    @Autowired
    private final IFamilyAndMemberService service;

    // Business Methods

    /**
     * Allows to add a new family Member.
     * @param info - InPersonDTO with person data.
     * @return An response entity that informs the operation success.
     */
    @PostMapping(Constants.URI_USERS)
    public ResponseEntity<Object> addFamilyMember(@RequestBody InPersonDTO info) {

        PersonDTO personDTO = PersonDTOMapper.mapToDTO(info);

        try {
            OutPersonDTO result = service.addFamilyMember(personDTO);
            Link linkToViewProfile = linkTo(methodOn(ViewProfileController.class)
                    .getUserProfile(result.getMainEmail()))
                    .withRel("view profile");
            Link linkToPersonRelations = linkTo(methodOn(ViewFamilyRelationsFromAPersonController.class)
                    .getFamilyRelationByPersonID(result.getMainEmail()))
                    .withRel("view my family relations");
            Link selfLink = linkTo(methodOn(AddFamilyMemberController.class)
                    .addFamilyMember(info))
                    .withSelfRel();
            result.add(selfLink);
            result.add(linkToViewProfile);
            result.add(linkToPersonRelations);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (IllegalArgumentException | InvalidDateException | InvalidVATException | InvalidEmailException | PersonAlreadyInSystemException | ElementNotFoundException | InvalidPersonNameException exception) {
            String errorMessage = "Error: " + exception.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }
}   
