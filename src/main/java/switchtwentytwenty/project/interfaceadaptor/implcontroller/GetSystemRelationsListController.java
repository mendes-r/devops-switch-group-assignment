package switchtwentytwenty.project.interfaceadaptor.implcontroller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IFamilyService;
import switchtwentytwenty.project.dto.outdto.OutSystemRelationsDTO;
import switchtwentytwenty.project.interfaceadaptor.icontroller.IGetSystemRelationsListController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class GetSystemRelationsListController implements IGetSystemRelationsListController {

    //Attributes

    @Autowired
    private final IFamilyService familyService;


    //Business Methods

    /**
     * Get family relation's from a person given his/her ID
     *
     * @return a family relation list
     */
    @GetMapping("/families/relations/")
    public ResponseEntity<Object> getSystemRelationsList() {

        OutSystemRelationsDTO outSystemRelationsDTO = familyService.getSystemRelations();

        Link linkToRelations = linkTo(methodOn(GetSystemRelationsListController.class).getSystemRelationsList()).withRel("view system relations");

        outSystemRelationsDTO.add(linkToRelations);

        return new ResponseEntity<>(outSystemRelationsDTO, HttpStatus.OK);
    }
}
