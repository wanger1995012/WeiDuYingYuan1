package com.bw.movie.wdyy.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.wdyy.R;
import com.bw.movie.wdyy.activity.YYXiangqingActivity;
import com.bw.movie.wdyy.bean.TuijianBean;
import com.bw.movie.wdyy.contract.ContractInterface;
import com.bw.movie.wdyy.presenter.MyPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * 作者:今夕何夕
 * 时间:${data}
 * Description:这个是注释
 */
public class TuijianAdapter extends RecyclerView.Adapter<TuijianAdapter.Holder> implements ContractInterface.VGuanzhu {
    List<TuijianBean.ResultBean> list;
    Context context;
    ContractInterface.PGuanzhu pGuanzhu;
    MyCall myCall;
    public TuijianAdapter(List<TuijianBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.tuijianadap_item, null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int i) {
        pGuanzhu=new MyPresenter(context);
        holder.tuijian_sim.setImageURI(list.get(i).getLogo());
        holder.tuijian_title.setText(list.get(i).getName());
        holder.tuijian_address.setText(list.get(i).getAddress());
        holder.tuijian_distance.setText(list.get(i).getDistance()+"m");
        if (list.get(i).getFollowCinema()==1){
            holder.tuijian_guanzhu.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.guanzhu));
            //设置取消关注的点击监听
            holder.tuijian_guanzhu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("weiguanzhu", "关注" );
                    list.get(i).setFollowCinema(2);
                    //去P层
                    pGuanzhu.PQvxiaoguanzhu(list.get(i).getId());
                    notifyDataSetChanged();
                }
            });

        }else {
            holder.tuijian_guanzhu.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.buguanzhu));

            //设置关注的点击监听
            holder.tuijian_guanzhu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("weiguanzhu", "开始关注" );
                    list.get(i).setFollowCinema(1);
                    pGuanzhu.PWeiguanzhu(list.get(i).getId());
                    notifyDataSetChanged();
                }
            });

        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,YYXiangqingActivity.class);
                intent.putExtra("YYid",list.get(i).getId()+"");
                intent.putExtra("titles",list.get(i).getName());
                intent.putExtra("address",list.get(i).getAddress());
                intent.putExtra("log",list.get(i).getLogo());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void VWeiguanzhu(String str) {
        myCall.weiGuanzhu(str);
    }

    @Override
    public void VQvxiaoguanzhu(String str) {
        myCall.QvxiaoGuanzhu(str);
    }

    public class Holder extends RecyclerView.ViewHolder{
        SimpleDraweeView tuijian_sim;
        TextView tuijian_title,tuijian_address,tuijian_distance;
        ImageView tuijian_guanzhu;
        public Holder(@NonNull View itemView) {
            super(itemView);
            tuijian_sim=itemView.findViewById(R.id.tuijian_sim);
            tuijian_title=itemView.findViewById(R.id.tuijian_title);
            tuijian_address=itemView.findViewById(R.id.tuijian_address);
            tuijian_guanzhu=itemView.findViewById(R.id.tuijian_guanzhu);
            tuijian_distance=itemView.findViewById(R.id.tuijian_jvli);
        }
    }
    //设置接口
    public interface MyCall{
        public void weiGuanzhu(String str);
        public void QvxiaoGuanzhu(String str);
    }
    public void setMyCall(MyCall myCall) {
        this.myCall = myCall;
    }
}
