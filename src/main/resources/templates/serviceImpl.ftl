
package ${package};

import ${daoPackage}.${dao};
import ${daoPackage}.BaseMapper;
import ${entityPackage}.${className};
import ${servicePackage}.${service};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class ${serviceImpl} extends BaseServiceImpl<${className}> implements ${service} {
    @Autowired
    private ${dao} mapper;
    @Override
    protected BaseMapper<${className}, Serializable> getDao() {
        return mapper;
    }


}








