package com.e.boombux;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;


public class Songs extends AppCompatActivity {
    ListView SongsListView;
 String[] items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);
        SongsListView =findViewById(R.id.SongsList);
        runtimePermission();
    }
    public void runtimePermission(){
       Dexter.withActivity(this)
               .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
               .withListener(new PermissionListener() {
                   @Override
                   public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                       display();
                   }

                   @Override
                   public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                   }

                   @Override
                   public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                       permissionToken.continuePermissionRequest();
                   }
               }).check();
    }
    public ArrayList<File> findSong (File file){
        ArrayList<File> arrayList=new ArrayList<>();
        File[] files = file.listFiles();
        for(File singleFile: files){
            if(singleFile.isDirectory() && !singleFile.isHidden()){
                arrayList.addAll(findSong(singleFile));
            }
            else{
                if(singleFile.getName().endsWith(".mp3") ||
                singleFile.getName().endsWith(".wav") ||
                singleFile.getName().endsWith(".mp4")){

                    arrayList.add(singleFile);
                }
            }

        }
        return arrayList;
    }

    void display() {
        final ArrayList<File> mySongs = findSong(Environment.getExternalStorageDirectory());
        items = new String[mySongs.size()];

        for (int i = 0; i < mySongs.size(); i++) {
            items[i] = mySongs.get(i).getName().toString().replace(".mp3", "").replace(".wav", "")
                    .replace(".mp4", "");
        }
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        SongsListView.setAdapter(myAdapter);
SongsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
String SongName =SongsListView.getItemAtPosition(i).toString();
        startActivity(new Intent(getApplicationContext(),PlayerActivity.class)
        .putExtra("songs",mySongs).putExtra("songname",SongName).putExtra("pos",i));


    }
});

    }

    }

