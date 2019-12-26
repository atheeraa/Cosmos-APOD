package com.example.favor.cosmos;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends  ArrayAdapter<list> {
 static   TextView author;
public MyAdapter(Context context, List<list> cosmo) {
        super(context, 0, cosmo);
        }

@Override
public View getView(int position, View convertView, ViewGroup parent) {


        View listItemView = convertView;
        if (listItemView == null) {
        listItemView = LayoutInflater.from(getContext()).inflate(
        R.layout.list_item, parent, false);
        }
        list current = getItem(position);
        TextView title = listItemView.findViewById(R.id.title);
        title.setText(current.getTitle());
        TextView section = listItemView.findViewById(R.id.date);
        section.setText(current.getDate());

        author = listItemView.findViewById(R.id.desc);
        author.setText(current.getExplanation());
        ImageView image = listItemView.findViewById(R.id.img);

    Picasso.get().load(current.getUrl()).into(image);
Log.v("1","Image is" + current.getUrl());
    return listItemView;
        }


        }