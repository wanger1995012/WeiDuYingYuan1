package com.bw.movie.wdyy.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bw.movie.wdyy.R;
import com.bw.movie.wdyy.bean.TongzhiBean;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * 作者:今夕何夕
 * 时间:${data}
 * Description:这个是注释
 */
public class MyTongzhiAdapter extends RecyclerView.Adapter<MyTongzhiAdapter.hoder> {
    List<TongzhiBean.ResultBean> list;
    Context context;
    int a;
    long date;
    Mycall mycall;
    MyCallXiaoxi myCallXiaoxi;
    int xiaoxiid;

    public MyTongzhiAdapter(List<TongzhiBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public hoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.tongzhi_adapter, null);
        return new hoder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull final hoder hoder, final int i) {
        hoder.tongzhi_ttle.setText(list.get(i).getTitle());
        hoder.tongzhi_content.setText(list.get(i).getContent());
        a = 0;
        if (list.get(i).getStatus() == 0) {
            hoder.tongzhi_xiaoxi.setVisibility(View.VISIBLE);
            a++;
            mycall.Tongzhixiaox(a);
        }
        //设置毫秒值
        date = list.get(i).getPushTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.CHINA);
        // time为转换格式后的字符串
        String time = dateFormat.format(new Date(date));
        hoder.tongzhi_pushTime.setText(time);

        hoder.tongzhi_weidubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hoder.tongzhi_weidubtn.equals("设为未读")) {
                    list.get(i).setStatus(1);
                    xiaoxiid = list.get(i).getId();
                    myCallXiaoxi.xiaoxiid(list,i);
                    //设置消息的未读事件
                    hoder.tongzhi_weidubtn.setText("设为已读");
                    list.get(i).setStatus(1);if (list.get(i).getStatus() == 1) {
                        hoder.tongzhi_xiaoxi.setVisibility(View.GONE);
                        a++;
                        mycall.Tongzhixiaox(a);
                    }
                } else {
                    //设置消息的已读读事件
                    list.get(i).setStatus(0);
                    xiaoxiid = list.get(i).getId();
                    myCallXiaoxi.xiaoxiid(list,i);
                    hoder.tongzhi_weidubtn.setText("设为未读");
                    if (list.get(i).getStatus() == 0) {
                        hoder.tongzhi_xiaoxi.setVisibility(View.VISIBLE);
                        a++;
                        mycall.Tongzhixiaox(a);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class hoder extends RecyclerView.ViewHolder {
        TextView tongzhi_ttle, tongzhi_content, tongzhi_pushTime, tongzhi_xiaoxi;
        TextView tongzhi_weidubtn;

        public hoder(@NonNull View itemView) {
            super(itemView);
            tongzhi_ttle = itemView.findViewById(R.id.tongzhi_title);
            tongzhi_content = itemView.findViewById(R.id.tongzhi_content);
            tongzhi_pushTime = itemView.findViewById(R.id.tongzhi_timeout);
            tongzhi_xiaoxi = itemView.findViewById(R.id.tongzhi_xiaoxi);
            tongzhi_weidubtn = itemView.findViewById(R.id.tongzhi_weidubtn);
        }
    }

    //设置接口
    public interface Mycall {
        public void Tongzhixiaox(int aa);
    }

    public interface MyCallXiaoxi {
        public void xiaoxiid(List<TongzhiBean.ResultBean> lst,int i);
    }

    public void setMycall(Mycall mycall) {
        this.mycall = mycall;
    }

    public void setMyCallXiaoxi(MyCallXiaoxi myCallXiaoxi) {
        this.myCallXiaoxi = myCallXiaoxi;
    }
}
