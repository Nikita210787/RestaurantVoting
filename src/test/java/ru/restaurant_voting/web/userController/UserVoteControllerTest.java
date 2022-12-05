package ru.restaurant_voting.web.userController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import ru.restaurant_voting.repository.UserRepository;
import ru.restaurant_voting.repository.VoteRepository;
import ru.restaurant_voting.service.TimeService;
import ru.restaurant_voting.web.AbstractControllerTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.restaurant_voting.web.testData.UserTestData.USER_ID;
import static ru.restaurant_voting.web.testData.VoteTestData.*;
import static ru.restaurant_voting.web.userControllers.UserVoteController.URL_VOTES;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserVoteControllerTest extends AbstractControllerTest {
    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TimeService clockForAppointTime;


    @Test
    @WithUserDetails(value = "user")
    void getUserVoteForToday() throws Exception {
        perform(MockMvcRequestBuilders.get(URL_VOTES))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void getUserVoteForTodayUnauthorized() throws Exception {
        perform(MockMvcRequestBuilders.get(URL_VOTES))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = "user")
    void voteBeforeDeadlineAgainOrFirstlyToday() throws Exception {
        clockForAppointTime.appointTime(ZonedDateTime.of(LocalDate.now(), LocalTime.of(10, 59), ZoneId.systemDefault()));
        perform(MockMvcRequestBuilders.post(URL_VOTES + "/restaurant/{id}", EXISTING_RESTAURANT_ID)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @WithUserDetails(value = "user")
    void voteAfterDeadlineAgainToday() throws Exception {
        clockForAppointTime.appointTime(ZonedDateTime.of(LocalDate.now(), LocalTime.of(11, 59), ZoneId.systemDefault()));
        perform(MockMvcRequestBuilders.post(URL_VOTES + "/restaurant/{id}", EXISTING_RESTAURANT_ID)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    @WithUserDetails(value = "user")
    void voteForNonExistent() throws Exception {
        perform(MockMvcRequestBuilders.post(URL_VOTES + "/restaurant/{id}", NON_EXISTENT_RESTAURANT_ID)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}