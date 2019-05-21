package com.bw.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.bw.movie.R;
import com.bw.movie.adapter.MyQianAdapter;
import com.bw.movie.bean.CheckBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者:今夕何夕
 * 时间:${data}
 * Description:这个是注释
 */
public class MyQianActivity extends AppCompatActivity {
    @BindView(R.id.btn_tuichuqiandao)
    Button btnTuichuqiandao;
    private List<CheckBean> checkBeanList;
    private MyQianAdapter mAdapter;
    private GridView mGridview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qiandao1);
        ButterKnife.bind(this);
        initDate();
        btnTuichuqiandao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyQianActivity.this,ShowActivity.class);
                intent.putExtra("id",2);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initDate() {

        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        int day = calendar.getActualMaximum(Calendar.DATE); // 获取当前月的天数

        checkBeanList = new ArrayList<CheckBean>();
        for (int i = 0; i < day + 1; i++) {
            CheckBean checkBean = new CheckBean();
            if ((int) (Math.random() * 20 % 4) == 3) {
                checkBean.day = i;
                checkBean.check_status = CheckBean.CHECKED;
            } else if ((int) (Math.random() * 20 % 4) == 2) {
                checkBean.day = i;
                checkBean.check_status = CheckBean.CHECK_NO;
            } else {
                checkBean.day = i;
                checkBean.check_status = CheckBean.CHECK_WAIT;
            }

            checkBeanList.add(checkBean);
        }

        mAdapter = new MyQianAdapter(MyQianActivity.this);
        mAdapter.setListDate(checkBeanList);

        mGridview = (GridView) findViewById(R.id.main_gridview);
        mGridview.setAdapter(mAdapter);
    }
}
