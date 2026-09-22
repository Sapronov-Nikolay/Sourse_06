package user;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Модель пользователя: логин и пароль.
 */
@Getter
@AllArgsConstructor
public class User {
    private final String username;
    private final String password;
}
