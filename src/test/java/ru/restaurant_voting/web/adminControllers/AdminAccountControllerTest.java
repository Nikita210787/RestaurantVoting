package ru.restaurant_voting.web.adminControllers;

import org.junit.jupiter.api.Assertions;
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
import ru.restaurant_voting.web.MatcherFactory;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.restaurant_voting.web.adminControllers.AdminAccountController.URL_ADMIN_ACCOUNT;
import static ru.restaurant_voting.web.testData.UserTestData.*;

class AdminAccountControllerTest extends AbstractControllerTest {
    @Autowired
    UserRepository userRepository;

    /**
     * return authorized admin profile
     */
    @Test
    @WithUserDetails(value = ADMIN_LOGIN)
    void getAccount() throws Exception {
        MvcResult result = perform(MockMvcRequestBuilders.get(URL_ADMIN_ACCOUNT + "/profile"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(ADMIN_NAME))
                .andExpect(jsonPath("$.login").value(ADMIN_LOGIN))
                .andReturn();

    }

    /**
     * get user by Id
     */
    @Test
    @WithUserDetails(value = ADMIN_LOGIN)
    void getbyId() throws Exception {
        MvcResult result = perform(MockMvcRequestBuilders.get(URL_ADMIN_ACCOUNT + "/" + USER_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(USER_NAME))
                .andExpect(jsonPath("$.login").value(USER_LOGIN))
                .andExpect(jsonPath("$.roles").value(Role.USER.toString()))
                .andReturn();
    }

    /**
     * delete User by ID
     */
    @Test
    @WithUserDetails(value = ADMIN_LOGIN)
    void delete() throws Exception {

        perform(MockMvcRequestBuilders.delete(URL_ADMIN_ACCOUNT + "/" + USER_ID))
                .andExpect(status().isNoContent());
        Assertions.assertFalse(userRepository.findById(USER_ID).isPresent());
        Assertions.assertTrue(userRepository.findById(ADMIN_ID).isPresent());
    }

    /**
     * update any user by id.
     */
    @Test
    @WithUserDetails(value = ADMIN_LOGIN)
    void update() throws Exception {
        Assertions.assertTrue(SecurityContextHolder.getContext().getAuthentication().isAuthenticated());

        MvcResult result = perform(MockMvcRequestBuilders.put(URL_ADMIN_ACCOUNT + "/" + USER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(TEST_USER)))
                .andExpect(status().isNoContent())
                .andReturn();
        User actualUser = userRepository.findById(USER_ID).get();
        MatcherFactory.usingRecursiveIgnoreFieldsComparator(User.class, "id", "password").assertMatch(actualUser, TEST_USER);
    }
}