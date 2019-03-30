package cn.appsys.pojo;

import java.util.Date;

public class InsuredCAInfo {
    private Integer id;//主键id
    private Integer insuredId;//外键，保险单号，来源于：insured_info表的主键id
    private String vehicleModel;//车辆型号
    private String vehicleBuyInTime;//车辆买入时间
    private Integer limit;//保额赔付上限（元人民币）
    private Date carDamage;//是否购买车损险
    private Date carFire;//是否购买自燃险
    private Date carGlass;//是否购买玻璃险
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

    public String getVehicleBuyInTime() {
        return vehicleBuyInTime;
    }

    public void setVehicleBuyInTime(String vehicleBuyInTime) {
        this.vehicleBuyInTime = vehicleBuyInTime;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Date getCarDamage() {
        return carDamage;
    }

    public void setCarDamage(Date carDamage) {
        this.carDamage = carDamage;
    }

    public Date getCarFire() {
        return carFire;
    }

    public void setCarFire(Date carFire) {
        this.carFire = carFire;
    }

    public Date getCarGlass() {
        return carGlass;
    }

    public void setCarGlass(Date carGlass) {
        this.carGlass = carGlass;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
