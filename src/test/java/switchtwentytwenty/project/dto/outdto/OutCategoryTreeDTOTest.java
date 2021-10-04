package switchtwentytwenty.project.dto.outdto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.share.designation.CategoryDesignation;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OutCategoryTreeDTOTest {

    @Test
    @DisplayName("Add child tree to category tree")
    void addChild() {
        OutCategoryTreeDTO tree1 = new OutCategoryTreeDTO("Food");
        OutCategoryTreeDTO treeChild = new OutCategoryTreeDTO("Fruit");
        tree1.addChildTree(treeChild);

        assertNotNull(tree1);
        assertNotNull(treeChild);
    }

    @Test
    void equalsChildTrue() {
        OutCategoryTreeDTO tree1 = new OutCategoryTreeDTO("Food");
        OutCategoryTreeDTO treeChild = new OutCategoryTreeDTO("Fruit");
        OutCategoryTreeDTO treeChild1 = new OutCategoryTreeDTO("Fruit");
        tree1.addChildTree(treeChild);
        tree1.addChildTree(treeChild1);

        boolean equals = treeChild.equals(treeChild1);

        assertTrue(equals);
        assertEquals(treeChild.hashCode(), treeChild1.hashCode());
    }


    @Test
    void equalsChildFalse() {
        OutCategoryTreeDTO tree1 = new OutCategoryTreeDTO("Food");
        OutCategoryTreeDTO treeChild = new OutCategoryTreeDTO("Fruit");
        OutCategoryTreeDTO treeChild1 = new OutCategoryTreeDTO("Veggies");
        tree1.addChildTree(treeChild);
        tree1.addChildTree(treeChild1);

        boolean equals = treeChild.equals(treeChild1);

        assertFalse(equals);
        assertNotEquals(treeChild.hashCode(), treeChild1.hashCode());
    }

    @Test
    void testChildCategoryTree() {
        OutCategoryTreeDTO tree1 = new OutCategoryTreeDTO("Food");
        OutCategoryTreeDTO treeChild = new OutCategoryTreeDTO("Fruit");
        OutCategoryTreeDTO treeChild1 = new OutCategoryTreeDTO("Veggies");
        tree1.addChildTree(treeChild);
        tree1.addChildTree(treeChild1);

        List<OutCategoryTreeDTO> result = tree1.getChildren();
        List<OutCategoryTreeDTO> expected = new ArrayList<>();
        expected.add(treeChild);
        expected.add(treeChild1);

        assertEquals(expected, result);
        assertNotNull(expected);
        assertNotNull(result);
    }

    @Test
    void testEqualsCategoryTree() {
        //arrange
        OutCategoryTreeDTO tree1 = new OutCategoryTreeDTO("Food");
        OutCategoryTreeDTO child1 = new OutCategoryTreeDTO("Fruit");
        tree1.addChildTree(child1);

        OutCategoryTreeDTO tree2 = new OutCategoryTreeDTO("Food");
        OutCategoryTreeDTO child2 = new OutCategoryTreeDTO("Fruit");
        tree2.addChildTree(child2);

        //act
        boolean result = tree1.equals(tree2);

        //assert
        assertTrue(result);
    }

    @Test
    void testNotEqualsCategoryTree() {
        //arrange
        OutCategoryTreeDTO tree1 = new OutCategoryTreeDTO("Food");
        OutCategoryTreeDTO child1 = new OutCategoryTreeDTO("Fruit");
        tree1.addChildTree(child1);

        OutCategoryTreeDTO tree2 = new OutCategoryTreeDTO("Food");
        OutCategoryTreeDTO child2 = new OutCategoryTreeDTO("Legumes");
        tree2.addChildTree(child2);

        //act
        boolean result = tree1.equals(tree2);

        //assert
        assertFalse(result);
    }

    @Test
    void testNotEqualsCategoryTree1() {
        //arrange
        OutCategoryTreeDTO tree1 = new OutCategoryTreeDTO("Food");
        OutCategoryTreeDTO child1 = new OutCategoryTreeDTO("Legumes");
        tree1.addChildTree(child1);

        OutCategoryTreeDTO tree2 = new OutCategoryTreeDTO(null);

        //act
        boolean result = tree1.equals(tree2);

        //assert
        assertFalse(result);
    }

    @Test
    void testNotEquals2() {
        //arrange
        OutCustomCategoryDTO customCategoryDTO = new OutCustomCategoryDTO("Legumes");
        OutCategoryTreeDTO tree = new OutCategoryTreeDTO("Legumes");
        //act
        boolean result = tree.equals(customCategoryDTO);
        //assert
        assertFalse(result);
    }

    @Test
    void testToStringCategoryTree() {
        OutCategoryTreeDTO tree1 = new OutCategoryTreeDTO("Food");
        OutCategoryTreeDTO child = new OutCategoryTreeDTO("Fruta");
        tree1.addChildTree(child);
        String expectedTree = "Food[Fruta[]]";
        assertEquals(expectedTree, tree1.toString());
        assertNotNull(tree1.toString());
    }


}
