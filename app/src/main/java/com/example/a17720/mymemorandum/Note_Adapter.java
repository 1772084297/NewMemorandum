package com.example.a17720.mymemorandum;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 17720 on 2018/1/29.
 */


//构建Adapter
public class Note_Adapter extends ArrayAdapter<Note> {

    private int resourceID;

    //将上下文，子项布局的id，数据传输进来
    public Note_Adapter(@NonNull Context context, int resource, @NonNull List<Note> notes) {
        super(context, resource, notes);
        resourceID = resource;
    }


    //getItem(position)得到实例，使用LayoutInflater为子项传入布局，在获取子项的实例并展示数据
    //convertView缓冲
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Note note  = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView ==null){
            view = LayoutInflater.from(getContext()).inflate(resourceID,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.item_content=view.findViewById(R.id.item_content);
            viewHolder.item_title=view.findViewById(R.id.item_title);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        
        viewHolder.item_title.setText(note.getTitle());
        viewHolder.item_content.setText(note.getContent());
        return view;

    }

    private class ViewHolder {
        TextView item_title;
        TextView item_content;
    }
}
