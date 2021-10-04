package switchtwentytwenty.project.interfaceadaptor.implcontroller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.ILedgerService;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.dto.outdto.OutMovementDTO;
import switchtwentytwenty.project.exception.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetListOfMovementBetweenDatesControllerTest {

    @Mock
    ILedgerService ledgerServiceMock;
    @InjectMocks
    GetListOfMovementsBetweenDatesController controller;

    @Test
    @DisplayName("Get a list of movements - positive response with empty list")
    void getListOfMovementsPositiveResponseWithEmptyList() throws Exception {
        //arrange
        int statusCodeExpected = 204;

        String personID = UUID.randomUUID().toString();
        String accountID = UUID.randomUUID().toString();
        String startDate = "1900-01-01";
        String endDate = "2021-01-01";

        //arrange mock
        doReturn(Collections.EMPTY_LIST).when(ledgerServiceMock).getListOfMovementsBetweenDates(anyString(), anyString(), anyString(), anyString());

        //act
        ResponseEntity<Object> response = controller.getListOfMovementsBetweenDates(personID, accountID, startDate, endDate);

        //assert
        assertEquals(response.getStatusCodeValue(), statusCodeExpected);
    }

    @Test
    @DisplayName("Get a list of movements - positive response")
    void getListOfMovementsPositiveResponse() throws Exception{
        //arrange
        int statusCodeExpected = 200;

        String personID = UUID.randomUUID().toString();
        String accountID = UUID.randomUUID().toString();
        String startDate = "1900-01-01";
        String endDate = "2021-01-01";

        OutMovementDTO dto = new OutMovementDTO.OutMovementDTOBuilder()
                .withAmount(100)
                .withDate("2019-01-01")
                .withCategory("Food")
                .withAccountID(UUID.randomUUID().toString())
                .withDescription("Delicious")
                .withBalanceToThisDate(500)
                .withMovementType(Constants.CREDIT)
                .build();

        //arrange mock
        doReturn(Collections.singletonList(dto)).when(ledgerServiceMock).getListOfMovementsBetweenDates(anyString(), anyString(), anyString(), anyString());

        //act
        ResponseEntity<Object> response = controller.getListOfMovementsBetweenDates(personID, accountID, startDate, endDate);

        //assert
        assertEquals(response.getStatusCodeValue(), statusCodeExpected);
    }

    @Test
    @DisplayName("Get a list of movements - exception was trown IllegalStateException")
    void getListOfMovementsIllegalStateException() throws Exception {
        //arrange
        int statusCodeExpected = 422;

        String personID = UUID.randomUUID().toString();
        String accountID = UUID.randomUUID().toString();
        String startDate = "1900-01-01";
        String endDate = "2021-01-01";

        //arrange mock
        doThrow(IllegalStateException.class).when(ledgerServiceMock).getListOfMovementsBetweenDates(anyString(), anyString(), anyString(), anyString());

        //act
        ResponseEntity<Object> response = controller.getListOfMovementsBetweenDates(personID, accountID, startDate, endDate);

        //assert
        assertEquals(response.getStatusCodeValue(), statusCodeExpected);
    }
}
