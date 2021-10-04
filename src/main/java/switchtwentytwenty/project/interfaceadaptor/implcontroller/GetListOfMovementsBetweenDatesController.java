package switchtwentytwenty.project.interfaceadaptor.implcontroller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.ILedgerService;
import switchtwentytwenty.project.dto.outdto.OutMovementDTO;
import switchtwentytwenty.project.exception.*;
import switchtwentytwenty.project.interfaceadaptor.icontroller.IGetListOfMovementsBetweenDatesController;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class GetListOfMovementsBetweenDatesController implements IGetListOfMovementsBetweenDatesController {

    //Attributes

    @Autowired
    private final ILedgerService ledgerService;


    //Business Methods

    /**
     * Gets all the movements from a given account and between a certain dates.
     *
     * @param personID  - person identifier as String
     * @param accountID - account identifier as String
     * @param startDate - initial date as String
     * @param endDate   - final date as String
     */
    @GetMapping(value = "/accounts/{accountID}/movements")
    public ResponseEntity<Object> getListOfMovementsBetweenDates(@PathVariable("accountID") String accountID, @RequestParam(name = "personID") String personID, @RequestParam(name = "startDate") String startDate, @RequestParam(name = "endDate") String endDate) throws InvalidDateException, ElementNotFoundException, InvalidVATException, InvalidPersonNameException, InvalidMovementTypeException, IOException {
        try {

            List<OutMovementDTO> dtoList = ledgerService.getListOfMovementsBetweenDates(personID, accountID, startDate, endDate);

            if (dtoList.isEmpty()) {
                return new ResponseEntity<>(dtoList, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(dtoList, HttpStatus.OK);

        } catch (IllegalStateException | ParseException | InvalidEmailException | InvalidRelationTypeException exception) {
            String msg = exception.getMessage();
            return new ResponseEntity<>(msg, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
