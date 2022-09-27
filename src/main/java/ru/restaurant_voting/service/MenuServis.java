package ru.restaurant_voting.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.restaurant_voting.model.Menu;

import javax.validation.Valid;

@Service
@AllArgsConstructor
@Slf4j
public class MenuServis {
    public Menu checkBeforUpdate(@Valid Menu menu) {
        return menu;
    }
}
