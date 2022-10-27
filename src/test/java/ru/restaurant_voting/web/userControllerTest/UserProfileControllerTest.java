package ru.restaurant_voting.web.userControllerTest;

import org.junit.jupiter.api.Test;
import ru.restaurant_voting.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

class UserProfileControllerTest extends AbstractControllerTest {
    static final String URL="/v1/api/user/profile";
    @Test
    void get() throws Exception {

    }

    @Test
    void delete() {
    }

    @Test
    void register() {
    }

    @Test
    void update() {
    }
    /*package web.userControllerTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.restaurant_voting.repository.UserRepository;
import ru.restaurant_voting.web.UserControllers.AbstractControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static web.userControllerTest.UserControllerTest.URL;
import static web.userControllerTest.UserTestUtil.*;

public class UserProfileControllerTest extends AbstractControllerTest {

            @Autowired
    private UserRepository userRepository;

            @Test
    @WithUserDetails(value = USER_LOGIN)
    void get() throws Exception {
                perform(MockMvcRequestBuilders.get(URL))
                                .andExpect(status().isOk())
                                .andDo(print())
                                .andExpect(content().contentTypeCompatibleWith(MediaTypes.HAL_JSON_VALUE));
            }

            @Test
    void getUnAuth() throws Exception {
                perform(MockMvcRequestBuilders.get(URL))
                                .andExpect(status().isUnauthorized());
            }

            @Test
    @WithUserDetails(value = USER_LOGIN)
    void delete() throws Exception {
                perform(MockMvcRequestBuilders.delete(URL))
                                .andExpect(status().isNoContent());
                Assertions.assertFalse(userRepository.findById(USER_ID).isPresent());
                Assertions.assertTrue(userRepository.findById(ADMIN_ID).isPresent());
            }
}*/

}