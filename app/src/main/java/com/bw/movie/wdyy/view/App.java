package com.bw.movie.wdyy.view;

import android.app.Application;

import com.baway.rikao0411.greendao.gen.DaoMaster;
import com.baway.rikao0411.greendao.gen.DaoSession;

import com.bw.movie.wdyy.utile.T;

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
    public static DaoSession daoSession;
    private static App        instance;
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

        T.init(this);
        instance = this;
    }

    public static App getInstance() {
        return instance;
    }
}
