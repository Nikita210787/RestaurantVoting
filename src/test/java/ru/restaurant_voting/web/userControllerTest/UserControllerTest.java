/*
package ru.restaurant_voting.web.userControllerTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.restaurant_voting.repository.UserRepository;
import ru.restaurant_voting.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.restaurant_voting.web.testData.UserTestData.*;

public class UserControllerTest extends AbstractControllerTest {
    static final String URL = "/v1/api/user/profile";

            @Autowired
    private UserRepository userRepository;

            @Test
    @WithUserDetails(value = ADMIN_LOGIN)
    void get() throws Exception {
                perform(MockMvcRequestBuilders.get(URL + USER_ID))
                                .andExpect(status().isOk())
                                .andDo(print())
                                .andExpect(content().contentTypeCompatibleWith(MediaTypes.HAL_JSON_VALUE));
            }

            @Test
    @WithUserDetails(value = ADMIN_LOGIN)
    void getAll() throws Exception {
                perform(MockMvcRequestBuilders.get(URL))
                                .andExpect(status().isOk())
                                .andDo(print())
                                .andExpect(content().contentTypeCompatibleWith(MediaTypes.HAL_JSON_VALUE));
            }

            @Test
    @WithUserDetails(value = ADMIN_LOGIN)
    void getByEmail() throws Exception {
                perform(MockMvcRequestBuilders.get(URL+  "search/by-login?login="+  ADMIN_LOGIN))
                                .andExpect(status().isOk())
                                .andDo(print())
                                .andExpect(content().contentTypeCompatibleWith(MediaTypes.HAL_JSON_VALUE));
            }

            @Test
    @WithUserDetails(value = USER_LOGIN)
    void getForbidden() throws Exception {
                perform(MockMvcRequestBuilders.get(URL))
                                .andExpect(status().isForbidden());
            }

            @Test
    @WithUserDetails(value = ADMIN_LOGIN)
    void delete() throws Exception {
                perform(MockMvcRequestBuilders.delete(URL + USER_ID))
                                .andExpect(status().isNoContent());
                Assertions.assertFalse(userRepository.findById(USER_ID).isPresent());
                Assertions.assertTrue(userRepository.findById(ADMIN_ID).isPresent());
            }
}
*/
