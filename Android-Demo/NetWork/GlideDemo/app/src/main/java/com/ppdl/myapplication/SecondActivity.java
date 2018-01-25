package com.ppdl.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ppdl.myapplication.Dao.GreenDaoManager;
import com.ppdl.myapplication.Dao.dataBean;

import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private TextView tvShow;
    private EditText etId;
    private EditText etJson;
    private GreenDaoManager daoManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        InitView();
    }

    private void InitView() {

        tvShow = (TextView) findViewById(R.id.tv_show);
        etId = (EditText) findViewById(R.id.ed_id);
        etJson = (EditText) findViewById(R.id.ed_json);

        daoManager = GreenDaoManager.getInstance();

        findViewById(R.id.btn_query).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=etId.getText().toString();
                if(!id.isEmpty()){
                    dataBean bean=daoManager.query(Integer.parseInt(id));
                    tvShow.setText(bean.getJson());
                }
            }
        });

        findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=etId.getText().toString();
                if(!id.isEmpty()){
                    dataBean bean=new dataBean();
                    bean.setId((long) Integer.parseInt(id));
                    bean.setJson("w2");
                    daoManager.delete(bean);
                }
            }
        });

        findViewById(R.id.btn_insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=etId.getText().toString();
                String json=etJson.getText().toString();
                if(!json.isEmpty()){
                    daoManager.insert((long) Integer.parseInt(id),json);
                }
            }
        });
        findViewById(R.id.btn_query_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<dataBean> mBeens=daoManager.queryAll();
                String json="";
                for (dataBean bean:mBeens){
                    json=json+"id:"+bean.getId()+" data:"+bean.getJson()+"\n";
                }
                tvShow.setText(json);
            }
        });
    }

}
