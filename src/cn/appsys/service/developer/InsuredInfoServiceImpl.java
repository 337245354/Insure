package cn.appsys.service.developer;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cn.appsys.dao.insuredinfo.InsuredInfoMapper;
import cn.appsys.pojo.InsuredInfo;
import org.springframework.stereotype.Service;

import cn.appsys.dao.appinfo.AppInfoMapper;
import cn.appsys.dao.appversion.AppVersionMapper;
import cn.appsys.pojo.AppInfo;
import cn.appsys.pojo.AppVersion;

@Service
public class InsuredInfoServiceImpl implements InsuredInfoService {
    @Resource
    private InsuredInfoMapper mapper;


    @Override
    public boolean add(InsuredInfo insuredInfo) throws Exception {
        // TODO Auto-generated method stub
        boolean flag = false;
        if(mapper.add(insuredInfo) > 0){
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean modify(InsuredInfo insuredInfo) throws Exception {
        // TODO Auto-generated method stub
        boolean flag = false;
        if(mapper.modify(insuredInfo) > 0){
            flag = true;
        }
        return flag;
    }

    @Override
    public int getInsuredInfoCount(Integer id,Integer policyStatus) throws Exception {
        // TODO Auto-generated method stub
        return mapper.getInsuredInfoCount(id,policyStatus);
    }

    @Override
    public InsuredInfo getInsuredInfo(Integer id,String buyerName) throws Exception {
        // TODO Auto-generated method stub
        return mapper.getInsuredInfo(id,buyerName);
    }


    @Override
    public List<InsuredInfo> getInsuredInfoList(Integer id,Integer policyStatus) throws Exception {
        // TODO Auto-generated method stub
        return mapper.getInsuredInfoList(id,null,policyStatus,
                null,null,null,null,
                null,null,null);
    }

}
