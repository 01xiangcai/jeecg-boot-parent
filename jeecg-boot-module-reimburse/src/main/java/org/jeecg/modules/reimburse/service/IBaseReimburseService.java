package org.jeecg.modules.reimburse.service;

import org.jeecg.modules.reimburse.entity.BaseReimburseDetails;
import org.jeecg.modules.reimburse.entity.BaseReimburse;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 报销基本信息表
 * @Author: jeecg-boot
 * @Date:   2023-10-27
 * @Version: V1.0
 */
public interface IBaseReimburseService extends IService<BaseReimburse> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(BaseReimburse baseReimburse,List<BaseReimburseDetails> baseReimburseDetailsList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(BaseReimburse baseReimburse,List<BaseReimburseDetails> baseReimburseDetailsList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
