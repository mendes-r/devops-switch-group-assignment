package switchtwentytwenty.project.dto.todomaindto;

import lombok.Getter;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.domain.share.transactiondata.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JpaToDomainLedgerDTO {

    //Attribute
    @Getter
    private LedgerID id;
    @Getter
    private List<Transaction> transactionList;

    public JpaToDomainLedgerDTO(LedgerID id) {
        this.id = id;
        this.transactionList = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JpaToDomainLedgerDTO)) return false;
        JpaToDomainLedgerDTO that = (JpaToDomainLedgerDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
