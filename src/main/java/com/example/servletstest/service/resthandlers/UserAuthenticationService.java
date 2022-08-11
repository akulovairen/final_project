package com.example.servletstest.service.resthandlers;

import com.example.servletstest.dao.UsersDao;
import com.example.servletstest.model.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Optional;

/**
 * Business logic for user authentication functionality
 */
public class UserAuthenticationService {
    private UsersDao userDaoJDBC = new UsersDao();

    public boolean isAuthenticated(String email, String inputPassword) {
        final Optional<User> optionalUser = userDaoJDBC.findByEmail(email);
        if (optionalUser.isEmpty()) return false;
        final User user = optionalUser.get();

        return user.getPassword().equals(DigestUtils.md5Hex(inputPassword));

    }
}
