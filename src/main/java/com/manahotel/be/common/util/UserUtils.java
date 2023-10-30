package com.manahotel.be.common.util;

import com.manahotel.be.model.entity.Staff;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtils {
    public static Staff getUser() {
        Staff staff = (Staff) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return staff;
    }
}
