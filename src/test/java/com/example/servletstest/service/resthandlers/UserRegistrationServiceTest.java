package com.example.servletstest.service.resthandlers;

import com.example.servletstest.dao.CourseUserDao;
import com.example.servletstest.dao.UsersDao;
import com.example.servletstest.exception.CustomValidationException;
import com.example.servletstest.model.User;
import com.example.servletstest.service.CourseUserService;
import com.example.servletstest.service.UserService;
import com.example.servletstest.util.LocalizedValidatorUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

class UserRegistrationServiceTest {
    @Mock
    private UsersDao usersDao;
    private UserRegistrationService userRegistrationService;

    public UserRegistrationServiceTest() {
        MockitoAnnotations.initMocks(this);
        this.userRegistrationService = new UserRegistrationService(usersDao);
    }

    @Test
    void registerUserSuccess() {
        when(usersDao.findByEmail(anyString())).thenReturn(Optional.empty());
        when(usersDao.createUser(any())).thenReturn(23);
        int registerUser = userRegistrationService.registerUser("Masha","Pozina","ma@gmail.com","234", "234","student", LocalDate.now().minusDays(1L), LocalizedValidatorUtil.UKRAINE_LOCALE);
        assertEquals(23,registerUser);
    }

    @Test
    void registerUserFailure(){
//        User user=new User();
//        user.setEmail("masha@gmail.com");

        User user = new User.UserBuilder().withEmail("masha@gmail.com").build();
        when(usersDao.findByEmail(anyString())).thenReturn(Optional.of(user));
        try {
            userRegistrationService.registerUser("Masha","Pozina","masha@gmail.com","123","123","student", LocalDate.now().minusDays(1L), LocalizedValidatorUtil.UKRAINE_LOCALE);
            fail();
        }catch (CustomValidationException e){
            Map<String, String> errorsMap = e.getErrorsMap();
            assertEquals("Користувач з таким email вже зареєстрований", errorsMap.get("email"));
        }
    }

    @Test
    void registerUserFailureValidation(){
        try {
            userRegistrationService.registerUser("","","masha","123","123","student", LocalDate.now().plusDays(1L), LocalizedValidatorUtil.UKRAINE_LOCALE);
            fail();
        }catch (CustomValidationException e){
            Map<String, String> errorsMap = e.getErrorsMap();assertEquals("Ім'я повинно мати довжину від 1 до 25 символів", errorsMap.get("name"));
            assertEquals("Прізвище повинно мати довжину від 1 до 40 символів",errorsMap.get("surname"));
            assertEquals("Дата народження повинна бути у минулому часі",errorsMap.get("birthday"));
            assertEquals("Вказано невалідний email",errorsMap.get("email"));
        }
    }

}