package switchtwentytwenty.project.applicationservice.appservice.iappservice;

import switchtwentytwenty.project.dto.outdto.OutCategoryTreeDTO;
import switchtwentytwenty.project.dto.outdto.OutCustomCategoryDTO;
import switchtwentytwenty.project.dto.outdto.OutStandardCategoryDTO;
import switchtwentytwenty.project.dto.outdto.OutCategoryDTO;
import switchtwentytwenty.project.dto.toservicedto.CustomCategoryDTO;
import switchtwentytwenty.project.exception.*;

import java.util.List;

public interface ICategoryService {
    /**
     * Create a root standard category
     *
     * @param designation - designation
     * @throws DesignationNotPossibleException
     */
    OutStandardCategoryDTO createRootStandardCategory(String designation) throws DesignationNotPossibleException;

    /**
     * Create a child standard category
     *
     * @param designation - designation
     * @param parentID    category parent ID
     * @throws ElementNotFoundException
     * @throws DesignationNotPossibleException
     */
    OutStandardCategoryDTO createChildStandardCategory(String designation, String parentID) throws ElementNotFoundException, DesignationNotPossibleException;

    /**
     * Creates a root custom category.
     *
     * @param dto - custom category dto
     * @return custom category DTO
     * @throws DesignationNotPossibleException if the designation already exists
     */
    OutCustomCategoryDTO createRootCustomCategory(CustomCategoryDTO dto) throws DesignationNotPossibleException, NullPointerException, IllegalArgumentException;


    /**
     * Creates a child custom category.
     *
     * @param dto - custom category dto
     * @return a custom category DTO
     * @throws ElementNotFoundException        if the ID of the parent doesn't exist
     * @throws DesignationNotPossibleException if the designation already exists
     */
    OutCustomCategoryDTO createChildCustomCategory(CustomCategoryDTO dto) throws DesignationNotPossibleException, NullPointerException, IllegalArgumentException;


    /**
     * Iterates over the list of standard categories where parent is null,creates a tree and adds it to a new list of trees.
     *
     * @return list with all the category trees dtos of the system
     */
    List<OutCategoryTreeDTO> getStandardCategoriesTree();

    /**
     * Get list of the categories on the family’s category tree.
     *
     * @param familyID - family identifier
     * @return list of categories
     */
    List<OutCategoryDTO> getListOfFamilyCategories(String familyID);

    /**
     * Gets all the standard categories in the system and adds them to a new list.
     *
     * @return list with standard categories DTOs
     */
    List<OutStandardCategoryDTO> getListOfStandardCategories();

    /**
     * Get all the standard categories in the system and all the standard category from the external source.
     *
     * @return list with standard categories DTOs
     */
    List<OutStandardCategoryDTO> getListOfAllStandardCategories();

    /**
     * Get category by identifier.
     *
     * @param categoryID category identifier
     * @return category dto
     */
    OutCategoryDTO getCategoryByID(String categoryID) throws ElementNotFoundException;
}



