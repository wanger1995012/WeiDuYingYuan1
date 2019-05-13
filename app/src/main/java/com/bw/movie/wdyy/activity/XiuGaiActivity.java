package com.bw.movie.wdyy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.baway.rikao0411.greendao.gen.ZhuceBeanDao;
import com.bw.movie.wdyy.R;
import com.bw.movie.wdyy.bean.LoginBean;
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
    int a=0;
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
                long riqi = editRiqik.getText().length();
                String sex = editSex.getText().toString();
                long youxiang = editYouxiang.getText().length();
                //将输入框的内容添加至bean中
                LoginBean.ResultBean.UserInfoBean userInfoBean=new LoginBean.ResultBean.UserInfoBean();
                userInfoBean.setNickName(nicheng);
                if (sex.equals("男")){
                    a=1;
                }else {
                    a=2;
                }
                userInfoBean.setSex(a);
                userInfoBean.setPhone(phone);
                userInfoBean.setBirthday(riqi);
                userInfoBean.setLastLoginTime(youxiang);
                Intent intent=getIntent();
                intent.putExtra("mmm",1);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
