package com.bwie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.bean.Shop;
import com.bwie.event.OnPriceEvent;
import com.bwie.yuekao1.MyjiajianView;
import com.bwie.yuekao1.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    public static final String TAG = MyAdapter.class.getSimpleName();
    private Context context;
    private Shop shop;
    private List<Shop.DataBean> list;

    public MyAdapter(Context context, Shop shop) {
        this.context = context;
        this.shop = shop;
        list = shop.getData();
    }

    public void addData(List<Shop.DataBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.myadapter, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final MyViewHolder holder1 = (MyViewHolder) holder;
        final Shop.DataBean bean = list.get(position);

        String[] imgs = shop.getData().get(position).getImages().split("\\|");
        Glide.with(context).load(imgs[0]).into(holder1.imageView);
        holder1.title.setText(shop.getData().get(position).getTitle());
        //holder1.myjiajian.setNumEt(bean.getSalenum());
        holder1.price.setText(shop.getData().get(position).getPrice());
        holder1.checkBox.setChecked(list.get(position).ischecked());
        //holder1.myjiajian.setJiaJianListener(new MyjiajianView.JiaJianListener() {
        //    @Override
         //   public void getNum(int num) {
       //         bean.setSalenum(num);
         //       bean.setIschecked(true);
                // holder1.checkBox.setChecked(true);
          //      Log.i(TAG, "postion:" + position + "bean" + bean.ischecked());
         //       notifyDataSetChanged();
         //   }
     //   });
        /*holder1.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //list.get(position).setIschecked(isChecked);
              //  bean.setIschecked(isChecked);
                initprice();
            }
        });*/
        holder1.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(position).setIschecked(!list.get(position).ischecked());
                notifyDataSetChanged();
            }
        });
        initprice();
    }

    private void initprice() {
        double price = 0;
        if (onPriceEvent != null) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).ischecked()) {
                    price += Double.parseDouble(list.get(i).getPrice()) * list.get(i).getSalenum();
                }
            }
        }
        onPriceEvent.price(price);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView price;
        private ImageView imageView;
        private CheckBox checkBox;
        private MyjiajianView myjiajian;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titlee);
            price = itemView.findViewById(R.id.price);
            imageView = itemView.findViewById(R.id.imagee);
            checkBox = itemView.findViewById(R.id.checkboxx);
           // myjiajian = itemView.findViewById(R.id.myjiajian);



        }
    }

    //全选全不选
    public void selectAll(boolean quanxuan) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setIschecked(quanxuan);
        }
        notifyDataSetChanged();
    }

    private OnPriceEvent onPriceEvent;

    public void setOnPriceEvent(OnPriceEvent onPriceEvent) {
        this.onPriceEvent = onPriceEvent;
    }

}
