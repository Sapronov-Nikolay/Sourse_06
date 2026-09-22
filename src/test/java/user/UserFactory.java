package user;

/**
 * Фабрика пользователей. Создаёт готовых юзеров для тестов.
 */
public final class UserFactory {
    private UserFactory() {
    }
    
    // === ВАЛИДНЫЕ ПОЛЬЗОВАТЕЛИ (могут войти в систему) ===
    
    /** Все поля верные согласно документации */
    public static User standardUser() {
        return new User("standard_user", "secret_sauce");
    }
    public static User problemUser() {
        return new User("problem_user", "secret_sauce");
    }
    public static User performanceGlitchUser() {
        return new User("performance_glitch_user", "secret_sauce");
    }
    public static User errorUser() {
        return new User("error_user", "secret_sauce");
    }
    public  static User visualUser() {
        return new User("visual_user", "secret_sauce");
    }
    
    // === ЗАБЛОКИРОВАННЫЙ ПОЛЬЗОВАТЕЛЬ (данные верные, но вход запрещён) ===
    public static User lockedOutUser() {
        return new User("locked_out_user", "secret_sauce");
    }
    
    // === НЕВАЛИДНЫЕ КОМБИНАЦИИ ДЛЯ НЕГАТИВНЫХ ТЕСТОВ ===
    
    /** Неверный логин + неверный пароль */
    public static User invalidUser() {
        return new User("invalid_user", "invalid_password");
    }
    
    /** Неверный логин + верный пароль */
    public static User invalidUsernameValidPassword() {
        return new User("invalid_user", "secret_sauce");
    }
    
    /** Пустой логин + верный пароль */
    public static User emptyUsernameValidPassword() {
        return new User("", "secret_sauce");
    }
    
    /** Верный логин + пустой пароль */
    public static User validUsernameEmptyPassword() {
        return new User("standard_user", "");
    }
    
    /** Пустой логин + неверный пароль */
    public static User emptyUsernameInvalidPassword() {
        return new User("", "invalid_password");
    }
    
    /** Неверный логин + пустой пароль */
    public static User invalidUsernameEmptyPassword() {
        return new User("invalid_user", "");
    }
    
    /** Оба поля пустые */
    public static User emptyFields() {
        return new User("", "");
    }
}
