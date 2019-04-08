package cn.appsys.pojo;

import java.util.Date;

public class InsuredGLInfo {
    private Integer id;//主键id
    private Integer insuredId;//外键，保险单号，来源于：insured_info表的主键id
    private String insuredPeople;//被保人姓名
    private String insuredPeopleID;//被保人身份证
    private Integer insuredPeopleAge;//被保人年龄
    private Boolean insuredPeopleGender;//被保人性别（false：女(0)，true：男(1)）
    private Integer limit;//保额赔付上限（元人民币）
    private Boolean peopleAccident;//是否购买人身意外险（默认值false：否(0)，true：是(1)）
    private Boolean peopleIllness;//是否购买重疾险（默认值false：否(0)，true：是(1)）
    private String comment;//备注

    public Integer getInsuredId() {
        return insuredId;
    }

    public void setInsuredId(Integer insuredId) {
        this.insuredId = insuredId;
    }

    public String getInsuredPeople() {
        return insuredPeople;
    }

    public void setInsuredPeople(String insuredPeople) {
        this.insuredPeople = insuredPeople;
    }

    public String getInsuredPeopleID() {
        return insuredPeopleID;
    }

    public void setInsuredPeopleID(String insuredPeopleID) {
        this.insuredPeopleID = insuredPeopleID;
    }

    public Integer getInsuredPeopleAge() {
        return insuredPeopleAge;
    }

    public void setInsuredPeopleAge(Integer insuredPeopleAge) {
        this.insuredPeopleAge = insuredPeopleAge;
    }

    public Boolean getInsuredPeopleGender() {
        return insuredPeopleGender;
    }

    public void setInsuredPeopleGender(Boolean insuredPeopleGender) {
        this.insuredPeopleGender = insuredPeopleGender;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Boolean getPeopleAccident() {
        return peopleAccident;
    }

    public void setPeopleAccident(Boolean peopleAccident) {
        this.peopleAccident = peopleAccident;
    }

    public Boolean getPeopleIllness() {
        return peopleIllness;
    }

    public void setPeopleIllness(Boolean peopleIllness) {
        this.peopleIllness = peopleIllness;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
