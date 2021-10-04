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
import switchtwentytwenty.project.exception.BusinessErrorMessage;
import switchtwentytwenty.project.applicationservice.appservice.implappservice.TransactionService;
import switchtwentytwenty.project.dto.toservicedto.TransferDTO;
import switchtwentytwenty.project.dto.toservicedto.TransferDTOMapper;
import switchtwentytwenty.project.dto.indto.InTransferDTO;
import switchtwentytwenty.project.dto.outdto.OutTransferDTO;
import switchtwentytwenty.project.exception.*;
import switchtwentytwenty.project.interfaceadaptor.icontroller.ITransferBetweenMembersCashController;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class TransferBetweenTwoCashAccountsController implements ITransferBetweenMembersCashController {

    //Attributes
    @Autowired
    private final TransactionService transactionService;


    //Business Methods

    /**
     * Make transfer between a family member's cash account and another family member's cash account
     *
     * @param info - InTransferDTO
     * @return a ResponseEntity in case of a successful transfer and in case of unsuccessful transfer
     */
    @PostMapping(value = "/transfers/memberAndMember")
    public ResponseEntity<Object> transferBetweenTwoCashAccounts(@RequestBody InTransferDTO info) {

        OutTransferDTO result;
        try {
            TransferDTO transferDTO = TransferDTOMapper.mapToTransferDTO(info);
            result = transactionService.transferBetweenCashAccounts(transferDTO);
            Link linkToTransfer = linkTo(TransferBetweenTwoCashAccountsController.class).slash("transfer").slash(result.getAmount()).withRel("memberAndMember");
            result.add(linkToTransfer);
            return new ResponseEntity<>("The transfer occurred successively", HttpStatus.CREATED);

        } catch (IllegalArgumentException | AccountNotCreatedException | InstantiationException | IOException | InvalidRelationTypeException | InvalidDateException | InvalidVATException | InvalidPersonNameException | NullPointerException | DepositNotPossibleException | WithdrawNotPossibleException | ElementNotFoundException | InvalidMovementTypeException | InvalidEmailException | ParseException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException exception) {
            BusinessErrorMessage msg = new BusinessErrorMessage(exception.getMessage(), BusinessErrorMessage.GROUP_DESCRIPTION_NOT_FOUND);

            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
    }
}
