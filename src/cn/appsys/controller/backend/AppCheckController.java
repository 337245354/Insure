package cn.appsys.controller.backend;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import cn.appsys.pojo.*;
import cn.appsys.service.developer.InsuredInfoService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.appsys.service.backend.AppService;
import cn.appsys.service.developer.AppCategoryService;
import cn.appsys.service.developer.AppVersionService;
import cn.appsys.service.developer.DataDictionaryService;
import cn.appsys.tools.Constants;
import cn.appsys.tools.PageSupport;

@Controller
@RequestMapping(value="/underwriter/backend/app")
public class AppCheckController {
	private Logger logger = Logger.getLogger(AppCheckController.class);
	
	@Resource
	private AppService appService;
	@Resource
	private InsuredInfoService insuredInfoService;
	@Resource
	private AppVersionService appVersionService;
	@Resource 
	private DataDictionaryService dataDictionaryService;
	@Resource 
	private AppCategoryService appCategoryService;
	
	@RequestMapping(value="/list")
	public String getAppInfoList(Model model,HttpSession session,
							@RequestParam(value="querySoftwareName",required=false) String querySoftwareName,
							@RequestParam(value="queryCategoryLevel1",required=false) String _queryCategoryLevel1,
							@RequestParam(value="queryCategoryLevel2",required=false) String _queryCategoryLevel2,
							@RequestParam(value="queryCategoryLevel3",required=false) String _queryCategoryLevel3,
							@RequestParam(value="queryFlatformId",required=false) String _queryFlatformId,
							@RequestParam(value="pageIndex",required=false) String pageIndex){
		
		logger.info("getAppInfoList -- > querySoftwareName: " + querySoftwareName);
		logger.info("getAppInfoList -- > queryCategoryLevel1: " + _queryCategoryLevel1);
		logger.info("getAppInfoList -- > queryCategoryLevel2: " + _queryCategoryLevel2);
		logger.info("getAppInfoList -- > queryCategoryLevel3: " + _queryCategoryLevel3);
		logger.info("getAppInfoList -- > queryFlatformId: " + _queryFlatformId);
		logger.info("getAppInfoList -- > pageIndex: " + pageIndex);
		
		List<AppInfo> appInfoList = null;
		List<DataDictionary> flatFormList = null;
		List<AppCategory> categoryLevel1List = null;//列出一级分类列表，注：二级和三级分类列表通过异步ajax获取
		List<AppCategory> categoryLevel2List = null;
		List<AppCategory> categoryLevel3List = null;
		//页面容量
		int pageSize = Constants.pageSize;
		//当前页码
		Integer currentPageNo = 1;
		
		if(pageIndex != null){
			try{
				currentPageNo = Integer.valueOf(pageIndex);
			}catch (NumberFormatException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		Integer queryCategoryLevel1 = null;
		if(_queryCategoryLevel1 != null && !_queryCategoryLevel1.equals("")){
			queryCategoryLevel1 = Integer.parseInt(_queryCategoryLevel1);
		}
		Integer queryCategoryLevel2 = null;
		if(_queryCategoryLevel2 != null && !_queryCategoryLevel2.equals("")){
			queryCategoryLevel2 = Integer.parseInt(_queryCategoryLevel2);
		}
		Integer queryCategoryLevel3 = null;
		if(_queryCategoryLevel3 != null && !_queryCategoryLevel3.equals("")){
			queryCategoryLevel3 = Integer.parseInt(_queryCategoryLevel3);
		}
		Integer queryFlatformId = null;
		if(_queryFlatformId != null && !_queryFlatformId.equals("")){
			queryFlatformId = Integer.parseInt(_queryFlatformId);
		}
		
		//总数量（表）
		int totalCount = 0;
		try {
			totalCount = appService.getAppInfoCount(querySoftwareName, queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3, queryFlatformId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//总页数
		PageSupport pages = new PageSupport();
		pages.setCurrentPageNo(currentPageNo);
		pages.setPageSize(pageSize);
		pages.setTotalCount(totalCount);
		int totalPageCount = pages.getTotalPageCount();
		//控制首页和尾页
		if(currentPageNo < 1){
			currentPageNo = 1;
		}else if(currentPageNo > totalPageCount){
			currentPageNo = totalPageCount;
		}
		try {
			appInfoList = appService.getAppInfoList(querySoftwareName, queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3, queryFlatformId, currentPageNo, pageSize);
			flatFormList = this.getDataDictionaryList("APP_FLATFORM");
			categoryLevel1List = appCategoryService.getAppCategoryListByParentId(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("appInfoList", appInfoList);
		model.addAttribute("flatFormList", flatFormList);
		model.addAttribute("categoryLevel1List", categoryLevel1List);
		model.addAttribute("pages", pages);
		model.addAttribute("querySoftwareName", querySoftwareName);
		model.addAttribute("queryCategoryLevel1", queryCategoryLevel1);
		model.addAttribute("queryCategoryLevel2", queryCategoryLevel2);
		model.addAttribute("queryCategoryLevel3", queryCategoryLevel3);
		model.addAttribute("queryFlatformId", queryFlatformId);
		
		//二级分类列表和三级分类列表---回显
		if(queryCategoryLevel2 != null && !queryCategoryLevel2.equals("")){
			categoryLevel2List = getCategoryList(queryCategoryLevel1.toString());
			model.addAttribute("categoryLevel2List", categoryLevel2List);
		}
		if(queryCategoryLevel3 != null && !queryCategoryLevel3.equals("")){
			categoryLevel3List = getCategoryList(queryCategoryLevel2.toString());
			model.addAttribute("categoryLevel3List", categoryLevel3List);
		}
		return "backend/applist";
	}

	@RequestMapping(value="/getinsuredinfolist")
	public String getInsuredInfoList(Model model,HttpSession session,
									 @RequestParam(value="id",required=false) Integer _id,
									 @RequestParam(value="policyStatus",required=false) String _policyStatus,
									 @RequestParam(value="pageIndex",required=false) String pageIndex){
		logger.info("getPolicyInfoList -- > id: " + _id);        //查询入参条件
		logger.info("getPolicyInfoList -- > policyStatus: " +  _policyStatus);
		logger.info("getPolicyInfoList -- > pageIndex: " + pageIndex);
//		Integer devId = ((DevUser)session.getAttribute(Constants.DEV_USER_SESSION)).getId();
		List<InsuredInfo> insuredInfoList = null;
		List<InsuredGLInfo> insuredGLInfoList = null;
		List<InsuredCAInfo> insuredCAInfoList = null;
		List<DataDictionary> policyStatusList = null;
		List<DataDictionary> paymentTypeList = null;
		//页面容量
		int pageSize = Constants.pageSize;
		//当前页码
		Integer currentPageNo = 1;

		if(pageIndex != null){
			try{
				currentPageNo = Integer.valueOf(pageIndex);
			}catch (NumberFormatException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		Integer queryStatus = null;
		if(_policyStatus != null && !_policyStatus.equals("")){
			queryStatus = Integer.parseInt(_policyStatus);
		}

		//总数量（表）
		int totalCount = 0;
		try {
			totalCount = insuredInfoService.getInsuredInfoCount(_id, queryStatus);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//总页数
		PageSupport pages = new PageSupport();
		pages.setCurrentPageNo(currentPageNo);
		pages.setPageSize(pageSize);
		pages.setTotalCount(totalCount);
		int totalPageCount = pages.getTotalPageCount();
		//控制首页和尾页
		if(currentPageNo < 1){
			currentPageNo = 1;
		}else if(currentPageNo > totalPageCount){
			currentPageNo = totalPageCount;
		}
		try {
			insuredInfoList = insuredInfoService.getInsuredInfoList(_id,queryStatus,currentPageNo, pageSize);
			policyStatusList = this.getInsuredDataDictionaryList("POLICY_STATUS");
			paymentTypeList = this.getInsuredDataDictionaryList("PAYMENT_TYPE");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("insuredInfoList", insuredInfoList);
		model.addAttribute("policyStatusList", policyStatusList);
		model.addAttribute("paymentTypeList", paymentTypeList);
		model.addAttribute("pages", pages);
		model.addAttribute("queryStatus", queryStatus);

		return "backend/policyCheckAllList";
	}
	/**
	 *返回出所有的InsuredInfoList并发送给json
	 */
	@RequestMapping(value="/getInsuredListToJson", method = RequestMethod.GET)
	@ResponseBody
	public Object getInsuredInfoListToJson(){
		List<InsuredInfo> insuredInfoList = null;
		List<InsuredGLInfo> insuredGLInfoList = null;
		List<InsuredCAInfo> insuredCAInfoList = null;
		List<DataDictionary> policyStatusList = null;
		List<DataDictionary> paymentTypeList = null;
		int ONE = Constants.ONE;
		int pageSizeForAll = Constants.pageSizeForAll;
		try {
			insuredInfoList = insuredInfoService.getInsuredInfoList(null,null,ONE,pageSizeForAll);
//			policyStatusList = this.getInsuredDataDictionaryList("POLICY_STATUS");
//			paymentTypeList = this.getInsuredDataDictionaryList("PAYMENT_TYPE");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONArray JsonInsuredInfoList = new JSONArray();
		for (int i=0 ;i < insuredInfoList.size();i ++ ){
			JsonInsuredInfoList.add(insuredInfoList.get(i));
		}

		return JsonInsuredInfoList;
	}

	/**
	 *返回出所有的InsuredInfoList并发送给json，用于保险统计页面
	 */
	@RequestMapping(value="/getInsuredListToFormDateToJson", method = RequestMethod.GET)
	@ResponseBody
	public Object getPremiumListByDateToJson(){
		List<InsuredInfo> insuredInfoList = null;                              //insuredInfoList is the List from DB
		List<FormDate> formDateList = new ArrayList<>();                        //formDateList is a totally new list for tran to json
		int ONE = Constants.ONE;
		int pageSizeForAll = Constants.pageSizeForAll;
		try {
			insuredInfoList = insuredInfoService.getInsuredInfoList(null,null,ONE,pageSizeForAll);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Integer formDateListId = 0;
		for (int i=0 ;i < insuredInfoList.size();i ++ ){
            FormDate formDate=new FormDate();
			formDate.setStartDate(insuredInfoList.get(i).getStartDate());
			formDate.setPremiumForDate(insuredInfoList.get(i).getPremium());      //把相同日期的premium全部都归结加到一起
			formDate.setBuyerName(insuredInfoList.get(i).getBuyerName());
			formDate.setId(insuredInfoList.get(i).getId());
			formDate.setPremiumForSinglePolicy(insuredInfoList.get(i).getPremium());
			if(insuredInfoList.get(i).getPolicyStatus() == 4){
				formDate.setNumberOfNotPayForDate(10);                  //催一张单子大概花10分钟
			}else{
				formDate.setNumberOfNotPayForDate(0);
			}


			Integer sameDateId = null;
			for(int ii=0 ;ii < formDateListId;ii ++){                                                 //validate has Duplicate date or not
				if(formDateList.get(ii).getStartDate().equals(insuredInfoList.get(i).getStartDate())){
					sameDateId = ii;              // sameDateId record id of formDateList,
				}
			}
			if(sameDateId != null){
				Integer id = formDateList.get(sameDateId).getPremiumForSinglePolicy().compareTo(formDate.getPremiumForSinglePolicy())>0 ? formDateList.get(sameDateId).getId():formDate.getId();       //id is just a property to figure out the Biggest premium's policy's buyerName
				String buyerName = insuredInfoList.get(id-5001).getPremium().compareTo(formDate.getPremiumForDate())>0 ? insuredInfoList.get(id-5001).getBuyerName():formDate.getBuyerName();
				BigDecimal premiumForSinglePolicy = insuredInfoList.get(id-5001).getPremium().compareTo(formDate.getPremiumForDate())>0 ? insuredInfoList.get(id-5001).getPremium():formDate.getPremiumForSinglePolicy();
				formDateList.get(sameDateId).setId(id);
				formDateList.get(sameDateId).setBuyerName(buyerName);
				formDateList.get(sameDateId).setPremiumForSinglePolicy(premiumForSinglePolicy);

				formDateList.get(sameDateId).setPremiumForDate(formDateList.get(sameDateId).getPremiumForDate().add(formDate.getPremiumForDate()));   //sum premiumForDate from before to now

				formDateList.get(sameDateId).setNumberOfNotPayForDate(formDateList.get(sameDateId).getNumberOfNotPayForDate() + formDate.getNumberOfNotPayForDate());   //sum numberOfNotPayForDate from before to now,the value has been multiply by 10


			}else{
				formDateList.add(formDate);//add(formDateListId,formDate);
				formDateListId++;
			}

		}

		JSONArray JsonFormDateList = new JSONArray();
		for (int i=0 ;i < formDateList.size();i ++ ){
			JsonFormDateList.add(formDateList.get(i));
		}

		return JsonFormDateList;
	}


	public List<DataDictionary> getDataDictionaryList(String typeCode){
		List<DataDictionary> dataDictionaryList = null;
		try {
			dataDictionaryList = dataDictionaryService.getDataDictionaryList(typeCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataDictionaryList;
	}

	public List<DataDictionary> getInsuredDataDictionaryList(String typeCode){
		List<DataDictionary> dataDictionaryList = null;
		try {
			dataDictionaryList = dataDictionaryService.getInsuredDataDictionaryList(typeCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataDictionaryList;
	}
	
	public List<AppCategory> getCategoryList (String pid){
		List<AppCategory> categoryLevelList = null;
		try {
			categoryLevelList = appCategoryService.getAppCategoryListByParentId(pid==null?null:Integer.parseInt(pid));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categoryLevelList;
	}
	
	/**
	 * 根据parentId查询出相应的分类级别列表
	 * @param pid
	 * @return
	 */
	@RequestMapping(value="/categorylevellist.json",method=RequestMethod.GET)
	@ResponseBody
	public List<AppCategory> getAppCategoryList (@RequestParam String pid){
		logger.debug("getAppCategoryList pid ============ " + pid);
		if(pid.equals("")) pid = null;
		return getCategoryList(pid);
	}


	/**
	 * 查看policyInfo信息，包括policy基本信息和其他信息
	 * @return
	 */
	@RequestMapping(value="/policyview/{id}",method=RequestMethod.GET)
	public String Insuredview(@PathVariable String id,Model model){
		InsuredInfo insuredInfo = null;
		InsuredCAInfo insuredCAInfo = null;
		InsuredGLInfo insuredGLInfo = null;
		try {
			insuredInfo = insuredInfoService.getInsuredInfo(Integer.parseInt(id),null);
			insuredCAInfo = insuredInfoService.getInsuredCAInfo(Integer.parseInt(id));
			insuredGLInfo = insuredInfoService.getInsuredGLInfo(Integer.parseInt(id));
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute(insuredInfo);
		model.addAttribute(insuredCAInfo);
		model.addAttribute(insuredGLInfo);
		return "backend/policyFound";
	}
	
	/**
	 * 跳转到APP信息审核页面
	 * @param appId
	 * @param versionId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/check",method=RequestMethod.GET)
	public String check(@RequestParam(value="aid",required=false) String appId,
					   @RequestParam(value="vid",required=false) String versionId,
					   Model model){
		AppInfo appInfo = null;
		AppVersion appVersion = null;
		try {
			appInfo = appService.getAppInfo(Integer.parseInt(appId));
			appVersion = appVersionService.getAppVersionById(Integer.parseInt(versionId));
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute(appVersion);
		model.addAttribute(appInfo);
		return "backend/appcheck";
	}

	/**
	 * 跳转到policy信息审核页面
	 * @param policyID
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/approve",method=RequestMethod.GET)
	public String check(@RequestParam(value="pid",required=false) String policyID, Model model){
		InsuredInfo insuredInfo = null;
		InsuredCAInfo insuredCAInfo = null;
		InsuredGLInfo insuredGLInfo = null;
		try {
			insuredInfo = insuredInfoService.getInsuredInfo(Integer.parseInt(policyID),null);
			insuredCAInfo = insuredInfoService.getInsuredCAInfo(Integer.parseInt(policyID));
			insuredGLInfo = insuredInfoService.getInsuredGLInfo(Integer.parseInt(policyID));
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute(insuredInfo);
		model.addAttribute(insuredCAInfo);
		model.addAttribute(insuredGLInfo);
		return "backend/policyCheck";
	}

	@RequestMapping(value="/checksave",method=RequestMethod.POST)
	public String checkSave(AppInfo appInfo){
		logger.debug("appInfo =========== > " + appInfo.getStatus());
		try {
			if(appService.updateSatus(appInfo.getStatus(),appInfo.getId())){
				return "redirect:/underwriter/backend/app/list";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "backend/appcheck";
	}

	@RequestMapping(value="/formMain")
	public String toformMain(){

		return "backend/formMain";
	}

	@RequestMapping(value="/approvesave",method=RequestMethod.POST)
	public String approveSave(InsuredInfo insuredInfo){
		logger.debug("insuredInfo =========== > " + insuredInfo.getPolicyStatus());
		try {
			if(insuredInfoService.updateStatus(insuredInfo.getPolicyStatus(),insuredInfo.getId())){
				return "redirect:/underwriter/backend/app/getinsuredinfolist";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "backend/policyCheck";
	}
}
