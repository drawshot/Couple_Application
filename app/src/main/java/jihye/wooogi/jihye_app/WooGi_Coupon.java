package jihye.wooogi.jihye_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class WooGi_Coupon extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_woo_gi__coupon);

        mRecyclerView = (RecyclerView)findViewById(R.id.recycleview_woogi);



        new FirebaseDatabaseHelper().readData(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<coupon> coupons, List<String> keys) {
                findViewById(R.id.loading_coupons_pb).setVisibility(View.GONE);

                new RecyclerView_Config().setConfig(mRecyclerView, WooGi_Coupon.this, coupons, keys);
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

        Button home_btn = (Button)findViewById(R.id.home_btn);
        Button coupon_add_btn = (Button)findViewById(R.id.coupon_plus_btn);

        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(WooGi_Coupon.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        coupon_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(WooGi_Coupon.this,CouponAdd.class);
                startActivity(intent);
            }
        });


    }
}
