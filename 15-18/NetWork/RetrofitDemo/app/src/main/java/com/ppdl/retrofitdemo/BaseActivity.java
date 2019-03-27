package com.ppdl.retrofitdemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ppdl.bean.WeatherBean;

import java.io.Reader;

import static android.R.attr.data;

public abstract class BaseActivity extends Activity implements View.OnClickListener{

    public abstract int setLayoutId();

    public abstract void InitView();

    private TextView textView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        InitView();
    }

    public Button setButton(int id){
        Button button=(Button) findViewById(id);
        button.setOnClickListener(this);
        return button;
    }

    public TextView setTextView(int id){
        this.textView=(TextView) findViewById(id);
        return this.textView;
    }

    public ImageView setImageView(int id){
        this.imageView=(ImageView) findViewById(id);
        return this.imageView;
    }

    public void setTextUI(TextView textView,String data){
        textView.setText(data);
    }

    public void setTextThread(final TextView textView, final String data){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(data);
            }
        });
    }

    public void setTextThread(final String data){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(textView != null)
                textView.setText(data);
            }
        });
    }

    public void setImgThread(final Bitmap bitmap){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(imageView != null)
                    imageView.setImageBitmap(bitmap);
            }
        });
    }

    public void setTextUI(final String data){
        if(textView != null)
            textView.setText(data);
    }

    public String anayleJson(Reader reader){
        Gson gson=new Gson();
        WeatherBean weatherBean = gson.fromJson(reader, WeatherBean.class);
        WeatherBean.DetailData detailData=weatherBean.getResult().getData();
        String data="禁忌: "+detailData.getAvoid()+"\n"
                +"年: "+detailData.getAnimalsYear()+"\n"
                +"周: "+detailData.getWeekday()+"\n"
                +"适宜 :"+detailData.getSuit()+"\n"
                +"那年: "+detailData.getLunarYear()+"\n"
                +"年月: "+detailData.getDate();
        return data;
    }

}
