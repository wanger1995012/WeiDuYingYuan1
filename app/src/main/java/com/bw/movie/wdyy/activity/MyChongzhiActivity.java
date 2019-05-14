package com.bw.movie.wdyy.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.wdyy.R;
import com.bw.movie.wdyy.contract.ContractInterface;
import com.bw.movie.wdyy.presenter.MyPresenter;
import com.bw.movie.wdyy.utile.EncryptUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyChongzhiActivity extends AppCompatActivity implements ContractInterface.VXiugaimima {

    @BindView(R.id.chongzhi_mima)
    TextView chongzhiMima;
    @BindView(R.id.chongzhi_xinmima1)
    EditText chongzhiXinmima1;
    @BindView(R.id.chongzhi_xinmima2)
    EditText chongzhiXinmima2;
    @BindView(R.id.chongzhi_queren)
    Button chongzhiQueren;
    @BindView(R.id.chongzhi_fanhui)
    ImageView chongzhiFanhui;
    private SharedPreferences sp;
    ContractInterface.PXiugaimima pXiugaimima;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_chongzhi);
        ButterKnife.bind(this);
        //设置返回
        ChongzhiFanhuiInit();
        //设置旧密码与修改密码
        ChongzhimimaInit();
    }
    //重置密码
    private void ChongzhimimaInit() {
        //设置旧密码
        sp = getSharedPreferences("ssp", MODE_PRIVATE);
        String phone = sp.getString("phone", "");
        final String pwd = sp.getString("pwd", "");
        Log.e("ccc", "账号: "+phone+"密码"+pwd );
        chongzhiMima.setText(pwd+"");
        //重置新密码


        //P
        pXiugaimima=new MyPresenter(this);
        chongzhiQueren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入框的密码
                final String pwd1 = chongzhiXinmima1.getText().toString();
                final String pwd2 = chongzhiXinmima2.getText().toString();
                //将输入框的密码加密
                final String encrypt1= EncryptUtil.encrypt(pwd1);
                final String encrypt2= EncryptUtil.encrypt(pwd2);
                //将旧密码加密
                final String jiumima = EncryptUtil.encrypt(pwd);
                Log.e("tag","新密码"+pwd1+"-"+encrypt1+"----"+pwd2+"-"+encrypt2+"----旧密码"+pwd+"-"+jiumima);

                pXiugaimima.Pxiugai(jiumima,encrypt1,encrypt2);
            }
        });
    }

    private void ChongzhiFanhuiInit() {
        chongzhiFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void Vxiugai(String str) {
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
        if (str.equals("密码修改成功")){
            Intent intent=new Intent(MyChongzhiActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pXiugaimima.onDestory();
        pXiugaimima=null;
    }
}
