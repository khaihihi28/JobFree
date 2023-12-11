package com.poly.duantotnghiep_jf.Util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimeUtils {
    public static String getCurrentDateTimeString() {
        // Lấy ngày giờ hiện tại
        Date currentDate = new Date();

        // Định dạng ngày tháng giờ
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.getDefault());

        // Chuyển đổi thành chuỗi
        return dateFormat.format(currentDate);
    }
}
