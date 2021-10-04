/*
package switchtwentytwenty.project.interfaceadaptor.implcontroller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import switchtwentytwenty.project.Application;
import switchtwentytwenty.project.applicationservice.irepository.IFamilyRepository;
import switchtwentytwenty.project.domain.aggregate.family.Family;
import switchtwentytwenty.project.domain.aggregate.family.FamilyFactory;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.familydata.FamilyName;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.dto.todomaindto.VOFamilyDTO;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc(addFilters = false)
public class GetFamilyProfileControllerIT {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    IFamilyRepository familyRepository;

    MediaType mediaType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            StandardCharsets.UTF_8
    );

    @Test
    @DisplayName("Get Family Profile successfully")
    void getFamilyProfileSuccessfully()
            throws Exception {
        //arrange
        String adminID = "admin@gmail.com";
        String name = "Costa";
        VOFamilyDTO familyDTO = new VOFamilyDTO(new FamilyID(
                UUID.randomUUID()),new LedgerID(UUID.randomUUID()),new Email(adminID),new FamilyName(name));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);
        String familyID = family.getID().toString();
        String registrationDate = family.getRegistrationDate().toString().split("0")[0];

        //act-assert
        this.mockMvc.perform(get("/families/" + familyID)
                .contentType(mediaType)
                .content(""))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(registrationDate)))
                .andExpect(content().string(containsString(adminID)))
                .andExpect(content().string(containsString(name)));
    }

    @Test
    @DisplayName("Failure Get Family Profile: family not found")
    void failureGetFamilyProfile_FamilyNotFound()
            throws Exception {
        //arrange
        String familyID = UUID.randomUUID().toString();

        //act-assert
        this.mockMvc.perform(get("/families/" + familyID)
                .contentType(mediaType)
                .content(""))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString(Constants.FAMILY_NOT_FOUND)));
    }
}*/
