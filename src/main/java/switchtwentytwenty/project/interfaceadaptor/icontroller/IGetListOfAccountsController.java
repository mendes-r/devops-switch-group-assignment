package switchtwentytwenty.project.interfaceadaptor.icontroller;

import org.springframework.http.ResponseEntity;


public interface IGetListOfAccountsController {

    ResponseEntity<Object> getListOfAccountsController(String personId);
}
