package cn.appsys.service.developer;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cn.appsys.dao.insuredinfo.InsuredInfoMapper;
import cn.appsys.pojo.*;
import org.springframework.stereotype.Service;

import cn.appsys.dao.appinfo.AppInfoMapper;
import cn.appsys.dao.appversion.AppVersionMapper;

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
    public boolean add(InsuredCAInfo insuredCAInfo) throws Exception {
        // TODO Auto-generated method stub
        boolean flag = false;
        if(mapper.addCA(insuredCAInfo) > 0){
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean add(InsuredGLInfo insuredGLInfo) throws Exception {
        // TODO Auto-generated method stub
        boolean flag = false;
        if(mapper.addGL(insuredGLInfo) > 0){
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean add(InsuredInfo insuredInfo, InsuredCAInfo insuredCAInfo, InsuredGLInfo insuredGLInfo) throws Exception {
        // TODO Auto-generated method stub
        boolean flag = false;
        int flagNumber = 0;
        if(mapper.add(insuredInfo) > 0 ){
            flagNumber ++;
        }
        if(mapper.addCA(insuredCAInfo) > 0 ){
            flagNumber ++;
        }
        if(mapper.addGL(insuredGLInfo) > 0 ){
            flagNumber ++;
        }
        if(flagNumber >= 3 ){
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean modify(InsuredInfo insuredInfo,InsuredCAInfo  insuredCAInfo, InsuredGLInfo insuredGLInfo) throws Exception {
        // TODO Auto-generated method stub
        boolean flag = false;
        int flagNumber = 0;
        if(mapper.modify(insuredInfo) > 0 ){
            flagNumber ++;
        }
        if(mapper.modifyCA(insuredCAInfo) > 0 ){
            flagNumber ++;
        }
        if(mapper.modifyGL(insuredGLInfo) > 0 ){
            flagNumber ++;
        }
        if(flagNumber >= 3 ){
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
    public InsuredCAInfo getInsuredCAInfo(Integer id) throws Exception {
        // TODO Auto-generated method stub
        return mapper.getInsuredCAInfo(id);
    }

    @Override
    public InsuredGLInfo getInsuredGLInfo(Integer id) throws Exception {
        // TODO Auto-generated method stub
        return mapper.getInsuredGLInfo(id);
    }

    @Override
    public List<InsuredInfo> getInsuredInfoList(Integer id,Integer policyStatus,Integer currentPageNo,Integer pageSize) throws Exception {
        // TODO Auto-generated method stub
        return mapper.getInsuredInfoList(id,null,policyStatus,
                null,null,null,null,
                null,null,null, (currentPageNo-1)*pageSize, pageSize);
    }

    @Override
    public boolean updateStatus(Integer policyStatus, Integer id) throws Exception {
        // TODO Auto-generated method stub
        boolean flag = false;
        boolean isApproved =false;
        if (policyStatus == 4){isApproved = true;}
        if(mapper.updateStatus(policyStatus, isApproved,id) > 0 ){
            flag = true;
        }
        return flag;
    }
}
