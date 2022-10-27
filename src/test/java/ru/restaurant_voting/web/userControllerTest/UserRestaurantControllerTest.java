package ru.restaurant_voting.web.userControllerTest;

import org.springframework.beans.factory.annotation.Autowired;
import ru.restaurant_voting.repository.RestaurantRepository;
import ru.restaurant_voting.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

class UserRestaurantControllerTest extends AbstractControllerTest {

    @Autowired
    RestaurantRepository restaurantRepository;

}