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

public class FirebaseDatabaseHelper {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceCoupons;
    private List<coupon> coupons = new ArrayList<>();
    public static int data_name = 0;
    public static boolean is_woogi = true;
    private String database_name;

    public interface DataStatus  {
        void DataIsLoaded(List<coupon> coupons, List<String> keys);
        void DataIsInstered();
        void DataIsUpdated();
        void DataIsDeleted();

    }

    public FirebaseDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();

        if(data_name == 0) {
            database_name = "woogi";
        }
        else if(data_name == 1) {
            database_name = "jihye";
        }
        else if(data_name == 2) {
            database_name = "food";
        }
        else {

        }

//        if(is_woogi) {
//            database_name = "woogi";
//        }
//        else {
//            database_name = "jihye";
//        }
        mReferenceCoupons = mDatabase.getReference(database_name);
    }

    public void readData(final DataStatus dataStatus){
         mReferenceCoupons.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 coupons.clear();
                 List<String> keys = new ArrayList<>();

                 if(data_name == 0 || data_name == 1) {
                     for(DataSnapshot keyNode : dataSnapshot.getChildren()){

                         coupon coupon = keyNode.getValue(coupon.class);

                         if(coupon.getIs_use().equals("T")) {
                             keys.add(keyNode.getKey());
                             coupons.add(coupon);
                         }

                     }

                     for(DataSnapshot keyNode : dataSnapshot.getChildren()){

                         coupon coupon = keyNode.getValue(coupon.class);
                         if(coupon.getIs_use().equals("F")) {
                             keys.add(keyNode.getKey());
                             coupons.add(coupon);
                         }

                     }
                 }

                 else if(data_name == 2) {

                     for(DataSnapshot keyNode : dataSnapshot.getChildren()) {
                         coupon coupon = keyNode.getValue(coupon.class);
                         keys.add(keyNode.getKey());
                         coupons.add(coupon);
                     }

//                     for(DataSnapshot keyNode : dataSnapshot.getChildren()) {
//
//                         coupon coupon = keyNode.getValue(coupon.class);
//                         if(coupon.getIs_click().equals("T")) {
//                             keys.add(keyNode.getKey());
//                             coupons.add(coupon);
//                         }
//                     }
//
//                     for(DataSnapshot keyNode : dataSnapshot.getChildren()) {
//
//                         coupon coupon = keyNode.getValue(coupon.class);
//                         if(coupon.getIs_click().equals("F")) {
//                             keys.add(keyNode.getKey());
//                             coupons.add(coupon);
//                         }
//                     }
                 }

                 dataStatus.DataIsLoaded(coupons, keys);

             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });
    }

    public void addData(coupon coupon, final DataStatus dataStatus){
        String key = mReferenceCoupons.push().getKey();
        mReferenceCoupons.child(key).setValue(coupon).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsInstered();
            }
        });
    }

    public void updateData(String key, coupon coupon, final DataStatus dataStatus) {
        mReferenceCoupons.child(key).setValue(coupon).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsUpdated();
            }
        });
    }

    public void deleteData(String key, final DataStatus dataStatus) {
        mReferenceCoupons.child(key).setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsDeleted();
            }
        });
    }
}
