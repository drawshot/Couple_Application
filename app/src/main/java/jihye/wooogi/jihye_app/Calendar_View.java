package jihye.wooogi.jihye_app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
//import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Calendar_View extends AppCompatActivity {

    CompactCalendarView compactCalendar;
    CalendarView calendar_view;
    private long one_nine_day = 1547020800000L;

    int[] day_split = new int[3];


    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar__view);

//        calendar_view = (CalendarView)findViewById(R.id.calendar_view);
//
//        calendar_view.addView();
        final ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(false);
//        actionBar.setTitle(null);

        compactCalendar = (CompactCalendarView) findViewById(R.id.calendar_view);
//        compactCalendar.setUseThreeLetterAbbreviation(true);
        compactCalendar.setShouldShowMondayAsFirstDay(false);

        Event ev1 = new Event(Color.RED, 1548316800000L, "Teachers' Professional Day");
        compactCalendar.addEvent(ev1);

        Event ev2 = new Event(Color.RED, 1547020800000L, "Teachers' Professional Day");
        compactCalendar.addEvent(ev2);

//        Event ev2 = new Event(Color.RED, 1546933397L);

        new FirebaseDatabaseHelper_Calendar().readData(new FirebaseDatabaseHelper_Calendar.DataStatus() {
            @Override
            public void DataIsLoaded(List<calendar> calendars, List<String> keys) {
//                for(keys.size())



                for(int i = 0 ; i < FirebaseDatabaseHelper_Calendar.calendar_point_key.size() ; i++) {

                    String[] day_split_string = FirebaseDatabaseHelper_Calendar.calendar_point_key.get(i).split("-");
//                    Log.d("woogi", "??" + day_split_string[0]);
//                    Log.d("woogi", "??" + day_split_string[1]);
//                    Log.d("woogi", "??" + day_split_string[2]);


                    for(int a = 0 ; a < 3 ; a++) {
                        day_split[a] = Integer.parseInt(day_split_string[a]);
                        Log.d("woogi", "split" + day_split[a]);
                    }




                }
                Log.d("woogi", "keys size = " + FirebaseDatabaseHelper_Calendar.calendar_point_key.get(0));
//                new RecyclerView_CalendarList().setConfig(c_RecyclerView, CalendarContents_List.this, calendars, keys);
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

        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Context context = getApplicationContext();

                Log.d("woogi", ""+dateClicked);

                Intent intent = new Intent(Calendar_View.this, CalendarContents_List.class);
                String date_Clicked = String.valueOf(dateClicked);
                intent.putExtra("dateClicked", date_Clicked);
                startActivity(intent);



//                if(dateClicked.toString().compareTo("Teachers' Professional Day") == 0) {
//                    Toast.makeText(context, "Teachers' Professional Day" , Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(context, "No Events Plaaned for that day" , Toast.LENGTH_SHORT).show();
//                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
//                actionBar.setTitle(dateFormatMonth.format(firstDayOfNewMonth));

            }
        });
    }
}
