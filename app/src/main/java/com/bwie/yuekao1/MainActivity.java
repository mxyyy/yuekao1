package com.bwie.yuekao1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.bwie.View.IRecyView;
import com.bwie.adapter.MyAdapter;
import com.bwie.bean.Shop;
import com.bwie.event.OnPriceEvent;
import com.bwie.persenter.RecyPersenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IRecyView {
    private XRecyclerView xRecyclerView;
    private MyAdapter adapter;
    private RecyPersenter persenter;
    private int page=1;
    private CheckBox checkBox;
    private TextView pricee;
    private EditText editText;
    private Button button;
    private Context context;
    private MyView myView;
    private LayoutInflater layoutInflater;
    private List<String> list=new ArrayList<>();
    private TextView studyy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        persenter=new RecyPersenter(this);
        persenter.Datashop();
        initData();
        initView();
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                persenter.Datashop();
            }

            @Override
            public void onLoadMore() {
                page++;
                persenter.Datashop();
            }
        });

    }

    private void initView() {
        editText=findViewById(R.id.edittext);
        button=findViewById(R.id.but1);
        myView=findViewById(R.id.myview);
        layoutInflater=LayoutInflater.from(context);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=editText.getText().toString();
                View view=LayoutInflater.from(context).inflate(R.layout.study,null);
                studyy=view.findViewById(R.id.studyy);
                list.add(s);
                for (int i=0;i<list.size();i++){
                    studyy.setText(list.get(i));
                }
                myView.addView(view);
            }

        });


    }

    private void initData() {
        xRecyclerView=findViewById(R.id.xre);
        checkBox=findViewById(R.id.checkbox2);
        pricee=findViewById(R.id.price);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        xRecyclerView.setLayoutManager(manager);
        xRecyclerView.setLoadingMoreEnabled(true);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                adapter.selectAll(isChecked);
            }
        });
    }

    @Override
    public void shopData(Shop shop) {
        if (page==1){
            adapter=new MyAdapter(this,shop);
            adapter.setOnPriceEvent(new OnPriceEvent() {
                @Override
                public void price(double price) {
                    pricee.setText(String.valueOf(price));
                }
            });
            xRecyclerView.setAdapter(adapter);
            xRecyclerView.refreshComplete();
        }else {
            if (adapter!=null){
                adapter.addData(shop.getData());
                xRecyclerView.loadMoreComplete();
            }
        }
    }

}
