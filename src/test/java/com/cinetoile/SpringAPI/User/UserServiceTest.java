package com.cinetoile.SpringAPI.User;

import com.cinetoile.SpringAPI.NotFoundException;
import com.cinetoile.SpringAPI.dto.dtoIn.LoginDTOIn;
import com.cinetoile.SpringAPI.dto.dtoIn.SignupDTOIn;
import com.cinetoile.SpringAPI.dto.dtoOut.UserDTOOut;
import com.cinetoile.SpringAPI.exceptions.ForbiddenException;
import com.cinetoile.SpringAPI.models.SessionEntity;
import com.cinetoile.SpringAPI.models.UserEntity;
import com.cinetoile.SpringAPI.repository.UserRepository;
import com.cinetoile.SpringAPI.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@AutoConfigureMockMvc
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    UserRepository repository;

    @InjectMocks
    UserService service;

    List<String> fakeRoles = Arrays.asList("admin", "moderator");

    UserEntity fakeUser = new UserEntity("fakeUsername", "fakeLastname", "fakeFristname", "fake-email@gmail.com",
            "fakePassword", 1);
    List<UserEntity> fakeUserList = Arrays.asList(fakeUser, fakeUser);
    SignupDTOIn fakeSignUp = new SignupDTOIn("firstname", "lastname", "username", "email@gmail.com", fakeRoles,
            "password", 0);

    @Before
    public void SetUp() {
        Mockito.when(repository.findAll()).thenReturn(fakeUserList);
    }

    @Test
    public void wrongRoleWhenSignUp() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            this.service.signup(fakeSignUp);
        });
    }

    @Test
    public void findAll() {
        List<UserDTOOut> users = service.findAll();
        Assert.assertEquals("", users.size(), fakeUserList.size());
    }

    @Test
    public void wrongUsername() {
        final String username = "falseLogin";
        final String password = "df";
        Assertions.assertThrows(NotFoundException.class, () -> {
            this.service.signin(new LoginDTOIn(username, password));
        });
    }

    @Test
    public void nullUsername() {
        final String username = null;
        final String password = "df";
        Assertions.assertThrows(NotFoundException.class,
                () -> this.service.signin(new LoginDTOIn(username, password)));
    }

    @Test
    public void emptyUsername() {
        final String username = "";
        final String password = "df";
        Assertions.assertThrows(NotFoundException.class,
                () -> this.service.signin(new LoginDTOIn(username, password)));
    }

    @Test
    public void wrongPwd() {
        final String username = "df";
        final String password = "wrongPwd";
        Assertions.assertThrows(NotFoundException.class, () -> this.service.signin(new LoginDTOIn(username,
                password)));
    }

    @Test
    public void nullPwd() {
        final String username = "df";
        final String password = null;
        Assertions.assertThrows(NotFoundException.class,
                () -> this.service.signin(new LoginDTOIn(username, password)));

    }

    @Test
    public void emptyPwd() {
        final String username = "df";
        final String password = "";
        Assertions.assertThrows(NotFoundException.class,
                () -> this.service.signin(new LoginDTOIn(username, password)));

    }
}