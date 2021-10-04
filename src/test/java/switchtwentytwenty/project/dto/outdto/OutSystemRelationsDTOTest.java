package switchtwentytwenty.project.dto.outdto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.aggregate.category.Category;
import switchtwentytwenty.project.domain.aggregate.category.CategoryFactory;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.designation.CategoryDesignation;
import switchtwentytwenty.project.domain.share.id.CategoryID;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OutSystemRelationsDTOTest {

    @Test
    @DisplayName("Test to get System Relations List")
    void testGetter() {
        //arrange
        List<String> relationList = new ArrayList<>();
        relationList.add(Constants.PARENT);
        relationList.add(Constants.PARTNER);
        OutSystemRelationsDTO outSystemRelationsDTO = new OutSystemRelationsDTO(relationList);
        //act
        List<String> relationListResult = outSystemRelationsDTO.getSystemRelationsList();
        int expected = 2;
        int result = relationListResult.size();
        //assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test to string")
    void testToString() {
        //arrange
        List<String> relationList = new ArrayList<>();
        relationList.add(Constants.PARENT);
        relationList.add(Constants.PARTNER);
        OutSystemRelationsDTO relationListDTO = new OutSystemRelationsDTO(relationList);
        //act
        String relationListResult = relationListDTO.toString();
        String expected = "[parent, partner]";
        //assert
        assertEquals(expected, relationListResult);
    }

    @Test
    @DisplayName("HashCode test")
    void testEqualsHashCode() {
        //arrange
        List<String> relationList1 = new ArrayList<>();
        relationList1.add(Constants.PARENT);
        relationList1.add(Constants.PARTNER);

        List<String> relationList2 = new ArrayList<>();
        relationList2.add(Constants.PARENT);
        relationList2.add(Constants.PARTNER);

        List<String> relationList3 = new ArrayList<>();
        relationList3.add(Constants.NEPHEW);
        relationList3.add(Constants.PARTNER);

        //act
        OutSystemRelationsDTO relationListDTO1 = new OutSystemRelationsDTO(relationList1);
        OutSystemRelationsDTO relationListDTO2 = new OutSystemRelationsDTO(relationList2);
        OutSystemRelationsDTO relationListDTO3 = new OutSystemRelationsDTO(relationList3);

        //assert
        assertEquals(relationListDTO1, relationListDTO2);
        assertEquals(relationListDTO1.hashCode(), relationListDTO2.hashCode());
        assertNotEquals(relationListDTO1.hashCode(), relationListDTO3.hashCode());
    }

    @Test
    @DisplayName("Same OutSystemRelationsDTO")
    void sameOutSystemRelationsDTO() {
        //arrange
        List<String> relationList1 = new ArrayList<>();
        relationList1.add(Constants.PARENT);
        relationList1.add(Constants.PARTNER);
        List<String> relationList2 = new ArrayList<>();
        relationList2.add(Constants.PARENT);
        relationList2.add(Constants.PARTNER);
        //act
        OutSystemRelationsDTO relationListDTO1 = new OutSystemRelationsDTO(relationList1);
        OutSystemRelationsDTO relationListDTO2 = new OutSystemRelationsDTO(relationList2);
        //assert
        assertEquals(relationListDTO1, relationListDTO2);
    }

    @Test
    @DisplayName("Not same OutSystemRelationsDTO")
    void notSameOutSystemRelationsDTO() {
        //arrange
        List<String> relationList1 = new ArrayList<>();
        relationList1.add(Constants.PARENT);
        relationList1.add(Constants.SIBLING);
        List<String> relationList2 = new ArrayList<>();
        relationList2.add(Constants.SPOUSE);
        relationList2.add(Constants.SIBLING);
        //act
        OutSystemRelationsDTO relationListDTO1 = new OutSystemRelationsDTO(relationList1);
        OutSystemRelationsDTO relationListDTO2 = new OutSystemRelationsDTO(relationList2);
        //assert
        assertNotEquals(relationListDTO1, relationListDTO2);
    }

    @Test
    @DisplayName("Not equal objects")
    void outSystemRelationsDTONotEqualToCategory() {
        //arrange
        String id = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(id);
        CategoryDesignation categoryDesignation = new CategoryDesignation("Food");
        Category category = CategoryFactory.create(categoryDesignation, categoryID, null);
        List<String> relationList = new ArrayList<>();
        relationList.add(Constants.PARENT);
        relationList.add(Constants.SIBLING);
        OutSystemRelationsDTO relationListDTO1 = new OutSystemRelationsDTO(relationList);
        //act
        boolean result = relationListDTO1.equals(category);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Same instance")
    void sameInstance() {
        //arrange
        List<String> relationList = new ArrayList<>();
        relationList.add(Constants.PARENT);
        relationList.add(Constants.SIBLING);
        OutSystemRelationsDTO outSystemRelationsDTO = new OutSystemRelationsDTO(relationList);
        OutSystemRelationsDTO sameOutSystemRelationsDTO = outSystemRelationsDTO;
        //assert
        assertEquals(outSystemRelationsDTO, sameOutSystemRelationsDTO);
    }

    @Test
    @DisplayName("Create DTO with null")
    void createDTOWithNull() {
        //arrange
        List<String> emptyList = new ArrayList<>();
        OutSystemRelationsDTO expected = new OutSystemRelationsDTO(emptyList);
        //act
        OutSystemRelationsDTO result = new OutSystemRelationsDTO(null);
        //assert
        assertEquals(expected, result);
    }
}