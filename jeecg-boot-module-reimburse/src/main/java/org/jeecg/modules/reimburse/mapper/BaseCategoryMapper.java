package org.jeecg.modules.reimburse.mapper;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.reimburse.entity.BaseCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 品类
 * @Author: jeecg-boot
 * @Date:   2023-10-27
 * @Version: V1.0
 */
public interface BaseCategoryMapper extends BaseMapper<BaseCategory> {

	/**
	 * 编辑节点状态
	 * @param id
	 * @param status
	 */
	void updateTreeNodeStatus(@Param("id") String id,@Param("status") String status);

}
