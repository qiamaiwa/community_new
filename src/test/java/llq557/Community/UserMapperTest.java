package llq557.Community;

import llq557.Community.Dao.DiscussPostMapper;
import llq557.Community.Dao.UserMapper;
import llq557.Community.entity.DiscussPost;
import llq557.Community.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CommunityApplication.class)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DiscussPostMapper discussPostMapper;


    @Test
    public  void  testSelect(){
        User user=userMapper.selectById(101);
        System.out.println(user);
//        Java直接调用Dao中的Mapper接口，具体的实现语句在Resource的Mapper.xml中。
    }

    @Test
    public  void  testSelectPost(){
        List<DiscussPost> posts=discussPostMapper.selectDiscussPosts(111,0,10);
        System.out.println(posts.size());
        for(DiscussPost ch:posts){
            System.out.println(ch.toString());
        }

        int rows=discussPostMapper.selectDiscussPostRows(111);
        System.out.println(rows);
//        Java直接调用Dao中的Mapper接口，具体的实现语句在Resource的Mapper.xml中。
    }

}
