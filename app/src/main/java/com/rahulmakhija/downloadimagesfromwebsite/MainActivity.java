package com.rahulmakhija.downloadimagesfromwebsite;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);


    }

    public void downloadImage(View view){
        DownloadTask downloadTask = new DownloadTask();
        try {
            Bitmap image = downloadTask.execute("https://hypb.imgix.net/image/ht/2016/02/bart-simpson-the-simpsons-drake-started-from-the-bottom-episode-0.jpg?w=960&q=90&fit=max&auto=compress%2Cformat").get();
            Log.i("Result", "Success");
            imageView.setImageBitmap(image);
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("Result", "Failure");
        }

    }

    public class DownloadTask extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... strings) {

            URL url;
            HttpURLConnection urlConnection;
            Bitmap image;

            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection)url.openConnection();
                urlConnection.connect();
                InputStream inputStream = urlConnection.getInputStream();
                image = BitmapFactory.decodeStream(inputStream);
                return image;

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
