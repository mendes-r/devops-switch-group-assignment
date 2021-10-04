package switchtwentytwenty.project.dto.outdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

@AllArgsConstructor
public class OutFamilyDTO extends RepresentationModel{

        //Attribute
        @Getter
        private String name;

        @Getter
        private String familyID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OutFamilyDTO that = (OutFamilyDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(familyID, that.familyID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, familyID);
    }
}
