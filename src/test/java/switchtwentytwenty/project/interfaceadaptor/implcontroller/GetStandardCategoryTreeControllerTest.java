package switchtwentytwenty.project.interfaceadaptor.implcontroller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.applicationservice.appservice.implappservice.CategoryService;
import switchtwentytwenty.project.dto.outdto.OutCategoryTreeDTO;
import switchtwentytwenty.project.exception.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.willThrow;

@ExtendWith(MockitoExtension.class)
public class GetStandardCategoryTreeControllerTest {

    @InjectMocks
    GetStandardCategoryTreeController controller;
    @Mock
    CategoryService service;

    @DisplayName("Rest controller - Status 200")
    @Test
    void getStandardCategoryTree() {
        //act
        List<OutCategoryTreeDTO> standardOutCategoryTreeDTO = new ArrayList<>();
        OutCategoryTreeDTO tree1 = new OutCategoryTreeDTO("Food");
        OutCategoryTreeDTO tree2 = new OutCategoryTreeDTO("Abacaxi");
        tree1.addChildTree(tree2);
        standardOutCategoryTreeDTO.add(tree1);
        Mockito.when(service.getStandardCategoriesTree()).thenReturn(standardOutCategoryTreeDTO);
        ResponseEntity<Object> standardTree = controller.getStandardCategoryTree();
        int expected = 200;

        //assert
        assertEquals(expected, standardTree.getStatusCodeValue());
    }
}
