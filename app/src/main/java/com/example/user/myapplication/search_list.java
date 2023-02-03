package com.example.user.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.ArrayList;
import java.util.List;

public class search_list extends AppCompatActivity {

    private ListView listView;
    List<value_search> dogs = new ArrayList<>();
    int dataSize;
    public value_search listDog = new value_search();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_list);
        initInstances();

        //Actionbar
        ActionBar actionBar=getSupportActionBar();
        //Set title
        actionBar.setTitle("List");

        //Navigation
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void initInstances() {

        prepareData();

        search_adapter adapter = new search_adapter(search_list.this, listDog);
        listView = (ListView) findViewById(R.id.list_item);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(listViewClickListener);
    }

    private void prepareData() {

        int resId[] =
                {
                        R.drawable.s1,
                        R.drawable.t1,


                };


        String breed[] = {
                "แมวสก็อตติช โฟลด์  ",
                "แมวไทย ",
        };


        String nameEng[]=
                {
                        "Scottish Fold",
                        "Thai Cat",


                };


        String descriptionA1[] =
                {
                        //สก๊อตทิช
                        getString(R.string.nails_b1),
                        //ไทย
                        getString(R.string.nails_b2),

                };

//        String descriptionA2[] =
//                {
//                        //โรคไขข้ออักเสบ
//                        getString(R.string.nails_b1),
//                        //โรคสะเก็ดเงิน
//                        getString(R.string.nails_b2),
//                        //โรคมะเร็ง
//                        getString(R.string.nails_b3),
//                        //โรคเครียด
//                        getString(R.string.nails_b5),
//                        //โรคโลหิตจาง
//                        getString(R.string.nails_b6)
//                };

        String description1[] =
                {
                        //สก๊อตทิช
                        getString(R.string.nails_care1),
                        //ไทย
                        getString(R.string.nails_care2),

                };
//        String description2[] =
//                {
//                        getString(R.string.garden_save1),
//                        getString(R.string.garden_save2),
//                        getString(R.string.garden_save3),
//                        getString(R.string.garden_save4),
//                        getString(R.string.garden_save5),

//                };
        dataSize = resId.length;


        Log.d("khem", "dataSize " + resId.length);
        Log.d("khem", "breed " + resId.length);
        Log.d("khem", "description " + resId.length);



        for (int i = 0; i < dataSize; i++) {
            Log.d("khem", " " + i);
            value_search dog = new value_search(
                    resId[i],
                    breed[i],
                    nameEng[i],
                    descriptionA1[i],
                    description1[i]);
            dogs.add(dog);
        }

        listDog.setDogs(dogs);

        //Log.d("khem",listDog);
    }

    /*************************
     * Listener
     ***************************/

    AdapterView.OnItemClickListener listViewClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            final Intent intent = new Intent(search_list.this,search_listview_detail.class);
            intent.putExtra("resId",listDog.getDogs().get(position).getResId());
            intent.putExtra("breed",listDog.getDogs().get(position).getBreed());
            intent.putExtra("descA1",listDog.getDogs().get(position).getDescriptionA1());
//            intent.putExtra("descA2",listDog.getDogs().get(position).getDescriptionA2());
            intent.putExtra("desc1",listDog.getDogs().get(position).getDescription1());
//            intent.putExtra("desc2",listDog.getDogs().get(position).getDescription2());


            startActivity(intent);
        }
    };

    //Navigetion
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.home:
                    Intent intent = new Intent(search_list.this,MainActivity.class);
                    startActivity(intent);
                    Animatoo.animateSlideRight(search_list.this);
                    finish();
                    return true;
                case R.id.search:
                    //noAction
                    //noAction
                    return true;

//                case R.id.book:
//                    Intent intent2 = new Intent(search_list.this,video_list.class);
//                    startActivity(intent2);
//                    finish();
//                    return true;
            }
            return false;
        }
    };
}
