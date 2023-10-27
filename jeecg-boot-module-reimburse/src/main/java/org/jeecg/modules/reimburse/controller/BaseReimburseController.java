package org.jeecg.modules.reimburse.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.vo.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.reimburse.entity.BaseReimburseDetails;
import org.jeecg.modules.reimburse.entity.BaseReimburse;
import org.jeecg.modules.reimburse.vo.BaseReimbursePage;
import org.jeecg.modules.reimburse.service.IBaseReimburseService;
import org.jeecg.modules.reimburse.service.IBaseReimburseDetailsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 报销基本信息表
 * @Author: jeecg-boot
 * @Date:   2023-10-27
 * @Version: V1.0
 */
@Api(tags="报销基本信息表")
@RestController
@RequestMapping("/reimburse/baseReimburse")
@Slf4j
public class BaseReimburseController {
	@Autowired
	private IBaseReimburseService baseReimburseService;
	@Autowired
	private IBaseReimburseDetailsService baseReimburseDetailsService;
	
	/**
	 * 分页列表查询
	 *
	 * @param baseReimburse
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "报销基本信息表-分页列表查询")
	@ApiOperation(value="报销基本信息表-分页列表查询", notes="报销基本信息表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(BaseReimburse baseReimburse,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<BaseReimburse> queryWrapper = QueryGenerator.initQueryWrapper(baseReimburse, req.getParameterMap());
		Page<BaseReimburse> page = new Page<BaseReimburse>(pageNo, pageSize);
		IPage<BaseReimburse> pageList = baseReimburseService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param baseReimbursePage
	 * @return
	 */
	@AutoLog(value = "报销基本信息表-添加")
	@ApiOperation(value="报销基本信息表-添加", notes="报销基本信息表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody BaseReimbursePage baseReimbursePage) {
		BaseReimburse baseReimburse = new BaseReimburse();
		BeanUtils.copyProperties(baseReimbursePage, baseReimburse);
		baseReimburseService.saveMain(baseReimburse, baseReimbursePage.getBaseReimburseDetailsList());
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param baseReimbursePage
	 * @return
	 */
	@AutoLog(value = "报销基本信息表-编辑")
	@ApiOperation(value="报销基本信息表-编辑", notes="报销基本信息表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody BaseReimbursePage baseReimbursePage) {
		BaseReimburse baseReimburse = new BaseReimburse();
		BeanUtils.copyProperties(baseReimbursePage, baseReimburse);
		BaseReimburse baseReimburseEntity = baseReimburseService.getById(baseReimburse.getId());
		if(baseReimburseEntity==null) {
			return Result.error("未找到对应数据");
		}
		baseReimburseService.updateMain(baseReimburse, baseReimbursePage.getBaseReimburseDetailsList());
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "报销基本信息表-通过id删除")
	@ApiOperation(value="报销基本信息表-通过id删除", notes="报销基本信息表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		baseReimburseService.delMain(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "报销基本信息表-批量删除")
	@ApiOperation(value="报销基本信息表-批量删除", notes="报销基本信息表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.baseReimburseService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "报销基本信息表-通过id查询")
	@ApiOperation(value="报销基本信息表-通过id查询", notes="报销基本信息表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		BaseReimburse baseReimburse = baseReimburseService.getById(id);
		if(baseReimburse==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(baseReimburse);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "报销明细表通过主表ID查询")
	@ApiOperation(value="报销明细表主表ID查询", notes="报销明细表-通主表ID查询")
	@GetMapping(value = "/queryBaseReimburseDetailsByMainId")
	public Result<?> queryBaseReimburseDetailsListByMainId(@RequestParam(name="id",required=true) String id) {
		List<BaseReimburseDetails> baseReimburseDetailsList = baseReimburseDetailsService.selectByMainId(id);
		return Result.OK(baseReimburseDetailsList);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param baseReimburse
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, BaseReimburse baseReimburse) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<BaseReimburse> queryWrapper = QueryGenerator.initQueryWrapper(baseReimburse, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<BaseReimburse> queryList = baseReimburseService.list(queryWrapper);
      // 过滤选中数据
      String selections = request.getParameter("selections");
      List<BaseReimburse> baseReimburseList = new ArrayList<BaseReimburse>();
      if(oConvertUtils.isEmpty(selections)) {
          baseReimburseList = queryList;
      }else {
          List<String> selectionList = Arrays.asList(selections.split(","));
          baseReimburseList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }

      // Step.3 组装pageList
      List<BaseReimbursePage> pageList = new ArrayList<BaseReimbursePage>();
      for (BaseReimburse main : baseReimburseList) {
          BaseReimbursePage vo = new BaseReimbursePage();
          BeanUtils.copyProperties(main, vo);
          List<BaseReimburseDetails> baseReimburseDetailsList = baseReimburseDetailsService.selectByMainId(main.getId());
          vo.setBaseReimburseDetailsList(baseReimburseDetailsList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "报销基本信息表列表");
      mv.addObject(NormalExcelConstants.CLASS, BaseReimbursePage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("报销基本信息表数据", "导出人:"+sysUser.getRealname(), "报销基本信息表"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
    }

    /**
    * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          MultipartFile file = entity.getValue();// 获取上传文件对象
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<BaseReimbursePage> list = ExcelImportUtil.importExcel(file.getInputStream(), BaseReimbursePage.class, params);
              for (BaseReimbursePage page : list) {
                  BaseReimburse po = new BaseReimburse();
                  BeanUtils.copyProperties(page, po);
                  baseReimburseService.saveMain(po, page.getBaseReimburseDetailsList());
              }
              return Result.OK("文件导入成功！数据行数:" + list.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.OK("文件导入失败！");
    }

}
