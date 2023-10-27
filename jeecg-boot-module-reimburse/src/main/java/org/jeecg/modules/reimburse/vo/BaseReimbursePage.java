package org.jeecg.modules.reimburse.vo;

import java.util.List;
import org.jeecg.modules.reimburse.entity.BaseReimburse;
import org.jeecg.modules.reimburse.entity.BaseReimburseDetails;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 报销基本信息表
 * @Author: jeecg-boot
 * @Date:   2023-10-27
 * @Version: V1.0
 */
@Data
@ApiModel(value="base_reimbursePage对象", description="报销基本信息表")
public class BaseReimbursePage {

	/**主键*/
	@ApiModelProperty(value = "主键")
	private java.lang.String id;
	/**流水号*/
	@Excel(name = "流水号", width = 15)
	@ApiModelProperty(value = "流水号")
	private java.lang.String serialNumber;
	/**报销人*/
	@ApiModelProperty(value = "报销人")
	private java.lang.String createBy;
	/**填单日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "填单日期")
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
	/**状态*/
	@Excel(name = "状态", width = 15, dicCode = "reimburse_status")
    @Dict(dicCode = "reimburse_status")
	@ApiModelProperty(value = "状态")
	private java.lang.Integer status;
	/**报销合计*/
	@Excel(name = "报销合计", width = 15)
	@ApiModelProperty(value = "报销合计")
	private java.math.BigDecimal totalReimbursement;
	/**报销归属*/
	@Excel(name = "报销归属", width = 15, dicCode = "reimburse_guishu")
    @Dict(dicCode = "reimburse_guishu")
	@ApiModelProperty(value = "报销归属")
	private java.lang.String reimbursementAttribution;
	/**备注*/
	@Excel(name = "备注", width = 15)
	@ApiModelProperty(value = "备注")
	private java.lang.String remark;

	@ExcelCollection(name="报销明细表")
	@ApiModelProperty(value = "报销明细表")
	private List<BaseReimburseDetails> baseReimburseDetailsList;

}
