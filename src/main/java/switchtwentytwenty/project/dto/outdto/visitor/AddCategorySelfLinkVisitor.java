package switchtwentytwenty.project.dto.outdto.visitor;

import lombok.NoArgsConstructor;
import org.springframework.hateoas.Link;
import switchtwentytwenty.project.dto.outdto.OutCategoryDTO;
import switchtwentytwenty.project.exception.ElementNotFoundException;
import switchtwentytwenty.project.interfaceadaptor.implcontroller.GetCategoryByIDController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@NoArgsConstructor
public class AddCategorySelfLinkVisitor implements IAddLinkVisitor {

    @Override
    public Link visit(OutCategoryDTO dto) throws ElementNotFoundException {
        return linkTo(methodOn(GetCategoryByIDController.class).getCategoryByID(dto.getId())).withRel("self link");
    }

}
