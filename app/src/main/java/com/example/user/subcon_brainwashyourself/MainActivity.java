package com.example.user.subcon_brainwashyourself;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity {

    int b = 0;

    int visibleTime;
    int delay;


    private ImageView showimaghe;

    int[] images = {R.drawable.p_0, R.drawable.p_1, R.drawable.p_2, R.drawable.p_3, R.drawable.p_4, R.drawable.p_5}; // Image array


    private WindowManager wm;
    private ImageView overlayedView;

    int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        showimaghe = (ImageView) findViewById(R.id.imageView);


        final BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);


        Spinner spinner = (Spinner) findViewById(R.id.spinner);


        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.names));

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdapter);


        //--------Change picture in spinner------------//

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i == 0) {
                    showimaghe.setImageResource(images[0]);
                }
                if (i == 1) {
                    showimaghe.setImageResource(images[1]);

                }
                if (i == 2) {
                    showimaghe.setImageResource(images[2]);

                }
                if (i == 3) {
                    showimaghe.setImageResource(images[3]);

                }
                if (i == 4) {
                    showimaghe.setImageResource(images[4]);

                }
                if (i == 5) {
                    showimaghe.setImageResource(images[5]);

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        final Intent aboutIntent = new Intent(this, AboutActivity.class);
        final Intent helpIntent = new Intent(this, HelpActivity.class);


        //Window manageer-------------------------//

        wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

        overlayedView = new ImageView(this);
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_TOAST, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, PixelFormat.TRANSLUCENT);

        params.x = 49;
        params.y = 49;


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.action_about:


                        startActivity(aboutIntent);
                        break;


                    case R.id.action_stop:


                        if (b == 0) {


                            Toast.makeText(getApplicationContext(), "Please Tap the Start Icon To Activate", Toast.LENGTH_SHORT).show();


                            // If b==0 Dont do anything on stop


                        } else if (b == 1) {
                            wm.removeView(overlayedView);
                            visibleTime = 0;
                            b = 0;
                            delay = 0;
                            bottomNavigationView.getMenu().getItem(2).setEnabled(true);
                            bottomNavigationView.getMenu().getItem(3).setEnabled(true);
                        }


                        break;


                    case R.id.action_start:


                        b = 1;


                        MainActivity.this.moveTaskToBack(true);

                        item.setEnabled(false);
                        bottomNavigationView.getMenu().getItem(3).setEnabled(false);

                        wm.addView(overlayedView, params);
                        viewImage(8);


                        break;

                    case R.id.action_test:


                        b = 1;
                        MainActivity.this.moveTaskToBack(true);


                        item.setEnabled(false);
                        bottomNavigationView.getMenu().getItem(2).setEnabled(false);

                        wm.addView(overlayedView, params);
                        viewImage(1000);

                        break;


                    case R.id.action_help:

                        startActivity(helpIntent);
                        break;
                }
                return true;
            }
        });


        //Button features to show the image on display-----------------///


//        final Button startBtn=(Button)findViewById(R.id.start_button);
//        final Button stopBtn=(Button)findViewById(R.id.stop_button);
//
//        stopBtn.setEnabled(false);
//
//
//        startBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                startBtn.setEnabled(false);
//                stopBtn.setEnabled(true);
//
//                wm.addView(overlayedView, params);
//
//                final Handler h = new Handler();
//                final int delay = 4000;
//                final int show = 500;//milliseconds
//
//                h.postDelayed(new Runnable(){
//                    public void run(){
//
//
//
//
//                        overlayedView.setImageResource(testimages[count]);
//                        show();
//
//                        count++;
//                        count=count%3;
//                        overlayedView.setVisibility(View.VISIBLE);
//                        h.postDelayed(this, delay);
//
//
//
//
//                    }
//                }, delay);
//
//
//
//
//            }
//        });


//        stopBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//
//
//                startBtn.setEnabled(true);
//                stopBtn.setEnabled(false);
//
//
//
//                wm.removeView(overlayedView);
//
//            }
//        });


    }


    void viewImage(int v) {


        final Handler h = new Handler();
        delay = 4000;//Interval Time between images
        visibleTime = v;


        h.postDelayed(new Runnable() {
            public void run() {


                overlayedView.setImageResource(images[count]);
                show();

                count++;
                count = count % 6;
                overlayedView.setVisibility(View.VISIBLE);
                h.postDelayed(this, delay);


            }
        }, delay);
    }


    void show() {

        final Handler k = new Handler();


        k.postDelayed(new Runnable() {
            public void run() {


                overlayedView.setVisibility(View.INVISIBLE);


            }
        }, visibleTime);


    }


}
