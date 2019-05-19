package com.bw.movie.wdyy.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.movie.wdyy.R;
import com.bw.movie.wdyy.contract.ContractInterface;
import com.bw.movie.wdyy.presenter.MyPresenter;

public class YiJianActivity extends AppCompatActivity implements View.OnClickListener ,ContractInterface.VYiJian {
    Button myyijian_tijiao;
    ImageView myyijian_fanhui;
    ContractInterface.PLogin pYiJian;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yi_jian);
        //初始化数据
        Viewinit();
        pYiJian=new MyPresenter(this);
        //设置返回的点击事件
        myyijian_tijiao.setOnClickListener(this);
        myyijian_fanhui.setOnClickListener(this);
    }

    private void Viewinit() {
        myyijian_fanhui=findViewById(R.id.myyijian_fanhui);
        myyijian_tijiao=findViewById(R.id.myyijian_tijiao);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.myyijian_fanhui:
                Intent intent=new Intent(YiJianActivity.this,ShowActivity.class);
                intent.putExtra("id",2);
                startActivity(intent);
                finish();
                break;
            case R.id.myyijian_tijiao:
                pYiJian.PYijian();
                break;
        }
    }

    @Override
    public void VYijian(String str) {
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
        if (str.equals("反馈成功")){
            Intent intent=new Intent(YiJianActivity.this,YijianJieguoActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void VBanben(String str) {

    }
}
