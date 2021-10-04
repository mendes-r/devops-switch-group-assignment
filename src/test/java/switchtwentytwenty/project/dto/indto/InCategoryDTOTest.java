package switchtwentytwenty.project.dto.indto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class InCategoryDTOTest {

    @Test
    @DisplayName("Create an InCategoryDTO with no parentID")
    void createInCategoryDTONoParentID() {
        //arrange
        InCategoryDTO dto = new InCategoryDTO();
        dto.setDesignation("Food");
        // act
        //assert
        assertNotNull(dto);
    }
    @Test
    @DisplayName("Create a in CategoryDTO with parentID")
    void createInCategoryDtoParentID() {
        //arrange
        InCategoryDTO dto = new InCategoryDTO();
        dto.setDesignation("Food");
        dto.setParentID(UUID.randomUUID().toString());
        // act
        //assert
        assertNotNull(dto);
    }

    @Test
    @DisplayName("Get category designation")
    void getDesignation() {
        //arrange
        InCategoryDTO dto= new InCategoryDTO();
        dto.setDesignation("Food");
        String expected = "Food";
        // act
        String result = dto.getDesignation();
        //assert
        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Get category's parentID")
    void getParentID() {
        //arrange
        InCategoryDTO dto= new InCategoryDTO();
        String parentID= UUID.randomUUID().toString();
        dto.setDesignation("Food");
        dto.setParentID(parentID);
        // act
        String result = dto.getParentID();
        //assert
        assertEquals(parentID,result);
    }



}