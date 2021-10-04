package switchtwentytwenty.project.interfaceadaptor.implcontroller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.ICategoryService;
import switchtwentytwenty.project.dto.outdto.OutStandardCategoryDTO;
import switchtwentytwenty.project.exception.ElementNotFoundException;
import switchtwentytwenty.project.interfaceadaptor.icontroller.IGetListOfStandardCategoriesController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class GetListOfStandardCategoriesController implements IGetListOfStandardCategoriesController {

    @Autowired
    private final ICategoryService categoryService;

    /**
     * Gets list of standard categories in the system.
     *
     * @return list with standard categories dtos
     */
    @GetMapping("/categories/standard")
    public ResponseEntity<Object> getListOfStandardCategories() throws ElementNotFoundException {

        List<OutStandardCategoryDTO> standardCategoriesDTOs = categoryService.getListOfStandardCategories();

        Link standardCategories = linkTo(methodOn(GetListOfStandardCategoriesController.class).getListOfStandardCategories()).withRel("View List of Standard Categories");

        for (OutStandardCategoryDTO outStandardCategoryDTO : standardCategoriesDTOs) {
            outStandardCategoryDTO.add(standardCategories);
            Link selfLink = linkTo(methodOn(GetCategoryByIDController.class).getCategoryByID(outStandardCategoryDTO.getId())).withRel("self link");
            outStandardCategoryDTO.add(selfLink);
        }
        return new ResponseEntity<>(standardCategoriesDTOs, HttpStatus.OK);

    }

}
