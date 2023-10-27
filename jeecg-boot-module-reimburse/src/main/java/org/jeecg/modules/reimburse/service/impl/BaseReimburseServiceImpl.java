package org.jeecg.modules.reimburse.service.impl;

import org.jeecg.modules.reimburse.entity.BaseReimburse;
import org.jeecg.modules.reimburse.entity.BaseReimburseDetails;
import org.jeecg.modules.reimburse.enums.StatusEnum;
import org.jeecg.modules.reimburse.mapper.BaseReimburseDetailsMapper;
import org.jeecg.modules.reimburse.mapper.BaseReimburseMapper;
import org.jeecg.modules.reimburse.service.IBaseReimburseService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 报销基本信息表
 * @Author: jeecg-boot
 * @Date:   2023-10-27
 * @Version: V1.0
 */
@Service
public class BaseReimburseServiceImpl extends ServiceImpl<BaseReimburseMapper, BaseReimburse> implements IBaseReimburseService {

	@Autowired
	private BaseReimburseMapper baseReimburseMapper;
	@Autowired
	private BaseReimburseDetailsMapper baseReimburseDetailsMapper;

	@Override
	@Transactional
	public void saveMain(BaseReimburse baseReimburse, List<BaseReimburseDetails> baseReimburseDetailsList) {
		BigDecimal totalReimbursement = baseReimburseDetailsList.stream()
				.map(BaseReimburseDetails::getReimburseAmount)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		// 设置保留两位小数
		totalReimbursement = totalReimbursement.setScale(2, RoundingMode.HALF_UP);
		baseReimburse.setTotalReimbursement(totalReimbursement);
		baseReimburse.setStatus(StatusEnum.FINISHED);
		baseReimburse.setSerialNumber(this.getCode());
		baseReimburseMapper.insert(baseReimburse);
		if(baseReimburseDetailsList!=null && baseReimburseDetailsList.size()>0) {
			for(BaseReimburseDetails entity:baseReimburseDetailsList) {
				//外键设置
				entity.setReimburse(baseReimburse.getId());
				baseReimburseDetailsMapper.insert(entity);
			}
		}
	}

	public String getCode() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("OPEC");
		// 获取当前系统时间
		Date now = new Date();
		// 创建一个格式化器，将时间转换为时分秒的字符串
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		// 格式化时间为时分秒的字符串
		String timeString = sdf.format(now);
		stringBuffer.append(timeString);
		// 返回生成的字符串
		return stringBuffer.toString();
	}
	@Override
	@Transactional
	public void updateMain(BaseReimburse baseReimburse,List<BaseReimburseDetails> baseReimburseDetailsList) {
		BigDecimal totalReimbursement = baseReimburseDetailsList.stream()
				.map(BaseReimburseDetails::getReimburseAmount)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		// 设置保留两位小数
		totalReimbursement = totalReimbursement.setScale(2, RoundingMode.HALF_UP);
		baseReimburse.setTotalReimbursement(totalReimbursement);
		baseReimburseMapper.updateById(baseReimburse);
		
		//1.先删除子表数据
		baseReimburseDetailsMapper.deleteByMainId(baseReimburse.getId());
		
		//2.子表数据重新插入
		if(baseReimburseDetailsList!=null && baseReimburseDetailsList.size()>0) {
			for(BaseReimburseDetails entity:baseReimburseDetailsList) {
				//外键设置
				entity.setReimburse(baseReimburse.getId());
				baseReimburseDetailsMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		baseReimburseDetailsMapper.deleteByMainId(id);
		baseReimburseMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			baseReimburseDetailsMapper.deleteByMainId(id.toString());
			baseReimburseMapper.deleteById(id);
		}
	}
	
}
