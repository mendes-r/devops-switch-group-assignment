package switchtwentytwenty.project.interfaceadaptor.implcontroller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switchtwentytwenty.project.exception.BusinessErrorMessage;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IAccountService;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.dto.outdto.OutCashAccountBalanceDTO;
import switchtwentytwenty.project.exception.*;
import switchtwentytwenty.project.interfaceadaptor.icontroller.ICheckChildCashAccountBalanceController;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CheckChildCashAccountBalanceController implements ICheckChildCashAccountBalanceController {

    @Autowired
    private IAccountService accountService;

    @GetMapping(value = "/balance/{parentId}/{childId}/{childAccountId}" )
    public ResponseEntity<Object> checkChildCashAccountBalance(@PathVariable("parentId") String parentID , @PathVariable("childId") String childID, @PathVariable("childAccountId") String cashAccountId) {

        try {

            MoneyValue value = accountService.checkChildCashAccountBalance(parentID, childID, cashAccountId);
            double childCashAccountBalance = value.toDouble();
            Link linkToParent = linkTo(CheckChildCashAccountBalanceController.class)
                    .slash("users").slash("accounts").withRel("balance"); //mudar

            OutCashAccountBalanceDTO response = new OutCashAccountBalanceDTO(childCashAccountBalance);
            response.add(linkToParent);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException | NullPointerException | InvalidDateException | InvalidPersonNameException | InvalidVATException | InvalidEmailException | ElementNotFoundException | InvalidRelationTypeException | IOException | AccountNotCreatedException | InstantiationException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException exception) {

            BusinessErrorMessage msg = new BusinessErrorMessage(exception.getMessage(), BusinessErrorMessage.GROUP_DESCRIPTION_NOT_FOUND);
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST); //httpstatus -> ver qual melhor value.

        }
    }
}
