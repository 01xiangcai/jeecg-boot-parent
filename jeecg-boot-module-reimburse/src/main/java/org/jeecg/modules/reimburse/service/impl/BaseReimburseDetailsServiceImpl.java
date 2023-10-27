package org.jeecg.modules.reimburse.service.impl;

import org.jeecg.modules.reimburse.entity.BaseReimburseDetails;
import org.jeecg.modules.reimburse.mapper.BaseReimburseDetailsMapper;
import org.jeecg.modules.reimburse.service.IBaseReimburseDetailsService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 报销明细表
 * @Author: jeecg-boot
 * @Date:   2023-10-27
 * @Version: V1.0
 */
@Service
public class BaseReimburseDetailsServiceImpl extends ServiceImpl<BaseReimburseDetailsMapper, BaseReimburseDetails> implements IBaseReimburseDetailsService {
	
	@Autowired
	private BaseReimburseDetailsMapper baseReimburseDetailsMapper;
	
	@Override
	public List<BaseReimburseDetails> selectByMainId(String mainId) {
		return baseReimburseDetailsMapper.selectByMainId(mainId);
	}
}
