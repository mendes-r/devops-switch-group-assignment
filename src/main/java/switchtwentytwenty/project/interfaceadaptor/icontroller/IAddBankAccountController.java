package switchtwentytwenty.project.interfaceadaptor.icontroller;

import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.dto.indto.InCreateAccountDTO;

public interface IAddBankAccountController {

    /**
     * Allows the user to add a bank account.
     * @param info - InCreateAccountDTO with account data.
     * @return An response entity with data of created account or an error message.
     */
    ResponseEntity<Object> addBankAccount(InCreateAccountDTO info);
}
