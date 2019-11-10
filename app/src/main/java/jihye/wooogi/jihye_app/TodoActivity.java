package jihye.wooogi.jihye_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class TodoActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private EditText todo_edit_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        mRecyclerView = (RecyclerView)findViewById(R.id.recycleview_todo);



        new FirebaseDatabaseHelper().readData(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<coupon> coupons, List<String> keys) {
                findViewById(R.id.loading_coupons_pb).setVisibility(View.GONE);

                new RecyclerView_Config().setConfig(mRecyclerView, TodoActivity.this, coupons, keys);
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
        todo_edit_text = (EditText)findViewById(R.id.todo_edit_text);
        Button todo_plus_btn = (Button)findViewById(R.id.todo_plus_btn);

        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(TodoActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        todo_plus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                coupon coupon = new coupon();
                coupon.setFood_name(todo_edit_text.getText().toString());
                coupon.setIs_click("T");


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

                todo_edit_text.setText("");
            }
        });






    }
}
