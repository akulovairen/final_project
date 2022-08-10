package com.example.servletstest.service.resthandlers;

import com.example.servletstest.dao.GradebookDao;
import com.example.servletstest.dao.UsersDao;
import com.example.servletstest.exception.CustomValidationException;
import com.example.servletstest.model.User;
import com.example.servletstest.util.LocalizedValidatorUtil;
import com.example.servletstest.util.RequestUtils;
import org.apache.commons.codec.digest.DigestUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 */
public class UserRegistrationService {
    private final UsersDao usersDao;

    /**
     * Create user registration service.
     *
     * @param usersDao - {@link UsersDao} model
     */
    public UserRegistrationService(UsersDao usersDao) {
        this.usersDao=usersDao;
    }

    /**
     * Registers user. Data validation.
     *
     * @param name - name
     * @param surname - surname
     * @param email - email
     * @param password - password
     * @param role - role
     * @param birthday - birthday
     * @return {@link User} id
     * @throws CustomValidationException - if validation fails
     */
    public int registerUser(String name, String surname, String email,String password,String repeatPassword, String role, LocalDate birthday, Locale locale) throws CustomValidationException {

        final Optional<User> foundUserByEmail = usersDao.findByEmail(email);

//        User user = new User();
//        user.setName(name);
//        user.setPassword(DigestUtils.md5Hex(password));
//        user.setSurname(surname);
//        user.setEmail(email);
//        user.setBirthday(birthday);
//        user.setRole(role);
//        user.setLocked(false);

        User user = new User.UserBuilder().withName(name)
                .withPassword(DigestUtils.md5Hex(password))
                .withSurname(surname)
                .withEmail(email).withBirthday(birthday)
                .withRole(role).withLocked(false).build();

        Validator validator = LocalizedValidatorUtil.getValidatorByLocale(locale);
        Set<ConstraintViolation<User>> constraintViolationSet = validator.validate(user);
        Map<String, String> errorMap =
                constraintViolationSet
                        .stream()
                        .collect(Collectors.toMap(userConstraintViolation -> userConstraintViolation.getPropertyPath().toString(), ConstraintViolation::getMessage));

        if(!password.equals(repeatPassword)){
            errorMap.put("password_repeat", LocalizedValidatorUtil.getLocalizationValue("register.repeatPassword.invalid", locale));
        }
        if (!errorMap.isEmpty() || foundUserByEmail.isPresent()) {
            if (foundUserByEmail.isPresent()) {
                errorMap.put("email", LocalizedValidatorUtil.getLocalizationValue("register.repeatEmail.invalid", locale));
            }
            throw new CustomValidationException(errorMap);
        }

        return usersDao.createUser(user);
    }
}
