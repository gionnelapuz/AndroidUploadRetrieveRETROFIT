package com.example.gin.androiduploadretrieveretrofit.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gin.androiduploadretrieveretrofit.OBJECT.Person;
import com.example.gin.androiduploadretrieveretrofit.R;

import java.util.List;

public class retrieveTextAdapter extends BaseAdapter {

    private Context context;
    private List<Person> person;

    public retrieveTextAdapter(Context context, List<Person> person){
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        View listView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listView = inflater.inflate(R.layout.activity_retrieveitem_text, parent, false);

        Person result = person.get(position);

        TextView names = (TextView) listView.findViewById(R.id.txtName);
        TextView ages = (TextView) listView.findViewById(R.id.txtAge);

        names.setText(result.getName());
        ages.setText(result.getAge());

        return listView;
    }}
