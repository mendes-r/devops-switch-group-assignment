package switchtwentytwenty.project.dto.outdto;

import switchtwentytwenty.project.domain.aggregate.category.Category;
import switchtwentytwenty.project.dto.toservicedto.StandardCategoryDTO;

import java.util.ArrayList;
import java.util.List;

public class OutStandardCategoryDTOMapper {

    /**
     * Sole Constructor.
     */
    private OutStandardCategoryDTOMapper() {
    }


    /**
     * From Domain to Data Transfer Object.
     *
     * @param standard category
     * @return - out standard category dto
     */
    public static OutStandardCategoryDTO toDTO(Category standard) {
        String designation = standard.getDesignation().toString();
        String id = standard.getID().toString();
        if (standard.getParentID() != null) {
            String parentID = standard.getParentID().toString();
            return new OutStandardCategoryDTO(designation, id, parentID);
        }
        return new OutStandardCategoryDTO(designation, id, null);
    }

    /**
     * From OutStandardCategoryDTO to OutCategoryDTO.
     *
     * @param standardCategoryDTO outStandardCategoryDTO instance
     * @return OutCategoryDTO instance
     */
    public static OutCategoryDTO toOutCategoryDTO(OutStandardCategoryDTO standardCategoryDTO) {
        return new OutCategoryDTO(standardCategoryDTO.getId(), standardCategoryDTO.getDesignation(),standardCategoryDTO.getParentID());
    }

    /**
     * From OutStandardCategoryDTO list to OutCategoryDTO list.
     *
     * @param standardCategoryDTOList list of outStandardCategoryDTO instance
     * @return list of OutCategoryDTO instance
     */
    public static List<OutCategoryDTO> toOutCategoryDTOList(List<OutStandardCategoryDTO> standardCategoryDTOList) {
        List<OutCategoryDTO> dtoList = new ArrayList<>();
        for (OutStandardCategoryDTO outStandardCategoryDTO : standardCategoryDTOList) {
            dtoList.add(toOutCategoryDTO(outStandardCategoryDTO));
        }
        return dtoList;
    }

    /**
     * From StandardCategoryDTO list to OutStandardCategoryDTO list
     *
     * @param standardList of standard category DTO's
     * @return list with Out Standard Category DTO's
     */
    public static List<OutStandardCategoryDTO> toOutStandardCategoryDTOList(List<StandardCategoryDTO> standardList) {
        List<OutStandardCategoryDTO> dtoList = new ArrayList<>();
        for (StandardCategoryDTO standardCategoryDTO : standardList) {
            OutStandardCategoryDTO outStandardCategoryDTO = new OutStandardCategoryDTO(standardCategoryDTO.getDesignation(), standardCategoryDTO.getId(), standardCategoryDTO.getParentID());
            dtoList.add(outStandardCategoryDTO);
        }
        return dtoList;
    }

}
