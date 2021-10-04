package switchtwentytwenty.project.dto.toservicedto;

import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.dto.indto.InCustomCategoryDTO;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CustomCategoryDTOMapperTest {

    @Test
    void customCategoryDTOMapper() {
        //arrange
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        String famID = familyID.toString();
        CategoryID parentID = new CategoryID(UUID.randomUUID().toString());
        InCustomCategoryDTO custom = new InCustomCategoryDTO("Gas", parentID.toString());

        //act
        CustomCategoryDTO customCategoryDTO = CustomCategoryDTOMapper.toDTO(custom,famID);

        //assert
        assertEquals(customCategoryDTO.getDesignation(), custom.getDesignation());
        assertEquals(customCategoryDTO.getParentID(), custom.getParentID());
        assertEquals(customCategoryDTO.getFamilyID(),famID);
        assertNotNull(customCategoryDTO);
    }
}
