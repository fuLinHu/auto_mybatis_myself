package com.framk.autocode.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable String id) {
        //for debug
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "product id : " + id;
    }

    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable String id) {
        //for debug
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "order id : " + id;
    }

  /*  @Autowired
    private TestService testService;

    @RequestMapping("/pagetest")
    @ResponseBody
    public ResultMessage findPageBy(Test test){
        ResultMessage rm = new ResultMessage();
        try {
            PageInfo pageInfo = testService.findPageBy(test);
            System.out.println("程序被执行！====================");
            rm.setData(pageInfo);
        }catch (Exception e){
            rm.setStatus(Constant.STATUS_500);
            rm.setSuccess(Constant.FALSE);
            e.printStackTrace();
        }
        return rm;
    }

    @RequestMapping("/save")
    @ResponseBody
    public ResultMessage save(Test test){
        ResultMessage rm = new ResultMessage();
        try {
            testService.save(test);
        }catch (Exception e){
            rm.setStatus(Constant.STATUS_500);
            rm.setSuccess(Constant.FALSE);
            e.printStackTrace();
        }
        return rm;
    }
    @RequestMapping("/update")
    @ResponseBody
    public ResultMessage update(Test test){
        ResultMessage rm = new ResultMessage();
        try {
            testService.update(test);
        }catch (Exception e){
            rm.setStatus(Constant.STATUS_500);
            rm.setSuccess(Constant.FALSE);
            e.printStackTrace();
        }
        return rm;
    }
    @RequestMapping("/deletebyid")
    @ResponseBody
    public ResultMessage deleteById(String id){
        ResultMessage rm = new ResultMessage();
        try {
            testService.deleteById(id);
        }catch (Exception e){
            rm.setStatus(Constant.STATUS_500);
            rm.setSuccess(Constant.FALSE);
            e.printStackTrace();
        }
        return rm;
    }


    @RequestMapping("/page")
    @ResponseBody
    public ResultMessage test(Page page){
        ResultMessage rm = new ResultMessage();
        try {
            Iterable all = testService.findAll();
            System.out.println("程序被执行！====================");
            rm.setData(all);
        }catch (Exception e){
                rm.setData("ok");
                rm.setStatus(Constant.STATUS_500);
            rm.setSuccess(Constant.FALSE);
            e.printStackTrace();
        }
        return rm;
    }
    @RequestMapping("/web")
    public String webtest(){
        return "404";
    }


    *//*@RequestMapping("/save")
    @ResponseBody*//*
    *//*public ResultMessage save(Test test){
        ResultMessage rm = new ResultMessage();
        try{
            testService.save(test);
        }catch (Exception e){
            rm.setStatus(Constant.STATUS_500);
            rm.setSuccess(Constant.FALSE);
            e.printStackTrace();
        }
        return rm;
    }*//*
*/


}
