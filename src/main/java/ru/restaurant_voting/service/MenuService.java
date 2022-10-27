package ru.restaurant_voting.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.restaurant_voting.model.Menu;
import ru.restaurant_voting.repository.MenuRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class MenuService {
    public void checkBeforUpdate(@Valid Menu menu) {}
    public static void deletingFromRepository(MenuRepository menuRepository, List<Menu> menus) {
        ArrayList<Integer> menusId = new ArrayList<>();
        for (Menu menu : menus) menusId.add(menu.id());
        for (Integer integer : menusId) menuRepository.deleteUserById(integer);
    }
}
