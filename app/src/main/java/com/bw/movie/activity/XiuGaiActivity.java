package com.bw.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.bw.movie.R;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.contract.ContractInterface;
import com.bw.movie.presenter.MyPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class XiuGaiActivity extends AppCompatActivity implements ContractInterface.VWDxiugai {


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
    ContractInterface.PWDxiugai pwDxiugai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiu_gai);
        ButterKnife.bind(this);
        pwDxiugai=new MyPresenter(this);
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
                intent.putExtra("mmm","12");
                intent.putExtra("NickName",userInfoBean.getNickName());
                intent.putExtra("Sex",userInfoBean.getSex());
                intent.putExtra("Phone",userInfoBean.getPhone());
                intent.putExtra("Birthday",userInfoBean.getBirthday());
                intent.putExtra("LastLoginTime",userInfoBean.getLastLoginTime());
                setResult(RESULT_OK,intent);
                finish();
                //去P
                pwDxiugai.PWDxiugai(nicheng,sex,youxiang+"");
            }
        });
    }

    @Override
    public void VWDxiugai(String str) {
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
        if (str.equals("修改成功")){
            finish();
        }
    }
}
