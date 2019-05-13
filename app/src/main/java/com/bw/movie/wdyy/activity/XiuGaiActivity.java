package com.bw.movie.wdyy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.baway.rikao0411.greendao.gen.ZhuceBeanDao;
import com.bw.movie.wdyy.R;
import com.bw.movie.wdyy.bean.ZhuceBean;
import com.bw.movie.wdyy.view.App;

import butterknife.BindView;
import butterknife.ButterKnife;

public class XiuGaiActivity extends AppCompatActivity {


    @BindView(R.id.edit_nicheng)
    EditText editNicheng;
    @BindView(R.id.edit_sex)
    EditText editSex;
    @BindView(R.id.edit_riqik)
    EditText editRiqik;
    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.edit_youxiang)
    EditText editYouxiang;
    @BindView(R.id.btn_xiugai)
    Button btnXiugai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiu_gai);
        ButterKnife.bind(this);
        //设置确认修改后的点击事件
        btnXiugai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入框的内容
                String nicheng = editNicheng.getText().toString();
                String phone = editPhone.getText().toString();
                String riqi = editRiqik.getText().toString();
                String sex = editSex.getText().toString();
                String youxiang = editYouxiang.getText().toString();
                //将输入框的内容添加至bean中
                ZhuceBean zhuceBean=new ZhuceBean();
                zhuceBean.setNickName(nicheng);
                zhuceBean.setSex(Integer.parseInt(sex));
                zhuceBean.setPhone(phone);
                zhuceBean.setBirthday(Long.parseLong(riqi));
                zhuceBean.setLastLoginTime(Long.parseLong(youxiang));
                //添加数据库
                ZhuceBeanDao daoSession = App.daoSession.getZhuceBeanDao();
                daoSession.insert(zhuceBean);
                Intent intent=new Intent(XiuGaiActivity.this,XinXiActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
