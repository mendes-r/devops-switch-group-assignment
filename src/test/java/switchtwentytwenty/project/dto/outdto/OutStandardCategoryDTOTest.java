package switchtwentytwenty.project.dto.outdto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OutStandardCategoryDTOTest {

    @Test
    @DisplayName("Create a OutStandardCategoryDTO successfully")
    void createOutStandardCategoryDTOSuccessfully(){
        //arrange
        String designation= "Food";
        String id = UUID.randomUUID().toString();
        String parentID =  UUID.randomUUID().toString();
        OutStandardCategoryDTO dto = new OutStandardCategoryDTO(designation,id,parentID);
        //assert
        assertNotNull(dto);
    }

    @Test
    @DisplayName("Get standard category's designation from dto")
    void getDesignation() {
        //arrange
        String designation= "Food";
        String id = UUID.randomUUID().toString();
        String parentID =  UUID.randomUUID().toString();
        OutStandardCategoryDTO dto = new OutStandardCategoryDTO(designation,id,parentID);

        //act
        String expected="Food";
        String result= dto.getDesignation();

        //assert
        assertEquals(expected,result);

    }

    @Test
    @DisplayName("Get Standard category's id from dto")
    void getID() {
        //arrange
        String designation= "Food";
        String id = UUID.randomUUID().toString();
        String parentID =  UUID.randomUUID().toString();
        OutStandardCategoryDTO dto = new OutStandardCategoryDTO(designation,id,parentID);

        //act
        String expected=parentID;
        String result= dto.getParentID();

        //assert
        assertEquals(expected,result);

    }

    @Test
    @DisplayName("Get Standard category's parent ID from dto")
    void getParentID() {
        //arrange
        String designation= "Food";
        String id = UUID.randomUUID().toString();
        String parentID =  UUID.randomUUID().toString();
        OutStandardCategoryDTO dto = new OutStandardCategoryDTO(designation,id,parentID);

        //act
        String expected=parentID;
        String result= dto.getParentID();

        //assert
        assertEquals(expected,result);

    }

    @Test
    @DisplayName("Test Equals: same dto")
    void testEqualsForTheSameDTO() {
        //arrange
        String designation= "Food";
        String id = UUID.randomUUID().toString();
        String parentID =  UUID.randomUUID().toString();
        OutStandardCategoryDTO dto = new OutStandardCategoryDTO(designation,id,parentID);
        OutStandardCategoryDTO dto2 = new OutStandardCategoryDTO(designation,id,parentID);

        // act
        boolean result = dto.equals(dto2);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test Equals: exact same dto")
    void testEqualsForTheExactSameDTO() {
        //arrange
        String designation= "Food";
        String id = UUID.randomUUID().toString();
        String parentID =  UUID.randomUUID().toString();
        OutStandardCategoryDTO dto = new OutStandardCategoryDTO(designation,id,parentID);

        // act
        boolean result = dto.equals(dto);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test Equals: different objects ")
    void testEqualsForDifferentObjects() {
        //arrange
        String designation= "Food";
        String id = UUID.randomUUID().toString();
        String parentID =  UUID.randomUUID().toString();
        OutStandardCategoryDTO dto = new OutStandardCategoryDTO(designation,id,parentID);
        BigDecimal numbers = new BigDecimal(10);
        // act
        boolean result = dto.equals(numbers);
        //assert
        assertFalse(result);
    }
    @Test
    @DisplayName("Test Equals: different OutStandardCategoryDTO objects- different category ")
    void testEqualsForDifferentDesignationDto() {
        //arrange
        String designationDto1= "Food";
        String designationDto2= "transportation";
        String id = UUID.randomUUID().toString();
        String parentID =  UUID.randomUUID().toString();

        OutStandardCategoryDTO dto = new OutStandardCategoryDTO(designationDto1,id,parentID);
        OutStandardCategoryDTO secondDto = new OutStandardCategoryDTO(designationDto2,id,parentID);
        // act
        boolean result = dto.equals(secondDto);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test Equals: different OutStandardCategoryDTO objects- different id ")
    void testEqualsForDifferentIdDto() {
        //arrange
        String designation= "Food";
        String id1 = UUID.randomUUID().toString();
        String id2 = UUID.randomUUID().toString();
        String parentID =  UUID.randomUUID().toString();

        OutStandardCategoryDTO dto = new OutStandardCategoryDTO(designation,id1,parentID);
        OutStandardCategoryDTO secondDto = new OutStandardCategoryDTO(designation,id2,parentID);
        // act
        boolean result = dto.equals(secondDto);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test Equals: different OutStandardCategoryDTO objects- different parent ids ")
    void testEqualsForDifferentParentIdsDto() {
        //arrange
        String designation= "Food";
        String id = UUID.randomUUID().toString();
        String parentId1 =  UUID.randomUUID().toString();
        String parentId2 =  UUID.randomUUID().toString();
        OutStandardCategoryDTO dto = new OutStandardCategoryDTO(designation,id,parentId1);
        OutStandardCategoryDTO secondDto = new OutStandardCategoryDTO(designation,id,parentId2);
        // act
        boolean result = dto.equals(secondDto);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test Hash Codes for similar dtos ")
    void testHashCodeForSimilarDtos() {
        //arrange
        String designation= "Food";
        String id = UUID.randomUUID().toString();
        String parentId =  UUID.randomUUID().toString();
        OutStandardCategoryDTO dto = new OutStandardCategoryDTO(designation,id,parentId);
        OutStandardCategoryDTO secondDto = new OutStandardCategoryDTO(designation,id,parentId);
        // act
        int firstHashCode = dto.hashCode();
        int secondHashCode = secondDto.hashCode();
        //assert
        assertEquals(firstHashCode,secondHashCode);
    }

    @Test
    @DisplayName("Test Hash Codes for different DTOs - different designation")
    void testHashCodeForDifferentDesignationDtos() {
        //arrange
        String designation1= "Food";
        String designation2= "Transport";
        String id = UUID.randomUUID().toString();
        String parentId =  UUID.randomUUID().toString();
        OutStandardCategoryDTO dto = new OutStandardCategoryDTO(designation1,id,parentId);
        OutStandardCategoryDTO secondDto = new OutStandardCategoryDTO(designation2,id,parentId);
        // act
        int firstHashCode = dto.hashCode();
        int secondHashCode = secondDto.hashCode();
        //assert
        assertNotEquals(firstHashCode,secondHashCode);
    }

    @Test
    @DisplayName("Test Hash Codes for different DTOs ")
    void testHashCodeForDTOsWithDifferentIds() {
        //arrange
        String designation= "Food";
        String id1 = UUID.randomUUID().toString();
        String id2 = UUID.randomUUID().toString();
        String parentId =  UUID.randomUUID().toString();
        OutStandardCategoryDTO dto = new OutStandardCategoryDTO(designation,id1,parentId);
        OutStandardCategoryDTO secondDto = new OutStandardCategoryDTO(designation,id2,parentId);
        // act
        int firstHashCode = dto.hashCode();
        int secondHashCode = secondDto.hashCode();
        //assert
        assertNotEquals(firstHashCode,secondHashCode);
    }
    @Test
    @DisplayName("Test Hash Codes for different DTOs ")
    void testHashCodeForDTOsWithDifferentParentIds() {
        //arrange
        String designation= "Food";
        String id = UUID.randomUUID().toString();
        String parentId1 =  UUID.randomUUID().toString();
        String parentId2 =  UUID.randomUUID().toString();
        OutStandardCategoryDTO dto = new OutStandardCategoryDTO(designation,id,parentId1);
        OutStandardCategoryDTO secondDto = new OutStandardCategoryDTO(designation,id,parentId2);
        // act
        int firstHashCode = dto.hashCode();
        int secondHashCode = secondDto.hashCode();
        //assert
        assertNotEquals(firstHashCode,secondHashCode);
    }

}