package org.jeecg.modules.reimburse.service;

import org.jeecg.modules.reimburse.entity.BaseCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.common.exception.JeecgBootException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.List;

/**
 * @Description: 品类
 * @Author: jeecg-boot
 * @Date:   2023-10-27
 * @Version: V1.0
 */
public interface IBaseCategoryService extends IService<BaseCategory> {

	/**根节点父ID的值*/
	public static final String ROOT_PID_VALUE = "0";
	
	/**树节点有子节点状态值*/
	public static final String HASCHILD = "1";
	
	/**树节点无子节点状态值*/
	public static final String NOCHILD = "0";

	/**新增节点*/
	void addBaseCategory(BaseCategory baseCategory);
	
	/**修改节点*/
	void updateBaseCategory(BaseCategory baseCategory) throws JeecgBootException;
	
	/**删除节点*/
	void deleteBaseCategory(String id) throws JeecgBootException;

	/**查询所有数据，无分页*/
    List<BaseCategory> queryTreeListNoPage(QueryWrapper<BaseCategory> queryWrapper);

}
