package com.example.coffeetime.common.Format;

import java.text.DecimalFormat;

public class CurrencyFormat {

    public static String roundMoney(double number){
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        return "S/ "+decimalFormat.format(number);
    }

    public static String roundMoney(String number){
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        return "S/ "+decimalFormat.format(number);
    }
}
