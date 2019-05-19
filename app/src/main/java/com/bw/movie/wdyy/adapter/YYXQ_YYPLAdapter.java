package com.bw.movie.wdyy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.wdyy.R;
import com.bw.movie.wdyy.bean.YypjBean;
import com.bw.movie.wdyy.contract.ContractInterface;
import com.facebook.drawee.view.SimpleDraweeView;

import org.jetbrains.annotations.Contract;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * 作者:今夕何夕
 * 时间:${data}
 * Description:这个是注释
 */
public class YYXQ_YYPLAdapter extends RecyclerView.Adapter<YYXQ_YYPLAdapter.holder> {
    List<YypjBean.ResultBean> list;
    Context context;
    MyCall myCall;
    public YYXQ_YYPLAdapter(List<YypjBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.yyxq_yypj_adapter, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final holder holder, final int i) {
        holder.yypj_title.setText(list.get(i).getCommentUserName());
        holder.yypj_connext.setText(list.get(i).getCommentContent());
        holder.yypj_sim.setImageURI(list.get(i).getCommentHeadPic());
        holder.yypj_dianzanshu.setText(list.get(i).getGreatNum()+"");
        //将毫秒值转换为年月日
        long commentTime = list.get(i).getCommentTime();
        //设置毫秒值
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
        // time为转换格式后的字符串
        String time = dateFormat.format(new Date(commentTime));
        holder.yypj_data.setText(time);
        //设置点赞的默认图片
        if (list.get(i).getIsGreat()==1){
            //是点过赞
            holder.yypj_dianzan.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.yypj_dianzan));
        }else {
            //没有点过赞
            holder.yypj_dianzan.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.yypj_dianzan1));
        }
        //点击点赞后进行改变
        holder.yypj_dianzan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是否点赞
                if (list.get(i).getIsGreat()==1){
                    //是点过赞
                    holder.yypj_dianzan.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.yypj_dianzan));
                    list.get(i).setIsGreat(1);
                    list.get(i).setGreatNum(list.get(i).getGreatNum()+1);
                    holder.yypj_dianzanshu.setText(list.get(i).getGreatNum()+"");
                    myCall.YYDZ(list,i);
                }else {
                    //不能重复点赞
                    holder.yypj_dianzan.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.yypj_dianzan));
                    list.get(i).setIsGreat(1);
                    list.get(i).setGreatNum(list.get(i).getGreatNum());
                    holder.yypj_dianzanshu.setText(list.get(i).getGreatNum()+"");
                    myCall.YYDZ(list,i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder extends RecyclerView.ViewHolder{
        SimpleDraweeView yypj_sim;
        ImageView yypj_dianzan;
        TextView yypj_title,yypj_connext,yypj_data,yypj_dianzanshu;
        public holder(@NonNull View itemView) {
            super(itemView);
            yypj_dianzan=itemView.findViewById(R.id.yypj_dianzan);
            yypj_title=itemView.findViewById(R.id.yypj_title);
            yypj_connext=itemView.findViewById(R.id.yypj_content);
            yypj_data=itemView.findViewById(R.id.yypj_data);
            yypj_dianzanshu=itemView.findViewById(R.id.yypj_renshu);
            yypj_sim=itemView.findViewById(R.id.yypj_sim);
        }
    }
    //设置接口
    public interface MyCall{
        public void YYDZ(List<YypjBean.ResultBean> lst,int i);
    }

    public void setMyCall(MyCall myCall) {
        this.myCall = myCall;
    }
}
