package com.bw.movie.wdyy.hotactivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.movie.wdyy.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HotActivity extends AppCompatActivity {

    ImageView imageFimlLoca;
    TextView textFimlLoca;
    ImageView image;
    EditText edFimlSe;
    TextView textFimlSe;
    RelativeLayout layoutFimlSe;
    int isShow = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot);
        imageFimlLoca = findViewById(R.id.image_fiml_loca);
        textFimlLoca = findViewById(R.id.text_fiml_loca);
        image = findViewById(R.id.image_sou);
        edFimlSe = findViewById(R.id.ed_fiml_se);
        textFimlSe = findViewById(R.id.text_fiml_se);
        layoutFimlSe = findViewById(R.id.layout_fiml_se);
        layoutFimlSe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isShow == 1){
                    textFimlSe.setVisibility(View.VISIBLE);
                    edFimlSe.setVisibility(View.VISIBLE);
                    ObjectAnimator animator=ObjectAnimator.ofFloat(layoutFimlSe,"translationX",0,-200);
                    animator.setDuration(2000);
                    animator.start();
                    isShow=2;
                }
            }
        });

        //点击搜索缩进去
        textFimlSe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShow==2){
                    ObjectAnimator animator=ObjectAnimator.ofFloat(layoutFimlSe,"translationX",-200,0);
                    animator.setDuration(2000);
                    animator.start();
                    textFimlSe.setVisibility(View.GONE);
                    edFimlSe.setVisibility(View.GONE);
                    isShow=1;
                }
            }
        });

    }
}
