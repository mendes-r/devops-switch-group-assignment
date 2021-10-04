package switchtwentytwenty.project.interfaceadaptor.icontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import switchtwentytwenty.project.dto.indto.InCategoryDTO;

public interface ICreateStandardCategoryController {

    /**
     * Allows the system manager to create a standard category
     * @param info entrance category DTO
     * @return created if successful, bad request otherwise
     */
    ResponseEntity<Object> createStandardCategory(@RequestBody InCategoryDTO info);
}
