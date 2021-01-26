/*
*
*1.	С этого урока будем писать приложение «Калькулятор». Выберите макет для работы с калькулятором. Обоснуйте, почему будете использовать именно этот тип макета.
2.	Сверстайте главный экран калькулятора. На нём должны быть кнопки, обозначающие цифры и знаки действия: «Плюс», «Умножить», «Разделить», «Вычесть» и т. п.
3.	* Добавьте фоновый рисунок для экрана калькулятора. Следите, чтобы рисунок был для общего использования. Ресурсы: PxHere, Pixabay.
*
*  */
package com.example.hw_1_2_05_01_2021;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private CalculatorLogic calculator;
    private TextView text;

    static {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // массив Id  цифровых кнопок
        int[] numberIds = new int[]{
                R.id.button_bt_0,
                R.id.button_bt_1,
                R.id.button_bt_2,
                R.id.button_bt_3,
                R.id.button_bt_4,
                R.id.button_bt_5,
                R.id.button_bt_6,
                R.id.button_bt_7,
                R.id.button_bt_8,
                R.id.button_bt_9,

        };

        //массив Id кнопок действий
        int[] actionIds = new int[]{
                R.id.button_addition,
                R.id.button_division,
                R.id.button_multiplication,
                R.id.button_subtracting,
                R.id.button_result,

        };

        text = findViewById(R.id.textView_print_result);
        calculator = new CalculatorLogic();

        //создаю обработчик события нажатия цифровой кнопки
        View.OnClickListener numberButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.onNumberPressed(v.getId());
                text.setText(calculator.getText());
            }
        };

        // создаю обработчик события нажатия кнопки действия
        View.OnClickListener actionButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.onActionPressed(v.getId());
                text.setText(calculator.getText());
            }
        };


        //создаю обработчик события при нажатии на кнопку сброса
        findViewById(R.id.button_reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.reset();
                text.setText(calculator.getText());
            }
        });

        //прохожу циклом по массиву и для каждой кнопки устанавливаю обработчик
        for (int i = 0; i < numberIds.length; i++) {
            findViewById(numberIds[i]).setOnClickListener(numberButtonClickListener);
        }

        for (int i = 0; i < actionIds.length; i++) {
            findViewById(actionIds[i]).setOnClickListener(actionButtonClickListener);
        }


    }
}