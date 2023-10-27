package org.jeecg.modules.reimburse.service;

import org.jeecg.modules.reimburse.entity.BaseReimburseDetails;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 报销明细表
 * @Author: jeecg-boot
 * @Date:   2023-10-27
 * @Version: V1.0
 */
public interface IBaseReimburseDetailsService extends IService<BaseReimburseDetails> {

	public List<BaseReimburseDetails> selectByMainId(String mainId);
}
