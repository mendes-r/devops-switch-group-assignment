package switchtwentytwenty.project.dto.indto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class InFamilyCashAccountDTO {

    @Getter
    private String familyAdministratorID;

    @Getter
    private double initialAmount;

    @Getter
    public String designation;

    @Getter
    private String description;


}