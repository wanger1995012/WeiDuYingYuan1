package com.bw.movie.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.ZhuceBean;
import com.bw.movie.model.MyModel;
import com.bw.movie.view.App;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class XinXiActivity extends AppCompatActivity {

    @BindView(R.id.myxinxi_tou)
    ImageView myxinxiTou;
    @BindView(R.id.myxinxi_nicheng)
    TextView myxinxiNicheng;
    @BindView(R.id.myxinxi_sex)
    TextView myxinxiSex;
    @BindView(R.id.myxinxi_riqi)
    TextView myxinxiRiqi;
    @BindView(R.id.myxinxi_phone)
    TextView myxinxiPhone;
    @BindView(R.id.myxinxi_youxiang)
    TextView myxinxiYouxiang;
    @BindView(R.id.myxinxi_chongzhi)
    ImageView myxinxiChongzhi;
    @BindView(R.id.myxinxi_fanhui)
    ImageView myxinxiFanhui;
    Context mContext = null;
    @BindView(R.id.myxinxi_btn_xiugai)
    Button myxinxiBtnXiugai;
    //相机相册
    private String icon = "com.bw.xiangji";

    //相册请求码
    private static final int ALBUM_REQUEST_CODE = 1;
    //相机请求码
    private static final int CAMERA_REQUEST_CODE = 2;
    //剪裁请求码
    private static final int CROP_REQUEST_CODE = 3;
    private static final int shujv = 4;
    //调用照相机返回图片文件
    private File tempFile;
    int i = 0;
    ZhuceBean zhuceBean;
    private SharedPreferences sp1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xin_xi);
        ButterKnife.bind(this);
        mContext = this;

        //拿到bean中信息

        sp1=getSharedPreferences("xinxi", MODE_PRIVATE);
        String name=sp1.getString("NickName","");
        String HeadPic=sp1.getString("HeadPic","");
        String Phone=sp1.getString("Phone","");
        int Sex=sp1.getInt("Sex",123);
        long LastLoginTime=sp1.getLong("LastLoginTime",123);
        long Birthday=sp1.getLong("Birthday",123);




        //设置返回
        FanhuiInit();
        //设置用户头像
        TouxiangInit();
        //设置用户的信息
        //设置毫秒值
        long date=Birthday;
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
        // time为转换格式后的字符串
        String time = dateFormat.format(new Date(date));
        myxinxiNicheng.setText(name);
        myxinxiPhone.setText(Phone);
        myxinxiRiqi.setText(time);
        if (Sex == 1) {
            myxinxiSex.setText("男");
        } else {
            myxinxiSex.setText("女");
        }
        myxinxiYouxiang.setText(LastLoginTime+"");


//        myxinxiNicheng.setText(nickName);
//        myxinxiPhone.setText(phone);
//        myxinxiRiqi.setText(riqi + "");
//        if (sex == 1) {
//            myxinxiSex.setText("男");
//        } else {
//            myxinxiSex.setText("女");
//        }
//        myxinxiYouxiang.setText(youxiang + "");


        //设置修改的点击事件
        myxinxiBtnXiugai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(XinXiActivity.this, XiuGaiActivity.class);
                intent.putExtra("aaa","12");//a传b在此处写你传的值
                startActivityForResult(intent, 4);
            }
        });
        //设置重置密码
        ChongzhiMima();

    }
    //重置密码
    private void ChongzhiMima() {
        myxinxiChongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(XinXiActivity.this,MyChongzhiActivity.class);
                startActivity(intent);
            }
        });
    }

    //设置用户头像
    private void TouxiangInit() {
        myxinxiTou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v);
            }
        });
    }

    //设置窗口
    private void showPopupWindow(View v) {
        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(mContext).inflate(
                R.layout.popuwindow_item, null);
        //获取id
        final TextView xiangji = contentView.findViewById(R.id.mytouxiang_xiangji);
        final TextView xiangce = contentView.findViewById(R.id.mytouxiang_xiangce);

        final PopupWindow popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        //设置相机的点击事件
        xiangji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPicFromCamera();//调用相机
                Toast.makeText(XinXiActivity.this, "相机", Toast.LENGTH_SHORT).show();
            }
        });
        //设置相册的点击事件
        xiangce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPicFromAlbm();//调用相册
                Toast.makeText(XinXiActivity.this, "相册", Toast.LENGTH_SHORT).show();
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.color.bai));

        // 设置好参数之后再show
        popupWindow.showAsDropDown(v);

    }

    //调用相册
    private void getPicFromAlbm() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, ALBUM_REQUEST_CODE);
    }

    /**
     * 从相机获取图片
     */
    private void getPicFromCamera() {
        //用于保存调用相机拍照后所生成的文件
        tempFile = new File(Environment.getExternalStorageDirectory().getPath(), System.currentTimeMillis() + ".jpg");
        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //判断版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {   //如果在Android7.0以上,使用FileProvider获取Uri
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(XinXiActivity.this, "com.hansion.chosehead", tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        } else {    //否则使用Uri.fromFile(file)方法获取Uri
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
    }

    /**
     * 裁剪图片
     */
    private void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            // 调用相机后返回
            case CAMERA_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    //用相机返回的照片去调用剪裁也需要对Uri进行处理
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Uri contentUri = FileProvider.getUriForFile(XinXiActivity.this, "com.hansion.chosehead", tempFile);
                        cropPhoto(contentUri);//裁剪图片
                    } else {
                        cropPhoto(Uri.fromFile(tempFile));//裁剪图片
                    }
                }
                break;
            //调用相册后返回
            case ALBUM_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    cropPhoto(uri);//裁剪图片
                }
                break;
            //调用剪裁后返回
            case CROP_REQUEST_CODE:
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    //在这里获得了剪裁后的Bitmap对象，可以用于上传
                    Bitmap image = bundle.getParcelable("data");
                    //设置到ImageView上
                    myxinxiTou.setImageBitmap(image);
                    //也可以进行一些保存、压缩等操作后上传
                    String path = saveImage("userHeader", image);
                    File file = new File(path);
                    /*
                     *上传文件的额操作
                     */
                }
                break;
            case shujv:
                if (resultCode == RESULT_OK) {
                    String a = data.getStringExtra("mmm");
                    String nickName = data.getStringExtra("NickName");
                    String Sex = data.getStringExtra("Sex");
                    String Phone = data.getStringExtra("Phone");
                    String Birthday = data.getStringExtra("Birthday");
                    String LastLoginTime = data.getStringExtra("LastLoginTime");
                    if (a.equals("12")){
                        LoginBean.ResultBean.UserInfoBean userInfoBean = new LoginBean.ResultBean.UserInfoBean();
                        Log.e("ab", "onCreate: " +nickName.toString());
                        myxinxiNicheng.setText(nickName);
                        myxinxiPhone.setText(Phone);
                        myxinxiRiqi.setText(Birthday + "");
                        if (Sex.equals("1")) {
                            myxinxiSex.setText("男");
                        } else {
                            myxinxiSex.setText("女");
                        }
                        myxinxiYouxiang.setText(LastLoginTime + "");
                    }
                }
                break;
        }
    }

    /**
     * 保存图片到本地
     *
     * @param name
     * @param bmp
     * @return
     */
    public String saveImage(String name, Bitmap bmp) {
        File appDir = new File(Environment.getExternalStorageDirectory().getPath());
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = name + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //设置返回按钮
    private void FanhuiInit() {
        myxinxiFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XinXiActivity.this, ShowActivity.class);
                intent.putExtra("id", 2);
                startActivity(intent);
                finish();
            }
        });
    }
}
