package llq557.Community.Controller;

import llq557.Community.Dao.StudentDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Controller
//@RequestMapping("alpha")
public class AlphaController {

    @RequestMapping("/hello")
    @ResponseBody
    public  String sayHello(){
        return "圣诞快乐！";
    }


    @RequestMapping("/http")
    public  void http(HttpServletResponse response, HttpServletRequest request) throws IOException {

        //获取请求数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> enumeration=request.getHeaderNames();
        while(enumeration.hasMoreElements()){
            String name=enumeration.nextElement();
            String  value=request.getHeader(name);
            System.out.println(name+":"+value);
        }

        System.out.println(request.getParameter("code"));

        //返回响应数据
        response.setContentType("text/html;charset=utf-8");

        try{
            PrintWriter printWriter=response.getWriter();
            printWriter.write("<h1>牛客网</h1>");}
        catch (Exception e){
            e.printStackTrace();
        }
    }

    // get请求
    @RequestMapping (path="/student",method= RequestMethod.GET)
    @ResponseBody
    public  String getStudent(@RequestParam(name = "current",required = false,defaultValue = "1") int current,
                              @RequestParam(name = "limit",required = false,defaultValue = "20") int limit){
        System.out.println(current);
        System.out.println(limit);
        return  "some students";
    }


    @RequestMapping (path="/student/{id}",method= RequestMethod.GET)
    @ResponseBody
    public  String getAStudent(@PathVariable(name = "id") int id,HttpServletRequest request){
        System.out.println(request.getServletPath());
        System.out.println(id);

        return  "A students";
    }

    // post请求
    @RequestMapping (path="/student",method= RequestMethod.POST)
    @ResponseBody
    public  String saveStudent(String name, int age){
        StudentDao studentDao=new StudentDao();
        studentDao.setAge(age);
        studentDao.setName(name);
        return  "succeed!"+studentDao.toString();

    }
    //响应html请求-1
    @RequestMapping(path = "/teacher",method = RequestMethod.GET)
    public  ModelAndView getTeacher(){
        ModelAndView mv=new ModelAndView();
        mv.addObject("name","张三");
        mv.addObject("age","30");
        mv.setViewName("/view");
        return mv;
    }
    //响应html请求-2
    @RequestMapping(path = "/school",method = RequestMethod.GET)
    public  String getSchool(Model model){
        model.addAttribute("name","Peking");
        model.addAttribute("age","120");
        return  "/view";

    }

    //响应异步请求
    //java对象->JSON字符串->JS对象

    @RequestMapping(path = "/emp",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getEmp(){

        Map<String,Object> map=new HashMap<>();
        map.put("name","llq");
        map.put("age",25);
        map.put("salary",900000.00);
        return map;
        //服务器将java对象转化成一个json字符串并返回 {"name":"llq","salary":900000.0,"age":25}

    }
}
