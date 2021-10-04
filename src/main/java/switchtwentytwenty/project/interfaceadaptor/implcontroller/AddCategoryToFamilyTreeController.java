
package switchtwentytwenty.project.interfaceadaptor.implcontroller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switchtwentytwenty.project.exception.BusinessErrorMessage;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.ICategoryService;
import switchtwentytwenty.project.dto.toservicedto.CustomCategoryDTO;
import switchtwentytwenty.project.dto.indto.InCustomCategoryDTO;
import switchtwentytwenty.project.dto.outdto.OutCustomCategoryDTO;
import switchtwentytwenty.project.dto.toservicedto.CustomCategoryDTOMapper;
import switchtwentytwenty.project.exception.*;
import switchtwentytwenty.project.interfaceadaptor.icontroller.IAddCategoryToFamilyTreeController;


@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class AddCategoryToFamilyTreeController implements IAddCategoryToFamilyTreeController {

    @Autowired
    private final ICategoryService categoryService;


    /**
     * Method that allows the user to create a new custom family.
     *
     * @return the category name if successfully or a message error otherwise
     */
    @PostMapping(value = "/categories/custom/{familyID}")
    public ResponseEntity<Object> addCategoryToFamilyTree(@RequestBody InCustomCategoryDTO info, @PathVariable String familyID)  {
        try {
            CustomCategoryDTO customCategoryDTO = CustomCategoryDTOMapper.toDTO(info,familyID);
            OutCustomCategoryDTO result;
            String parentID = info.getParentID();
            if (parentID == null) {
                result = this.categoryService.createRootCustomCategory(customCategoryDTO);
            } else {
                result = this.categoryService.createChildCustomCategory(customCategoryDTO);
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (IllegalArgumentException | NullPointerException | DesignationNotPossibleException exception) {
            BusinessErrorMessage msg = new BusinessErrorMessage(exception.getMessage(), BusinessErrorMessage.GROUP_DESCRIPTION_NOT_FOUND);
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
    }
}

