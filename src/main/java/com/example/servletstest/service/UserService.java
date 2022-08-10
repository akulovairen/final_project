package com.example.servletstest.service;

import com.example.servletstest.dao.UsersDao;
import com.example.servletstest.exception.CustomValidationException;
import com.example.servletstest.exception.serviceException.user.UserNotFoundException;
import com.example.servletstest.model.User;
import com.example.servletstest.util.LocalizedValidatorUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Business logic for user functionality
 */
public class UserService {
    private UsersDao usersDao = new UsersDao();
    private Logger log = LogManager.getLogger(CourseService.class);
//    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//    private final Validator validator = factory.getValidator();

    /**
     * Create user service.
     *
     * @param usersDao - {@link UsersDao} model
     */
    public UserService(UsersDao usersDao) {
        this.usersDao=usersDao;
    }

    public User getUserByEmail(String email) {
        Optional<User> user = usersDao.findByEmail(email);
        return user.orElseThrow(UserNotFoundException::new);
    }

    public Optional<User> getUserById(int userId) {
        log.info("Getting user by id={}", userId);
        return usersDao.findUser(userId);
    }

    public boolean userBlock(int userId) {
        log.info("User blocking");
        return usersDao.userBlock(userId);
    }

    public boolean userUnblock(int userId) {
        log.info("User unblocking");
        return usersDao.userUnblock(userId);
    }

    public boolean deleteUser(int userId) {
        log.info("Deleting user by userId={}", userId);
        return usersDao.deleteUser(userId);
    }

    /**
     * Finds all teachers.
     *
     * @param offset
     * @param limit
     * @return {@link List<User>} with sorting by surname
     */
    public List<User> findAllTeacher(int offset, int limit) {
        log.info("Getting list of teachers");
        List<User> allTeacher = usersDao.findAllTeacher(offset, limit);
        allTeacher.sort(Comparator.comparing(User::getSurname));
        return allTeacher;
    }

    public int getNumberOfRows(String sql) {
        return usersDao.getNumberOfRows(sql);
    }

    /**
     * Updates user. Data validation.
     *
     * @param id - user id
     * @param name - user name
     * @param surname - user surname
     * @param birthday - user birthday
     * @param email - user email
     * @return {@link User} model
     * @throws CustomValidationException - if validation fails
     */
    public User updateUser(int id, String name, String surname, LocalDate birthday, String email, Locale locale) throws CustomValidationException {
        log.info("Updating user with id={},name={},surname={},birthday={}", id, name, surname, birthday);
//        User user = new User();
//        user.setName(name);
//        user.setSurname(surname);
//        user.setBirthday(birthday);
//        user.setId(id);
//        user.setEmail(email);

        User user = new User.UserBuilder()
                .withName(name)
                .withSurname(surname)
                .withBirthday(birthday)
                .withId(id).withEmail(email).build();

        Validator validator = LocalizedValidatorUtil.getValidatorByLocale(locale);
        Set<ConstraintViolation<User>> constraintViolationSet = validator.validate(user);
        Map<String, String> errorMap =
                constraintViolationSet
                        .stream()
                        .collect(Collectors.toMap(userConstraintViolation -> userConstraintViolation.getPropertyPath().toString(), ConstraintViolation::getMessage));

        Optional<User> foundUser = usersDao.findByEmail(email);
        if (foundUser.isPresent() && foundUser.get().getId() != id) {
            errorMap.put("email", LocalizedValidatorUtil.getLocalizationValue("register.repeatEmail.invalid", locale));
        }
        if (!errorMap.isEmpty() ) {
            throw new CustomValidationException(errorMap);
        }
        return usersDao.updateUser(user);
    }

    /**
     * Finds all users.
     *
     * @param offset
     * @param limit
     * @return {@link List<User>} with sorting by surname
     */
    public List<User> findAllUsers(int offset, int limit) {
        log.info("Getting all users");
        List<User> allUsers = usersDao.findAllUsers(offset, limit);
        allUsers.sort(Comparator.comparing(User::getSurname));
        return allUsers;
    }

    public List<User> searchTeacher(String text) {
        return usersDao.searchTeacher(text);
    }
}
