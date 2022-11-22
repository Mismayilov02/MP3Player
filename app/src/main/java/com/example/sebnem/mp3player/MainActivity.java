package com.example.sebnem.mp3player;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> musicNameList = new ArrayList<String>();
    ArrayList<Integer> musicId = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);

        musicId.add(R.raw.enemy);
        musicNameList.add(getResources().getResourceEntryName(R.raw.enemy));
        musicId.add(R.raw.dancer);
        musicNameList.add(getResources().getResourceEntryName(R.raw.dancer));

        MusicListAdapter musicListAdapter = new MusicListAdapter(this ,R.layout.music_list_design ,musicNameList );
        listView.setAdapter(musicListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this , Player.class);
                intent.putExtra("id" , musicId.get(i));
                intent.putExtra("musicName" , musicNameList.get(i));
                startActivity(intent);
            }
        });

    }
}