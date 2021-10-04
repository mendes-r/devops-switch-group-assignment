package switchtwentytwenty.project.interfaceadaptor.implcontroller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IAccountService;
import switchtwentytwenty.project.dto.indto.InFamilyCashAccountDTO;
import switchtwentytwenty.project.dto.outdto.OutFamilyCashAccountDTO;
import switchtwentytwenty.project.exception.*;
import switchtwentytwenty.project.interfaceadaptor.icontroller.ICreateFamilyCashAccountController;
import switchtwentytwenty.project.interfaceadaptor.icontroller.IGetCashAccountBalanceController;

import java.io.IOException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CreateFamilyCashAccountController implements ICreateFamilyCashAccountController {

    // Attributes
    @Autowired
    private final IAccountService accountService;


    // Business Methods

    @PostMapping(value = "/familyCashAccount")
    public ResponseEntity<Object> createFamilyCashAccount(@RequestBody InFamilyCashAccountDTO info)  {
        try {
            OutFamilyCashAccountDTO result = accountService.createFamilyCashAccount(info.getFamilyAdministratorID(), info.getInitialAmount(), info.getDesignation());
            Link linkToAccount = linkTo(IGetCashAccountBalanceController.class)
                    .slash("api").slash("balance").slash(info.getFamilyAdministratorID()).slash(result.getAccountID()).withRel("check account balance");

            result.add(linkToAccount);

            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (IllegalArgumentException | ElementNotFoundException | IOException | AccountNotCreatedException | InvalidEmailException | InvalidRelationTypeException | InvalidDateException | InvalidVATException | InvalidPersonNameException exception){
        return new ResponseEntity<>("Error. Process failed!", HttpStatus.BAD_REQUEST); //httpstatus -> ver qual melhor value.
        }
    }
}
