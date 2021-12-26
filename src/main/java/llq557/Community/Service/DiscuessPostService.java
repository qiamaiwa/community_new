package llq557.Community.Service;

import llq557.Community.Dao.DiscussPostMapper;
import llq557.Community.entity.DiscussPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscuessPostService {
    @Autowired
    private DiscussPostMapper discussPostMapper;

    public   List<DiscussPost>   findDiscussPosts(int userId,int offeset,int limit){
        return discussPostMapper.selectDiscussPosts(userId,offeset,limit);
    }
    public int findDiscussPostRows(int userId) {
        return discussPostMapper.selectDiscussPostRows(userId);
    }


}
