package switchtwentytwenty.project.interfaceadaptor.implcontroller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.ICategoryService;
import switchtwentytwenty.project.dto.outdto.OutStandardCategoryDTO;
import switchtwentytwenty.project.interfaceadaptor.icontroller.IGetAllStandardCategoriesController;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class GetAllStandardCategoriesController implements IGetAllStandardCategoriesController {

    @Autowired
    private final ICategoryService categoryService;

    /**
     * Gets list of standard categories in the system.
     *
     * @return list with standard categories dtos
     */
    @GetMapping("/categories/standard/list")
    public ResponseEntity<Object> getAllStandardCategories() {

        List<OutStandardCategoryDTO> standardCategoriesDTOs = categoryService.getListOfAllStandardCategories();

        return new ResponseEntity<>(standardCategoriesDTOs, HttpStatus.OK);
    }
}

