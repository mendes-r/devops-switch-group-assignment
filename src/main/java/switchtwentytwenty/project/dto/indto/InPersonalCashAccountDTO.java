package switchtwentytwenty.project.dto.indto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class InPersonalCashAccountDTO{

    @Getter
    private final String personID;

    @Getter
    private final double initialAmount;

    @Getter
    public String designation;
}