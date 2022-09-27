package ru.restaurant_voting.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import ru.restaurant_voting.model.Menu;
import ru.restaurant_voting.repository.MenuRepository;
import ru.restaurant_voting.repository.RestaurantRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class MenuUtil {

    public static void checkDate(Menu menu) {
        if (menu.getDate() == null) menu.setDate(LocalDate.now());
    }

    public static void checkMenubyId(Menu menu, int menuId) {
        if (menu.isNew()) {
            menu.setId(menuId);
        }
    }

    public static void checkExists(int menuId, MenuRepository menuRepository) {
        if (menuRepository.existsById(menuId)) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Menu with sach exists. Cannot not be changed");
        }
    }

    public static void deletingFromRepository(MenuRepository menuRepository, List<Menu> menus) {

        ArrayList<Integer> menusId = new ArrayList<>();
        for (Menu menu : menus) menusId.add(menu.id());
        for (Integer integer : menusId) menuRepository.deleteUserById(integer);
    }
}
