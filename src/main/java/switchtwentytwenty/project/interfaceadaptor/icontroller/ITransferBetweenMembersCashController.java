package switchtwentytwenty.project.interfaceadaptor.icontroller;

import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.dto.indto.InTransferDTO;

public interface ITransferBetweenMembersCashController {

    /**
     * Make transfer between a family member's cash account and another family member's cash account
     *
     * @param info - InTransferDTO
     * @return a ResponseEntity in case of a successful transfer and in case of unsuccessful transfer
     */
    ResponseEntity<Object> transferBetweenTwoCashAccounts(InTransferDTO info);

}
