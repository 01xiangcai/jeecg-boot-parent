package org.jeecg.modules.reimburse.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 报销明细表
 * @Author: jeecg-boot
 * @Date:   2023-10-27
 * @Version: V1.0
 */
@ApiModel(value="base_reimburse对象", description="报销基本信息表")
@Data
@TableName("base_reimburse_details")
public class BaseReimburseDetails implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "主键")
	private java.lang.String id;
	/**创建人*/
	@ApiModelProperty(value = "创建人")
	private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "创建日期")
	private java.util.Date createTime;
	/**更新人*/
	@ApiModelProperty(value = "更新人")
	private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "更新日期")
	private java.util.Date updateTime;
	/**所属部门*/
	@ApiModelProperty(value = "所属部门")
	private java.lang.String sysOrgCode;
	/**报销id*/
	@ApiModelProperty(value = "报销id")
	private java.lang.String reimburse;
	/**日期*/
	@Excel(name = "日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "日期")
	private java.util.Date reimburseTime;
	/**项目*/
	@Excel(name = "项目", width = 15)
	@ApiModelProperty(value = "项目")
	private java.lang.String reimburseProject;
	/**品名*/
	@Excel(name = "品名", width = 15)
	@ApiModelProperty(value = "品名")
	private java.lang.String reimburseCategory;
	/**用途*/
	@Excel(name = "用途", width = 15)
	@ApiModelProperty(value = "用途")
	private java.lang.String reimburseUse;
	/**金额*/
	@Excel(name = "金额", width = 15)
	@ApiModelProperty(value = "金额")
	private java.math.BigDecimal reimburseAmount;
	/**特殊备注*/
	@Excel(name = "特殊备注", width = 15)
	@ApiModelProperty(value = "特殊备注")
	private java.lang.String speciaRemark;
}
