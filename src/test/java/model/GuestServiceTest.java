package model;

import org.junit.Before;
import org.junit.Test;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.ReportDao;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.impl.ConnectionPoolHolder;
import ua.training.model.entities.User;
import ua.training.model.service.GuestService;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GuestServiceTest {
    private GuestService guestService = new GuestService();
    private DaoFactory daoFactory = mock(DaoFactory.class);
    private ReportDao reportDao = mock(ReportDao.class);
    private UserDao userDao = mock(UserDao.class);
    private ConnectionPoolHolder connectionPool = mock(ConnectionPoolHolder.class);
    private Connection connection = mock(Connection.class);

    @Before
    public void setUp() throws Exception {
        Field daoFactoryField = guestService.getClass().getDeclaredField("daoFactory");
        daoFactoryField.setAccessible(true);
        daoFactoryField.set(guestService, daoFactory);

        Field connectionPoolField = guestService.getClass().getDeclaredField("connectionPool");
        connectionPoolField.setAccessible(true);
        connectionPoolField.set(guestService, connectionPool);

        when(connectionPool.getConnection()).thenReturn(connection);
        when(daoFactory.createReportDao(connection)).thenReturn(reportDao);
        when(daoFactory.createUserDao(connection)).thenReturn(userDao);

        when(userDao.findByUsernameAhdPassword("username", "password")).thenReturn(User.newBuilder().id(1L).build());
    }

    @Test
    public void getUser() {
        assertEquals(Optional.of(1L), Optional.of(guestService.getUser("username", "password").getId()));
    }

    @Test
    public void createUser() {
        User user = User.newBuilder().build();
        assertTrue(guestService.createUser(user, "password"));
    }

//    @Test
//    public void createUserAlreadyExists() throws Exception {
//        User user = User.newBuilder().username("username").build();
//        assertFalse(guestService.createUser(user, "password"));
//    }
}
