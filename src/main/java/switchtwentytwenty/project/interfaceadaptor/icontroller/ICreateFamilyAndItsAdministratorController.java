package switchtwentytwenty.project.interfaceadaptor.icontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import switchtwentytwenty.project.dto.indto.InFamilyAndAdminDTO;

public interface ICreateFamilyAndItsAdministratorController {

    /**
     * Start family and create administrator
     * @param info - family and admin information
     * @return family and admin information and links
     */
    ResponseEntity<Object> startFamily(@RequestBody InFamilyAndAdminDTO info);
}
