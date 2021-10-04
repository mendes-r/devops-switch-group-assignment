package switchtwentytwenty.project.domain.aggregate.category;

import switchtwentytwenty.project.domain.share.designation.CategoryDesignation;
import switchtwentytwenty.project.domain.share.designation.Designation;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.dto.todomaindto.VOCategoryDTO;

public class CategoryFactory {

    /**
     * Private constructor.
     */
    private CategoryFactory() {
    }

    /**
     * Method that receives a DTO and creates a domain object.
     *
     * @param voCategoryDTO - DTO with the jpa object
     * @return - Category object
     */
    public static Category create(VOCategoryDTO voCategoryDTO) {
        CategoryID id = voCategoryDTO.getId();
        CategoryID parentID = voCategoryDTO.getParentID();
        CategoryDesignation designation = voCategoryDTO.getDesignation();
        FamilyID familyID = voCategoryDTO.getFamilyID();
        Category category;

        if (familyID == null) {
            category = new Standard(designation, id, parentID);
        } else {
            category = new Custom(designation, id, parentID, familyID);
        }
        return category;
    }

    /**
     * Creates a domain object .
     *
     * @param designation of the category
     * @param parentID    of the category
     * @param id          of the category
     * @return a domain object
     */
    public static Category create(Designation designation, CategoryID id, CategoryID parentID) {
        return new Standard(designation, id, parentID);
    }

    /**
     * Creates a domain object.
     *
     * @param id          of the category
     * @param designation of the category
     * @param parentID    of the category
     * @param familyID    of the category
     * @return a domain object
     */
    public static Category create(Designation designation, CategoryID id, CategoryID parentID, FamilyID familyID) {
        return new Custom(designation, id, parentID, familyID);
    }
}
