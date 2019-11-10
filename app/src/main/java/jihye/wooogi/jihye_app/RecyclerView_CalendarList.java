package jihye.wooogi.jihye_app;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class RecyclerView_CalendarList {

    private Context c_Context;
    private CalendarAdapter c_CalendarAdapter;

    private int contents_num;
    private String date;
    private String contents_1;
    private String contents_2;
    private String contents_3;
    private String contents_4;
    private String key;


    public void setConfig(RecyclerView recyclerView, Context context, List<calendar> calendars, List<String> keys){
        c_Context = context;
        c_CalendarAdapter = new CalendarAdapter(calendars, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(c_CalendarAdapter);
    }

    class CalendarItemView extends RecyclerView.ViewHolder{
        private TextView c_date_text;
        private TextView c_contents_text;
        private String key;

        public CalendarItemView(ViewGroup parent) {

            super(LayoutInflater.from(c_Context).inflate(R.layout.calendar_list_item, parent, false));

            c_date_text = (TextView)itemView.findViewById(R.id.calendar_list_date_text);
            c_contents_text = (TextView)itemView.findViewById(R.id.calendar_list_contents_text);

            itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
            });
        }

        public void bind(calendar calendar, String key) {

            c_date_text.setText(calendar.getDate());

            Log.d("woogi", "calendar_current_number = " + FirebaseDatabaseHelper_Calendar.calendar_current_number);
            if(FirebaseDatabaseHelper_Calendar.calendar_current_number == 0 ) {
                c_contents_text.setText(calendar.getContents_1());
            }
            else if (FirebaseDatabaseHelper_Calendar.calendar_current_number == 1 ) {
                c_contents_text.setText(calendar.getContents_2());
            }

            FirebaseDatabaseHelper_Calendar.calendar_current_number += 1;

        }
    }

    class CalendarAdapter extends RecyclerView.Adapter<CalendarItemView>{
        private List<calendar> c_CalendarList;
        private List<String> c_Keys;

        public CalendarAdapter(List<calendar> c_CalendarList, List<String> c_Keys) {
            this.c_CalendarList = c_CalendarList;
            this.c_Keys = c_Keys;
        }

        @Override
        public CalendarItemView onCreateViewHolder(ViewGroup parent, int viewType) {
            return new CalendarItemView(parent);
        }

        @Override
        public void onBindViewHolder(CalendarItemView holder, int position) {
            holder.bind(c_CalendarList.get(position), c_Keys.get(position));

        }

        @Override
        public int getItemCount() {
            return c_CalendarList.size();
        }
    }

}
