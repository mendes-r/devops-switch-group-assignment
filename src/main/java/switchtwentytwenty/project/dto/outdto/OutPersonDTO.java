package switchtwentytwenty.project.dto.outdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

@AllArgsConstructor
public class OutPersonDTO extends RepresentationModel<OutPersonDTO> {

    //Attribute
    @Getter
    private String name;

    @Getter
    private String mainEmail;

    @Getter
    private String familyID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OutPersonDTO)) return false;
        OutPersonDTO that = (OutPersonDTO) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(mainEmail, that.mainEmail) &&
                Objects.equals(familyID, that.familyID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, mainEmail);
    }


}
