package jihye.wooogi.jihye_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class CouponAdd extends AppCompatActivity {

    private EditText start_year;
    private EditText start_month;
    private EditText start_day;
    private EditText end_year;
    private EditText end_month;
    private EditText end_day;
    private EditText start_text;

    private String final_start_date;
    private String final_end_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_add);


        Button home_btn = (Button)findViewById(R.id.home_btn);
        Button coupon_add_btn = (Button)findViewById(R.id.coupon_add_btn);
        start_year = (EditText)findViewById(R.id.start_year);
        start_month = (EditText)findViewById(R.id.start_month);
        start_day = (EditText)findViewById(R.id.start_day);
        end_year = (EditText)findViewById(R.id.end_year);
        end_month = (EditText)findViewById(R.id.end_month);
        end_day = (EditText)findViewById(R.id.end_day);
        start_text = (EditText)findViewById(R.id.start_text);

        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(CouponAdd.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        coupon_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coupon coupon = new coupon();
                coupon.setCoupon("T");

                final_start_date = start_year.getText().toString() + "-" + start_month.getText().toString() + "-" + start_day.getText().toString();
                final_end_date = end_year.getText().toString() + "-" + end_month.getText().toString() + "-" + end_day.getText().toString();

                coupon.setS_Date(final_start_date);
                coupon.setE_Date(final_end_date);
                coupon.setS_Text(start_text.getText().toString());
                coupon.setIs_use("T");
                coupon.setE_Text(" ");

                new FirebaseDatabaseHelper().addData(coupon, new FirebaseDatabaseHelper.DataStatus() {
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
    }
}
