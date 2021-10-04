package switchtwentytwenty.project.dto.outdto;

import org.springframework.hateoas.Link;
import switchtwentytwenty.project.domain.aggregate.category.Category;
import switchtwentytwenty.project.dto.outdto.visitor.AddCategorySelfLinkVisitor;
import switchtwentytwenty.project.dto.outdto.visitor.AddCategoryStandardLinkVisitor;
import switchtwentytwenty.project.exception.ElementNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class OutCategoryDTOMapper {

    //Constructor Methods

    /**
     * Sole constructor.
     */
    private OutCategoryDTOMapper() {}


    //Business Methods

    /**
     * Transaction list to DTO list.
     *
     * @param categoryList - category list
     * @return a Collection of OutCategoryDTO
     */
    public static List<OutCategoryDTO> toDTOList(List<Category> categoryList) {
        List<OutCategoryDTO> dtoList = new ArrayList<>();
        for (Category category : categoryList) {
            OutCategoryDTO dto = toDTO(category);
            dtoList.add(dto);
        }
        return dtoList;
    }

    /**
     * Category to OUTCategoryDTO.
     *
     * @param category - a category instance
     * @return dto
     */
    public static OutCategoryDTO toDTO(Category category) {
        String id = category.getID().toString();
        String designation = category.getDesignation().toString();
        String parentID=null;
        if(category.getParentID()!=null) {
            parentID = category.getParentID().toString();
        }
        OutCategoryDTO outCategoryDTO = new OutCategoryDTO(id, designation,parentID);

        try {
            Link selfLink = outCategoryDTO.accept(new AddCategorySelfLinkVisitor());
            Link standardLink = outCategoryDTO.accept(new AddCategoryStandardLinkVisitor());
            outCategoryDTO.add(selfLink);
            outCategoryDTO.add(standardLink);
        } catch (ElementNotFoundException exception) {
            exception.printStackTrace();
        }
        return outCategoryDTO;
    }


}
