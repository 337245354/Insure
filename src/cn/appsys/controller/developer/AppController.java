package cn.appsys.controller.developer;

import java.io.File;
import java.math.BigDecimal;
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
    private InsuredInfoService insuredInfoService;
	@Resource 
	private DataDictionaryService dataDictionaryService;




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
	 * 增加insured信息（跳转到新增insuredinfo页面）
	 * @param insuredInfo
	 * @return
	 */
    @RequestMapping(value="/insuredinfoadd",method=RequestMethod.GET)
    public String insuredinfoadd(@ModelAttribute("insuredInfo") InsuredInfo insuredInfo){



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

		return "developer/policyAllList";
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
			insuredInfo.setPolicyStatus(2);
            session.setAttribute(Constants.NEW_POLICY,insuredInfo);//

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
			insuredInfo.setPolicyStatus(2);    //
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



}
