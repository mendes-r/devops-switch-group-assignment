package switchtwentytwenty.project.dto.outdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
public class OutFamilyAndAdminDTO extends RepresentationModel<OutFamilyAndAdminDTO> {

    //Attributes
    @Getter
    private String familyName;
    @Getter
    private String adminName;
    @Getter
    private String familyID;
    @Getter
    private String personID;
}
