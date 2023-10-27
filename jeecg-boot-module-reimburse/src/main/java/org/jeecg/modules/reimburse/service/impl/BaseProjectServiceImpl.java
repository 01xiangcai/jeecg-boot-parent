package org.jeecg.modules.reimburse.service.impl;

import org.jeecg.modules.reimburse.entity.BaseProject;
import org.jeecg.modules.reimburse.mapper.BaseProjectMapper;
import org.jeecg.modules.reimburse.service.IBaseProjectService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: 项目信息
 * @Author: jeecg-boot
 * @Date:   2023-10-27
 * @Version: V1.0
 */
@Service
public class BaseProjectServiceImpl extends ServiceImpl<BaseProjectMapper, BaseProject> implements IBaseProjectService {

    @Override
    public void saveBaseProject(BaseProject baseProject) {
        baseProject.setProjectCode(this.getCode());
        this.save(baseProject);
    }


    public String getCode() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("JO");
        // 获取当前系统时间
        Date now = new Date();
        // 创建一个格式化器，将时间转换为时分秒的字符串
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        // 格式化时间为时分秒的字符串
        String timeString = sdf.format(now);
        stringBuffer.append(timeString);
        // 返回生成的字符串
        return stringBuffer.toString();
    }

}
