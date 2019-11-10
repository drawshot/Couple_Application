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

import java.util.List;

public class RecyclerView_Config {
    private Context mContext;
    private CouponAdapter mCouponAdapter;

    private String is_coupon_txt;
    private String s_date_txt;
    private String e_date_txt;
    private String is_use_txt;
    private String s_text;
    private String e_text;
    private String layout_name;
    private String end_content;
    private String key;

    private boolean is_button = true;

    public void setConfig(RecyclerView recyclerView, Context context, List<coupon> coupons, List<String> keys){
        mContext = context;
        mCouponAdapter = new CouponAdapter(coupons, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mCouponAdapter);
    }

    class CouponItemView extends RecyclerView.ViewHolder{
        private TextView mCoupon;
        private TextView mIs_use;
        private TextView mS_Date;
        private TextView mE_Date;
        private TextView mContent;
        private Button use_btn;
        private TextView use_text;
        private ConstraintLayout coupon_layout;

        private TextView coupon_txtView;
        private TextView content_txt;
        private TextView todo_food_text;

        private Button todo_trash_btn;
        private ImageView todo_spoon_img;
        private TextView todo_is_click_text;
        private ImageView todo_is_click_line;

        private String key;

        public CouponItemView(ViewGroup parent) {

            super(LayoutInflater.from(mContext).inflate(R.layout.coupon_list_item, parent, false));

            mCoupon = (TextView)itemView.findViewById(R.id.coupon_txtView);
            mS_Date = (TextView)itemView.findViewById(R.id.date_start_txtView);
            mE_Date = (TextView)itemView.findViewById(R.id.date_end_textView);
            mContent = (TextView)itemView.findViewById(R.id.content_txt);
            use_text = (TextView)itemView.findViewById(R.id.use_text);
            coupon_layout = (ConstraintLayout) itemView.findViewById(R.id.coupon_list);
            mIs_use = (TextView)itemView.findViewById(R.id.coupon_is_use);

            coupon_txtView = (TextView)itemView.findViewById(R.id.coupon_txtView);
            content_txt = (TextView)itemView.findViewById(R.id.content_txt);

            todo_trash_btn = (Button)itemView.findViewById(R.id.todo_trash_btn);
            todo_spoon_img = (ImageView)itemView.findViewById(R.id.todo_spoon_img);
            todo_food_text = (TextView)itemView.findViewById(R.id.todo_food_text);
            todo_is_click_text = (TextView)itemView.findViewById(R.id.todo_is_click_text);
            todo_is_click_line = (ImageView)itemView.findViewById(R.id.todo_click_img);

            if(FirebaseDatabaseHelper.data_name == 0 || FirebaseDatabaseHelper.data_name == 1) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                    Toast.makeText(mContext, "is_use = " + mIs_use, Toast.LENGTH_SHORT).show();

//                    Toast.makeText(mContext, "hello", Toast.LENGTH_SHORT).show();
                        if(mIs_use.getText().toString().equals("T")){
                            Intent intent = new Intent(mContext, CouponUse.class);
                            intent.putExtra("key", key);
                            intent.putExtra("is_coupon_txt", mCoupon.getText().toString());
                            intent.putExtra("s_date_txt", mS_Date.getText().toString());
                            intent.putExtra("e_date_txt", mE_Date.getText().toString());
                            intent.putExtra("is_use_txt", mIs_use.getText().toString());
                            intent.putExtra("s_text", mContent.getText().toString());

                            mContext.startActivity(intent);
                        }
                        else {

                        }

//                    Intent intent = new Intent(mContext, CouponUse.class);
//                    intent.putExtra("key", key);
//                    intent.putExtra("is_coupon_txt", mCoupon.getText().toString());
//                    intent.putExtra("s_date_txt", mS_Date.getText().toString());
//                    intent.putExtra("e_date_txt", mE_Date.getText().toString());
//                    intent.putExtra("is_use_txt", mIs_use.getText().toString());
//                    intent.putExtra("s_text", mContent.getText().toString());
//
//                    mContext.startActivity(intent);

                    }
                });
            }
            else if(FirebaseDatabaseHelper.data_name == 2) {

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        coupon coupon = new coupon();
                        coupon.setFood_name(todo_food_text.getText().toString());

                        if(todo_is_click_text.getText().toString().equals("T")) {
//                            Toast.makeText(mContext, "False", Toast.LENGTH_SHORT).show();
//                            Toast.makeText(mContext, "True", Toast.LENGTH_SHORT).show();
                            todo_is_click_line.setVisibility(View.VISIBLE);
                            coupon.setIs_click("F");
                            todo_is_click_text.setText("F");
                        }
                        else if (todo_is_click_text.getText().toString().equals("F")){
//                            Toast.makeText(mContext, "False", Toast.LENGTH_SHORT).show();
                            todo_is_click_line.setVisibility(View.INVISIBLE);
                            coupon.setIs_click("T");
                            todo_is_click_text.setText("T");
                        }
                        else {
                            coupon.setIs_click("Error");
                        }


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

                    }
                });


            }

            todo_trash_btn.setOnClickListener(new View.OnClickListener() {
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
                }
            });



//            mIs_use = (TextView)itemView.findViewById(R.id.is_use_txtView);

        }

        public void bind(coupon coupon, String key) {



            if(FirebaseDatabaseHelper.data_name == 0 || FirebaseDatabaseHelper.data_name == 1) {
                todo_spoon_img.setVisibility(View.INVISIBLE);
                todo_trash_btn.setVisibility(View.INVISIBLE);
                todo_food_text.setVisibility(View.INVISIBLE);
                mCoupon.setVisibility(View.VISIBLE);
                mS_Date.setVisibility(View.VISIBLE);
                mE_Date.setVisibility(View.VISIBLE);
                mContent.setVisibility(View.VISIBLE);


                if(coupon.getCoupon().equals("T")){
                    mCoupon.setText("소원권");
//                Log.d("is_coupon = ", "True");
                }

                mIs_use.setText(coupon.getIs_use());

                if(coupon.getIs_use().equals("T")){
                    mContent.setText(coupon.getS_Text());
                    is_button = true;
                }
                else {
                    mContent.setText(coupon.getE_Text());
                    is_button = false;
                }

//            mCoupon.setText(coupon.getCoupon());

                mS_Date.setText(coupon.getS_Date());
                mE_Date.setText(coupon.getE_Date());
//            mIs_use.setText(coupon.getIs_use());
                this.key = key;


                if(is_button) {
                    coupon_layout.getBackground().setAlpha(255);
//                use_btn.setVisibility(View.VISIBLE);
//                use_text.setVisibility(View.GONE);
                }
                else {
                    coupon_layout.getBackground().setAlpha(80);
                    coupon_txtView.setText("사용완료");
//                use_btn.setVisibility(View.GONE);
//                use_text.setVisibility(View.VISIBLE);
                }
            }

            else if(FirebaseDatabaseHelper.data_name == 2) {
                todo_food_text.setText(coupon.getFood_name());

                todo_is_click_text.setText(coupon.getIs_click());

                if(todo_is_click_text.getText().toString().equals("T")) {
                    todo_is_click_line.setVisibility(View.INVISIBLE);
                }
                else {
                    todo_is_click_line.setVisibility(View.VISIBLE);
                }
//                Toast.makeText(mContext, "d"+coupon.getIs_click(), Toast.LENGTH_SHORT).show();


                coupon_layout.setMaxHeight(200);
                this.key = key;
            }


        }
    }

    class CouponAdapter extends RecyclerView.Adapter<CouponItemView>{
        private List<coupon> mCouponList;
        private List<String> mKeys;

        public CouponAdapter(List<coupon> mCouponList, List<String> mKeys) {
            this.mCouponList = mCouponList;
            this.mKeys = mKeys;
        }

        @Override
        public CouponItemView onCreateViewHolder(ViewGroup parent, int viewType) {
            return new CouponItemView(parent);
        }

        @Override
        public void onBindViewHolder(CouponItemView holder, int position) {
            holder.bind(mCouponList.get(position), mKeys.get(position));

        }

        @Override
        public int getItemCount() {
            return mCouponList.size();
        }
    }

}
