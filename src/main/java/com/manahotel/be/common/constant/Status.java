package com.manahotel.be.common.constant;

public enum Status {
    ACTIVATE(1L),
    DEACTIVATE(2L),

    // Inventory Check
    CANCEL(3L),
    TEMPORARY(4L),
    BALANCE(5L);

    private final Long statusId;

    Status(Long statusId) {
        this.statusId = statusId;
    }

    public Long getStatusId() {
        return statusId;
    }
}
