package dev.rozhkova.ibank.service;

import dev.rozhkova.ibank.entity.UserEntity;
import dev.rozhkova.ibank.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findByLogin() {
    }

    @Test
    public void create() throws Exception{

    }

    @Test
    public void getAllUsers() {
    }

    @Test
    public void getUserById() {
    }

    @Test
    public void removeUser() {
    }

    @Test
    public void updateUser() {
    }

    @Test
    public void findByLogin1() {
    }

    @Test
    public void create1() {
    }

    @Test
    public void getAllUsers1() {
    }

    @Test
    public void getUserById1() {
    }

    @Test
    public void removeUser1() {
    }

    @Test
    public void updateUser1() {
    }
}