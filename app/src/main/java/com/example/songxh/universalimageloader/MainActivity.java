package com.example.songxh.universalimageloader;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;

public class MainActivity extends AppCompatActivity {
    private ImageView img;
    private ImageLoader imageLoader;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = (ImageView)findViewById(R.id.img);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("图片加载中");
        progressDialog.setMessage("请稍后");


        //加载并显示bitmap
        imageLoader = ImageLoader.getInstance();
        //一般使用imageLoader.displayImage(),直接显示图片，如果使用imageLoader.loadImage()的话，不会直接显示位图，必须在监听里面，load完成之后，手动来显示
        imageLoader.displayImage("http://img1.gtimg.com/sports/pics/hv1/254/243/2090/135964469.jpg", img,
                new ImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String s, View view) {
                        progressDialog.show();
                    }

                    @Override
                    public void onLoadingFailed(String s, View view, FailReason failReason) {
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onLoadingCancelled(String s, View view) {//该回调只有在手动调用ImageLoader.cancel()才调用
                        progressDialog.dismiss();
                    }
                }
            );
    }

}