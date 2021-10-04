package switchtwentytwenty.project.dto.toservicedto;

import switchtwentytwenty.project.dto.indto.InPaymentDTO;

public abstract class PaymentDTOMapper {

    /**
     * Map to Payment dto.
     *
     * @param info - payment dto.
     * @return The payment dto.
     */
    public static PaymentDTO mapToDTO(InPaymentDTO info, String accountID) {
        PaymentDTO newDTO = new PaymentDTO();
        newDTO.setPersonID(info.getPersonID());
        newDTO.setAccountID(accountID);
        newDTO.setAmount(info.getAmount());
        newDTO.setCategoryID(info.getCategoryID());
        newDTO.setDate(info.getDate());
        newDTO.setDesignation(info.getDesignation());
        return newDTO;
    }
}
