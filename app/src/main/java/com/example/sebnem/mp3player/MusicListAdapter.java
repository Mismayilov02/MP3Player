package com.example.sebnem.mp3player;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MusicListAdapter  extends ArrayAdapter<String> {

    ArrayList<String> musicNameList = new ArrayList<>();
    Context context;
    Integer designId;
    MusicListAdapter(Context context , Integer designId , ArrayList<String> musicNameList){
        super(context , designId , musicNameList);
        this.context = context;
        this.musicNameList = musicNameList;
        this.designId =designId;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(designId , null);

        TextView name = view.findViewById(R.id.music_name);
        name.setText(musicNameList.get(position));


        return  view;
    }
}
