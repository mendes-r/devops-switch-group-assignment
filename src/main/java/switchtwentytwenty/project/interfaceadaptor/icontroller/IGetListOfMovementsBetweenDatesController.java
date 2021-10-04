package switchtwentytwenty.project.interfaceadaptor.icontroller;

import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.exception.*;

import java.io.IOException;

public interface IGetListOfMovementsBetweenDatesController {

    /**
     * Gets all the movements from a given account and between a certain dates.
     *
     * @param personID  - person identifier as String
     * @param accountID - account identifier as String
     * @param startDate - initial date as String
     * @param endDate   - final date as String
     */
    ResponseEntity<Object> getListOfMovementsBetweenDates(String personID, String accountID, String startDate, String endDate) throws InvalidDateException, InvalidVATException, InvalidPersonNameException, InvalidMovementTypeException, IOException, ElementNotFoundException;
}
