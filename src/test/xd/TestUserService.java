package xd;

import com.xd.domain.User;
import com.xd.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:../../main/resources/Bean.xml"})
public class TestUserService {
    @Autowired
    private UserService userService;
    @Test
    public void hasMatchUser(){
        boolean b1 = userService.hasMatchUser("xudai", "a123");
        boolean b2 = userService.hasMatchUser("admin", "a123");
        System.out.println("b1:" + b1 + " b2:" + b2);
        assertTrue(b1);
        assertTrue(!b2);
    }
    @Test
    public void testFindUserByUserName(){
        User user = userService.findUserByName("admin");
        assertEquals(user.getUserName(), "admin");
    }
    @Test
    public void testAddLoginLog(){
        User user = userService.findUserByName("admin");
        user.setUserId(1);
        user.setUserName("admin");
        user.setLastIp("192.168.12.7");
        user.setLastVisit((new Date()));
        userService.loginSuccess(user);
    }
}
