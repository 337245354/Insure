package cn.appsys.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class FormDate {
    private Date startDate;//保单开始时间
    private BigDecimal premiumForDate;//保费
    private String buyerName;//购买保险人姓名
    private Integer id;//主键id
    private BigDecimal premiumForSinglePolicy;//保费
    private Integer numberOfNotPayForDate; //每天没有付费的单子数量


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumberOfNotPayForDate() {
        return numberOfNotPayForDate;
    }

    public void setNumberOfNotPayForDate(Integer numberOfNotPayForDate) {
        this.numberOfNotPayForDate = numberOfNotPayForDate;
    }

    public BigDecimal getPremiumForDate() {
        return premiumForDate;
    }

    public void setPremiumForDate(BigDecimal premiumForDate) {
        this.premiumForDate = premiumForDate;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public BigDecimal getPremiumForSinglePolicy() {
        return premiumForSinglePolicy;
    }

    public void setPremiumForSinglePolicy(BigDecimal premiumForSinglePolicy) {
        this.premiumForSinglePolicy = premiumForSinglePolicy;
    }
}
