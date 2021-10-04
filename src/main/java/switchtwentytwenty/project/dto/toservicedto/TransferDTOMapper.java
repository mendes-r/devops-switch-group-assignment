package switchtwentytwenty.project.dto.toservicedto;

import switchtwentytwenty.project.domain.domaindto.TransferDomainDTO;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.TransactionDescription;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.transactiondata.TransactionDate;
import switchtwentytwenty.project.dto.indto.InTransferDTO;
import switchtwentytwenty.project.exception.InvalidEmailException;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.UUID;

public class TransferDTOMapper {

    /**
     * Sole constructor
     */
    private TransferDTOMapper() {
    }

    /**
     * Map that turns a TransferDTO into TransferDomainDTO
     *
     * @param transferDTO - DTO that has the information to be treated
     * @return a TransferDomainDTO
     * @throws InvalidEmailException - invalid email error
     * @throws ParseException        - parse error
     */
    public static TransferDomainDTO mapToTransferDomainDTO(TransferDTO transferDTO) throws InvalidEmailException, ParseException {

        String senderID = transferDTO.getSenderID();

        Email receiverID = new Email(transferDTO.getReceiverID());

        UUID originID = UUID.fromString(transferDTO.getOriginAccountID());
        AccountID senderAccountID = new AccountID(originID);

        UUID destinationID = UUID.fromString(transferDTO.getDestinationAccountID());
        AccountID receiverAccountID = new AccountID(destinationID);

        MoneyValue amount = new MoneyValue(BigDecimal.valueOf(transferDTO.getAmount()));

        UUID categoryStringID = UUID.fromString(transferDTO.getCategoryID());
        CategoryID categoryID = new CategoryID(transferDTO.getCategoryID());

        TransactionDescription description = new TransactionDescription(transferDTO.getDescription());

        TransactionDate date = new TransactionDate(transferDTO.getDate());

        return new TransferDomainDTO.TransferBuilder()
                .withSenderID(senderID)
                .withReceiverID(receiverID)
                .withOriginAccountID(senderAccountID)
                .withDestinationAccountID(receiverAccountID)
                .withAmount(amount)
                .withDate(date)
                .withCategoryID(categoryID)
                .withDescription(description)
                .build();
    }

    /**
     * Map that turns a InTransferDTO into TransferDTO
     *
     * @param inTransferDTO - DTO that has the information to be treated
     * @return a TransferDomainDTO
     */
    public static TransferDTO mapToTransferDTO(InTransferDTO inTransferDTO) {

        return new TransferDTO.TransferDTOBuilder()
                .senderId(inTransferDTO.getSenderId())
                .receiverId(inTransferDTO.getReceiverId())
                .originAccountId(inTransferDTO.getOriginAccountId())
                .destinationAccountId(inTransferDTO.getDestinationAccountId())
                .amount(inTransferDTO.getAmount())
                .categoryId(inTransferDTO.getCategoryId())
                .description(inTransferDTO.getDescription())
                .date(inTransferDTO.getDate())
                .build();
    }
}
