package com.example.dreamcatch.controller;

import com.example.dreamcatch.model.User;
import com.example.dreamcatch.service.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUserService userService;
    private User user1;
    private User user2;

    @BeforeEach
    public void setUp() {
        user1 = new User(1L, "user1", "password1", "salt1");
        user2 = new User(2L, "user2", "password2", "salt2");
    }

    @Test
    public void testGetAllUsers() throws Exception {
        List<User> users = Arrays.asList(user1, user2);
        when(userService.getAll()).thenReturn(users);

        mockMvc.perform(get("/user/get"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(user1.getId()))
                .andExpect(jsonPath("$[0].username").value(user1.getUsername()))
                .andExpect(jsonPath("$[1].id").value(user2.getId()))
                .andExpect(jsonPath("$[1].username").value(user2.getUsername()));

        verify(userService, times(1)).getAll();
    }

    @Test
    public void testGetUserById() throws Exception {
        when(userService.getById(user1.getId())).thenReturn(ResponseEntity.ok(user1));

        mockMvc.perform(get("/user/get/{id}", user1.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user1.getId()))
                .andExpect(jsonPath("$.username").value(user1.getUsername()));

        verify(userService, times(1)).getById(user1.getId());
    }

    @Test
    public void testRegisterUser() throws Exception {
        when(userService.register(any(User.class))).thenReturn(
                ResponseEntity.status(HttpStatus.CREATED).body(user1));

        mockMvc.perform(post("/user/register")
                        .contentType("application/json")
                        .content(
                                "{\"id\": 1, \"username\": \"user1\", \"password\": \"password1\", \"salt\": \"salt1\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(user1.getId()))
                .andExpect(jsonPath("$.username").value(user1.getUsername()));

        verify(userService, times(1)).register(any(User.class));
    }

    @Test
    public void testLoginUser() throws Exception {
        when(userService.login(any(User.class))).thenReturn(ResponseEntity.ok(user1));

        mockMvc.perform(post("/user/login")
                        .contentType("application/json")
                        .content("{\"username\": \"user1\", \"password\": \"password1\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user1.getId()))
                .andExpect(jsonPath("$.username").value(user1.getUsername()));

        verify(userService, times(1)).login(any(User.class));
    }

    @Test
    public void testEditUser() throws Exception {
        when(userService.edit(anyLong(), any(User.class))).thenReturn(ResponseEntity.ok(user1));

        mockMvc.perform(put("/user/edit/{id}", user1.getId())
                        .contentType("application/json")
                        .content("{\"username\": \"user1\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user1.getId()))
                .andExpect(jsonPath("$.username").value(user1.getUsername()));

        verify(userService, times(1)).edit(anyLong(), any(User.class));
    }

    @Test
    public void testDeleteUser() throws Exception {
        when(userService.delete(user1.getId())).thenReturn(ResponseEntity.noContent().build());

        mockMvc.perform(delete("/user/delete/{id}", user1.getId()))
                .andExpect(status().isNoContent());

        verify(userService, times(1)).delete(user1.getId());
    }
}