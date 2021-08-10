package cinema.security;

import java.util.Optional;

import cinema.exception.RegistrationException;
import cinema.exception.AuthenticationException;
import cinema.lib.Inject;
import cinema.lib.Service;
import cinema.model.User;
import cinema.service.ShoppingCartService;
import cinema.service.UserService;
import cinema.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private ShoppingCartService shoppingCartService;
    @Inject
    private UserService userService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        Optional<User> userFromDb = userService.findByEmail(email);
        if (userFromDb.isPresent() && matchPasswords(password, userFromDb.get())) {
            return userFromDb.get();
        }
        throw new AuthenticationException("Incorrect email or password!");
    }

    @Override
    public User register(String email, String password) throws RegistrationException {
        if (userService.findByEmail(email).isEmpty()) {
            User user = userService.add(new User(email, password));
            shoppingCartService.registerNewShoppingCart(user);
            return user;
        }
        throw new RegistrationException("User with this name "
                + email + " already exists!");
    }

    private boolean matchPasswords(String rawPassword, User userFromDb) {
        String hashedPassword = HashUtil.hashPassword(rawPassword, userFromDb.getSalt());
        return hashedPassword.equals(userFromDb.getPassword());
    }
}
