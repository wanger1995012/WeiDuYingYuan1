package com.bw.movie.wdyy.view;

import android.app.Application;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.util.ByteConstants;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

/**
 * @Author：lenovo
 * @E-mail： 1003195060@163.com
 * @Date：2019/5/10 19:28
 * @Description：描述信息
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //设置fresco的缓存路径及大小
        DiskCacheConfig diskCacheConfig=DiskCacheConfig.newBuilder(this)
                .setBaseDirectoryPath(this.getApplicationContext().getCacheDir())
                .setBaseDirectoryName("com.bw.fresco")
                .setMaxCacheSize(100*ByteConstants.MB)
                .build();
        ImagePipelineConfig builder=ImagePipelineConfig.newBuilder(this)
                .setMainDiskCacheConfig(diskCacheConfig)
                .build();
        Fresco.initialize(this,builder);
    }
}
