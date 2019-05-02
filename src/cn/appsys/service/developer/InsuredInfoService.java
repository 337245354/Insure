package cn.appsys.service.developer;

import cn.appsys.pojo.AppInfo;
import cn.appsys.pojo.InsuredCAInfo;
import cn.appsys.pojo.InsuredGLInfo;
import cn.appsys.pojo.InsuredInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InsuredInfoService {

	/**
	 * 新增policy基础信息
	 * @param insuredInfo
	 * @return
	 * @throws Exception
	 */
	public boolean add(InsuredInfo insuredInfo) throws Exception;

	/**
	 * 新增policy车辆信息
	 * @param insuredCAInfo
	 * @return
	 * @throws Exception
	 */
	public boolean add(InsuredCAInfo  insuredCAInfo) throws Exception;

    /**
     * 新增policy寿险信息
     * @param insuredGLInfo
     * @return
     * @throws Exception
     */
    public boolean add(InsuredGLInfo  insuredGLInfo) throws Exception;

	/**
	 * 新增policy多个信息
	 * @param insuredInfo
	 * @param insuredCAInfo
	 * @param insuredGLInfo
	 * @return
	 * @throws Exception
	 */
	public boolean add(InsuredInfo insuredInfo, InsuredCAInfo  insuredCAInfo, InsuredGLInfo insuredGLInfo) throws Exception;

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
	 * 根据id、buyerName查找InsuredBasicInfo
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public InsuredInfo getInsuredInfo(Integer id, String buyerName)throws Exception;

	/**
	 * 根据id查找InsuredCAInfo
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public InsuredCAInfo getInsuredCAInfo(Integer id)throws Exception;

	/**
	 * 根据id查找InsuredGLInfo
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public InsuredGLInfo getInsuredGLInfo(Integer id)throws Exception;

	/**
	 * 根据id更新policy的status
	 * @param policyStatus
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean updateStatus(@Param(value="policyStatus")Integer policyStatus, @Param(value="id")Integer id)throws Exception;

}
