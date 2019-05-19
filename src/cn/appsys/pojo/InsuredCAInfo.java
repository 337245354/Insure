package cn.appsys.pojo;

import java.util.Date;

public class InsuredCAInfo {
    private Integer id;//主键id
    private Integer insuredId;//外键，保险单号，来源于：insured_info表的主键id
    private String vehicleModel;//车辆型号
    private Date vehicleBuyInTime;//车辆买入时间
    private Integer limitCA;//保额赔付上限（元人民币）
    private Boolean carDamage;//是否购买车损险
    private Boolean carFire;//是否购买自燃险
    private Boolean carGlass;//是否购买玻璃险
    private String comment;//备注

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInsuredId() {
        return insuredId;
    }

    public void setInsuredId(int insuredId) {
        this.insuredId = insuredId;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public Date getVehicleBuyInTime() {
        return vehicleBuyInTime;
    }

    public void setVehicleBuyInTime(Date vehicleBuyInTime) {
        this.vehicleBuyInTime = vehicleBuyInTime;
    }

    public Integer getLimitCA() {
        return limitCA;
    }

    public void setLimitCA(Integer limitCA) {
        this.limitCA = limitCA;
    }

    public Boolean getCarDamage() {
        return carDamage;
    }

    public void setCarDamage(Boolean carDamage) {
        this.carDamage = carDamage;
    }

    public Boolean getCarFire() {
        return carFire;
    }

    public void setCarFire(Boolean carFire) {
        this.carFire = carFire;
    }

    public Boolean getCarGlass() {
        return carGlass;
    }

    public void setCarGlass(Boolean carGlass) {
        this.carGlass = carGlass;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
