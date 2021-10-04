package switchtwentytwenty.project.interfaceadaptor.implcontroller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IAccountService;
import switchtwentytwenty.project.dto.indto.InPersonalCashAccountDTO;
import switchtwentytwenty.project.dto.outdto.OutPersonalCashAccountDTO;
import switchtwentytwenty.project.exception.*;

import switchtwentytwenty.project.interfaceadaptor.icontroller.ICreatePersonalCashAccountController;
import switchtwentytwenty.project.interfaceadaptor.icontroller.IGetCashAccountBalanceController;

import java.io.IOException;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class CreatePersonalCashAccountController implements ICreatePersonalCashAccountController {

    // Attributes
    @Autowired
    private IAccountService accountService;

    // Business Methods

    /**
     * Create person cash account
     * @param info - account information
     * @return - response entity
     */
    @PostMapping(value = "/accounts/cash")
    public ResponseEntity<Object> createPersonalCashAccount(@RequestBody InPersonalCashAccountDTO info)  {
        try {
            OutPersonalCashAccountDTO result = accountService.createPersonalCashAccount(info.getPersonID(), info.getInitialAmount(), info.getDesignation());
            Link linkToAccount = linkTo(IGetCashAccountBalanceController.class)
                    .slash("api").slash("balance").slash(info.getPersonID()).slash(result.getAccountID()).withRel("check account balance");

            result.add(linkToAccount);

            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (IllegalArgumentException | ElementNotFoundException | IOException | AccountNotCreatedException | InvalidEmailException | InvalidDateException | InvalidVATException | InvalidPersonNameException exception){
            return new ResponseEntity<>("Error. Process failed!", HttpStatus.BAD_REQUEST); //httpstatus -> ver qual melhor value.
        }
    }

}
