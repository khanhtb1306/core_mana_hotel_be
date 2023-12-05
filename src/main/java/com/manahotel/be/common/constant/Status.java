package com.manahotel.be.common.constant;

public class Status {

    // Activate or deactivate
    public static final long ACTIVATE = 1;
    public static final long DEACTIVATE = 2;

    // Inventory checklist
    public static final long CANCEL = 3;
    public static final long TEMPORARY = 4;
    public static final long BALANCE = 5;
    public static final long IMPORT = 7;

    // Delete
    public static final long DELETE = 6;

    //Booking status
    public static final String ROOM_EMPTY = "ROOM_EMPTY";
    public static final String ROOM_USING = "ROOM_USING";

    //Clean room
    public static final String CLEAN = "ROOM_CLEAN";
    public static final String UNCLEAN = "ROOM_UNCLEAN";

    //Reservation_type
    public static final String HOURLY = "HOURLY";
    public static final String DAILY = "DAILY";
    public static final String OVERNIGHT = "OVERNIGHT";

    // Reservation and Reservation Detail
    public static final String PENDING = "PENDING"; // For reservation only
    public static final String BOOKING = "BOOKING";
    public static final String CHECK_IN = "CHECK_IN";
    public static final String CHECK_OUT = "CHECK_OUT";
    public static final String DISCARD = "DISCARD"; // For reservation only
    public static final String DONE = "DONE";

    // Order
    public static final String UNCONFIRMED = "UNCONFIRMED";

    public static final String CONFIRMED = "CONFIRMED";

    public static final String PAID = "PAID";

    public static final String CANCEL_ORDER = "CANCEL_ORDER";

    // CustomerGroup and Staff
    public static final String ACTIVE = "ACTIVE";
    public static final String NO_ACTIVE = "NO_ACTIVE";

    // Payment Method
    public static final String CASH = "CASH";
    public static final String CARD = "CARD";
    public static final String TRANSFER = "TRANSFER";

    // Income and Expense
    public static final String INCOME = "INCOME"; // Collect customer's money
    public static final String EXPENSE = "EXPENSE"; // Pay supplier
    public static final String OTHER_INCOME = "OTHER_INCOME";
    public static final String OTHER_EXPENSE = "OTHER_EXPENSE";

    // Status FundBook and Invoice
    public static final String COMPLETE = "COMPLETE";
    public static final String ABORT = "ABORT";
}
