package ru.restaurant_voting.web.adminControllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.restaurant_voting.error.IllegalRequestDataException;
import ru.restaurant_voting.model.Role;
import ru.restaurant_voting.model.User;
import ru.restaurant_voting.repository.UserRepository;
import ru.restaurant_voting.util.ValidationUtil;
import ru.restaurant_voting.web.AuthUser;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping(value = AdminAccountController.URL_ADMIN_ACCOUNT, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
@Tag(name = "Admin account Controller")
public class AdminAccountController {
    public static final String URL_ADMIN_ACCOUNT = "/v1/api/admin/account";

    UserRepository userRepository;

    /**
     * @return authorized admin
     */
    @GetMapping(value = "/profile", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getAccount(@AuthenticationPrincipal AuthUser authUser) {
        log.info("get {}", authUser);
        return authUser.getUser();
    }

    /**
     * @return any authorized user by id
     */
    @GetMapping(value = "/{id}")
    public User getbyId(@PathVariable int id) {
        log.info("Admin request User by id:{}", id);
        return userRepository.findById(id).orElseThrow(
                () -> new IllegalRequestDataException("There is no restaurant with such id"));
    }

    /**
     * delete any user by id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete any user # {}", id);
        userRepository.deleteById(id);   //Interface: CrudRepository-PagingAndSortingRepository-JpaRepository
    }

    /**
     * post new user
     */
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<User> register(@Valid @RequestBody User user) {
        log.info("register new User {}", user);
        ValidationUtil.checkNew(user);
        user.setRoles(Set.of(Role.USER));
        user = userRepository.save(user);
        return ResponseEntity.ok(userRepository.save(user));
    }

    /**
     * update any user by id.
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody User user, @PathVariable int id) {
        log.info("update user id {}", id);
        User oldUser = userRepository.getByID(id).orElseThrow(() -> new IllegalRequestDataException("User with such id not present"));
        user.setId(id);
        if (user.getName() == null || user.getName().equals("")) user.setName(oldUser.getName());
        if (user.getLogin() == null || user.getLogin().equals("")) user.setPassword(oldUser.getLogin());
        if (user.getPassword() == null || user.getPassword().equals("")) user.setPassword(oldUser.getPassword());
        user.setRoles(oldUser.getRoles());
        userRepository.save(user);
    }
}
