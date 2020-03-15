package ${package.Service};

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.soft.fire.common.SqlFilter;

import ${package.Entity}.${entity};
import ${superServiceClassPackage};

/**
 * <p>
    * ${table.comment!} 服务类
    * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

 /**
     * 分页查询
     * @param sqlFilter
     * @return
     */
    IPage<${entity}> findBySqlFilter(SqlFilter sqlFilter);

}
</#if>