package com.manahotel.be.common.constant;

public enum Status {
    ACTIVE(1L),
    DEACTIVATE(2L);

    private final Long statusId;

    Status(Long statusId) {
        this.statusId = statusId;
    }

    public Long getStatusId() {
        return statusId;
    }
}
