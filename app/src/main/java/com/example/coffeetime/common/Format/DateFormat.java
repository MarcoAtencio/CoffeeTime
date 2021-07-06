package com.example.coffeetime.common.Format;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {

    public static String dateFormatBasic(Date date){
        return new SimpleDateFormat("dd--MM--yyyy").format(date);
    }

}
