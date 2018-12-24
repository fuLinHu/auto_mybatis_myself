package ${package};



import ${entityPackage}.${className};
import ${servicePackage}.${service};
import com.framk.autocode.publicmoduel.Entity.Constant;
import com.framk.autocode.publicmoduel.Entity.Page;
import com.framk.autocode.publicmoduel.Entity.ResultMessage;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/${controllerRootPath}")
public class ${controller} {

    @Autowired
    private ${service} service;

    @RequestMapping("/page")
    @ResponseBody
    public ResultMessage findPageBy(${className} param){
        ResultMessage rm = new ResultMessage();
        try {
            PageInfo pageInfo = service.findPageBy(param);
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
    public ResultMessage save(${className} param){
        ResultMessage rm = new ResultMessage();
        try {
            service.save(param);
        }catch (Exception e){
            rm.setStatus(Constant.STATUS_500);
            rm.setSuccess(Constant.FALSE);
            e.printStackTrace();
        }
        return rm;
    }
    @RequestMapping("/update")
    @ResponseBody
    public ResultMessage update(${className} param){
        ResultMessage rm = new ResultMessage();
        try {
            service.update(test);
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
            service.deleteById(id);
        }catch (Exception e){
            rm.setStatus(Constant.STATUS_500);
            rm.setSuccess(Constant.FALSE);
            e.printStackTrace();
        }
        return rm;
    }
}





















public class ${className} {

<#if list??>
    <#list list as key>
    private ${key.javaType} ${key.javaName};
    <#--private ${userMap[key].javaType} ${userMap[key].javaName};-->
    <#--  <#if key==className >
   </#if>-->
    </#list>
</#if>
<#if list??>
    <#list list as key>
    public ${key.javaType} ${key.getName}() {
        return ${key.javaName};
    }
    public void ${key.setName}(${key.javaType} ${key.javaName}) {
        this.${key.javaName} = ${key.javaName};
    }

    </#list>
</#if>

}