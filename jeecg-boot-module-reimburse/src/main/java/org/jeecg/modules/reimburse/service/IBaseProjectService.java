package org.jeecg.modules.reimburse.service;

import org.jeecg.modules.reimburse.entity.BaseProject;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 项目信息
 * @Author: jeecg-boot
 * @Date:   2023-10-27
 * @Version: V1.0
 */
public interface IBaseProjectService extends IService<BaseProject> {

    void saveBaseProject(BaseProject baseProject);
}
