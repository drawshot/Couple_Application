package jihye.wooogi.jihye_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CalendarContents_List extends AppCompatActivity {

    TextView calendar_text;
    String dateClicked;
    String c_year;
    String c_month;
    String c_day;
    String c_dayofweek;
    public static String total_day;
    private RecyclerView c_RecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_contents__list);


        Intent intent = getIntent();
        dateClicked = intent.getStringExtra("dateClicked");

        String[] date_separated = dateClicked.split(" ");

        c_year = date_separated[5];
        c_month = date_separated[1];
        c_day = date_separated[2];
        c_dayofweek = date_separated[0];

//        Log.d("woogi", ""+c_year);
//        Log.d("woogi", ""+c_month);
//        Log.d("woogi", ""+c_day);

        c_month = month_convert(c_month);

//        Log.d("woogi", ""+c_month);

        total_day = c_year + "-" + c_month + "-" + c_day;

//        final List<String> c_key = new ArrayList<>();
//
//        c_key.add(total_day);


//        Log.d("woogi", ""+total_day);



        c_RecyclerView = (RecyclerView)findViewById(R.id.calendar_list_recycler_view);

        new FirebaseDatabaseHelper_Calendar().readData(new FirebaseDatabaseHelper_Calendar.DataStatus() {
            @Override
            public void DataIsLoaded(List<calendar> calendars, List<String> keys) {
                new RecyclerView_CalendarList().setConfig(c_RecyclerView, CalendarContents_List.this, calendars, keys);
            }

            @Override
            public void DataIsInstered() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });

    }

    public String month_convert(String month) {

        String month_convert = "";

        if(month.equals("Jan")) {
            month_convert = "01";
        }

        else if(month.equals("Feb")) {
            month_convert = "02";
        }

        else if(month.equals("Mar")) {
            month_convert = "03";
        }

        else if(month.equals("Apr")) {
            month_convert = "04";
        }

        else if(month.equals("May")) {
            month_convert = "05";
        }

        else if(month.equals("Jun")) {
            month_convert = "06";
        }

        else if(month.equals("Jul")) {
            month_convert = "07";
        }

        else if(month.equals("Aug")) {
            month_convert = "08";
        }

        else if(month.equals("Sep")) {
            month_convert = "09";
        }

        else if(month.equals("Oct")) {
            month_convert = "10";
        }

        else if(month.equals("Nov")) {
            month_convert = "11";
        }

        else if(month.equals("Dec")) {
            month_convert = "12";
        }

        return month_convert;
    }
}
