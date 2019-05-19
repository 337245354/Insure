package cn.appsys.controller.developer;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.appsys.pojo.*;
import cn.appsys.service.developer.*;
import com.mysql.cj.util.StringUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSONArray;
import cn.appsys.tools.Constants;
import cn.appsys.tools.PageSupport;

@Controller
@RequestMapping(value="/dev/flatform/app")
public class AppController {
	private Logger logger = Logger.getLogger(AppController.class);

	@Resource
	private AppInfoService appInfoService;
    @Resource
    private InsuredInfoService insuredInfoService;
	@Resource 
	private DataDictionaryService dataDictionaryService;
	@Resource 
	private AppCategoryService appCategoryService;
	@Resource
	private AppVersionService appVersionService;

	@RequestMapping(value="/list")
	public String getAppInfoList(Model model,HttpSession session,
							@RequestParam(value="querySoftwareName",required=false) String querySoftwareName,
							@RequestParam(value="queryStatus",required=false) String _queryStatus,
							@RequestParam(value="queryCategoryLevel1",required=false) String _queryCategoryLevel1,
							@RequestParam(value="queryCategoryLevel2",required=false) String _queryCategoryLevel2,
							@RequestParam(value="queryCategoryLevel3",required=false) String _queryCategoryLevel3,
							@RequestParam(value="queryFlatformId",required=false) String _queryFlatformId,
							@RequestParam(value="pageIndex",required=false) String pageIndex){
		
		logger.info("getAppInfoList -- > querySoftwareName: " + querySoftwareName);
		logger.info("getAppInfoList -- > queryStatus: " + _queryStatus);
		logger.info("getAppInfoList -- > queryCategoryLevel1: " + _queryCategoryLevel1);
		logger.info("getAppInfoList -- > queryCategoryLevel2: " + _queryCategoryLevel2);
		logger.info("getAppInfoList -- > queryCategoryLevel3: " + _queryCategoryLevel3);
		logger.info("getAppInfoList -- > queryFlatformId: " + _queryFlatformId);
		logger.info("getAppInfoList -- > pageIndex: " + pageIndex);
		
		Integer devId = ((DevUser)session.getAttribute(Constants.DEV_USER_SESSION)).getId();
		List<AppInfo> appInfoList = null;
		List<DataDictionary> statusList = null;
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
		Integer queryStatus = null;
		if(_queryStatus != null && !_queryStatus.equals("")){
			queryStatus = Integer.parseInt(_queryStatus);
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
			totalCount = appInfoService.getAppInfoCount(querySoftwareName, queryStatus, queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3, queryFlatformId, devId);
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
			appInfoList = appInfoService.getAppInfoList(querySoftwareName, queryStatus, queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3, queryFlatformId, devId, currentPageNo, pageSize);
			statusList = this.getDataDictionaryList("APP_STATUS");
			flatFormList = this.getDataDictionaryList("APP_FLATFORM");
			categoryLevel1List = appCategoryService.getAppCategoryListByParentId(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("appInfoList", appInfoList);
		model.addAttribute("statusList", statusList);
		model.addAttribute("flatFormList", flatFormList);
		model.addAttribute("categoryLevel1List", categoryLevel1List);
		model.addAttribute("pages", pages);
		model.addAttribute("queryStatus", queryStatus);
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
		return "developer/appinfolist";
	}

	@RequestMapping(value="/policyBasicInfo")
	public String getPolicyBasicInfoList(Model model,HttpSession session){
		return "developer/policyBasicInfoList";
	}

	@RequestMapping(value="/policyCAInfo")
	public String getPolicyCAInfoList(Model model,HttpSession session) { return "developer/policyCAInfoList"; }

	@RequestMapping(value="/policyGLInfo")
	public String getPolicyGLInfoList(Model model,HttpSession session) { return "developer/policyGLInfoList"; }

	@RequestMapping(value="/policyOverview")
	public String getPolicyOverview(Model model,HttpSession session) { return "developer/policyOverview"; }

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
	/**
	 * 根据typeCode查询出相应的数据字典列表
	 * @param pid
	 * @return
	 */
	@RequestMapping(value="/datadictionarylist.json",method=RequestMethod.GET)
	@ResponseBody
	public List<DataDictionary> getDataDicList (@RequestParam String tcode){
		logger.debug("getDataDicList tcode ============ " + tcode);
		return this.getDataDictionaryList(tcode);
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
	 * 增加app信息（跳转到新增appinfo页面）
	 * @param appInfo
	 * @return
	 */
	@RequestMapping(value="/appinfoadd",method=RequestMethod.GET)
	public String add(@ModelAttribute("appInfo") AppInfo appInfo){
		return "developer/appinfoadd";
	}


	/**
	 * 增加insured信息（跳转到新增insuredinfo页面）
	 * @param insuredInfo
	 * @return
	 */
    @RequestMapping(value="/insuredinfoadd",method=RequestMethod.GET)
    public String insuredinfoadd(@ModelAttribute("insuredInfo") InsuredInfo insuredInfo){

		logger.info("123321123");


		return "developer/policyBasicInfoList";

    }


	//test

	@RequestMapping(value="/getinsuredinfolist")
	public String getInsuredInfoList(Model model,HttpSession session,
								 @RequestParam(value="id",required=false) Integer _id,
								 @RequestParam(value="policyStatus",required=false) String _policyStatus,
								 @RequestParam(value="pageIndex",required=false) String pageIndex){
		logger.info("getPolicyInfoList -- > id: " + _id);        //查询入参条件
		logger.info("getPolicyInfoList -- > policyStatus: " +  _policyStatus);
		logger.info("getPolicyInfoList -- > pageIndex: " + pageIndex);
		Integer devId = ((DevUser)session.getAttribute(Constants.DEV_USER_SESSION)).getId();
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
			insuredInfoList = insuredInfoService.getInsuredInfoList(_id,queryStatus);
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

		return "developer/policyAllList";
	}






	/**
	 * 保存新增appInfo（主表）的数据
	 * @param appInfo
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/appinfoaddsave",method=RequestMethod.POST)
	public String addSave(AppInfo appInfo,HttpSession session,HttpServletRequest request,
					@RequestParam(value="a_logoPicPath",required= false) MultipartFile attach){		
		
		String logoPicPath =  null;
		String logoLocPath =  null;
		if(!attach.isEmpty()){
			String path = request.getSession().getServletContext().getRealPath("statics"+java.io.File.separator+"uploadfiles");
			logger.info("uploadFile path: " + path);
			String oldFileName = attach.getOriginalFilename();//原文件名
			String prefix = FilenameUtils.getExtension(oldFileName);//原文件后缀
			int filesize = 500000;
			if(attach.getSize() > filesize){//上传大小不得超过 50k
				request.setAttribute("fileUploadError", Constants.FILEUPLOAD_ERROR_4);
				return "developer/appinfoadd";
            }else if(prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png") 
			   ||prefix.equalsIgnoreCase("jepg") || prefix.equalsIgnoreCase("pneg")){//上传图片格式
				 String fileName = appInfo.getAPKName() + ".jpg";//上传LOGO图片命名:apk名称.apk
				 File targetFile = new File(path,fileName);
				 if(!targetFile.exists()){
					 targetFile.mkdirs();
				 }
				 try {
					attach.transferTo(targetFile);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					request.setAttribute("fileUploadError", Constants.FILEUPLOAD_ERROR_2);
					return "developer/appinfoadd";
				} 
				 logoPicPath = request.getContextPath()+"/statics/uploadfiles/"+fileName;
				 logoLocPath = path+File.separator+fileName;
			}else{
				request.setAttribute("fileUploadError", Constants.FILEUPLOAD_ERROR_3);
				return "developer/appinfoadd";
			}
		}
		appInfo.setCreatedBy(((DevUser)session.getAttribute(Constants.DEV_USER_SESSION)).getId());
		appInfo.setCreationDate(new Date());
		appInfo.setLogoPicPath(logoPicPath);
		appInfo.setLogoLocPath(logoLocPath);
		appInfo.setDevId(((DevUser)session.getAttribute(Constants.DEV_USER_SESSION)).getId());
		appInfo.setStatus(1);
		try {
			if(appInfoService.add(appInfo)){
				return "redirect:/dev/flatform/app/list";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "developer/appinfoadd";
	}
	
	/**
	 * 增加appversion信息（跳转到新增app版本页面）
	 * @param appInfo
	 * @return
	 */
	@RequestMapping(value="/appversionadd",method=RequestMethod.GET)
	public String addVersion(@RequestParam(value="id")String appId,
							 @RequestParam(value="error",required= false)String fileUploadError,
							 AppVersion appVersion,Model model){
		logger.debug("fileUploadError============> " + fileUploadError);
		if(null != fileUploadError && fileUploadError.equals("error1")){
			fileUploadError = Constants.FILEUPLOAD_ERROR_1;
		}else if(null != fileUploadError && fileUploadError.equals("error2")){
			fileUploadError	= Constants.FILEUPLOAD_ERROR_2;
		}else if(null != fileUploadError && fileUploadError.equals("error3")){
			fileUploadError = Constants.FILEUPLOAD_ERROR_3;
		}
		appVersion.setAppId(Integer.parseInt(appId));
		List<AppVersion> appVersionList = null;
		try {
			appVersionList = appVersionService.getAppVersionList(Integer.parseInt(appId));
			appVersion.setAppName((appInfoService.getAppInfo(Integer.parseInt(appId),null)).getSoftwareName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("appVersionList", appVersionList);
		model.addAttribute(appVersion);
		model.addAttribute("fileUploadError",fileUploadError);
		return "developer/appversionadd";
	}
	/**
	 * 保存新增appversion数据（子表）-上传该版本的apk包
	 * @param appInfo
	 * @param appVersion
	 * @param session
	 * @param request
	 * @param attach
	 * @return
	 */
	@RequestMapping(value="/addversionsave",method=RequestMethod.POST)
	public String addVersionSave(AppVersion appVersion,HttpSession session,HttpServletRequest request,
						@RequestParam(value="a_downloadLink",required= false) MultipartFile attach ){		
		String downloadLink =  null;
		String apkLocPath = null;
		String apkFileName = null;
		if(!attach.isEmpty()){
			String path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles");
			logger.info("uploadFile path: " + path);
			String oldFileName = attach.getOriginalFilename();//原文件名
			String prefix = FilenameUtils.getExtension(oldFileName);//原文件后缀
			if(prefix.equalsIgnoreCase("apk")){//apk文件命名：apk名称+版本号+.apk
				 String apkName = null;
				 try {
					apkName = appInfoService.getAppInfo(appVersion.getAppId(),null).getAPKName();
				 } catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				 }
				 if(apkName == null || "".equals(apkName)){
					 return "redirect:/dev/flatform/app/appversionadd?id="+appVersion.getAppId()
							 +"&error=error1";
				 }
				 apkFileName = apkName + "-" +appVersion.getVersionNo() + ".apk";
				 File targetFile = new File(path,apkFileName);
				 if(!targetFile.exists()){
					 targetFile.mkdirs();
				 }
				 try {
					attach.transferTo(targetFile);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "redirect:/dev/flatform/app/appversionadd?id="+appVersion.getAppId()
							 +"&error=error2";
				} 
				downloadLink = request.getContextPath()+"/statics/uploadfiles/"+apkFileName;
				apkLocPath = path+File.separator+apkFileName;
			}else{
				return "redirect:/dev/flatform/app/appversionadd?id="+appVersion.getAppId()
						 +"&error=error3";
			}
		}
		appVersion.setCreatedBy(((DevUser)session.getAttribute(Constants.DEV_USER_SESSION)).getId());
		appVersion.setCreationDate(new Date());
		appVersion.setDownloadLink(downloadLink);
		appVersion.setApkLocPath(apkLocPath);
		appVersion.setApkFileName(apkFileName);
		try {
			if(appVersionService.appsysadd(appVersion)){
				return "redirect:/dev/flatform/app/list";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/dev/flatform/app/appversionadd?id="+appVersion.getAppId();
	}
	
	@RequestMapping(value="/{appid}/sale",method=RequestMethod.PUT)
	@ResponseBody
	public Object sale(@PathVariable String appid,HttpSession session){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Integer appIdInteger = 0;
		try{
			appIdInteger = Integer.parseInt(appid);
		}catch(Exception e){
			appIdInteger = 0;
		}
		resultMap.put("errorCode", "0");
		resultMap.put("appId", appid);
		if(appIdInteger>0){
			try {
				DevUser devUser = (DevUser)session.getAttribute(Constants.DEV_USER_SESSION);
				AppInfo appInfo = new AppInfo();
				appInfo.setId(appIdInteger);
				appInfo.setModifyBy(devUser.getId());
				if(appInfoService.appsysUpdateSaleStatusByAppId(appInfo)){
					resultMap.put("resultMsg", "success");
				}else{
					resultMap.put("resultMsg", "success");
				}		
			} catch (Exception e) {
				resultMap.put("errorCode", "exception000001");
			}
		}else{
			//errorCode:0为正常
			resultMap.put("errorCode", "param000001");
		}
		
		/*
		 * resultMsg:success/failed
		 * errorCode:exception000001
		 * appId:appId
		 * errorCode:param000001
		 */
		return resultMap;
	}
	
	/**
	 * 判断APKName是否唯一
	 * @param apkName
	 * @return
	 */
	@RequestMapping(value="/apkexist.json",method=RequestMethod.GET)
	@ResponseBody
	public Object apkNameIsExit(@RequestParam String APKName){
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if(StringUtils.isNullOrEmpty(APKName)){
			resultMap.put("APKName", "empty");
		}else{
			AppInfo appInfo = null;
			try {
				appInfo = appInfoService.getAppInfo(null, APKName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(null != appInfo)
				resultMap.put("APKName", "exist");
			else
				resultMap.put("APKName", "noexist");
		}
		return JSONArray.toJSONString(resultMap);
	}
	
	/**
	 * 查看app信息，包括app基本信息和版本信息列表（跳转到查看页面）
	 * @param appInfo
	 * @return
	 */
	@RequestMapping(value="/appview/{id}",method=RequestMethod.GET)
	public String view(@PathVariable String id,Model model){
		AppInfo appInfo = null;
		List<AppVersion> appVersionList = null;
		try {
			appInfo = appInfoService.getAppInfo(Integer.parseInt(id),null);
			appVersionList = appVersionService.getAppVersionList(Integer.parseInt(id));
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("appVersionList", appVersionList);
		model.addAttribute(appInfo);
		return "developer/appinfoview";
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
        return "developer/policyFound";
    }

    /**
     * 修改policyInfo信息，包括policy基本信息和其他信息
     * 仅仅显示页面，不涉及具体业务
     * @return
     */
    @RequestMapping(value="/policymodify/{id}")
    public String Insuredmodify(@PathVariable String id,Model model){
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
        return "developer/policyModify";
    }


    /**
     * 修改policyInfo信息，包括policy基本信息和其他信息
     * 修改的具体逻辑
     * @return
     */

    @RequestMapping(value = "/policymodify/modifysave" , method = RequestMethod.POST)
    public  String modifysave(InsuredInfo insuredInfo,InsuredCAInfo insuredCAInfo,InsuredGLInfo insuredGLInfo,Model model, HttpSession session, HttpServletRequest request) {
        String n = "we";

        try {


            //rating engine

            insuredInfo.setPremium(RatingController.rateEngine(session));
            session.setAttribute(Constants.NEW_POLICY,insuredInfo);
            if(
                    insuredInfoService.modify(insuredInfo,insuredCAInfo,insuredGLInfo)
//                    insuredInfoService.add(insuredInfo,insuredCAInfo,insuredGLInfo)
                    ){   //insert into DB
//				session.removeAttribute(Constants.NEW_POLICY);
//				session.removeAttribute(Constants.CALINE);
//				session.removeAttribute(Constants.GLLINE);
                return "developer/policyModify";
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "developer/policyModify";
    }


    /**
     * 修改app信息，包括：修改app基本信息（appInfo）和修改版本信息（appVersion）
     * 分为两步实现：
     * 1 修改app基本信息（appInfo）
     * 2 修改版本信息（appVersion）
     */
	
	/**
	 * 修改appInfo信息（跳转到修改appInfo页面）
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/appinfomodify",method=RequestMethod.GET)
	public String modifyAppInfo(@RequestParam("id") String id,
								@RequestParam(value="error",required= false)String fileUploadError,
								Model model){
		AppInfo appInfo = null;
		logger.debug("modifyAppInfo --------- id: " + id);
		if(null != fileUploadError && fileUploadError.equals("error1")){
			fileUploadError = Constants.FILEUPLOAD_ERROR_1;
		}else if(null != fileUploadError && fileUploadError.equals("error2")){
			fileUploadError	= Constants.FILEUPLOAD_ERROR_2;
		}else if(null != fileUploadError && fileUploadError.equals("error3")){
			fileUploadError = Constants.FILEUPLOAD_ERROR_3;
		}else if(null != fileUploadError && fileUploadError.equals("error4")){
			fileUploadError = Constants.FILEUPLOAD_ERROR_4;
		}
		try {
			appInfo = appInfoService.getAppInfo(Integer.parseInt(id),null);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute(appInfo);
		model.addAttribute("fileUploadError",fileUploadError);
		return "developer/appinfomodify";
	}
	
	/**
	 * 修改最新的appVersion信息（跳转到修改appVersion页面）
	 * @param versionId
	 * @param appId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/appversionmodify",method=RequestMethod.GET)
	public String modifyAppVersion(@RequestParam("vid") String versionId,
									@RequestParam("aid") String appId,
									@RequestParam(value="error",required= false)String fileUploadError,
									Model model){
		AppVersion appVersion = null;
		List<AppVersion> appVersionList = null;
		if(null != fileUploadError && fileUploadError.equals("error1")){
			fileUploadError = Constants.FILEUPLOAD_ERROR_1;
		}else if(null != fileUploadError && fileUploadError.equals("error2")){
			fileUploadError	= Constants.FILEUPLOAD_ERROR_2;
		}else if(null != fileUploadError && fileUploadError.equals("error3")){
			fileUploadError = Constants.FILEUPLOAD_ERROR_3;
		}
		try {
			appVersion = appVersionService.getAppVersionById(Integer.parseInt(versionId));
			appVersionList = appVersionService.getAppVersionList(Integer.parseInt(appId));
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute(appVersion);
		model.addAttribute("appVersionList",appVersionList);
		model.addAttribute("fileUploadError",fileUploadError);
		return "developer/appversionmodify";
	}
	
	/**
	 * 保存修改后的appVersion
	 * @param appVersion
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/appversionmodifysave",method=RequestMethod.POST)
	public String modifyAppVersionSave(AppVersion appVersion,HttpSession session,HttpServletRequest request,
					@RequestParam(value="attach",required= false) MultipartFile attach){	
		
		String downloadLink =  null;
		String apkLocPath = null;
		String apkFileName = null;
		if(!attach.isEmpty()){
			String path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles");
			logger.info("uploadFile path: " + path);
			String oldFileName = attach.getOriginalFilename();//原文件名
			String prefix = FilenameUtils.getExtension(oldFileName);//原文件后缀
			if(prefix.equalsIgnoreCase("apk")){//apk文件命名：apk名称+版本号+.apk
				 String apkName = null;
				 try {
					apkName = appInfoService.getAppInfo(appVersion.getAppId(),null).getAPKName();
				 } catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				 }
				 if(apkName == null || "".equals(apkName)){
					 return "redirect:/dev/flatform/app/appversionmodify?vid="+appVersion.getId()
							 +"&aid="+appVersion.getAppId()
							 +"&error=error1";
				 }
				 apkFileName = apkName + "-" +appVersion.getVersionNo() + ".apk";
				 File targetFile = new File(path,apkFileName);
				 if(!targetFile.exists()){
					 targetFile.mkdirs();
				 }
				 try {
					attach.transferTo(targetFile);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "redirect:/dev/flatform/app/appversionmodify?vid="+appVersion.getId()
							 +"&aid="+appVersion.getAppId()
							 +"&error=error2";
				} 
				downloadLink = request.getContextPath()+"/statics/uploadfiles/"+apkFileName;
				apkLocPath = path+File.separator+apkFileName;
			}else{
				return "redirect:/dev/flatform/app/appversionmodify?vid="+appVersion.getId()
						 +"&aid="+appVersion.getAppId()
						 +"&error=error3";
			}
		}
		appVersion.setModifyBy(((DevUser)session.getAttribute(Constants.DEV_USER_SESSION)).getId());
		appVersion.setModifyDate(new Date());
		appVersion.setDownloadLink(downloadLink);
		appVersion.setApkLocPath(apkLocPath);
		appVersion.setApkFileName(apkFileName);
		try {
			if(appVersionService.modify(appVersion)){
				return "redirect:/dev/flatform/app/list";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "developer/appversionmodify";
	}


    /**
     * 保存修改后的InsuredInfo
     * @param insuredInfo
     * @param session
     * @return
     */

	@RequestMapping(value = "/insuredbasicinfoaddsave", method = RequestMethod.POST)
    public  String InsuredBasicInfoaddSave(InsuredInfo insuredInfo, HttpSession session, HttpServletRequest request) {
        String buyerName = null;
        logger.info("get policyinfo: " + "");
		try {
			int policyNumber = insuredInfoService.getInsuredInfoCount(null,null)+5001;
			insuredInfo.setId(policyNumber);
			session.setAttribute(Constants.NEW_POLICY, insuredInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		try {
//			if(insuredInfoService.add(insuredInfo)){   //insert into DB
////				session.removeAttribute(Constants.NEW_POLICY);
//				return "developer/policyBasicInfoList";
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        return "developer/policyCAInfoList";
    }


    /**
     * 保存修改后的CAInsuredInfo
     * @param insuredCAInfo
     * @param session
     * @return
     */

//    @RequestMapping(value = "/insuredCAinfoaddsave", method = RequestMethod.POST)
    @RequestMapping(value = "/insuredCAinfoaddsave")
    public  String InsuredCAInfoaddSave(InsuredCAInfo insuredCAInfo, HttpSession session, HttpServletRequest request) {
        String buyerName = null;
        try {
            InsuredInfo insuredInfo = (InsuredInfo) session.getAttribute("newPolicy");
            int policyNumber =  insuredInfo.getId();
            insuredCAInfo.setInsuredId(policyNumber);
            session.setAttribute(Constants.CALINE, insuredCAInfo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

//		try {
//			if(insuredInfoService.add(insuredInfo)){   //insert into DB
////				session.removeAttribute(Constants.NEW_POLICY);
//				return "developer/policyBasicInfoList";
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        return "developer/policyGLInfoList";
    }

	/**
	 * 保存修改后的GLInsuredInfo
	 * @param insuredGLInfo
	 * @param session
	 * @return
	 */

	@RequestMapping(value = "/insuredGLinfoaddsave", method = RequestMethod.POST)
	public  String InsuredGLInfoaddSave(InsuredGLInfo insuredGLInfo, HttpSession session, HttpServletRequest request) {
		String buyerName = null;
		try {
			InsuredInfo insuredInfo = (InsuredInfo) session.getAttribute("newPolicy");
			int policyNumber =  insuredInfo.getId();
			insuredGLInfo.setInsuredId(policyNumber);
			session.setAttribute(Constants.GLLINE, insuredGLInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		try {
//			if(insuredInfoService.add(insuredInfo)){   //insert into DB
////				session.removeAttribute(Constants.NEW_POLICY);
//				return "developer/policyBasicInfoList";
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//rating engine
		InsuredInfo insuredInfo = (InsuredInfo) session.getAttribute("newPolicy");
		insuredInfo.setPremium(RatingController.rateEngine(session));
		session.setAttribute(Constants.NEW_POLICY,insuredInfo);
		return "developer/policyOverview";
	}

	/**
	 * 保存修改后的InsuredOverviewInfo
	 * @param insuredInfo
	 * @param insuredCAInfo
	 * @param insuredGLInfo
	 * @param session
	 * @return
	 */

	@RequestMapping(value = "/insuredOverviewinfoaddsave", method = RequestMethod.POST)
	public  String InsuredOverviewInfoaddSave(InsuredInfo insuredInfo,InsuredCAInfo insuredCAInfo,InsuredGLInfo insuredGLInfo, HttpSession session, HttpServletRequest request) {
		try {
			InsuredInfo insuredBasicOverviewInfo = (InsuredInfo) session.getAttribute("newPolicy");
			insuredInfo = insuredBasicOverviewInfo;
			if(insuredCAInfo != null){
			InsuredCAInfo insuredCAOverviewInfo = (InsuredCAInfo) session.getAttribute("CALine");
			insuredCAInfo = insuredCAOverviewInfo;
			}
			if(insuredGLInfo != null){
			InsuredGLInfo insuredGLOverviewInfo = (InsuredGLInfo) session.getAttribute("GLLine");
			insuredGLInfo = insuredGLOverviewInfo;
			}
			insuredInfo.setPolicyStatus(2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			if(
					insuredInfoService.add(insuredInfo,insuredCAInfo,insuredGLInfo)
					){   //insert into DB
//				session.removeAttribute(Constants.NEW_POLICY);
//				session.removeAttribute(Constants.CALINE);
//				session.removeAttribute(Constants.GLLINE);
				return "developer/policyOverview";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "developer/policyOverview";
	}


    /**
	 * 修改操作时，删除文件（logo图片/apk文件），并更新数据库（app_info/app_version）
	 * @param fileUrlPath
	 * @param fileLocPath
	 * @param flag
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delfile",method=RequestMethod.GET)
	@ResponseBody
	public Object delFile(@RequestParam(value="flag",required=false) String flag,
						 @RequestParam(value="id",required=false) String id){
		HashMap<String, String> resultMap = new HashMap<String, String>();
		String fileLocPath = null;
		if(flag == null || flag.equals("") ||
			id == null || id.equals("")){
			resultMap.put("result", "failed");
		}else if(flag.equals("logo")){//删除logo图片（操作app_info）
			try {
				fileLocPath = (appInfoService.getAppInfo(Integer.parseInt(id), null)).getLogoLocPath();
				File file = new File(fileLocPath);
			    if(file.exists())
			     if(file.delete()){//删除服务器存储的物理文件
						if(appInfoService.deleteAppLogo(Integer.parseInt(id))){//更新表
							resultMap.put("result", "success");
						 }
			    }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(flag.equals("apk")){//删除apk文件（操作app_version）
			try {
				fileLocPath = (appVersionService.getAppVersionById(Integer.parseInt(id))).getApkLocPath();
				File file = new File(fileLocPath);
			    if(file.exists())
			     if(file.delete()){//删除服务器存储的物理文件
						if(appVersionService.deleteApkFile(Integer.parseInt(id))){//更新表
							resultMap.put("result", "success");
						 }
			    }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return JSONArray.toJSONString(resultMap);
	}
	
	/**
	 * 保存修改后的appInfo
	 * @param appInfo
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/appinfomodifysave",method=RequestMethod.POST)
	public String modifySave(AppInfo appInfo,HttpSession session,HttpServletRequest request,
							@RequestParam(value="attach",required= false) MultipartFile attach){		
		String logoPicPath =  null;
		String logoLocPath =  null;
		String APKName = appInfo.getAPKName();
		if(!attach.isEmpty()){
			String path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles");
			logger.info("uploadFile path: " + path);
			String oldFileName = attach.getOriginalFilename();//原文件名
			String prefix = FilenameUtils.getExtension(oldFileName);//原文件后缀
			int filesize = 500000;
			if(attach.getSize() > filesize){//上传大小不得超过 50k
            	 return "redirect:/dev/flatform/app/appinfomodify?id="+appInfo.getId()
						 +"&error=error4";
            }else if(prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png") 
			   ||prefix.equalsIgnoreCase("jepg") || prefix.equalsIgnoreCase("pneg")){//上传图片格式
				 String fileName = APKName + ".jpg";//上传LOGO图片命名:apk名称.apk
				 File targetFile = new File(path,fileName);
				 if(!targetFile.exists()){
					 targetFile.mkdirs();
				 }
				 try {
					attach.transferTo(targetFile);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "redirect:/dev/flatform/app/appinfomodify?id="+appInfo.getId()
							+"&error=error2";
				} 
				 logoPicPath = request.getContextPath()+"/statics/uploadfiles/"+fileName;
				 logoLocPath = path+File.separator+fileName;
            }else{
            	return "redirect:/dev/flatform/app/appinfomodify?id="+appInfo.getId()
						 +"&error=error3";
            }
		}
		appInfo.setModifyBy(((DevUser)session.getAttribute(Constants.DEV_USER_SESSION)).getId());
		appInfo.setModifyDate(new Date());
		appInfo.setLogoLocPath(logoLocPath);
		appInfo.setLogoPicPath(logoPicPath);
		try {
			if(appInfoService.modify(appInfo)){
				return "redirect:/dev/flatform/app/list";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "developer/appinfomodify";
	}
	
	
	@RequestMapping(value="/delapp.json")
	@ResponseBody
	public Object delApp(@RequestParam String id){
		logger.debug("delApp appId===================== "+id);
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if(StringUtils.isNullOrEmpty(id)){
			resultMap.put("delResult", "notexist");
		}else{
			try {
				if(appInfoService.appsysdeleteAppById(Integer.parseInt(id)))
					resultMap.put("delResult", "true");
				else
					resultMap.put("delResult", "false");
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return JSONArray.toJSONString(resultMap);
	}
}
