package switchtwentytwenty.project.dto.outdto;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OutSystemRelationsDTO extends RepresentationModel<OutSystemRelationsDTO> {


    //Attributes
    @Getter
    private final List<String> systemRelationsList;

    //Constructor

    /**
     * Sole constructor
     *
     * @param relationList - relation list
     */
    public OutSystemRelationsDTO(List<String> relationList) {
        if(relationList != null){
            this.systemRelationsList =new ArrayList<>(relationList) ;
        }
        else {
            this.systemRelationsList = new ArrayList<>();
        }
    }

    //Equals method
    /**
     * Override equal method
     *
     * @param o - object that we want to compare to
     * @return true if the two objects are true, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OutSystemRelationsDTO)) return false;
        OutSystemRelationsDTO that = (OutSystemRelationsDTO) o;
        return Objects.equals(systemRelationsList, that.systemRelationsList);
    }

    /**
     * Override hashCode method
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(systemRelationsList);
    }

    @Override
    public String toString() {
        return systemRelationsList.toString();
    }
}
