package switchtwentytwenty.project.interfaceadaptor.implcontroller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IFamilyService;
import switchtwentytwenty.project.dto.outdto.OutFamilyDTO;
import switchtwentytwenty.project.interfaceadaptor.icontroller.IGetListOfFamiliesController;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class GetListOfFamiliesController implements IGetListOfFamiliesController {

    //Attributes
    @Autowired
    private final IFamilyService familyService;

    //Business Methods

    /**
     * Get list of all families
     *
     * @return list of families
     */
    @GetMapping(value = "/families")
   /* @PreAuthorize("hasRole('ADMIN')")*/
    public ResponseEntity<Object> getListOfFamilies() {
        try {
            List<OutFamilyDTO> familyList = familyService.getListOfFamilies();

            return new ResponseEntity<>(familyList, HttpStatus.OK);
        } catch (Exception exception) {
            String msg = exception.getMessage();
            return new ResponseEntity<>(msg, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
