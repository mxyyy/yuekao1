package com.bwie.yuekao1;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MyjiajianView extends LinearLayout {
    private TextView jiaTv,jiantv;
    private EditText numEt;
    private int num = 1;
    public MyjiajianView(Context context) {
        this(context,null);
    }

    public MyjiajianView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public  MyjiajianView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {

        View view = LayoutInflater.from(context).inflate(R.layout.jiajian,this,true);
//        addView(view);
        jiantv = view.findViewById(R.id.jian);
        jiaTv = view.findViewById(R.id.jia);
        numEt = view.findViewById(R.id.num);



        numEt.setText(num+"");



        jiaTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // num = Integer.parseInt(numEt.getText().toString());
                num++;
                // numEt.setText(num+"");
                if (jiaJianListener!=null){
                    jiaJianListener.getNum(num);
                }
            }
        });
        jiantv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // num = Integer.parseInt(numEt.getText().toString());
                num--;
                if (num<=0){
                    Toast.makeText(getContext(), "数量不能小于1", Toast.LENGTH_SHORT).show();
                    num = 1;
                }

                // numEt.setText(num+"");

                if (jiaJianListener!=null){
                    jiaJianListener.getNum(num);
                }



            }
        });

    }

    public void setNumEt(int n) {
        numEt.setText(n+"");
        num = Integer.parseInt(numEt.getText().toString());
    }

    private JiaJianListener jiaJianListener;

    public void setJiaJianListener(JiaJianListener jiaJianListener) {
        this.jiaJianListener = jiaJianListener;
    }

    public interface JiaJianListener{
        void getNum(int num);
    }
}
