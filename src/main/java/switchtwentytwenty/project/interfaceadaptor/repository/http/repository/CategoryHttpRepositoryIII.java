package switchtwentytwenty.project.interfaceadaptor.repository.http.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.dto.toservicedto.StandardCategoryDTO;
import switchtwentytwenty.project.interfaceadaptor.repository.http.dto.ExternalCategoryMapperIII;
import switchtwentytwenty.project.interfaceadaptor.repository.http.dto.ExternalStandardCategoryDTOIII;
import switchtwentytwenty.project.interfaceadaptor.repository.http.dto.ExternalStandardCategoryDTOWrapperIII;
import switchtwentytwenty.project.interfaceadaptor.repository.http.irepository.ICategoryHttpRepository;

import java.util.Collections;
import java.util.List;

@Repository
public class CategoryHttpRepositoryIII implements ICategoryHttpRepository {

    RestTemplate restTemplate = new RestTemplate();

    /**
     * Get a list of standard categories from group III api.
     *
     * @return The list of standard categories in OutStandardCategoryDTO format.
     */
    public List<StandardCategoryDTO> getStandardCategories() {
        ExternalStandardCategoryDTOWrapperIII response;
        try {
            response = restTemplate
                    .getForObject(Constants.URL_CATEGORIES_GROUP_III, ExternalStandardCategoryDTOWrapperIII.class);
        } catch (Exception exception) {
            return Collections.emptyList();
        }
        if (response == null) {
            return Collections.emptyList();
        }
        List<ExternalStandardCategoryDTOIII> dtoList = response.getOutputCategoryDTOList();
        return ExternalCategoryMapperIII.toStandardDTO(dtoList);
    }
}

