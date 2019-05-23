package com.bw.movie.hotactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.view.MoveSeatView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class XuanZuoActivity extends AppCompatActivity {

    @BindView(R.id.xuan_yuan_name)
    TextView xuanYuanName;
    @BindView(R.id.xuan_yuan_address)
    TextView xuanYuanAddress;
    @BindView(R.id.xuan_ying_name)
    TextView xuanYingName;
    @BindView(R.id.begin)
    TextView begin_time;
    @BindView(R.id.end)
    TextView end_time;
    @BindView(R.id.ting)
    TextView ting_time;
    @BindView(R.id.seat_MoveSeatView)
    MoveSeatView seatMoveSeatView;
    TextView price;
    LinearLayout linearLayout;
    RelativeLayout relative_xian_cang;
    ImageView image_abandon,image_confirm,image_yincang;
    RadioButton rb1,rb2;
    TextView zhifu,text_sum_price;
    RadioGroup group;
    public float i1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xuan_zuo);
        ButterKnife.bind(this);
        rb1 = findViewById(R.id.radio_b1);//微信
        rb2 = findViewById(R.id.radio_b2);//支付宝
        zhifu = findViewById(R.id.text_sum_price);//总价
        image_yincang = findViewById(R.id.image_yincang);// ↓
        image_abandon = findViewById(R.id.image_abandon);//×
        image_confirm = findViewById(R.id.image_confirm);//√
        //选择好支付方式后的支付金额和提示支付的方式是微信还是支付宝
        text_sum_price = findViewById(R.id.text_sum_price);
        group = findViewById(R.id.radio_group);
        Intent intent = getIntent();
        final String prices = intent.getStringExtra("price");
        String begin = intent.getStringExtra("begin");
        String end = intent.getStringExtra("end");
        String ting = intent.getStringExtra("ting");
        String y_name = intent.getStringExtra("y_name");
        String m_name = intent.getStringExtra("m_name");
        String y_address = intent.getStringExtra("y_address");
        xuanYuanName.setText(y_name);
        xuanYuanAddress.setText(y_address);
        xuanYingName.setText(m_name);
        begin_time.setText(begin+" - ");
        end_time.setText(end);
        ting_time.setText(ting);
        MoveSeatView moveSeatView;
        moveSeatView = findViewById(R.id.seat_MoveSeatView);
        //有选中座位的时候，显示此布局
        linearLayout = findViewById(R.id.linear_xian);
        //点击对勾，显示此布局//点击对勾，显示此布局
        relative_xian_cang = findViewById(R.id.relative_xian_cang);

        price = findViewById(R.id.price_jia);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.radio_b1:
                        //微信
                        text_sum_price.setText("微信支付"+i1+"元");
                        text_sum_price.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(XuanZuoActivity.this, "微信支付成功",Toast.LENGTH_LONG).show();
                            }
                        });
                        break;
                    case R.id.radio_b2:
                        //支付宝
                        text_sum_price.setText("支付宝支付"+i1+"元");
                        text_sum_price.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(XuanZuoActivity.this, "支付宝支付成功",Toast.LENGTH_LONG).show();
                            }
                        });
                        break;
                }
            }
        });


        //点击对勾的监听
        image_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relative_xian_cang.setVisibility(View.VISIBLE);
            }
        });
        //点击隐藏微信和支付宝的支付界面
        image_yincang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relative_xian_cang.setVisibility(View.GONE);
            }
        });
        //点击×，finish 掉选座界面
        image_abandon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        moveSeatView.setMaxSelected(5);//设置最多选中
        moveSeatView.setSeatChecker(new MoveSeatView.SeatChecker() {



            @Override
            public boolean isValidSeat(int row, int column) {
                if (column == 2) {
                    return false;
                }
                return true;
            }

            @Override
            public boolean isSold(int row, int column) {
                if (row == 6 && column == 6) {
                    return true;
                }
                return false;
            }

            //选中座位时
            int is = 0;
            @Override
            public void checked(int row, int column) {
                is++;
                if(is >0){
                    linearLayout.setVisibility(View.VISIBLE);
                }

                double v = Double.parseDouble(prices);
                double i = v * is;
                i1 = (float) i;
                price.setText(i1+"");

            }
            //取消一个座位时
            @Override
            public void unCheck(int row, int column) {
                is--;
                double v = Double.parseDouble(prices);
                double i = v * is;
                i1 = (float) i;
                if(is == 0){
                    linearLayout.setVisibility(View.GONE);
                }
                price.setText(i1 +"");
            }

            @Override
            public String[] checkedSeatTxt(int row, int column) {
                return null;
            }

        });
//        int i = seatsTotal / 15;
//        Log.e("tag","   %     " + seatsTotal % 15);
        moveSeatView.setData(7, 15);


    }
}
