package com.manahotel.be.common.util;

import com.manahotel.be.common.constant.PolicyCont;
import com.manahotel.be.exception.NoLateSurchargePolicyException;
import com.manahotel.be.model.entity.PolicyDetail;
import com.manahotel.be.repository.PolicyDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ControlPolicyUtils {

    private static PolicyDetailRepository policyDetailRepository;

    @Autowired
    public ControlPolicyUtils(PolicyDetailRepository policyDetailRepository) {
        ControlPolicyUtils.policyDetailRepository = policyDetailRepository;
    }


    public static float calculateLateSurcharge(String roomCategoryId, long lateTime) {
        List<PolicyDetail> policyDetails = policyDetailRepository.findPolicyDetailByPolicyNameAndRoomCategoryId(PolicyCont.LATER_OVERTIME_SURCHARGE, roomCategoryId);

        if(policyDetails.isEmpty()) {
            throw new NoLateSurchargePolicyException("Chính sách phụ thu trả muộn của hạng phòng " + roomCategoryId + "không tồn tại");
        }

        float surcharge = 0;

        for(PolicyDetail policyDetail : policyDetails) {
            for(long i = lateTime; i >= policyDetail.getFrom(); i--) {
                surcharge += policyDetail.getValue();

                if(i == policyDetail.getFrom()) {
                    lateTime = i - 1;
                }
            }
        }
        return surcharge;
    }
}
