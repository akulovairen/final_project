package com.example.servletstest.service;

import com.example.servletstest.dao.UsersDao;
import com.example.servletstest.exception.CustomValidationException;
import com.example.servletstest.model.User;
import com.example.servletstest.exception.serviceException.user.UserNotFoundException;
import com.example.servletstest.util.LocalizedValidatorUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class UserServiceTest {
    @Mock
    private UsersDao usersDao;
    private UserService userService;

    public UserServiceTest() {
        MockitoAnnotations.initMocks(this);
        this.userService = new UserService(usersDao);
    }

    @Test
    void getUserByEmailSuccess() {

//        User userExpected = new User();
//        userExpected.setEmail("userExpected@mail.com");
//        userExpected.setId(1);

        User userExpected = new User.UserBuilder().withEmail("userExpected@mail.com").withId(1).build();

        given(usersDao.findByEmail(anyString())).willReturn(Optional.of(userExpected));

        User userActual = userService.getUserByEmail(anyString());

        assertEquals(userExpected.getEmail(), userActual.getEmail());
        assertEquals(userExpected.getId(), userActual.getId());

    }

    @Test
    void getUserByEmail_Failure() {

        when(usersDao.findByEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUserByEmail(anyString()));

    }

    @Test
    void userBlock_Success() {

        given(usersDao.userBlock(anyInt())).willReturn(true);

        boolean userExists = userService.userBlock(anyInt());

        assertThat(userExists).isTrue();
    }

    @Test
    void userBlock_Failure() {

        given(usersDao.userBlock(anyInt())).willReturn(false);

        boolean userExists = userService.userBlock(anyInt());

        assertThat(userExists).isFalse();
    }

    @Test
    void userUnblockSuccess() {

        given(usersDao.userUnblock(anyInt())).willReturn(true);

        boolean userExists = userService.userUnblock(anyInt());

        assertThat(userExists).isTrue();
    }

    @Test
    void userUnlock_Failure() {

        given(usersDao.userUnblock(anyInt())).willReturn(false);

        boolean userExists = userService.userUnblock(anyInt());

        assertThat(userExists).isFalse();
    }

    @Test
    void testFindAllTeacherSuccess() {
        User userExpected = new User(1, "Ira", "Akulova",LocalDate.of(2000,2,10), "akulova@example.com", "123", "teacher");
        List<User> expectedList = new ArrayList<>();
        expectedList.add(userExpected);

        given(usersDao.findAllTeacher(1, 1)).willReturn(expectedList);

        List<User> teachersActual = userService.findAllTeacher(1, 1);

        assertNotNull(teachersActual);
        assertEquals(expectedList.size(), teachersActual.size());

        User userActual = teachersActual.get(0);
        assertEquals(userExpected.getId(), userActual.getId());
        assertEquals(userExpected.getName(), userActual.getName());
        assertEquals(userExpected.getSurname(), userActual.getSurname());
        assertEquals(userExpected.getBirthday(), userActual.getBirthday());
        assertEquals(userExpected.getEmail(), userActual.getEmail());
        assertEquals(userExpected.getPassword(), userActual.getPassword());
        assertEquals(userExpected.getRole(), userActual.getRole());
        assertEquals(userExpected.getLocked(), userActual.getLocked());

    }

    @Test
    void getUserById() {
//        User userExpected = new User();
//        userExpected.setId(1);

        User userExpected = new User.UserBuilder().withId(1).build();

        given(usersDao.findUser(anyInt())).willReturn(Optional.of(userExpected));

        Optional<User> userActual = userService.getUserById(anyInt());
        assertTrue(userActual.isPresent());
        User userActualObject = userActual.get();

        assertEquals(userExpected.getId(), userActualObject.getId());
    }

    @Test
    void deleteUserSuccess() {
        given(usersDao.deleteUser(anyInt())).willReturn(true);
        boolean deleteUser = userService.deleteUser(anyInt());
        assertTrue(deleteUser);
    }

    @Test
    void deleteUserFailure() {
        given(usersDao.deleteUser(anyInt())).willReturn(false);
        boolean deleteUser = userService.deleteUser(anyInt());
        assertFalse(deleteUser);
    }


    @Test
    void findAllUsersSuccess() {
        User userExpected = new User(1, "Marina", "Malisheva", LocalDate.of(2000,5,20), "akulova@example.com", "123", "student");
        List<User> expectedList = new ArrayList<>();
        expectedList.add(userExpected);

        given(usersDao.findAllUsers(anyInt(), anyInt())).willReturn(expectedList);

        List<User> listActual = userService.findAllUsers(anyInt(), anyInt());

        assertNotNull(listActual);
        assertEquals(expectedList.size(), listActual.size());

        User userActual = listActual.get(0);
        assertEquals(userExpected.getId(), userActual.getId());
        assertEquals(userExpected.getName(), userActual.getName());
        assertEquals(userExpected.getSurname(), userActual.getSurname());
        assertEquals(userExpected.getBirthday(), userActual.getBirthday());
        assertEquals(userExpected.getEmail(), userActual.getEmail());
        assertEquals(userExpected.getPassword(), userActual.getPassword());
        assertEquals(userExpected.getRole(), userActual.getRole());
        assertEquals(userExpected.getLocked(), userActual.getLocked());
    }

    @Test
    void getNumberOfRowsTest() {
        given(usersDao.getNumberOfRows(anyString())).willReturn(3);
        int numberOfRowsActual = userService.getNumberOfRows(UsersDao.SQLTask.GET_ALL_USERS_COUNT.getQUERY());
        assertEquals(3,numberOfRowsActual);
    }

    @Test
    void updateUserSuccess(){
//        User user=new User();
//        user.setId(2);
//        user.setName("ivan");

        User user = new User.UserBuilder().withId(2).withName("ivan").build();
        given(usersDao.updateUser(any())).willReturn(user);
        User updateUser = userService.updateUser(2, "Ivan", "Famko", LocalDate.of(2000, 5, 10), "ivan@gmail.com", LocalizedValidatorUtil.UKRAINE_LOCALE);
        verify(usersDao).updateUser(any());
    }

    @Test
    void updateUserFailure(){
        try {
            userService.updateUser(2, "", "", LocalDate.now().plusDays(1L), "ivan",LocalizedValidatorUtil.UKRAINE_LOCALE);
            fail();
        }catch (CustomValidationException e){
            Map<String, String> errorsMap = e.getErrorsMap();
            assertEquals("Ім'я повинно мати довжину від 1 до 25 символів", errorsMap.get("name"));
            assertEquals("Прізвище повинно мати довжину від 1 до 40 символів",errorsMap.get("surname"));
            assertEquals("Дата народження має бути у минулому часі",errorsMap.get("birthday"));
            assertEquals("Вказано невалідний email",errorsMap.get("email"));
        }
    }

    @Test
    void updateUserEmailExceptionTest(){
//        User user=new User();
//        user.setId(1);
//        user.setName("ivan");
//        user.setSurname("lobo");
//        user.setBirthday(LocalDate.of(2000,2,4));
//        user.setEmail("ivan@gmail.com");
        User user = new User.UserBuilder().withId(1)
                .withName("ivan")
                .withSurname("lobo")
                .withBirthday(LocalDate.of(2000, 2, 4))
                .withEmail("ivan@gmail.com").build();

        when(usersDao.findByEmail(anyString())).thenReturn(Optional.of(user));
        try {
            User updateUser = userService.updateUser(2, "ivan", "Fomos", LocalDate.now().minusDays(1L), "ivan@gmail.com",LocalizedValidatorUtil.UKRAINE_LOCALE);
            fail();
        }catch (CustomValidationException e){
            Map<String, String> errorsMap = e.getErrorsMap();
            assertEquals("Користувач з таким email вже існує", errorsMap.get("email"));
        }


    }
}
