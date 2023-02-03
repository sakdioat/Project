package com.example.user.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class search_adapter extends BaseAdapter {
    Context context;
    //Dog dogs = new Dog();
    value_search garden;
    search_adapter(Context context, value_search dogs) {
        this.context = context;
        this.garden = dogs;
    }

    @Override
    public int getCount() {
        if (garden == null)
            return 0;
        if (garden.getDogs() == null)
            return 0;

        return garden.getDogs().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        LayoutInflater mInflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = mInflater.inflate(R.layout.row_list_search, parent, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.image);

        TextView textView = (TextView) view.findViewById(R.id.title);
        TextView textDescEng = (TextView) view.findViewById(R.id.descEng);
//        TextView textDescSci = (TextView) view.findViewById(R.id.descSci);
//        TextView textDescCate = (TextView) view.findViewById(R.id.descCate);

        if (garden != null && garden.getDogs() != null){

            imageView.setImageResource(garden.getDogs().get(position).getResId());
            textView.setText(garden.getDogs().get(position).getBreed());

            textDescEng.setText(garden.getDogs().get(position).getNameEng());
//            textDescSci.setText(garden.getDogs().get(position).getNameSci());
//            textDescCate.setText(garden.getDogs().get(position).getNameCate());
        }
        return view;

    }
}
