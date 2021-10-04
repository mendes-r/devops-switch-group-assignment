package switchtwentytwenty.project.applicationservice.appservice.iappservice;

import switchtwentytwenty.project.dto.outdto.OutMovementDTO;
import switchtwentytwenty.project.exception.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface ILedgerService {

    /**
     * Gets all the movements from a given account and between a certain dates.
     *
     * @param personIDString  - person identifier
     * @param accountIDString - account identifier
     * @param startDateString - initial date
     * @param endDateString   - final date
     */
   List<OutMovementDTO> getListOfMovementsBetweenDates(String personIDString, String accountIDString, String startDateString, String endDateString) throws ParseException, InvalidEmailException, ElementNotFoundException, InvalidDateException, InvalidVATException, InvalidPersonNameException, InvalidMovementTypeException, IOException, InvalidRelationTypeException;
}
