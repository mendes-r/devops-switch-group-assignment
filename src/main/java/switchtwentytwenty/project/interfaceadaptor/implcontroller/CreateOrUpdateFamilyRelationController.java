package switchtwentytwenty.project.interfaceadaptor.implcontroller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IFamilyAndMemberService;
import switchtwentytwenty.project.dto.indto.InFamilyRelationDTO;
import switchtwentytwenty.project.dto.outdto.OutFamilyRelationDTO;
import switchtwentytwenty.project.exception.*;
import switchtwentytwenty.project.interfaceadaptor.icontroller.ICreateOrUpdateFamilyRelationController;

import java.io.IOException;
import java.text.ParseException;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class CreateOrUpdateFamilyRelationController implements ICreateOrUpdateFamilyRelationController {


    //Attributes

    @Autowired
    private final IFamilyAndMemberService familyAndMemberService;


    //Business Methods

    /**
     * Creates a family relation between two persons.
     *
     * @param familyIdentifier - family identifier
     * @param info - json dto
     * @return HTTP response
     */
    @PostMapping(value = "families/{familyID}/relations")
    public ResponseEntity<Object> createOrUpdateFamilyRelation(@PathVariable(value = "familyID") String familyIdentifier, @RequestBody InFamilyRelationDTO info) throws ElementNotFoundException, InvalidDateException {

        String personEmail = info.getPersonEmail();
        String kinEmail = info.getKinEmail();
        String relationTypeName = info.getRelationType();

        try {
            Optional<OutFamilyRelationDTO> optional = familyAndMemberService.createFamilyRelation(personEmail, kinEmail, familyIdentifier, relationTypeName);
            if (optional.isPresent()) {

                Link selfLink = linkTo(methodOn(CreateOrUpdateFamilyRelationController.class).createOrUpdateFamilyRelation(familyIdentifier, info)).withRel("self");
                Link personLink = linkTo(methodOn(ViewFamilyRelationsFromAPersonController.class).getFamilyRelationByPersonID(personEmail)).withRel("relations");
                Link kinLink = linkTo(methodOn(ViewFamilyRelationsFromAPersonController.class).getFamilyRelationByPersonID(kinEmail)).withRel("relations");
                Link relationsLink = linkTo(methodOn(GetSystemRelationsListController.class).getSystemRelationsList()).withRel("system valid relations");

                OutFamilyRelationDTO dto = optional.get();
                dto.add(selfLink);
                dto.add(personLink);
                dto.add(kinLink);
                dto.add(relationsLink);

                return new ResponseEntity<>(dto, HttpStatus.CREATED);
            }
            return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);

        } catch ( AccountNotCreatedException | InstantiationException | ParseException | InvalidMovementTypeException |InvalidPersonNameException | InvalidVATException | InvalidRelationTypeException | IOException | InvalidEmailException | IllegalArgumentException exception) {
            String msg = exception.getMessage();
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
    }

}
