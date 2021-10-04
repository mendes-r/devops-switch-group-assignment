package switchtwentytwenty.project.dto.outdto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.aggregate.category.Category;
import switchtwentytwenty.project.domain.aggregate.category.CategoryFactory;
import switchtwentytwenty.project.domain.share.designation.CategoryDesignation;
import switchtwentytwenty.project.domain.share.id.CategoryID;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OutViewRelationDTOTest {

    @Test
    @DisplayName("Test getter")
    void testGetter() {
        //arrange
        List<String> relationList = new ArrayList<>();
        relationList.add("[João, Maria, Parent]");
        relationList.add("[João, Pedro, Sibling]");

        OutViewRelationDTO relationListDTO = new OutViewRelationDTO(relationList);

        //act
        List<String> relationListResult = relationListDTO.getRelationList();
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
        relationList.add("[João, Maria, Parent]");
        relationList.add("[João, Pedro, Sibling]");

        OutViewRelationDTO relationListDTO = new OutViewRelationDTO(relationList);

        //act
        String relationListResult = relationListDTO.toString();
        String expected = "[[João, Maria, Parent], [João, Pedro, Sibling]]";

        //assert
        assertEquals(expected, relationListResult);
    }

    @Test
    @DisplayName("HashCode test")
    void testEqualsHashCode() {
        //arrange
        List<String> relationList1 = new ArrayList<>();
        relationList1.add("[João, Maria, Parent]");
        relationList1.add("[João, Pedro, Sibling]");

        List<String> relationList2 = new ArrayList<>();
        relationList2.add("[João, Maria, Parent]");
        relationList2.add("[João, Pedro, Sibling]");

        List<String> relationList3 = new ArrayList<>();
        relationList3.add("[João, Maria, Spouse]");
        relationList3.add("[João, Alfredo, Spouse]");

        //act
        OutViewRelationDTO relationListDTO1 = new OutViewRelationDTO(relationList1);
        OutViewRelationDTO relationListDTO2 = new OutViewRelationDTO(relationList2);
        OutViewRelationDTO relationListDTO3 = new OutViewRelationDTO(relationList3);

        //assert
        assertEquals(relationListDTO1, relationListDTO2);
        assertEquals(relationListDTO1.hashCode(), relationListDTO2.hashCode());
        assertNotEquals(relationListDTO1.hashCode(), relationListDTO3.hashCode());
    }

    @Test
    @DisplayName("Same ViewRelationDTO")
    void sameViewRelationDTO() {
        //arrange
        List<String> relationList1 = new ArrayList<>();
        relationList1.add("[João, Maria, Parent]");
        relationList1.add("[João, Pedro, Sibling]");
        List<String> relationList2 = new ArrayList<>();
        relationList2.add("[João, Maria, Parent]");
        relationList2.add("[João, Pedro, Sibling]");
        //act
        OutViewRelationDTO outViewRelationDTO1 = new OutViewRelationDTO(relationList1);
        OutViewRelationDTO outViewRelationDTO2 = new OutViewRelationDTO(relationList2);
        //assert
        assertEquals(outViewRelationDTO1, outViewRelationDTO2);
    }

    @Test
    @DisplayName("Not same ViewRelationDTO")
    void notSameViewRelationDTO() {
        //arrange
        List<String> relationList1 = new ArrayList<>();
        relationList1.add("[João, Maria, Parent]");
        relationList1.add("[João, Pedro, Sibling]");
        List<String> relationList2 = new ArrayList<>();
        relationList2.add("[João, Maria, Spouse]");
        relationList2.add("[João, Pedro, Sibling]");
        //act
        OutViewRelationDTO outViewRelationDTO1 = new OutViewRelationDTO(relationList1);
        OutViewRelationDTO outViewRelationDTO2 = new OutViewRelationDTO(relationList2);
        //assert
        assertNotEquals(outViewRelationDTO1, outViewRelationDTO2);
    }

    @Test
    @DisplayName("Not equal objects")
    void viewRelationDTONotEqualToCategory() {
        //arrange
        String id = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(id);
        CategoryDesignation categoryDesignation = new CategoryDesignation("Food");
        Category category = CategoryFactory.create(categoryDesignation, categoryID, null);
        List<String> relationList = new ArrayList<>();
        relationList.add("[João, Maria, Parent]");
        relationList.add("[João, Pedro, Sibling]");
        OutViewRelationDTO outViewRelationDTO = new OutViewRelationDTO(relationList);
        //act
        boolean result = outViewRelationDTO.equals(category);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Same instance")
    void sameInstance() {
        //arrange
        List<String> relationList = new ArrayList<>();
        relationList.add("[João, Maria, Parent]");
        relationList.add("[João, Pedro, Sibling]");
        OutViewRelationDTO outViewRelationDTO = new OutViewRelationDTO(relationList);
        OutViewRelationDTO sameOutViewRelationDTO = outViewRelationDTO;
        //assert
        assertEquals(outViewRelationDTO, sameOutViewRelationDTO);
    }

    @Test
    void createDTOWithNull() {
        //arrange
        List<String> relationList = null;
        OutViewRelationDTO dto = new OutViewRelationDTO(relationList);
        //act
        int result = dto.listSize();
        //assert
        assertEquals(0, result);
    }

    @Test
    void getFamilyList() {
        //arrange
        List<String> familyRelationList = new ArrayList<>();
        familyRelationList.add("Ana is child of Adam");
        OutViewRelationDTO dto = new OutViewRelationDTO(familyRelationList);
        //act
        List<String> result = dto.getRelationList();
        //assert
        assertTrue(result.contains("Ana is child of Adam"));
    }
}