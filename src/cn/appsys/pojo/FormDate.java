package cn.appsys.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class FormDate {
    private Date startDate;//保单开始时间
    private BigDecimal premium;//保费


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }
}
