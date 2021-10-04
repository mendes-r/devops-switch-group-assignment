package switchtwentytwenty.project.interfaceadaptor.icontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import switchtwentytwenty.project.dto.indto.InTransferDTO;

public interface ITransferFamilyAndMemberCashController {

    /**
     * Make transfer between a family's cash account and a family member's cash account
     *
     * @param info - InTransferDTO
     * @return a ResponseEntity in case of a successful transfer and in case of unsuccessful transfer
     */
    ResponseEntity<Object> transferFamilyAndMemberCash(@RequestBody InTransferDTO info);
}
