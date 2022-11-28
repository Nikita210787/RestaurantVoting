package ru.restaurant_voting.web.adminControllers;

import org.junit.jupiter.api.Test;
import ru.restaurant_voting.web.AbstractControllerTest;

import static org.junit.jupiter.api.Assertions.*;

class AdminAccountControllerTest extends AbstractControllerTest {

    @Test
    void getAccount() {}
    @Test
    void getbyId() {
        /*    @Test
    @WithUserDetails(value = USER_LOGIN)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(URL + USER_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaTypes.HAL_JSON_VALUE));
    }*/
    }
/*            @Test
    @WithUserDetails(value = ADMIN_LOGIN)
    void getByEmail() throws Exception {
                perform(MockMvcRequestBuilders.get(URL+  "search/by-login?login="+  ADMIN_LOGIN))
                                .andExpect(status().isOk())
                                .andDo(print())
                                .andExpect(content().contentTypeCompatibleWith(MediaTypes.HAL_JSON_VALUE));
            }*/

    @Test
    void delete() {

         /*         DELETE   @Test
    @WithUserDetails(value = ADMIN_LOGIN)
    void delete() throws Exception {
                perform(MockMvcRequestBuilders.delete(URL + USER_ID))
                                .andExpect(status().isNoContent());
                Assertions.assertFalse(userRepository.findById(USER_ID).isPresent());
                Assertions.assertTrue(userRepository.findById(ADMIN_ID).isPresent());
            }*/
    }
    @Test
    void register() {}
    @Test
    void update() {}
    /*    @Test
    @WithUserDetails(value = USER_LOGIN)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(URL + USER_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaTypes.HAL_JSON_VALUE));
    }*/
    /*      getAllUsers      @Test
    @WithUserDetails(value = ADMIN_LOGIN)
    void getAll() throws Exception {
                perform(MockMvcRequestBuilders.get(URL))
                                .andExpect(status().isOk())
                                .andDo(print())
                                .andExpect(content().contentTypeCompatibleWith(MediaTypes.HAL_JSON_VALUE));
            }*/

}