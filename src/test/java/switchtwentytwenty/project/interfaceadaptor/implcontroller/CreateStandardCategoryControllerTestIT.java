package switchtwentytwenty.project.interfaceadaptor.implcontroller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.Application;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.ICategoryService;
import switchtwentytwenty.project.applicationservice.irepository.ICategoryRepository;

import switchtwentytwenty.project.dto.indto.InCategoryDTO;
import switchtwentytwenty.project.dto.outdto.OutStandardCategoryDTO;
import switchtwentytwenty.project.exception.DesignationNotPossibleException;

import switchtwentytwenty.project.exception.ElementNotFoundException;
import switchtwentytwenty.project.interfaceadaptor.icontroller.ICreateStandardCategoryController;


import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = Application.class)
public class CreateStandardCategoryControllerTestIT {
    @Autowired
    ICategoryRepository categoryRepository;
    @Autowired
    ICategoryService categoryService;
    @Autowired
    ICreateStandardCategoryController controller;

    @BeforeEach
    public void before(){
        categoryRepository.deleteAll();
    }

    @Test
    void createStandardCategory()  {
        //arrange
        String designation = "Abacaxi";
        InCategoryDTO info= new InCategoryDTO();
        info.setDesignation(designation);



        ResponseEntity<Object> result = controller.createStandardCategory(info);
        assertEquals(201,result.getStatusCodeValue());

    }

    @Test
    void createStandardChildCategory() throws DesignationNotPossibleException {
        //arrange
        //create parentCategory
        String designationParent ="food";
        OutStandardCategoryDTO outparent= categoryService.createRootStandardCategory(designationParent);
        String parentID= outparent.getId();

        //create childCategory
        String designationChild = "Abacaxi";
        InCategoryDTO info= new InCategoryDTO();
        info.setDesignation(designationChild);
        info.setParentID(parentID);

        //act
        ResponseEntity<Object> result = controller.createStandardCategory(info);

        //assert
        assertEquals(201,result.getStatusCodeValue());
    }

    @Test
    void createStandardCategoryFailure() throws DesignationNotPossibleException, ElementNotFoundException {
        //arrange
        //create parentCategory
        String designationParent ="Abacaxi";
        OutStandardCategoryDTO outparent= categoryService.createRootStandardCategory(designationParent);
        String parentID= outparent.getId();

        //create childCategory
        String designationChild = "Abacaxi";
        InCategoryDTO info= new InCategoryDTO();
        info.setDesignation(designationChild);
        info.setParentID(parentID);
        categoryService.createChildStandardCategory(designationParent,parentID);

        //act
        ResponseEntity<Object> result = controller.createStandardCategory(info);

        //assert
        assertEquals(400,result.getStatusCodeValue());
    }

}