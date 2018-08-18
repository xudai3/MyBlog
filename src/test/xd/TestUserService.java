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
@ContextConfiguration(locations = {"classpath:Bean.xml"})
public class TestUserService {
    @Autowired
    private UserService userService;
    @Test
    public void hasMatchUser(){

    }
    @Test
    public void testFindUserByUserName(){
    }
    @Test
    public void testAddLoginLog(){
    }
}
