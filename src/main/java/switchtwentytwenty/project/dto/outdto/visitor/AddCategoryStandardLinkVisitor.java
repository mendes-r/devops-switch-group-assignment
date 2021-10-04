package switchtwentytwenty.project.dto.outdto.visitor;

import org.springframework.hateoas.Link;
import switchtwentytwenty.project.dto.outdto.OutCategoryDTO;
import switchtwentytwenty.project.exception.ElementNotFoundException;
import switchtwentytwenty.project.interfaceadaptor.implcontroller.GetListOfStandardCategoriesController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class AddCategoryStandardLinkVisitor implements IAddLinkVisitor {

    @Override
    public Link visit(OutCategoryDTO dto) throws ElementNotFoundException {
        return linkTo(methodOn(GetListOfStandardCategoriesController.class).getListOfStandardCategories()).withRel("system's standard categories");
    }

}
