package tech.nermindedovic.xmlmessageservice.business;

import java.util.EnumMap;

public enum PaymentEnum {
    NAME, ADDRESS, ACCOUNT_NUMBER, AMOUNT;

    private static EnumMap<PaymentEnum, String> map;

    public static EnumMap<PaymentEnum, String> getEnumMap() {
        if (map != null) {
            return map;
        }

        map = new EnumMap<PaymentEnum, String>(PaymentEnum.class);
        map.put(PaymentEnum.NAME, "name");
        map.put(PaymentEnum.ADDRESS, "address");
        map.put(PaymentEnum.ACCOUNT_NUMBER, "accountNumber");
        map.put(PaymentEnum.AMOUNT, "amount");
        return map;
    }
}
