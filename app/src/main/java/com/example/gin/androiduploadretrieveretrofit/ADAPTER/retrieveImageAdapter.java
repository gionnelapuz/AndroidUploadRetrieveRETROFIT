package com.example.gin.androiduploadretrieveretrofit.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.gin.androiduploadretrieveretrofit.OBJECT.Person;
import com.example.gin.androiduploadretrieveretrofit.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class retrieveImageAdapter extends BaseAdapter {

    private Context context;

    private List<Person> person;

    public retrieveImageAdapter(Context context, List<Person> person){
        this.context = context;
        this.person = person;
    }

    @Override
    public int getCount() {
        return person.size();
    }

    @Override
    public Object getItem(int position) {
        return person.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View grid;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        grid = inflater.inflate(R.layout.activity_retrieveitem_image, null);

        Person result = person.get(position);

        ImageView image = (ImageView) grid.findViewById(R.id.imageView);

        Picasso.with(context)
                .load(result.getImage())
                .fit()
                .placeholder(R.mipmap.ic_launcher)
                .into(image);

        return grid;
    }
}
