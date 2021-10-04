package switchtwentytwenty.project.dto.outdto.visitor;

import org.springframework.hateoas.Link;
import switchtwentytwenty.project.dto.outdto.OutCategoryDTO;
import switchtwentytwenty.project.exception.ElementNotFoundException;

public interface IAddLinkVisitor {

    Link visit(OutCategoryDTO dto) throws ElementNotFoundException;
}
