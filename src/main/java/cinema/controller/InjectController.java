package cinema.controller;

import java.util.Set;
import cinema.model.Role;
import cinema.model.RoleName;
import cinema.model.User;
import cinema.service.AuthenticationService;
import cinema.service.RoleService;
import cinema.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InjectController {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final RoleService roleService;

    public InjectController(UserService userService, AuthenticationService authenticationService,
                            RoleService roleService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.roleService = roleService;
    }

    @GetMapping("/inject")
    public String injectData() {
        Role adminRole = new Role();
        adminRole.setRoleName(RoleName.ADMIN);
        roleService.add(adminRole);

        Role userRole = new Role();
        userRole.setRoleName(RoleName.USER);
        roleService.add(userRole);

        User adminUser = new User();
        adminUser.setEmail("bob@gmail.com");
        adminUser.setPassword("1234");
        adminUser.setRoles(Set.of(roleService.getRoleByName(String.valueOf(RoleName.ADMIN))));
        userService.add(adminUser);

        User user = new User();
        user.setEmail("alice@gmail.com");
        user.setPassword("1234");
        user.setRoles(Set.of(roleService.getRoleByName(String.valueOf(RoleName.USER))));
        userService.add(user);

        return "Done!!!";
    }
}
