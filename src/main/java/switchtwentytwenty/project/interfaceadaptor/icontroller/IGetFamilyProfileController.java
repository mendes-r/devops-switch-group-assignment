package switchtwentytwenty.project.interfaceadaptor.icontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface IGetFamilyProfileController {

    /**
     * Get Family Profile information
     * @param familyID - family ID
     * @return family profile: name, registration date, administratorID, administrator name.
     */
    ResponseEntity<Object> getFamilyProfile(@PathVariable("id") String familyID);
}
