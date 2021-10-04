package switchtwentytwenty.project.interfaceadaptor.icontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface IViewFamilyRelationsFromAPerson {

    /**
     * Get family relation's from a person given his/her ID
     *
     * @param personID - person ID
     * @return a family relation list
     */
    ResponseEntity<Object> getFamilyRelationByPersonID(@PathVariable("id") String personID);
}
