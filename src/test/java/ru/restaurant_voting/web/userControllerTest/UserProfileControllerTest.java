package ru.restaurant_voting.web.userControllerTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.restaurant_voting.model.Role;
import ru.restaurant_voting.model.User;
import ru.restaurant_voting.repository.UserRepository;
import ru.restaurant_voting.util.JsonUtil;
import ru.restaurant_voting.web.AbstractControllerTest;
import ru.restaurant_voting.web.AuthUser;
import ru.restaurant_voting.web.MatcherFactory;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.restaurant_voting.web.testData.UserTestData.*;
import static ru.restaurant_voting.web.userControllers.UserProfileController.URL_PROFILE_CONTROLLER_TEST;

public class UserProfileControllerTest extends AbstractControllerTest {

    @Autowired
    private UserRepository userRepository;


    /**
     * return authorized user
     */
    @Test
    @WithUserDetails(value = USER_LOGIN)
    void get() throws Exception {
        MvcResult result = perform(MockMvcRequestBuilders.get(URL_PROFILE_CONTROLLER_TEST))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(USER_NAME))
                .andExpect(jsonPath("$.login").value(USER_LOGIN))
                .andExpect(jsonPath("$.roles").value(Role.USER.toString()))
                .andReturn();

    }

    /**
     * delete authorized user
     */
    @Test
    @WithUserDetails(value = USER_LOGIN)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(URL_PROFILE_CONTROLLER_TEST))
                .andExpect(status().isNoContent());
    }

    /**
     * create user
     */
    @Test
    void create() throws Exception {
        MvcResult result = perform(MockMvcRequestBuilders.post(URL_PROFILE_CONTROLLER_TEST + "/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(TEST_USER)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(redirectedUrlPattern("**" + URL_PROFILE_CONTROLLER_TEST + "/*"))
                .andExpect(jsonPath("$.name").value("userTest"))
                .andExpect(jsonPath("$.name").value("userTest"))
                .andExpect(status().isCreated())
                .andReturn();
    }

    /**
     * Update authorized user
     */
    @Test
    @WithUserDetails(value = USER_LOGIN)
    void update() throws Exception {
        AuthUser authUser = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MvcResult result = perform(MockMvcRequestBuilders.put(URL_PROFILE_CONTROLLER_TEST)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(TEST_USER)))
                .andExpect(status().isNoContent())
                .andReturn();
        User actualUser = userRepository.findById(authUser.id()).get();
        MatcherFactory.usingRecursiveIgnoreFieldsComparator(User.class, "id", "password").assertMatch(actualUser, TEST_USER);
    }

    /**
     * Get profile unauthorized user
     */
    @Test
    void getForbidden() throws Exception {
        perform(MockMvcRequestBuilders.get(URL_PROFILE_CONTROLLER_TEST))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }
}