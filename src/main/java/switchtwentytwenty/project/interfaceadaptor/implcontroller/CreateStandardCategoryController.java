package switchtwentytwenty.project.interfaceadaptor.implcontroller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.ICategoryService;
import switchtwentytwenty.project.dto.indto.InCategoryDTO;
import switchtwentytwenty.project.dto.outdto.OutStandardCategoryDTO;
import switchtwentytwenty.project.exception.DesignationNotPossibleException;
import switchtwentytwenty.project.exception.ElementNotFoundException;
import switchtwentytwenty.project.interfaceadaptor.icontroller.ICreateStandardCategoryController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class CreateStandardCategoryController implements ICreateStandardCategoryController {
    @Autowired
    private final ICategoryService categoryService;

    /**
     * Allows the system manager to create a standard category
     * @param info entrance category DTO
     * @return created if successful, bad request otherwise
     */
    @PostMapping(value = "/categories/standard")
    public ResponseEntity<Object> createStandardCategory(@RequestBody InCategoryDTO info) {
        try {
            OutStandardCategoryDTO result;
            String parentID = info.getParentID();
            if (parentID == null) {
                result = this.categoryService.createRootStandardCategory(info.getDesignation());
            } else {
                result = this.categoryService.createChildStandardCategory(info.getDesignation(), info.getParentID());
            }

            Link linkToTree = linkTo(GetStandardCategoryTreeController.class)
                    .slash("categories").withRel("standard categories");

            result.add(linkToTree);

            return new ResponseEntity<>(result, HttpStatus.CREATED);

        } catch (DesignationNotPossibleException | ElementNotFoundException | IllegalArgumentException | NullPointerException exception) {
            String errorMessage = "Error: " + exception.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }


}
