package switchtwentytwenty.project.interfaceadaptor.implcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switchtwentytwenty.project.exception.BusinessErrorMessage;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.ITransactionService;
import switchtwentytwenty.project.dto.toservicedto.PaymentDTO;
import switchtwentytwenty.project.dto.toservicedto.PaymentDTOMapper;
import switchtwentytwenty.project.dto.indto.InPaymentDTO;
import switchtwentytwenty.project.dto.outdto.OutPaymentDTO;
import switchtwentytwenty.project.exception.*;
import switchtwentytwenty.project.interfaceadaptor.icontroller.IAddPaymentTransactionController;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AddPaymentTransactionController implements IAddPaymentTransactionController {

    @Autowired
    private ITransactionService transactionService;

    /**
     * Add payment transaction
     *
     * @return true, if payment is added to the ledger successfully
     */
    //TODO: fix links
    @PostMapping(value="/accounts/{accountID}/payment")
    public ResponseEntity<Object> addPaymentTransaction(@PathVariable String accountID, @RequestBody InPaymentDTO dto) {
        try {
            OutPaymentDTO payment;
            PaymentDTO newDTO = PaymentDTOMapper.mapToDTO(dto, accountID);
            payment = this.transactionService.addPaymentTransaction(newDTO);

            Link linkToPayment = linkTo(AddPaymentTransactionController.class)
                    .slash("addpayment").slash("payment").withRel(payment.getDesignation());

            Link linkToAccountInfo = linkTo(GetCashAccountBalanceController.class)
                    .slash("api").slash(dto.getPersonID()).slash(accountID).withRel(accountID);

            payment.add(linkToPayment);
            payment.add(linkToAccountInfo);

            return new ResponseEntity<>(payment, HttpStatus.CREATED);

        } catch (InvalidPersonNameException | InvalidEmailException | InvalidVATException | InvalidDateException | AccountNotCreatedException | ElementNotFoundException | InstantiationException | ParseException | WithdrawNotPossibleException | InvalidMovementTypeException | UnsupportedOperationException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | IOException exception) {
            BusinessErrorMessage msg = new BusinessErrorMessage(exception.getMessage(), BusinessErrorMessage.GROUP_DESCRIPTION_NOT_FOUND);
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST); //httpstatus -> ver qual melhor value.
        }
    }
}
