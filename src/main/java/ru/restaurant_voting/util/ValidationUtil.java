package ru.restaurant_voting.util;

import lombok.experimental.UtilityClass;
import ru.restaurant_voting.dto.MenuWithListIdMeal;
import ru.restaurant_voting.error.IllegalRequestDataException;
import ru.restaurant_voting.model.BaseEntity;
import ru.restaurant_voting.repository.MenuRepository;
import ru.restaurant_voting.repository.RestaurantRepository;

@UtilityClass
public class ValidationUtil {

    public static void checkNew(BaseEntity entity) {
        if (!entity.isNew())
            throw new IllegalRequestDataException(entity.getClass().getSimpleName() + " must be new (id=null)");
    }


    //  Conservative when you reply, but accept liberally (http://stackoverflow.com/a/32728226/548473)'
    public static void assureIdConsistent(BaseEntity entity, int id) {
        if (entity.isNew()) {
            entity.setId(id);
        } else if (entity.id() != id) {
            throw new IllegalRequestDataException(entity.getClass().getSimpleName() + " must has id=" + id);
        }
    }
}
