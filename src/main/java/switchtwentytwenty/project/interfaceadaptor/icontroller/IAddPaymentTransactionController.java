package switchtwentytwenty.project.interfaceadaptor.icontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import switchtwentytwenty.project.dto.indto.InPaymentDTO;

public interface IAddPaymentTransactionController {

    /**
     * create payment transaction
     * @param dto - payment dto
     * @return - response entity
     */
    ResponseEntity<Object> addPaymentTransaction(@PathVariable String accountID,InPaymentDTO dto);


}
