package switchtwentytwenty.project.interfaceadaptor.implcontroller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IAccountService;
import switchtwentytwenty.project.dto.outdto.OutAccountDTO;
import switchtwentytwenty.project.interfaceadaptor.icontroller.IGetListOfAccountsController;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class GetListOfAccountsController implements IGetListOfAccountsController {

    @Autowired
    private final IAccountService accountService;

    /**
     * Obtain the list of accounts
     * @param personId
     * @return
     */
    @GetMapping(value = "/users/{personId}/accounts")
    public ResponseEntity<Object> getListOfAccountsController(@PathVariable String personId) {
        try {
            List<OutAccountDTO> accountList = accountService.getListOfAccounts(personId);
            return new ResponseEntity<>(accountList, HttpStatus.OK);
        } catch (Exception exception) {
            String msg = exception.getMessage();
            return new ResponseEntity<>(msg, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

}
