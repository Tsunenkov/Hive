package com.example.myhive;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Level4 extends AppCompatActivity {

    Dialog dialog;
    Dialog dialogEnd;

    public int numLeft; //Переменная для левой картинки + текст
    public int numRight; //Переменная для правой картинки + текст
    Array array = new Array();
    Random random = new Random(); //Генерация случайных чисел
    public int count = 0; //счетчик правильных ответов


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        TextView text_levels = findViewById(R.id.text_levels);
        text_levels.setText(R.string.level2);//Установка текста

        final ImageView img_left = (ImageView) findViewById(R.id.img_left);
//скругление углов левой картинки
        img_left.setClipToOutline(true);

        final ImageView img_right = (ImageView) findViewById(R.id.img_right);
//скругление углов правой картинки
        img_right.setClipToOutline(true);


        //Путь к левой TextView
        final TextView text_left = findViewById(R.id.text_left);
        //Путь к левой TextView
        final TextView text_right = findViewById(R.id.text_right);

    //Разворот игры на весь экран
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        //Вызов дилогового окна в начале игры
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//скрыть заголовок
        dialog.setContentView(R.layout.previewdialog2);//путь к макету диалогового окна
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//прозрачность
        dialog.setCancelable(false);//нельзя закрыть сиситемной кнопкой "Назад"

        ImageView previewimg = (ImageView)dialog.findViewById(R.id.previewimg);
        previewimg.setImageResource(R.drawable.previewimgtwo);


        //Кнопка которавя закрывает диалоговое окно
        TextView btnclose = (TextView)dialog.findViewById(R.id.btn_close);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                //Вернуться к выбору уровня
                    Intent intent = new Intent(Level4.this, GameLevels.class);//намерение для перехода
                    startActivity(intent);finish();
                }catch (Exception e){
            }
                dialog.dismiss();
            }
        });

        //кнопка "Продолжить"
        Button btncontinue = (Button)dialog.findViewById(R.id.btncontinue);
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
//_________________________________
        //Вызов дилогового окна в конце игры
        dialogEnd = new Dialog(this);
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE);//скрыть заголовок
        dialogEnd.setContentView(R.layout.dialogend);//путь к макету диалогового окна
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//прозрачность
        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT);
        dialogEnd.setCancelable(false);//нельзя закрыть сиситемной кнопкой "Назад"

        //Кнопка которавя закрывает диалоговое окно
        TextView btnclose2 = (TextView)dialogEnd.findViewById(R.id.btn_close);
        btnclose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //Вернуться к выбору уровня
                    Intent intent = new Intent(Level4.this, GameLevels.class);//намерение для перехода
                    startActivity(intent);finish();
                }catch (Exception e){
                }
                dialogEnd.dismiss();
            }
        });

        //кнопка "Продолжить"
        Button btncontinue2 = (Button)dialog.findViewById(R.id.btncontinue);
        btncontinue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level4.this,Level3.class);
                    startActivity(intent);finish();
                }catch (Exception e){

                }



                dialog.dismiss();
            }
        });

        //________________________________
        //кнопка "Назад"

       /*
        Button btn_back = (Button)findViewById(R.id.button_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level1.this, GameLevels.class);
                    startActivity(intent);finish();
                }catch (Exception e){
                }

            }
        });

      */

        //Массив для прогресса игры
        final int[] progress = {
                R.id.point1, R.id.point2,R.id.point3,R.id.point4,R.id.point5,R.id.point6,
        };

        //Подключение анимации
        final Animation a = AnimationUtils.loadAnimation(Level4.this,R.anim.alpha);


        numLeft = random.nextInt(10); // генерация от 0 до 9
        img_left.setImageResource(array.images1[numLeft]);//достаем из массива картинку
        text_left.setText(array.texts1[numLeft]); //Достатем из массива текст

        numRight = random.nextInt(10); // генерация от 0 до 9

        while (numLeft==numRight){
            numRight = random.nextInt(10);

        }

        img_right.setImageResource(array.images1[numRight]);
        text_right.setText(array.texts1[numRight]);

        //Обработка нажатия на левую картинку
        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Условие касания картинки
                if (event.getAction()==MotionEvent.ACTION_DOWN) {
            img_right.setEnabled(false);//Блокировка первой картинки
            if (numLeft>numRight){
            img_left.setImageResource(R.drawable.img_true);
            }else{
                img_left.setImageResource(R.drawable.img_false);
            }
                }else if (event.getAction()==MotionEvent.ACTION_UP){
            if (numLeft>numRight){
                if(count<6){
                    count++;
                }
                //Закрасить прогресс серым
                for (int i=0;i<6;i++){
                    TextView tv = findViewById(progress[i]);
                    tv.setBackgroundResource(R.drawable.style_points);
                }
                //Правильный ответ закрасить зеленым
                for (int i=0; i<count; i++){
                    TextView tv = findViewById(progress[i]);
                    tv.setBackgroundResource(R.drawable.style_points_green);

                }
            } else {
                if (count>0){
                    if (count==1){
                count=0;
                    }
                    else {
                        count=count-2;

                    }
                }
                //Закрасить прогресс серым
                for (int i=0;i<5;i++){
                    TextView tv = findViewById(progress[i]);
                    tv.setBackgroundResource(R.drawable.style_points);
                }
                //Правильный ответ закрасить зеленым
                for (int i=0; i<count; i++){
                    TextView tv = findViewById(progress[i]);
                    tv.setBackgroundResource(R.drawable.style_points_green);

                }

            }

            if (count==6){
                //ВЫХОД ИЗ УРОВНЯ
            }else {
                numLeft = random.nextInt(10); // генерация от 0 до 9
                img_left.setImageResource(array.images1[numLeft]);//достаем из массива картинку
                img_left.startAnimation(a);
                text_left.setText(array.texts1[numLeft]); //Достатем из массива текст

                numRight = random.nextInt(10); // генерация от 0 до 9

                while (numLeft==numRight){
                    numRight = random.nextInt(10);

                }

                img_right.setImageResource(array.images1[numRight]);
                img_right.startAnimation(a);
                text_right.setText(array.texts1[numRight]);
                img_right.setEnabled(true);//Выключаем правую картинку
            }
                }
                return true;
            }
        });



//Обработка нажатия на правую картинку
        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Условие касания картинки
                if (event.getAction()==MotionEvent.ACTION_DOWN) {
                    img_left.setEnabled(false);//Блокировка левой картинки
                    if (numLeft<numRight){
                        img_right.setImageResource(R.drawable.img_true);
                    }else{
                        img_right.setImageResource(R.drawable.img_false);
                    }
                }else if (event.getAction()==MotionEvent.ACTION_UP){
                    if (numLeft<numRight){
                        if(count<6){
                            count++;
                        }
                        //Закрасить прогресс серым
                        for (int i=0;i<6;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Правильный ответ закрасить зеленым
                        for (int i=0; i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);

                        }
                    } else {
                        if (count>0){
                            if (count==1){
                                count=0;
                            }
                            else {
                                count=count-2;

                            }
                        }
                        //Закрасить прогресс серым
                        for (int i=0;i<5;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Правильный ответ закрасить зеленым
                        for (int i=0; i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);

                        }

                    }

                    if (count==6){
                        //ВЫХОД ИЗ УРОВНЯ
                    }else {
                        numLeft = random.nextInt(10); // генерация от 0 до 9
                        img_left.setImageResource(array.images1[numLeft]);//достаем из массива картинку
                        img_left.startAnimation(a);
                        text_left.setText(array.texts1[numLeft]); //Достатем из массива текст

                        numRight = random.nextInt(10); // генерация от 0 до 9

                        while (numLeft==numRight){
                            numRight = random.nextInt(10);

                        }

                        img_right.setImageResource(array.images1[numRight]);
                        img_right.startAnimation(a);
                        text_right.setText(array.texts1[numRight]);
                        img_left.setEnabled(true);//Выключаем левую картинку
                    }
                }
                return true;
            }
        });




    }
    //Cистемная кнопка "назад"
    @Override
    public void onBackPressed(){
        try {
            Intent intent = new Intent(Level4.this, GameLevels.class);
            startActivity(intent);finish();
        }catch (Exception e){
        }
    }


}