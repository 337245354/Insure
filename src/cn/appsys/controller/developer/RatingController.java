package cn.appsys.controller.developer;

import cn.appsys.pojo.InsuredCAInfo;
import cn.appsys.pojo.InsuredGLInfo;
import cn.appsys.tools.Constants;
import org.springframework.ui.Model;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

public class RatingController {
    public static BigDecimal rateEngine(HttpSession session){
        BigDecimal premium = new BigDecimal(100.0);
        BigDecimal CAAmount = new BigDecimal(0.0);
        BigDecimal GLAmount = new BigDecimal(0.0);

        InsuredCAInfo insuredCAInfo = null;
        InsuredGLInfo insuredGLInfo = null;

        Object CALine = session.getAttribute(Constants.CALINE);
        if(CALine instanceof InsuredCAInfo) {
            insuredCAInfo = (InsuredCAInfo) CALine;
        }

        Object GLLine = session.getAttribute(Constants.GLLINE);
        if(GLLine instanceof InsuredGLInfo) {
            insuredGLInfo = (InsuredGLInfo) GLLine;
        }

        if(insuredCAInfo != null){
            BigDecimal baseLimit = BigDecimal.valueOf(insuredCAInfo.getLimit());
            BigDecimal factor = new BigDecimal(0.0);
            BigDecimal factorCarDamage = new BigDecimal(0.04);
            BigDecimal factorCarFire = new BigDecimal(0.02);
            BigDecimal factorCarGlass = new BigDecimal(0.01);
            if (insuredCAInfo.getCarDamage() != null && insuredCAInfo.getCarDamage()){
                factor = factor.add(factorCarDamage);
            }
            if (insuredCAInfo.getCarFire() != null && insuredCAInfo.getCarFire()){
                factor = factor.add(factorCarFire);
            }
            if (insuredCAInfo.getCarGlass() != null && insuredCAInfo.getCarGlass()){
                factor = factor.add(factorCarGlass);
            }
            CAAmount = baseLimit.multiply(factor).setScale(2,BigDecimal.ROUND_HALF_EVEN);
        }

        if(insuredGLInfo != null){
            BigDecimal baseLimit = BigDecimal.valueOf(insuredGLInfo.getLimit());
            BigDecimal factor = new BigDecimal(0.0);
            BigDecimal factorPeopleAccident = new BigDecimal(0.02);
            BigDecimal factorPeopleIllness = new BigDecimal(0.03);
            if (insuredGLInfo.getPeopleAccident() != null && insuredGLInfo.getPeopleAccident()){
                factor = factor.add(factorPeopleAccident);
            }
            if (insuredGLInfo.getPeopleIllness() != null && insuredGLInfo.getPeopleIllness()){
                factor = factor.add(factorPeopleIllness);
            }

            GLAmount = baseLimit.multiply(factor).setScale(2,BigDecimal.ROUND_HALF_EVEN);
        }

        premium = premium.add(GLAmount).add(CAAmount);

        return premium;
    }
}
