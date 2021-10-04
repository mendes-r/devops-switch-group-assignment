package switchtwentytwenty.project.dto.toservicedto;

import switchtwentytwenty.project.dto.indto.InCustomCategoryDTO;

public class CustomCategoryDTOMapper {

    /**
     * Sole constructor.
     */
    private CustomCategoryDTOMapper(){}

    /**
     * Map to Custom Category DTO.
     *
     * @param inDTO - InCustomCategoryDTO, is the DTO that controller receives
     * @param familyID - family id of the category
     * @return a new CustomCategoryDTO
     */
    public static CustomCategoryDTO toDTO(InCustomCategoryDTO inDTO, String familyID) {
        return new CustomCategoryDTO(inDTO.getDesignation(), inDTO.getParentID(),familyID);
    }

}
