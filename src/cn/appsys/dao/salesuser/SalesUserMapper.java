package cn.appsys.dao.salesuser;

import cn.appsys.pojo.SalesUser;
import org.apache.ibatis.annotations.Param;

public interface SalesUserMapper {
	/**
	 * 通过userCode获取User
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	public SalesUser getLoginUser(@Param("salesCode") String salesCode)throws Exception;
}
