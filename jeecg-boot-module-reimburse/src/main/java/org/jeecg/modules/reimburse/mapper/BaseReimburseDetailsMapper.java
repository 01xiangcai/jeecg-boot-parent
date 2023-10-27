package org.jeecg.modules.reimburse.mapper;

import java.util.List;
import org.jeecg.modules.reimburse.entity.BaseReimburseDetails;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 报销明细表
 * @Author: jeecg-boot
 * @Date:   2023-10-27
 * @Version: V1.0
 */
public interface BaseReimburseDetailsMapper extends BaseMapper<BaseReimburseDetails> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<BaseReimburseDetails> selectByMainId(@Param("mainId") String mainId);
}
