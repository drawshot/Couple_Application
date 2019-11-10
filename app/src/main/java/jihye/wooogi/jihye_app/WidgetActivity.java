package jihye.wooogi.jihye_app;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.support.v7.view.menu.MenuView;
import android.widget.Button;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Implementation of App Widget functionality.
 */
public class WidgetActivity extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        long date = date_calendar();

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_activity);
        views.setTextViewText(R.id.appwidget_button, "" + date + "일❤︎");

        Intent intent = new Intent(context, Splash.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        views.setOnClickPendingIntent(R.id.appwidget_button, pendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);


    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    public static long date_calendar() {
        // Create Calendar instance
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat today_day = new SimpleDateFormat("dd");
        SimpleDateFormat today_month = new SimpleDateFormat("MM");
        SimpleDateFormat today_year = new SimpleDateFormat("yyyy");
        int formatted_day = Integer.parseInt(today_day.format(c));
        int formatted_month = Integer.parseInt(today_month.format(c));
        int formatted_year = Integer.parseInt(today_year.format(c));

        // Set the values for the calendar fields YEAR, MONTH, and DAY_OF_MONTH.
        calendar2.set(formatted_year, formatted_month, formatted_day);
        calendar1.set(2018, 11, 19);

        int we_day = 0;
        int we_after_year_to_day = 0;
        if(formatted_year - 2018 >= 1) {
            int a = formatted_year - 2018;
            we_after_year_to_day = 365 * a;
        }

        int we_before_month_to_day = 0;
        int we_after_month_to_day = 0;
        we_before_month_to_day = month_to_day(11 - 1);
        we_after_month_to_day = month_to_day(formatted_month - 1);

        we_before_month_to_day += 19;
        we_after_month_to_day += formatted_day;

        int we_before_total_day = 0;
        int we_after_total_day = 0;

        we_before_total_day = we_before_month_to_day;
        we_after_total_day = we_after_year_to_day + we_after_month_to_day;

        we_day = we_after_total_day - we_before_total_day + 1;

        /*
         * Use getTimeInMillis() method to get the Calendar's time value in
         * milliseconds. This method returns the current time as UTC
         * milliseconds from the epoch
         */
        long miliSecondForDate1 = calendar1.getTimeInMillis();
        long miliSecondForDate2 = calendar2.getTimeInMillis();
        // Calculate the difference in millisecond between two dates
        long diffInMilis = miliSecondForDate2 - miliSecondForDate1;
        /*
         * Now we have difference between two date in form of millsecond we can
         * easily convert it Minute / Hour / Days by dividing the difference
         * with appropriate value. 1 Second : 1000 milisecond 1 Hour : 60 * 1000
         * millisecond 1 Day : 24 * 60 * 1000 milisecond
         */


//        long diffInSecond = diffInMilis / 1000;
//        long diffInMinute = diffInMilis / (60 * 1000);
//        long diffInHour = diffInMilis / (60 * 60 * 1000);
        long diffInDays = diffInMilis / (24 * 60 * 60 * 1000);
//        System.out.println("Difference in Seconds : " + diffInSecond);
//        System.out.println("Difference in Minute : " + diffInMinute);
//        System.out.println("Difference in Hours : " + diffInHour);
//        System.out.println("Difference in Days : " + diffInDays);

        return we_day;

    }

    public static int month_to_day(int month) {

        int total_day = 0;

        if(month == 1) {
            total_day = 31;
        }
        else if(month == 2) {
            total_day = 31 + 28;
        }
        else if(month == 3) {
            total_day = 31 + 28 + 31;
        }
        else if(month == 4) {
            total_day = 31 + 28 + 31 + 30;
        }
        else if(month == 5) {
            total_day = 31 + 28 + 31 + 30 + 31;
        }
        else if(month == 6) {
            total_day = 31 + 28 + 31 + 30 + 31 + 30;
        }
        else if(month == 7) {
            total_day = 31 + 28 + 31 + 30 + 31 + 30 + 31;
        }
        else if(month == 8) {
            total_day = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31;
        }
        else if(month == 9) {
            total_day = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30;
        }
        else if(month == 10) {
            total_day = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31;
        }
        else if(month == 11) {
            total_day = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + 30;
        }
        else if(month == 12) {
            total_day = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + 30 + 31;
        }

        return total_day;
    }
}

