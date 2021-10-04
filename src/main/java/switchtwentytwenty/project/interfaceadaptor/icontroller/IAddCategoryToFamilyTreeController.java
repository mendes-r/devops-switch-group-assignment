package switchtwentytwenty.project.interfaceadaptor.icontroller;

import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.dto.indto.InCustomCategoryDTO;
import switchtwentytwenty.project.exception.InvalidDateException;

public interface IAddCategoryToFamilyTreeController {

    /**
     * Method that allows the user to create a new custom family.
     *
     * @return the category name if successfully or a message error otherwise
     */
    ResponseEntity<Object> addCategoryToFamilyTree(InCustomCategoryDTO dto,String familyID)throws InvalidDateException;


}
