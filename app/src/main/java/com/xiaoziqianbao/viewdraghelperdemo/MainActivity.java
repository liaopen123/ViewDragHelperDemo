package com.xiaoziqianbao.viewdraghelperdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        ListView lv = (ListView) findViewById(R.id.lv);



        lv.setAdapter(new MyAdapter());
    }





    public  class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 20;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View inflate = View.inflate(MainActivity.this, R.layout.item_listview, null);
            inflate.findViewById(R.id.view_red).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "打电话", Toast.LENGTH_SHORT).show();
                }
            });
            inflate.findViewById(R.id.view_green).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "发短信", Toast.LENGTH_SHORT).show();
                }
            });
            return inflate;
        }
    }
}
