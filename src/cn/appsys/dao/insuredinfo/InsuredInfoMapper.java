package cn.appsys.dao.insuredinfo;

import cn.appsys.pojo.AppInfo;
import cn.appsys.pojo.InsuredCAInfo;
import cn.appsys.pojo.InsuredGLInfo;
import cn.appsys.pojo.InsuredInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface InsuredInfoMapper {

	public int add(InsuredInfo insuredInfo) throws Exception;

	public int addCA(InsuredCAInfo insuredCAInfo) throws Exception;

	public int addGL(InsuredGLInfo insuredGLInfo) throws Exception;

	public int modify(InsuredInfo insuredInfo)throws Exception;

	public int deleteInsuredInfoById(@Param(value = "id") Integer delId)throws Exception;

	public List<InsuredInfo> getInsuredInfoList(@Param(value = "id") Integer queryId,
                                        @Param(value = "buyerName") String queryBuyerName,
                                        @Param(value = "policyStatus") Integer queryPolicyStatus,
                                        @Param(value = "startDate") Date queryStartDate,
                                        @Param(value = "endDate") Date queryEndDate,
                                        @Param(value = "beneficiaryName") String queryBeneficiaryName,
                                        @Param(value = "premium") BigDecimal queryPremium,
                                        @Param(value = "isApproved") Boolean queryIsApproved,
                                        @Param(value = "isPaid") Boolean queryIsPaid,
										@Param(value = "versionId")	Integer queryVersionId)throws Exception;


	public InsuredInfo getInsuredInfo(@Param(value = "id") Integer id, @Param(value = "buyerName") String buyerName)throws Exception;

	public InsuredCAInfo getInsuredCAInfo(@Param(value = "id") Integer id)throws Exception;

	public InsuredGLInfo getInsuredGLInfo(@Param(value = "id") Integer id)throws Exception;


	public int getInsuredInfoCount( @Param(value="id") Integer queryId,
									@Param(value = "policyStatus") Integer querypolicyStatus)throws Exception;
	/**
	 * 根据appId，更新最新versionId
	 * @param versionId
	 * @param appId
	 * @return
	 * @throws Exception
	 */
	public int updateVersionId(@Param(value = "versionId") Integer versionId, @Param(value = "id") Integer appId)throws Exception;

	/**
	 * updateIsApprovedById
	 * @param appId
	 * @return
	 * @throws Exception
	 */
	public int updateIsApprovedById(@Param(value = "id") Integer appId) throws Exception;

	/*
	 * 更新policy状态
	 * @param policyStatus
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int updateStatus(@Param(value="policyStatus")Integer policyStatus,@Param(value="isApproved")boolean isApproved,@Param(value="id")Integer id)throws Exception;

}
