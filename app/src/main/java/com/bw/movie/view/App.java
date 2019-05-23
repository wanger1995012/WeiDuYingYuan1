package com.bw.movie.view;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;

import com.bw.movie.dao.greendao.gen.DaoMaster;
import com.bw.movie.dao.greendao.gen.DaoSession;
import com.bw.movie.utile.T;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.util.ByteConstants;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import org.greenrobot.greendao.database.Database;

/**
 * @Author：lenovo
 * @E-mail： 1003195060@163.com
 * @Date：2019/5/10 19:28
 * @Description：描述信息
 */
public class App extends Application {
    public static final boolean ENCRYPTED = true;

        private static App        instance;
    public static DaoSession daoSession;
    public static DaoSession daoSession2;


    @Override
    public void onCreate() {
        super.onCreate();
        //设置fresco的缓存路径及大小
        DiskCacheConfig diskCacheConfig=DiskCacheConfig.newBuilder(this)
                .setMaxCacheSize(200*ByteConstants.MB)
                .setBaseDirectoryName("com.bw.fresco")
                .setBaseDirectoryPath(this.getApplicationContext().getCacheDir())
                .build();
        ImagePipelineConfig builder=ImagePipelineConfig.newBuilder(this)
                .setMainDiskCacheConfig(diskCacheConfig)
                .build();
        Fresco.initialize(this,builder);
        //开启数据库
        DaoMaster.DevOpenHelper helper = new  DaoMaster.DevOpenHelper(this, ENCRYPTED ? "users-db-encrypted" : "users-db");
        //注意这里是getWritableDb()
        Database db =  helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();


        DaoMaster.DevOpenHelper helper2 =  new DaoMaster.DevOpenHelper(this, "types");
        SQLiteDatabase database = helper2.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        daoSession2 = daoMaster.newSession();


        T.init(this);
        instance = this;

        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
    }

    public static App getInstance() {
        return instance;
    }
}
