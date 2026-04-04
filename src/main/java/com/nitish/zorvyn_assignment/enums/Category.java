package com.nitish.zorvyn_assignment.enums;

public enum Category {

    // Expense categories
    FOOD(RecordType.EXPENSE),
    RENT(RecordType.EXPENSE),
    UTILITIES(RecordType.EXPENSE),
    TRANSPORT(RecordType.EXPENSE),
    ENTERTAINMENT(RecordType.EXPENSE),
    HEALTHCARE(RecordType.EXPENSE),
    EDUCATION(RecordType.EXPENSE),
    SHOPPING(RecordType.EXPENSE),
    TRAVEL(RecordType.EXPENSE),
    INSURANCE(RecordType.EXPENSE),
    BILLS(RecordType.EXPENSE),
    SUBSCRIPTIONS(RecordType.EXPENSE),
    LOAN(RecordType.EXPENSE),
    EMI(RecordType.EXPENSE),
    GROCERIES(RecordType.EXPENSE),
    PERSONAL_CARE(RecordType.EXPENSE),
    GIFTS(RecordType.EXPENSE),
    DONATIONS(RecordType.EXPENSE),
    TAX(RecordType.EXPENSE),
    OTHER_EXPENSE(RecordType.EXPENSE),

    // Income categories
    SALARY(RecordType.INCOME),
    BONUS(RecordType.INCOME),
    FREELANCE(RecordType.INCOME),
    BUSINESS(RecordType.INCOME),
    INVESTMENT(RecordType.INCOME),
    RENTAL_INCOME(RecordType.INCOME),
    INTEREST(RecordType.INCOME),
    DIVIDENDS(RecordType.INCOME),
    REFUND(RecordType.INCOME),
    COMMISSION(RecordType.INCOME),
    OTHER_INCOME(RecordType.INCOME);

    private final RecordType type;

    Category(RecordType type) {
        this.type = type;
    }

    public RecordType getRecordType() {
        return type;
    }
}
