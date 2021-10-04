package switchtwentytwenty.project.interfaceadaptor.icontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import switchtwentytwenty.project.dto.indto.InFamilyCashAccountDTO;

public interface ICreateFamilyCashAccountController {

    ResponseEntity<Object> createFamilyCashAccount(@RequestBody InFamilyCashAccountDTO info);

}
