package switchtwentytwenty.project.interfaceadaptor.implcontroller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import switchtwentytwenty.project.exception.BusinessErrorMessage;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IFamilyAndMemberService;
import switchtwentytwenty.project.dto.toservicedto.FamilyAndAdminDTO;
import switchtwentytwenty.project.dto.toservicedto.FamilyAndAdminDTOMapper;
import switchtwentytwenty.project.dto.outdto.OutFamilyAndAdminDTO;
import switchtwentytwenty.project.dto.indto.InFamilyAndAdminDTO;
import switchtwentytwenty.project.exception.*;
import switchtwentytwenty.project.interfaceadaptor.icontroller.ICreateFamilyAndItsAdministratorController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

//@Controller             //springboot component
@RestController
@AllArgsConstructor     //Constructor method with all class arguments (lombock)
@CrossOrigin(origins = "*", maxAge = 3600)
public class CreateFamilyAndItsAdministratorController implements ICreateFamilyAndItsAdministratorController {

    //Attributes
    @Autowired
    private final IFamilyAndMemberService familyAndMemberService;

    //Constructor Method

    //Business Method

    /**
     * Start family and create administrator
     * @param info - family and admin information
     * @return family and admin information and links
     */
    @PostMapping(value = "/families")
    public ResponseEntity<Object> startFamily(@RequestBody InFamilyAndAdminDTO info) {
        try {

            FamilyAndAdminDTO familyAndAdminDTO = FamilyAndAdminDTOMapper.mapToDTO(info);

            OutFamilyAndAdminDTO result = this.familyAndMemberService.startFamily(familyAndAdminDTO);

            Link selfLink = linkTo(methodOn(CreateFamilyAndItsAdministratorController.class).startFamily(info)).withRel("self");

            result.add(selfLink);
            return new ResponseEntity<>(result, HttpStatus.CREATED);

        } catch (PersonAlreadyInSystemException | NullPointerException | InvalidDateException | InvalidEmailException
                | InvalidPersonNameException | InvalidVATException | IllegalArgumentException e) {

            BusinessErrorMessage msg = new BusinessErrorMessage(e.getMessage(), BusinessErrorMessage.GROUP_DESCRIPTION_NOT_FOUND);

            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST); //http status -> ver qual melhor code.
        }
    }
}
