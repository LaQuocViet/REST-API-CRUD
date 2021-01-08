package com.training.controller;

import com.training.model.Account;
import com.training.model.User;
import com.training.service.AccountService;
import com.training.service.UserService;
import com.training.utils.Utils;
import javafx.scene.input.KeyCode;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    private UserService userService;

    @MockBean
    private AccountService accountService;

    private List<User> users;

    private void addUsers() {
        users = new ArrayList<>();
        users.add(new User("La", "Quoc Viet", "1999/08/01", true, "Hung Yen",
                "laquocviet9x@gmail.com", "Student", "0987434342", 1));
        users.add(new User("La", "Quoc Viet", "1999/08/01", true, "Hung Yen",
                "laquocviet9x@gmail.com", "Student", "0987434342", 1));
        users.add(new User("La", "Quoc Viet", "1999/08/01", true, "Hung Yen",
                "laquocviet9x@gmail.com", "Student", "0987434342", 1));

    }

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        addUsers();
    }

    // get
    @Test
    public void getAllUsers() throws Exception {

        Mockito.doReturn(users).when(userService).getAllUser();
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void getAllUsersById() throws Exception {
        Optional<User> user = Optional.of(users.get(0));
        Mockito.doReturn(user).when(userService).findUserById(1);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/users/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andDo(print())
                .andReturn();
    }


    // post
    @Test
    public void createUser() throws Exception {
        User user = new User("La", "Quoc Viet", "1999/08/01", true, "Hung Yen",
                "laquocviet9x@gmail.com", "Student", "0987434342", 1);
        Mockito.doReturn(user).when(userService).saveUser(user);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utils.convertToJsonString(user))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void createUserFirstNameInvalid() throws Exception {
        User user = new User(null, "Quoc Viet", "1999/08/01", true, "Hung Yen",
                "laquocviet9x@gmail.com", "Student", "0987434342", 1);
        Mockito.doReturn(user).when(userService).saveUser(user);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utils.convertToJsonString(user))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void createUserLastNameInvalid() throws Exception {
        User user = new User("La", null, "1999/08/01", true, "Hung Yen",
                "laquocviet9x@gmail.com", "Student", "0987434342", 1);
        Mockito.doReturn(user).when(userService).saveUser(user);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utils.convertToJsonString(user))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(print())
                .andReturn();
    }

    // put
//    @Test
//    public void update() throws Exception {
//        User user = new User("La", null, "1999/08/01", true, "Hung Yen",
//                "laquocviet9x@gmail.com", "Student", "0987434342", 1);
//        Mockito.doReturn(user).when(userService).u(user);
//        mockMvc.perform(
//                MockMvcRequestBuilders
//                        .post("/users")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(Utils.convertToJsonString(user))
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isBadRequest())
//                .andDo(print())
//                .andReturn();
//    }


    // delete
    @Test
    public void deleteAllUsers() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void deleteUserById() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/users/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(print())
                .andReturn();
    }
}