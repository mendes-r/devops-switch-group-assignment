package switchtwentytwenty.project.dto.outdto;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

public class OutTransferDTO extends RepresentationModel<OutTransferDTO> {

    //Attributes

    @Getter
    private final String originAccountID;
    @Getter
    private final String destinationAccountID;
    @Getter
    private final String date;
    @Getter
    private final double amount;

    //Constructor Methods

    public OutTransferDTO(String originAccountID, String destinationAccountID, String date, double amount) {
        Objects.requireNonNull(originAccountID);
        Objects.requireNonNull(destinationAccountID);
        Objects.requireNonNull(date);
        this.originAccountID = originAccountID;
        this.destinationAccountID = destinationAccountID;
        this.date = date;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OutTransferDTO)) return false;
        OutTransferDTO that = (OutTransferDTO) o;
        return Double.compare(that.amount, amount) == 0 && Objects.equals(originAccountID, that.originAccountID) && Objects.equals(destinationAccountID, that.destinationAccountID) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), originAccountID, destinationAccountID, date, amount);
    }
}
