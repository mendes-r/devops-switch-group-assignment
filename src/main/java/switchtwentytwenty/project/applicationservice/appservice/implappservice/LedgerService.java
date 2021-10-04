package switchtwentytwenty.project.applicationservice.appservice.implappservice;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.ILedgerService;
import switchtwentytwenty.project.applicationservice.irepository.ILedgerRepository;
import switchtwentytwenty.project.applicationservice.irepository.IPersonRepository;
import switchtwentytwenty.project.domain.aggregate.ledger.Ledger;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.domain.share.transactiondata.Transaction;
import switchtwentytwenty.project.domain.share.transactiondata.TransactionDate;
import switchtwentytwenty.project.dto.outdto.OutMovementDTO;
import switchtwentytwenty.project.dto.outdto.OutMovementDTOMapper;
import switchtwentytwenty.project.exception.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LedgerService implements ILedgerService {

    //Attributes

    @Autowired
    private final IPersonRepository personRepository;

    @Autowired
    private final ILedgerRepository ledgerRepository;


    //Constructor Methods

    //Getter and Setters

    //Business Methods

    /**
     * Gets all the movements from a given account and between a certain dates.
     *
     * @param personIDString  - person identifier
     * @param accountIDString - account identifier
     * @param startDateString - initial date
     * @param endDateString   - final date
     * @throws ParseException           - Wrong date format
     * @throws InvalidEmailException    - invalid email
     * @throws ElementNotFoundException - person not found in system
     */
    @Override
    public List<OutMovementDTO> getListOfMovementsBetweenDates(String personIDString, String accountIDString, String startDateString, String endDateString) throws ParseException, InvalidEmailException, ElementNotFoundException, InvalidDateException, InvalidVATException, InvalidPersonNameException, InvalidMovementTypeException, IOException, InvalidRelationTypeException {
        //primitives to value objects
        Email personID = new Email(personIDString);
        AccountID accountID = new AccountID(UUID.fromString(accountIDString));
        TransactionDate startDate = new TransactionDate(startDateString);
        TransactionDate endDate = new TransactionDate(endDateString);

        Person accountHolder = personRepository.findByID(personID);
        if (accountHolder.isMyAccount(accountID)) {
            LedgerID myLedgerID = accountHolder.getLedgerID();
            Ledger myLedger = ledgerRepository.findByID(myLedgerID);
            List<Transaction> transactionBetweenDates = myLedger.getListOfMovementsBetweenDates(accountID, startDate, endDate);
            return OutMovementDTOMapper.toDTOList(transactionBetweenDates, accountID);
        } else {
            throw new IllegalArgumentException("Invalid account ID.");
        }
    }

    //Equals and HashCode
}
