package jihye.wooogi.jihye_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class CouponUse extends AppCompatActivity {

    private EditText wish_content;
    private Button coupon_use_btn;
    private Button coupon_delete_btn;

    private String key;
    private String is_coupon_txt;
    private String s_date_txt;
    private String e_date_txt;
    private String is_use_txt;
    private String s_text;
    private String e_text;
    private Button home_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_use);

        key = getIntent().getStringExtra("key");
        is_coupon_txt = getIntent().getStringExtra("is_coupon_txt");
        s_date_txt = getIntent().getStringExtra("s_date_txt");
        e_date_txt = getIntent().getStringExtra("e_date_txt");
        is_use_txt = getIntent().getStringExtra("is_use_txt");
        s_text = getIntent().getStringExtra("s_text");

//        Toast.makeText(this, "key = " + key, Toast.LENGTH_SHORT).show();

        wish_content = (EditText)findViewById(R.id.wish_content);
        coupon_use_btn = (Button)findViewById(R.id.coupon_use_btn);
        coupon_delete_btn = (Button)findViewById(R.id.coupon_delete_btn);

        home_btn = (Button)findViewById(R.id.home_btn);

        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(CouponUse.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        coupon_use_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                coupon coupon = new coupon();
                coupon.setCoupon(is_coupon_txt);
                coupon.setS_Date(s_date_txt);
                coupon.setE_Date(e_date_txt);
                coupon.setIs_use("F");
                coupon.setS_Text(s_text);
                coupon.setE_Text(wish_content.getText().toString());

                new FirebaseDatabaseHelper().updateData(key, coupon, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<jihye.wooogi.jihye_app.coupon> coupons, List<String> keys) {

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

                finish();
            }
        });

        coupon_delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FirebaseDatabaseHelper().deleteData(key, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<coupon> coupons, List<String> keys) {

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

                finish();
            }
        });

    }
}
