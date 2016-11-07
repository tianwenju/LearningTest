package com.example.vwenjutian.Dragger2.app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vwenjutian.Dragger2.R;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by V.Wenju.Tian on 2016/11/7.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyHolder> {

    private List<String> datas;
    private Context context;

    @Inject
    public ListAdapter(List<String> datas, Context context) {
        this.datas = datas;

        this.context = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        holder.setText(R.id.tv, datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        private SparseArray<View> views = new SparseArray<>();
        private View itemView;

        public MyHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }

        public <T extends View> T findView(int resId) {
            View view = views.get(resId);
            if (view == null) {

                view = itemView.findViewById(resId);
                views.put(resId, view);
            }

            return (T) view;
        }

        public void setText(int resId, String content) {
            TextView textview = findView(resId);
            textview.setText(content);
        }
    }

}
