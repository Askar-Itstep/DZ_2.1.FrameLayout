package com.example.framelayout;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.PersistableBundle;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import java.security.cert.Extension;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "===MainActivity===";
    private GridLayout gridLayoutRoot;

    private CompoundButton rbRed;
    private  CompoundButton  rbGreen;
    private  CompoundButton  rbBlue;
    /**  * Ссылка на виджет, которому будет меняться цвет  * фона, в зависимости от выбранной радиокнопки  */
    //или чекбокса
    private ViewGroup clColor;

    private final static String KEY_COLOR = "keyColor";
    private static Integer sumColor ;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "Menu is active!");
        int id = item.getItemId();
        switch (id){
            case R.id.dz_2_1:
                this.setContentView(R.layout.activity_main);
                return true;
            case  R.id.dz_2_2:
                this.setContentView(this.gridLayoutRoot);
                return  true;
            case R.id.dz_2_3:
                this.setContentView(R.layout.second_activity);

                this.rbRed  = (RadioButton) this. findViewById(R.id.rbRed);
                this.rbGreen = (RadioButton) this. findViewById(R.id.rbGreen);
                this.rbBlue  = (RadioButton) this. findViewById(R.id.rbBlue);
                this.clColor = (ConstraintLayout) this. findViewById(R.id.clColor);

                //--Назначение обработчика события смены состояния
                // --для радиокнопок------------------------------
                MyCheckedChangeListener myCCL = new MyCheckedChangeListener();
                this.rbRed.setOnCheckedChangeListener(myCCL);
                this.rbGreen.setOnCheckedChangeListener(myCCL);
                this.rbBlue.setOnCheckedChangeListener(myCCL);
                return true;
            case R.id.dz_2_4: Log.d(TAG, "Jeckie Chan commit!");
                this.setContentView(R.layout.third_activity);
                this.rbRed  = (CheckBox) this. findViewById(R.id.cbRed);
                this.rbGreen = (CheckBox) this. findViewById(R.id.cbGreen);
                this.rbBlue  = (CheckBox) this. findViewById(R.id.cbBlue);
                this.clColor = (LinearLayout) this. findViewById(R.id.llColor);

                //--Назначение обработчика события смены состояния
                // --для checkbox------------------------------
                MyCheckedChangeListener myCCL2 = new MyCheckedChangeListener();
                this.rbRed.setOnCheckedChangeListener(myCCL2);
                this.rbGreen.setOnCheckedChangeListener(myCCL2);
                this.rbBlue.setOnCheckedChangeListener(myCCL2);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "method Create");
        //установ. исх. цвета (для ДЗ 2.3)
        if(savedInstanceState != null)
            sumColor = savedInstanceState.getInt(MainActivity.KEY_COLOR);
        else
            sumColor = 0;

        //Далее по ДЗ 2.1------------------------------------------------------------------------------
        //1) - корнев. GridLayout
        gridLayoutRoot = new GridLayout(this);
        gridLayoutRoot.setOrientation(GridLayout.VERTICAL);
        gridLayoutRoot.setRowCount(2);
        GridLayout.LayoutParams layoutParamsRoot = new GridLayout.LayoutParams();
        layoutParamsRoot.width = GridLayout.LayoutParams.MATCH_PARENT;
        layoutParamsRoot.height = GridLayout.LayoutParams.MATCH_PARENT;
        layoutParamsRoot.setMargins(10,10,10,10);
        gridLayoutRoot.setLayoutParams(layoutParamsRoot);

//=================================================================================
        //1.1) -FrameLayout1
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setBackgroundColor(this.getColor(R.color.colorTopArea));
//        FrameLayout.LayoutParams frameParams =                                            //только с h = 1024
//                new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 512);   //а как на др. экране??????
//        frameLayout.setLayoutParams(frameParams);

        GridLayout.LayoutParams layoutParamsFrame = new GridLayout.LayoutParams();

        GridLayout.LayoutParams param= new GridLayout.LayoutParams(GridLayout.spec( 0,1f),
                GridLayout.spec(0,1f));

        frameLayout.setLayoutParams(param);
//---------------------------------------------------------------------------------------

        //1.1.1) GrigLayout1
        GridLayout gridLayout1 = new GridLayout(this);
        gridLayout1.setRowCount(2);
        gridLayout1.setColumnCount(2);
        FrameLayout.LayoutParams gridLayoutParams =
                 new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        gridLayoutParams.gravity = Gravity.CENTER;
        gridLayout1.setLayoutParams(gridLayoutParams);
        //кнопки
        for(int i = 0; i < 4; i++){
            Button button = new Button(this);
            button.setText(String.valueOf(i+1));
            gridLayout1.addView(button);
        }

        frameLayout.addView(gridLayout1);
        gridLayoutRoot.addView(frameLayout);
//=========================================================================================
        //1.2) - framLayout2
        FrameLayout frameLayout2 = new FrameLayout(this);
        frameLayout2.setBackgroundColor(this.getColor(R.color.colorBottomArea));

        GridLayout.LayoutParams layoutParamsFrame2 = new GridLayout.LayoutParams();
        GridLayout.LayoutParams param2= new GridLayout.LayoutParams(GridLayout.spec( 1,1f),
                GridLayout.spec(0,1f));
        frameLayout2.setLayoutParams(param2);
//--------------------------------------------------------------------------
        //1.1.2) GrigLayout2
        GridLayout gridLayout2 = new GridLayout(this);
        gridLayout2.setRowCount(2);
        gridLayout2.setColumnCount(2);
        gridLayout2.setLayoutParams(gridLayoutParams);
        //кнопки
        for(int i = 0; i < 4; i++){
            Button button = new Button(this);
            button.setText(String.valueOf(i+4));
            gridLayout2.addView(button);
        }

        frameLayout2.addView(gridLayout2);
        gridLayoutRoot.addView(frameLayout2);

        this.setContentView(gridLayoutRoot);
    }

    //обработ.checkBox (+ radioButton)
    //--Inner Classes--------------------------------
         class MyCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (buttonView instanceof RadioButton) {
                        {
                            if (buttonView.getId() == MainActivity.this.rbRed.getId())
                                MainActivity.this.clColor.setBackgroundColor(Color.RED);
                            else if (buttonView.getId() == MainActivity.this.rbGreen.getId())
                                MainActivity.this.clColor.setBackgroundColor(Color.GREEN);
                            else if (buttonView.getId() == MainActivity.this.rbBlue.getId())
                                MainActivity.this.clColor.setBackgroundColor(Color.BLUE);
                        }
                    } else {
                        if (buttonView.getId() == MainActivity.this.rbRed.getId()) {
                            Log.d(TAG, "redRgb=" + Color.RED);
                            sumColor += Color.RED;
                        }
                        if (buttonView.getId() == MainActivity.this.rbGreen.getId())
                            sumColor += Color.GREEN;

                        if (buttonView.getId() == MainActivity.this.rbBlue.getId())
                            sumColor += Color.BLUE;
//
                        MainActivity.this.clColor.setBackgroundColor((int) sumColor);
                    }
                }
                else if(!isChecked && !(buttonView instanceof RadioButton)){
                    if (buttonView.getId() == MainActivity.this.rbRed.getId())
                        sumColor -= Color.RED;

                    if (buttonView.getId() == MainActivity.this.rbGreen.getId())
                        sumColor -= Color.GREEN;

                    if (buttonView.getId() == MainActivity.this.rbBlue.getId())
                        sumColor -= Color.BLUE;

                    MainActivity.this.clColor.setBackgroundColor((int) sumColor);
                }

            }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt("keyColor", sumColor);
    }
}
