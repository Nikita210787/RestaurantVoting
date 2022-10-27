package ru.restaurant_voting.util;

import lombok.experimental.UtilityClass;
import ru.restaurant_voting.model.Menu;

import java.time.LocalDate;

@UtilityClass
public class MenuUtil {

    public static void checkDate(Menu menu) {
        if (menu.getDate() == null) menu.setDate(LocalDate.now());
    }


}
