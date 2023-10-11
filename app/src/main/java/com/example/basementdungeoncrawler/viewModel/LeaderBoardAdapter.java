package com.example.basementdungeoncrawler.viewModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.basementdungeoncrawler.R;

import java.util.ArrayList;

public class LeaderBoardAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> dataList1;
    private ArrayList<String> dataList2;

    public LeaderBoardAdapter(Context context, ArrayList<String> row1, ArrayList<String> row2) {
        this.context = context;
        this.dataList1 = row1;
        this.dataList2 = row2;
    }

    @Override
    public int getCount() {
        return dataList1.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.list_item_layout, viewGroup, false);
        }
        TextView line1TextView = view.findViewById(R.id.line1TextView);
        TextView line2TextView = view.findViewById(R.id.line2TextView);

        line1TextView.setText(dataList1.get(i));
        line2TextView.setText(dataList2.get(i));

        return view;
    }
}
