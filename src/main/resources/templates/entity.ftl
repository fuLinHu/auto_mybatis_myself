package ${package};

public class ${className} extends BaseEntity{

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