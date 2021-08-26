package cinema.service.impl;

import java.util.Set;

import cinema.model.RoleName;
import cinema.model.User;
import cinema.service.RoleService;
import cinema.service.UserService;
import cinema.service.AuthenticationService;
import cinema.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final RoleService roleService;

    public AuthenticationServiceImpl(UserService userService,
                                     ShoppingCartService shoppingCartService,
                                     RoleService roleService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.roleService = roleService;
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(Set.of(roleService.getRoleByName(String.valueOf(RoleName.ADMIN))));
        userService.add(user);
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }
}
