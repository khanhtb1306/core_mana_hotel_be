package com.manahotel.be.security;

import com.manahotel.be.model.entity.Staff;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {
    private Staff staff;
    private String applicationUrl;

    public RegistrationCompleteEvent(Staff staff, String applicationUrl) {
        super(staff);
        this.staff = staff;
        this.applicationUrl = applicationUrl;
    }
}
