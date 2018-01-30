package com.example.a17720.mymemorandum;

import android.app.Notification;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

//2018.1.17 创建
public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemLongClickListener {

    private Toolbar toolbar;
    Button btn_add;
    Button btn_search;
    ListView listView;
    Note_Adapter adapter;
    private List<Note> noteList ;
    final String TAG = "myTAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        RefreshData();
    }




    //将noteList中的数据刷新,并将adapter重置  实现界面展示新数据
    private void RefreshData() {
        noteList = DataSupport.findAll(Note.class);

        adapter  = new Note_Adapter(MainActivity.this,R.layout.list_item,noteList);
        listView.setAdapter(adapter);
    }



    //初始化各种资源
    private void init(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);

        btn_search = findViewById(R.id.btn_search);
        btn_search.setOnClickListener(this);

        LitePal.getDatabase();     //初始化LitePal

        listView = findViewById(R.id.listView);
        listView.setOnItemLongClickListener(this);
    }


    //按钮的点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add:
                Note note = new Note();
                note.setTitle("Title");
                note.setContent("Content");
                note.save();
                RefreshData();
                Log.d(TAG,"successful create");
                break;
            case R.id.btn_search:
                List<Note> notes = DataSupport.findAll(Note.class);
                for (Note aNote:notes){
                    Log.d(TAG,"ID"+aNote.getId());
                    Log.d(TAG,"Content"+aNote.getContent());
                    Log.d(TAG,"Title"+aNote.getTitle());
                }
                Log.d(TAG,"successful search");
                break;
        }
    }


    //listView子项的长点击事件
    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {

        Note note = noteList.get(position);
        DataSupport.delete(Note.class,note.getId());

        RefreshData();
        return true;//点击完成后不会执行点击事件，若为false，长点击事件完成后进行点击事件
    }
}
