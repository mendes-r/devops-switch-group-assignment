package switchtwentytwenty.project.interfaceadaptor.implcontroller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.applicationservice.appservice.implappservice.AccountService;
import switchtwentytwenty.project.dto.indto.InFamilyCashAccountDTO;
import switchtwentytwenty.project.dto.outdto.OutFamilyCashAccountDTO;
import switchtwentytwenty.project.exception.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CreateFamilyCashAccountControllerTest {

    @InjectMocks
    CreateFamilyCashAccountController controller;
    @Mock
    AccountService accountService;

    @Test
    void createFamilyCashAccount() throws Exception {

        //arrange

        String familyAdministratorID = "constantinosever@gmail.com";
        double cashAmount = 10;
        String designation = "Food";
        String description = "created";
        InFamilyCashAccountDTO info =   new InFamilyCashAccountDTO(familyAdministratorID,cashAmount,designation, description);
        OutFamilyCashAccountDTO outdto = Mockito.mock(OutFamilyCashAccountDTO.class);
        //act
        when(accountService.createFamilyCashAccount(familyAdministratorID,cashAmount,designation)).thenReturn(outdto);
        ResponseEntity<Object> result = controller.createFamilyCashAccount(info);

        //assert
        assertEquals(201,result.getStatusCodeValue());

    }

    @Test
    void createFamilyCashAccount_InvalidAmount() throws Exception {

        //arrange
        String familyAdministratorID = "constantinos4ever@gmail.com";
        double cashAmount = -10;
        String designation = "Food";
        String description = "not created";
        InFamilyCashAccountDTO info =   new InFamilyCashAccountDTO(familyAdministratorID,cashAmount,designation, description);
        //act
        doThrow(AccountNotCreatedException.class).when(accountService).createFamilyCashAccount(familyAdministratorID,cashAmount,designation);
        ResponseEntity<Object> result = controller.createFamilyCashAccount(info);

        //assert
        assertEquals(400,result.getStatusCodeValue());

    }

    @Test
    void createFamilyCashAccount_InvalidEmail() throws Exception {

        //arrange
        String familyAdministratorID = "1234";
        double cashAmount = 10;
        String designation = "Food";
        String description = "not created";
        InFamilyCashAccountDTO info =   new InFamilyCashAccountDTO(familyAdministratorID,cashAmount,designation, description);
        //act
        doThrow(InvalidEmailException.class).when(accountService).createFamilyCashAccount(familyAdministratorID,cashAmount,designation);
        ResponseEntity<Object> result = controller.createFamilyCashAccount(info);

        //assert
        assertEquals(400,result.getStatusCodeValue());

    }

    @Test
    void createFamilyCashAccount_ElementNotFound() throws Exception{

        //arrange
        String familyAdministratorID = "constantinos4ever@gmail.com";
        double cashAmount = 10;
        String designation = "8€#";
        String description = "not created";
        InFamilyCashAccountDTO info =   new InFamilyCashAccountDTO(familyAdministratorID,cashAmount,designation, description);
        //act
        doThrow(AccountNotCreatedException.class).when(accountService).createFamilyCashAccount(familyAdministratorID,cashAmount,designation);
        ResponseEntity<Object> result = controller.createFamilyCashAccount(info);

        //assert
        assertEquals(400,result.getStatusCodeValue());
    }
}
