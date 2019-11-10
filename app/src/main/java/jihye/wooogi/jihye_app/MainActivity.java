package jihye.wooogi.jihye_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        FirebaseFirestore db = FirebaseFirestore.getInstance();


        long date = date_calendar();

        TextView date_text = (TextView)findViewById(R.id.love_date);
        date_text.setText("" + date + "일❤︎");

//        Button coupon_btn = (Button)findViewById(R.id.coupon_btn);
        Button todo_btn = (Button)findViewById(R.id.todo_btn);
        Button home_btn = (Button)findViewById(R.id.home_btn);
        ImageView woogi_img = (ImageView)findViewById(R.id.woogi_img);
        ImageView jihye_img = (ImageView)findViewById(R.id.jihye_img);
        Button calendar_btn = (Button)findViewById(R.id.calendar_btn);

//        coupon_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent=new Intent(MainActivity.this,CouponActivity.class);
//                startActivity(intent);
//            }
//        });

        todo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabaseHelper.data_name = 2;
                Intent intent=new Intent(MainActivity.this,TodoActivity.class);
                startActivity(intent);
            }
        });

        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        woogi_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                FirebaseDatabaseHelper.is_woogi = true;
                FirebaseDatabaseHelper.data_name = 0;
                Intent intent=new Intent(MainActivity.this,WooGi_Coupon.class);
                startActivity(intent);
            }
        });

        jihye_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                FirebaseDatabaseHelper.is_woogi = false;
                FirebaseDatabaseHelper.data_name = 1;
                Intent intent=new Intent(MainActivity.this,JiHye_Coupon.class);
                startActivity(intent);
            }
        });

        calendar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                FirebaseDatabaseHelper.is_woogi = false;
//                FirebaseDatabaseHelper.data_name = 1;
                Intent intent=new Intent(MainActivity.this,Calendar_View.class);
                startActivity(intent);
            }
        });
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

//        Log.d("woogi", "day = " + formatted_year + " " + formatted_month + " " + formatted_day);

        // Set the values for the calendar fields YEAR, MONTH, and DAY_OF_MONTH.
//        calendar2.set(formatted_year, formatted_month, formatted_day);
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

//        Log.d("woogi", "before_day = " + "2018년 11월 19일");
//        Log.d("woogi", "after_day = " + formatted_year + "년 " + formatted_month + "월 " + formatted_day + "일");
//        Log.d("woogi", "before_total_day = " + we_before_total_day);
//        Log.d("woogi", "after_total_day = " + we_after_total_day);
//        Log.d("woogi", "day = " + we_day);




//        Calendar cal1 = new GregorianCalendar(2018, 11, 19);
//        Calendar cal2 = new GregorianCalendar(formatted_year, formatted_month, formatted_day);
//        Log.d("woogi", "day = " + cal1.get(Calendar.YEAR) + "년 " + cal1.get(Calendar.MONTH) + "월 " + cal1.get(Calendar.DATE) + "일");
//        Log.d("woogi", "day = " + cal2.get(Calendar.YEAR) + "년 " + cal2.get(Calendar.MONTH) + "월 " + cal2.get(Calendar.DATE) + "일");
//
//        Calendar cal3 = new GregorianCalendar(2019, 1, 31);
//        Log.d("woogi", "1월 31일 = " + cal3.getTimeInMillis());
//        Log.d("woogi", "2월 1일 = " + cal2.getTimeInMillis());



        /*
         * Use getTimeInMillis() method to get the Calendar's time value in
         * milliseconds. This method returns the current time as UTC
         * milliseconds from the epoch
         */
        long miliSecondForDate1 = calendar1.getTimeInMillis();

        long miliSecondForDate2 = calendar2.getTimeInMillis();
        // Calculate the difference in millisecond between two dates
        long diffInMilis = miliSecondForDate2 - miliSecondForDate1;

//        Log.d("woogi", "day = " + miliSecondForDate1);
//        Log.d("woogi", "day = " + miliSecondForDate2);
//        Log.d("woogi", "day = " + diffInMilis);
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

