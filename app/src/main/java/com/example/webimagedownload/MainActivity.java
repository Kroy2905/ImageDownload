package com.example.webimagedownload;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    public void downloadbutton(View view){
        downloadimage task = new downloadimage();
        Bitmap myimage;
        try{
            myimage = task.execute("https://i.ebayimg.com/images/g/V~EAAOSwCTddUyzv/s-l1600.jpg").get();
            imageView.setImageBitmap(myimage);
        }catch (Exception e){
            e.printStackTrace();
        }
        Log.i("BUTTON CLICKED","ok");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView)findViewById(R.id.imageView);
    }
    public class downloadimage extends AsyncTask<String,Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream in = connection.getInputStream();
                Bitmap mybitmap = BitmapFactory.decodeStream(in);
                return mybitmap;
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }


        }
    }
}