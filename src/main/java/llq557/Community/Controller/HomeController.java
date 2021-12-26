package llq557.Community.Controller;

import llq557.Community.Service.DiscuessPostService;
import llq557.Community.Service.UserService;
import llq557.Community.entity.DiscussPost;
import llq557.Community.entity.Page;
import llq557.Community.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private DiscuessPostService discussPostService;
    @Autowired
    private UserService userService;

    @RequestMapping(path = "/index",method = RequestMethod.GET)
    public String getIndexPage(Model model,Page page){
        //方法调用前, Springmvc会自动实例化Mode和Page,并将Page注入Model
        // 所以,在 thymeleaf中可以直接访问Page对象中的数据




        page.setRows(discussPostService.findDiscussPostRows(111));//数据总数
        page.setPath("/index");

        List<DiscussPost> list = discussPostService.findDiscussPosts(111,page.getOffset(),page.getLimit());


        List<Map<String, Object>> discussPosts = new ArrayList<>();
        if (list != null) {
            for (DiscussPost post : list) {
                Map<String, Object> map = new HashMap<>();
                map.put("post", post);
                User user = userService.findUserById(post.getUserId());
                map.put("user", user);
                discussPosts.add(map);
            }
        }

        model.addAttribute("discussPosts", discussPosts);
        return  "index";
    }


}
