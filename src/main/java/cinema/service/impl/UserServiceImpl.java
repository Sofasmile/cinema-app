package cinema.service.impl;

import java.util.Optional;

import cinema.dao.UserDao;
import cinema.model.User;
import cinema.util.HashUtil;
import cinema.lib.Inject;
import cinema.lib.Service;
import cinema.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;

    @Override
    public User add(User user) {
        user.setSalt(HashUtil.getSalt());
        user.setPassword(HashUtil.hashPassword(user.getPassword(), user.getSalt()));
        return userDao.add(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
