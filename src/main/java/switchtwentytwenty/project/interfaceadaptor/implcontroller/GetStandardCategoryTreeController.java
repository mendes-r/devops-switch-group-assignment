package switchtwentytwenty.project.interfaceadaptor.implcontroller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.ICategoryService;
import switchtwentytwenty.project.dto.outdto.OutCategoryTreeDTO;
import switchtwentytwenty.project.interfaceadaptor.icontroller.IGetStandardCategoryTreeController;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class GetStandardCategoryTreeController implements IGetStandardCategoryTreeController {


    //Attributes
    @Autowired
    private final ICategoryService categoryService;

    //Business Methods

    /**
     * Allows the user to observe an existing standard category tree.
     *
     * @return standard category tree if successful, bad request otherwise
     */
    @GetMapping("/categories/standard/tree")
    public ResponseEntity<Object> getStandardCategoryTree() {

        List<OutCategoryTreeDTO> standardOutCategoryTreeDTO = categoryService.getStandardCategoriesTree();

        return new ResponseEntity<>(standardOutCategoryTreeDTO, HttpStatus.OK);

    }


}
