package com.xu.xmaster.adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xu.xmaster.R;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position);

        void onItemDel(int position);
    }

    private List<String> list;
    private Context context;
    private boolean isLight;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public HistoryAdapter(Context context) {
        this(context, false);
    }

    public HistoryAdapter(Context context, boolean isLight) {
        this.context = context;
        list = new ArrayList<>();
        this.isLight = isLight;
    }

    public void setList(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public String getItem(int postion) {
        return list.get(postion);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_history, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.item_name.setText(list.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(v, holder, position);
                }
            }
        });

        holder.item_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemDel(position);
                }
            }
        });

        if (isLight) {
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.colorWhite));
            holder.item_name.setTextColor(context.getResources().getColor(R.color.colorGray40));
            holder.item_img.setImageTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.colorGray40)));
            holder.item_del.setImageTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.colorGray40)));
        } else {
            holder.item_name.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.item_img.setImageTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.colorWhite)));
            holder.item_del.setImageTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.colorWhite)));
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView item_name;
        private ImageView item_img, item_del;

        public MyViewHolder(View itemView) {
            super(itemView);
            item_name = itemView.findViewById(R.id.item_name);
            item_img = itemView.findViewById(R.id.item_img);
            item_del = itemView.findViewById(R.id.item_del);
        }
    }
}
