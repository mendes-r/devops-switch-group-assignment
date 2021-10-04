package switchtwentytwenty.project.interfaceadaptor.icontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import switchtwentytwenty.project.dto.indto.InPersonalCashAccountDTO;

public interface ICreatePersonalCashAccountController {

    ResponseEntity<Object> createPersonalCashAccount(@RequestBody InPersonalCashAccountDTO info);

}
