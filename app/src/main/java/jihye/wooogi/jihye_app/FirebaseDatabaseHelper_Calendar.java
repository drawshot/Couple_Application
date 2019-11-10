package jihye.wooogi.jihye_app;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper_Calendar {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceCalendars;
    private List<calendar> calendars = new ArrayList<>();
//    public static int data_name = 0;
    public static boolean is_woogi = true;
    private String database_name_calendar;
    public static int calendar_current_number = 0;
    public static List<String> calendar_point_key = new ArrayList<>();

    public interface DataStatus  {
        void DataIsLoaded(List<calendar> calendars, List<String> keys);
        void DataIsInstered();
        void DataIsUpdated();
        void DataIsDeleted();

    }

    public FirebaseDatabaseHelper_Calendar() {
        mDatabase = FirebaseDatabase.getInstance();

        database_name_calendar = "calendar";

//        if(data_name == 0) {
//            database_name = "woogi";
//        }
//        else if(data_name == 1) {
//            database_name = "jihye";
//        }
//        else if(data_name == 2) {
//            database_name = "food";
//        }
//        else if(data_name == 3) {
//            database_name = "calendar";
//        }
//        else {
//
//        }

//        if(is_woogi) {
//            database_name = "woogi";
//        }
//        else {
//            database_name = "jihye";
//        }
        mReferenceCalendars = mDatabase.getReference(database_name_calendar);
    }

    public void readData(final FirebaseDatabaseHelper_Calendar.DataStatus dataStatus){
        mReferenceCalendars.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                calendars.clear();
                List<String> keys = new ArrayList<>();


                calendar_point_key.clear();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()) {
                    calendar calendar = keyNode.getValue(calendar.class);
//                    keys.add(keyNode.getKey());
//                    calendars.add(calendar);



                    calendar_current_number = 0;

                    calendar_point_key.add(keyNode.getKey());

                    if(keyNode.getKey().equals(CalendarContents_List.total_day)) {
                        for(int i = 0 ; i < calendar.getContents_num() ; i++) {
                            keys.add(keyNode.getKey());
                            calendars.add(calendar);
                        }
                    }



                }

                dataStatus.DataIsLoaded(calendars, keys);

//                if(data_name == 0 || data_name == 1) {
//                    for(DataSnapshot keyNode : dataSnapshot.getChildren()){
//
//                        coupon coupon = keyNode.getValue(coupon.class);
//
//                        if(coupon.getIs_use().equals("T")) {
//                            keys.add(keyNode.getKey());
//                            coupons.add(coupon);
//                        }
//
//                    }
//
//                    for(DataSnapshot keyNode : dataSnapshot.getChildren()){
//
//                        coupon coupon = keyNode.getValue(coupon.class);
//                        if(coupon.getIs_use().equals("F")) {
//                            keys.add(keyNode.getKey());
//                            coupons.add(coupon);
//                        }
//
//                    }
//                }
//
//                else if(data_name == 2) {
//                    for(DataSnapshot keyNode : dataSnapshot.getChildren()) {
//
//                        coupon coupon = keyNode.getValue(coupon.class);
//                        if(coupon.getIs_click().equals("T")) {
//                            keys.add(keyNode.getKey());
//                            coupons.add(coupon);
//                        }
//                    }
//
//                    for(DataSnapshot keyNode : dataSnapshot.getChildren()) {
//
//                        coupon coupon = keyNode.getValue(coupon.class);
//                        if(coupon.getIs_click().equals("F")) {
//                            keys.add(keyNode.getKey());
//                            coupons.add(coupon);
//                        }
//                    }
//                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addData(calendar calendar, final FirebaseDatabaseHelper_Calendar.DataStatus dataStatus){
        String key = mReferenceCalendars.push().getKey();
        mReferenceCalendars.child(key).setValue(calendar).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsInstered();
            }
        });
    }

    public void updateData(String key, calendar calendar, final FirebaseDatabaseHelper_Calendar.DataStatus dataStatus) {
        mReferenceCalendars.child(key).setValue(calendar).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsUpdated();
            }
        });
    }

    public void deleteData(String key, final FirebaseDatabaseHelper_Calendar.DataStatus dataStatus) {
        mReferenceCalendars.child(key).setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsDeleted();
            }
        });
    }
}
