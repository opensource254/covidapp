package app.azim.opensource254.covidkenya.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UtilityFunctions {

    public static String getFormatedCurrentDate(){
        Date todaysDate = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(todaysDate);
        return formattedDate;

    }
}
