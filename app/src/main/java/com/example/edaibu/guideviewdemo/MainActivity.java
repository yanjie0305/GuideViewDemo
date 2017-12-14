package com.example.edaibu.guideviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhl.userguideview.UserGuideView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String[] datas = new String[]{"收藏","字体大小","软件设置","换肤"};
    private ImageView back;
    private ImageView top;
    private ImageView icon;
    private GridView gridview;
    private UserGuideView guideView;
    private View tipTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        back = findViewById(R.id.back);
        top = findViewById(R.id.top);
        icon = findViewById(R.id.icon);
        gridview = findViewById(R.id.gridview);
        gridview.setAdapter(new MyAdapter());
        guideView = findViewById(R.id.guideView);
        guideView.setTouchOutsideDismiss(false);
        tipTextView = LayoutInflater.from(this).inflate(R.layout.custom_tipview,null);
        back.setOnClickListener(this);
        top.setOnClickListener(this);
        icon.setOnClickListener(this);
         guideView.setHightLightView(back,top,icon);
         guideView.setOnDismissListener(new UserGuideView.OnDismissListener() {
             @Override
             public void onDismiss(UserGuideView userGuideView) {
                 Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                 startActivity(intent);
             }
         });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.top:
                guideView.setTipView(tipTextView,400,200);
                guideView.setHighLightView(top);
                break;
            case R.id.icon:
                guideView.setTipView(tipTextView,400,200);
                guideView.setHighLightView(icon);
                break;
            case R.id.back:
                guideView.setTipView(tipTextView,400,200);
                guideView.setHighLightView(back);
                break;

        }
    }

    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return datas.length;
        }

        @Override
        public Object getItem(int i) {
            return datas[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if(convertView==null){
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.grid_item,parent,false);
                viewHolder.textView = (TextView) convertView.findViewById(R.id.tx);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.textView.setText(datas[position]);
//            if(position==1){
////                guideView.setTipView(BitmapFactory.decodeResource(getResources(),R.mipmap.sidebar_photo));
//                guideView.setHighLightView(convertView);
//            }
            return convertView;
        }
        private class ViewHolder{
            public TextView textView;
        }
    }
}
