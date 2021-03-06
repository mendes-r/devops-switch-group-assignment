package switchtwentytwenty.project.dto.todomaindto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import switchtwentytwenty.project.domain.share.designation.CategoryDesignation;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.domain.share.id.FamilyID;

@AllArgsConstructor
public class VOCategoryDTO {

    //Attribute
    @Getter
    private CategoryID id;
    @Getter
    private CategoryID parentID;
    @Getter
    private CategoryDesignation designation;
    @Getter
    private FamilyID familyID;

    /**
     * Standard Category constructor.
     *
     * @param id          - category id
     * @param designation - category designation
     * @param parentID    - of the category
     */
    public VOCategoryDTO(CategoryID id, CategoryID parentID, CategoryDesignation designation) {
        this.id = id;
        this.designation = designation;
        this.parentID = parentID;
    }


}
