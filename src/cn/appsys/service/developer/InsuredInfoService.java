package cn.appsys.service.developer;

import cn.appsys.pojo.AppInfo;
import cn.appsys.pojo.InsuredInfo;

import java.util.List;

public interface InsuredInfoService {

	/**
	 * 新增app
	 * @param insuredInfo
	 * @return
	 * @throws Exception
	 */
	public boolean add(InsuredInfo insuredInfo) throws Exception;
	/**
	 * 修改app信息
	 * @param insuredInfo
	 * @return
	 * @throws Exception
	 */
	public boolean modify(InsuredInfo insuredInfo)throws Exception;


	/**
	 * 根据条件查询insuredInfo表记录数
	 * @param id
	 * @param policyStatus
	 * @return
	 * @throws Exception
	 */
	public int getInsuredInfoCount(Integer id,Integer policyStatus)throws Exception;


	/**
	 * 根据条件查询出InsuredInfo列表
	 * @param id
	 * @param policyStatus
	 * @return
	 * @throws Exception
	 */
	public List<InsuredInfo> getInsuredInfoList(Integer id,Integer policyStatus)throws Exception;



	/**
	 * 根据id、buyerName查找InsuredInfo
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public InsuredInfo getInsuredInfo(Integer id, String buyerName)throws Exception;
	

}
