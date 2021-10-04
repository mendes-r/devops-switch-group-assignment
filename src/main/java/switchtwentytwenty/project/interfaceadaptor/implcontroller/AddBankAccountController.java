package switchtwentytwenty.project.interfaceadaptor.implcontroller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IAccountService;
import switchtwentytwenty.project.dto.indto.InCreateAccountDTO;
import switchtwentytwenty.project.dto.outdto.OutAccountDTO;
import switchtwentytwenty.project.exception.*;
import switchtwentytwenty.project.interfaceadaptor.icontroller.IAddBankAccountController;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class AddBankAccountController implements IAddBankAccountController {

    // Attributes
    @Autowired
    private final IAccountService accountService;

    // Business Methods

    /**
     * Allows the user to add a bank account.
     *
     * @param info - InCreateAccountDTO with account data.
     * @return An response entity with data of created account or an error message.
     */
    @PostMapping(value = "/accounts/bank")
    public ResponseEntity<Object> addBankAccount(@RequestBody InCreateAccountDTO info) {
        try {
            OutAccountDTO result = accountService.addBankAccount(info.getDesignation(), info.getHolderID(), info.getAccountType());
            Link linkToViewBalance = linkTo(methodOn(CheckAccountBalanceController.class)
                    .checkAccountBalance(info.getHolderID(), result.getAccountID()))
                    .withRel("view account balance");
            result.add(linkToViewBalance);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (InvalidEmailException | ClassNotFoundException | ElementNotFoundException | InstantiationException | IllegalArgumentException | NullPointerException | AccountNotCreatedException | InvalidDateException | InvalidVATException | InvalidPersonNameException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | IOException exception) {
            String errorMessage = "Error: " + exception.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }

    }

}
