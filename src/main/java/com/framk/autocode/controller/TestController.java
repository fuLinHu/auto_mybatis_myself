package com.framk.autocode.controller;


import com.framk.autocode.entity.Test;
import com.framk.autocode.publicmoduel.Entity.Constant;
import com.framk.autocode.publicmoduel.Entity.Page;
import com.framk.autocode.publicmoduel.Entity.ResultMessage;
import com.framk.autocode.service.TestService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
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


    /*@RequestMapping("/save")
    @ResponseBody*/
    /*public ResultMessage save(Test test){
        ResultMessage rm = new ResultMessage();
        try{
            testService.save(test);
        }catch (Exception e){
            rm.setStatus(Constant.STATUS_500);
            rm.setSuccess(Constant.FALSE);
            e.printStackTrace();
        }
        return rm;
    }*/



}
