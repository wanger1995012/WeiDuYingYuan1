package com.bw.movie.wdyy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bw.movie.wdyy.R;
import com.bw.movie.wdyy.contract.ContractInterface;
import com.bw.movie.wdyy.presenter.MyPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class XiePinglunActivity extends AppCompatActivity implements ContractInterface.VYYXPL {

    @BindView(R.id.xiepinglun_edit)
    EditText xiepinglunEdit;
    @BindView(R.id.xiepinglu_btn)
    Button xiepingluBtn;
    ContractInterface.PYYXPL pyyxpl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xie_pinglun);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        pyyxpl=new MyPresenter(this);
        final String cinemaId = intent.getStringExtra("cinemaId");
        //点击提交
        xiepingluBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入框的内容
                String edit = xiepinglunEdit.getText().toString();
                pyyxpl.PYYXiepinglun(cinemaId,edit);
            }
        });
    }

    @Override
    public void VYYXiepinglun(String str) {
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
        if (str.equals("评论成功")){
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pyyxpl.onDestory();
        pyyxpl=null;
    }
}
