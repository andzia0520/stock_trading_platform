/*
package com.kodilla.stock_trading_platform.controller;

import com.google.gson.Gson;
import com.kodilla.stock_trading_platform.domain.User;
import com.kodilla.stock_trading_platform.domain.UserDto;
import com.kodilla.stock_trading_platform.mapper.UserMapper;
import com.kodilla.stock_trading_platform.service.UserAlreadyExistException;
import com.kodilla.stock_trading_platform.service.UserDbService;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDbService userDbService;

    @MockBean
    private UserMapper userMapper;

    @Test
    public void shouldCreateUser() throws Exception {
        //Given
        User user = new User(1L, "testUser", "test@test.pl");
        UserDto userDto = new UserDto(1L, "testUserDto", "testDto@test.pl");

        when(userDbService.saveUser(userMapper.mapToUser(userDto))).thenReturn(user);
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto);

        //When & Then
        mockMvc.perform(post("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    */
/*@Test
    public void shouldUpdateUser() throws Exception {
        //Given
        User user = new User(1L, "testUser", "test@test.pl");
        UserDto userDto = new UserDto(1L, "testUserDto", "testDto@test.pl");

        when(userDbService.updateUser(userMapper.mapToUser(userDto))).thenReturn(user);
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto);

        //When & Then
        mockMvc.perform(put("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.login", is("Test")))
                .andExpect(jsonPath("$.email", is("testDto@test.pl")));
    }*//*


    @Test
    public void shouldDeleteTask() throws Exception {
        //Given
        User user = new User(1L, "testUser", "test@test.pl");
        Long id = user.getId();
        userDbService.deleteById(id);

        //When & Then
        mockMvc.perform(delete("/v1/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .param("userId", "1"))
                .andExpect(status().isOk());
    }
    @Test
    public void shouldGetUserById() throws Exception {
        //Given
        User user = new User(1L, "testUser", "test@test.pl");
        //Optional<User> optionalUser = Optional.of(user);
        UserDto userDto = new UserDto(1L, "testUserDto", "testDto@test.pl");
        long id = user.getId();

        when(userDbService.getUserById(id)).thenReturn(user);
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);

        //When & Then
        mockMvc.perform(get("/v1/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .param("userId", "1"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.login", is("testUserDto")))
                .andExpect(jsonPath("$.email", is("testDto@test.pl")));
    }

   */
/* @Test
    public void shouldGetUserByEmail() throws Exception {
        //Given
        User user = new User(1L, "testUser", "test@test.pl");
        Optional<User> optionalUser = Optional.of(user);
        UserDto userDto = new UserDto(1L, "testUserDto", "testDto@test.pl");
        long id = user.getId();

        when(userDbService.getUserById(id)).thenReturn(optionalUser);
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);

        //When & Then
        mockMvc.perform(get("/v1/usersByMail/1")
                .contentType(MediaType.APPLICATION_JSON)
                .param("email", "testDto@test.pl"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.login", is("testUserDto")))
                .andExpect(jsonPath("$.email", is("testDto@test.pl")));
    }*//*

}
*/
