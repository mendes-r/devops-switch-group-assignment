package switchtwentytwenty.project.dto.outdto;


import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.transactiondata.Transaction;

import java.util.ArrayList;
import java.util.List;

public class OutMovementDTOMapper {


    //Constructor methods

    /**
     * Sole constructor.
     */
    private OutMovementDTOMapper() {}

    //Business Methods

    /**
     * Transaction list to DTO list.
     *
     * @param list      - transaction list
     * @param accountID - account identifier
     * @return a Collection of OUTMovementDTO
     */
    public static List<OutMovementDTO> toDTOList(List<Transaction> list, AccountID accountID) {
        List<OutMovementDTO> dtoList = new ArrayList<>();
        for (Transaction transaction : list) {
            OutMovementDTO dto = toDTO(transaction, accountID);
            dtoList.add(dto);
        }
        return dtoList;
    }

    /**
     * Transaction to OUTMovementDTO.
     *
     * @param transaction - a transaction
     * @param accountID   - account identifier
     * @return dto
     */
    public static OutMovementDTO toDTO(Transaction transaction, AccountID accountID) {
        return new OutMovementDTO.OutMovementDTOBuilder()
                .withAmount(transaction.getMyAmount(accountID).floatValue())
                .withAccountID(accountID.toString())
                .withMovementType(transaction.getMyMovementType(accountID).toString())
                .withDate(transaction.getDate().toString())
                .withCategory(transaction.getCategoryID().toString())
                .withBalanceToThisDate(transaction.getBalanceToThisDate().floatValue())
                .withDescription(transaction.getDescription().toString())
                .build();
    }

}
