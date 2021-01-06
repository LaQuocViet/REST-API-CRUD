package com.training.controller;

import com.training.model.Account;
import com.training.service.AccountService;
import com.training.utils.Utils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    private AccountService accountService;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void loginOkAPI() throws Exception {
        Optional<Account> account = Optional.of(new Account("laquocviet9x@gmail.com", "12345678"));
        Mockito.doReturn(account).when(accountService).findAccount(Mockito.anyString(), Mockito.anyString());
        mockMvc.perform(
                MockMvcRequestBuilders
                .post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Utils.convertToJsonString(account.get()))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(account.get().getEmail() + "-" + account.get().getPassword()))
                .andDo(print())
                .andReturn();
    }

    @Test
    public void loginNoContentAPI() throws Exception {
        Optional<Account> account = Optional.of(new Account("laquocviet9x@gmail.com", "12345678"));
        Mockito.doReturn(account).when(accountService).findAccount(Mockito.anyString(), Mockito.anyString());
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utils.convertToJsonString(account.get()))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(account.get().getEmail() + "-" + account.get().getPassword()))
                .andDo(print())
                .andReturn();
    }
}

