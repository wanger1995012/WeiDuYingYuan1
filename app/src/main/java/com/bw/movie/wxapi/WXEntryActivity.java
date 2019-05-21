package com.bw.movie.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.bw.movie.activity.LoginActivity;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.contract.ContractInterface;
import com.bw.movie.presenter.MyPresenter;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * 作者:今夕何夕
 * 时间:${data}
 * Description:这个是注释
 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler,ContractInterface.VWXDL {

    // APP_ID 替换为你的应用从官方网站申请到的合法appID
    private static final String APP_ID = "wxb3852e6a6b7d9516";
    // IWXAPI 是第三方app和微信通信的openApi接口
    private IWXAPI api;
    ContractInterface.PWXDL pwxdl;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pwxdl=new MyPresenter(this);
        regToWx();
    }

    private void regToWx() {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, APP_ID,false);
        // 将应用的appId注册到微信
        api.registerApp(APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        api.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp.getType() == ConstantsAPI.COMMAND_SENDAUTH) {
            //Log.e("tag","微信登录");
            SendAuth.Resp authResp = (SendAuth.Resp) baseResp;
            String code = authResp.code;
            //Log.e("tag",code);
            pwxdl.PWXDL(code);
        }
    }

    @Override
    public void VWXDL(Object str) {
        LoginBean loginBean = (LoginBean)str;
        if(loginBean.getMessage().equals("登陆成功")){
            Toast.makeText(this, loginBean.getMessage(), Toast.LENGTH_SHORT).show();
            int userId = loginBean.getResult().getUserId();
            String sessionId = loginBean.getResult().getSessionId();
            SharedPreferences sp=getSharedPreferences("config",MODE_PRIVATE);
            SharedPreferences.Editor edit = sp.edit();
            edit.putString("userId", String.valueOf(userId));
            edit.putString("sessionId",sessionId);
            edit.commit();
            Intent intentss = new Intent(WXEntryActivity.this,LoginActivity.class);
            startActivity(intentss);
        }else{
            Toast.makeText(this, loginBean.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}
