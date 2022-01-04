package next.dao;

import static org.junit.Assert.assertEquals;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import core.jdbc.ConnectionManager;
import next.model.User;

public class UserDaoTest {




    @Before
    public void setup() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("jwp.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }




    @Test
    public void crud() throws Exception {
        User expected = new User("userId", "password", "name", "javajigi@email.com");
        UserDao userDao = new UserDao();
        userDao.insert(expected);
        User actual = userDao.findByUserId(expected.getUserId());
        assertEquals(expected, actual);

        expected.update(new User("userId", "password2", "name2", "sanjigi@email.com"));
        userDao.update(expected);
        actual = userDao.findByUserId(expected.getUserId());
        assertEquals(expected, actual);
    }

    @Test
    public void findAll() throws Exception {
        UserDao userDao = new UserDao();
        userDao.insert(new User(getRandomStr(10),getRandomStr(10),getRandomStr(10),getRandomStr(10)));

        List<User> users = userDao.findAll();
        assertEquals(2, users.size());
    }

    private String getRandomStr(int size){
        byte[] array = new byte[size]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));

        return array.toString();
    }
}