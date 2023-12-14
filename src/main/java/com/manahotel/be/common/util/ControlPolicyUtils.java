package com.manahotel.be.common.util;

import com.manahotel.be.model.dto.response.CustomerDTO;
import com.manahotel.be.model.entity.PolicyDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
@Slf4j
@Component
public class ControlPolicyUtils {

    public static float calculateLateSurcharge(long lateTime, float roomPrice, List<PolicyDetail> policyDetails) {
        float surcharge = 0;
        try {
            for(PolicyDetail policyDetail : policyDetails) {
                if(policyDetail.getLimitValue() <= lateTime) {
                    surcharge = (policyDetail.getPolicyValue() / 100) * roomPrice;
                    break;
                }
            }
        }catch (Exception e){
            log.error("calculate Late Surcharge is fail " + e.getMessage());
        }

        return surcharge;
    }

    public static float calculateEarlySurcharge(long earlyTime, float roomPrice, List<PolicyDetail> policyDetails) {
        float surcharge = 0;
        try{
            for(PolicyDetail policyDetail : policyDetails) {
                if(policyDetail.getLimitValue() <= earlyTime) {
                    surcharge = (policyDetail.getPolicyValue() / 100) * roomPrice;
                    break;
                }
            }
        }catch (Exception e){
            log.error("calculate Early Surcharge is fail " + e.getMessage());
        }
        return surcharge;
    }

    public static float calculateAdditionalAdultSurcharge(long totalAdult, float priceRoom, long timeUse, List<PolicyDetail> policyDetails) {
        float result = 0;
        try{
            float surcharge = 0;
            for(PolicyDetail policyDetail : policyDetails) {
                for(long i = totalAdult; i >= policyDetail.getLimitValue(); i--) {
                    surcharge += (policyDetail.getPolicyValue()*priceRoom/100);
                    if(i == policyDetail.getLimitValue()) {
                        totalAdult = i - 1;
                    }
                }
            }
            result = surcharge * timeUse;
        }catch (Exception e){
            log.error("calculate Additional Adult Surcharge is fail " + e.getMessage());
        }
        return result;
    }

    public static float calculateAdditionalChildrenSurcharge(List<PolicyDetail> policyDetails, List<CustomerDTO> customerDTOS, float priceRoom, long timeUse) {
        float result = 0;
        float TotalSurcharge = 0;
        try{
            Collections.sort(policyDetails, Comparator.comparing(PolicyDetail::getLimitValue).reversed());
            for (CustomerDTO dto : customerDTOS) {
                LocalDateTime dobDateTime = LocalDateTime.parse(dto.getDob(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                int age = Period.between(
                        dobDateTime.atZone(ZoneId.systemDefault()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                        LocalDate.now()).getYears();
                float surcharge = 0;
                float tmp = 0;
                for(PolicyDetail policyDetail : policyDetails) {
                    if(age < 6){
                        surcharge =0;
                    }else if(age > 5 && tmp <= age  && age < policyDetail.getLimitValue()){
                        surcharge = (policyDetail.getPolicyValue()/100)*priceRoom;
                    }
                    tmp = policyDetail.getLimitValue();
                }
                TotalSurcharge += surcharge;
            }
            result = TotalSurcharge * timeUse;
        }catch (Exception e){
            log.error("calculate Additional Children Surcharge is fail " + e.getMessage());
        }
        return result;
    }
}
