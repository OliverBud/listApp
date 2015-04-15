package com.example.obud.listapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.math.BigInteger;
import java.util.ArrayList;


public class MainActivity extends Activity {

    ListView list;
    ArrayAdapter adapter;
    ArrayList <BigInteger> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView)findViewById(R.id.listView);
        itemList = new ArrayList<BigInteger>();
        itemList.add(BigInteger.valueOf(0));
        itemList.add(BigInteger.valueOf(1));
        for (int i = 0; i < 10; i ++){
            itemList.add(i + 2, itemList.get(i).add(itemList.get(i + 1)));
        }


        list.setVerticalScrollBarEnabled(false);



        adapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1, itemList);
        list.setAdapter(adapter);
        list.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                int listSize = itemList.size();
                for (int i = listSize; i < listSize + 10; i ++){

                    itemList.add(i, itemList.get(i - 1).add(itemList.get(i - 2)));


                }
                adapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_reset) {
            AlphaAnimation animation1 = new AlphaAnimation(1f, 0f);
            animation1.setDuration(600);
//            animation1.setStartOffset(5000);
//            animation1.setFillAfter(true);
            animation1.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    itemList.clear();
                    itemList.add(BigInteger.valueOf(0));
                    itemList.add(BigInteger.valueOf(1));
                    for (int i = 0; i < 10; i ++){
                        itemList.add(i + 2, itemList.get(i).add(itemList.get(i + 1)));
                    }
                    adapter.notifyDataSetChanged();

                    AlphaAnimation animation1 = new AlphaAnimation(0f, 1.0f);
                    animation1.setDuration(600);
//                    animation1.setStartOffset(5000);
//                    animation1.setFillAfter(true);
                    list.startAnimation(animation1);

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            list.startAnimation(animation1);

            return true;


        }

        return super.onOptionsItemSelected(item);
    }
}
