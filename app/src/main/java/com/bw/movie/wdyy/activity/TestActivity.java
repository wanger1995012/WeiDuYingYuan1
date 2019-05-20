package com.bw.movie.wdyy.activity;

import android.app.Activity;
import android.widget.ListView;

import com.bw.movie.wdyy.R;
import com.bw.movie.wdyy.treeList.FileBean;
import com.bw.movie.wdyy.treeList.SimpleTreeAdapter;
import com.bw.movie.wdyy.treeList.TreeListViewAdapter;
import com.bw.movie.wdyy.utile.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者:今夕何夕
 * 时间:${data}
 * Description:这个是注释
 */
public class TestActivity extends BaseActivity {
    private List<FileBean> mDatas = new ArrayList<FileBean>();
    private ListView mTree;
    private TreeListViewAdapter mAdapter;

    @Override
    protected int initLayout() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView() {
        initDatas();
        mTree = (ListView) findViewById(R.id.listView);
        try {

            mAdapter = new SimpleTreeAdapter<FileBean>(mTree, this, mDatas, 10);
            mTree.setAdapter(mAdapter);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void initData() {

    }


    private void initDatas() {

        // id , pid , label , 其他属性
        mDatas.add(new FileBean(1, 0, "文件管理系统"));
        mDatas.add(new FileBean(2, 1, "游戏"));
        mDatas.add(new FileBean(3, 1, "文档"));
        mDatas.add(new FileBean(4, 1, "程序"));
        mDatas.add(new FileBean(5, 2, "war3"));
        mDatas.add(new FileBean(6, 2, "刀塔传奇"));

        mDatas.add(new FileBean(7, 4, "面向对象"));
        mDatas.add(new FileBean(8, 4, "非面向对象"));

        mDatas.add(new FileBean(9, 7, "C++"));
        mDatas.add(new FileBean(10, 7, "JAVA"));
        mDatas.add(new FileBean(11, 7, "Javascript"));
        mDatas.add(new FileBean(12, 8, "C"));

    }
}
