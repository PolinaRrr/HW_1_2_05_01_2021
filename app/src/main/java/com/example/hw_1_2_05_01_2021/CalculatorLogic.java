package com.example.hw_1_2_05_01_2021;

import android.content.Intent;

public class CalculatorLogic {
    private double firstArg;
    private double secondArg;

    StringBuilder inputString = new StringBuilder();
    private int actionSelected;

    private State state;

    public void reset() {
        state = State.firstArgInput;
        inputString.setLength(0);
    }

    //создаем перечисление из состояний со статическим доступом
    private enum State {
        firstArgInput,
        operationSelected,
        secondArgInput,
        resultShow
    }

    //конструктор для инициализации полей
    public CalculatorLogic() {
        state = State.firstArgInput;
    }

    //метод обработки нажатия цифровой кнопки
    public void onNumberPressed(int buttonId) {
        if (state.equals(State.resultShow)) {
            state = State.firstArgInput;
            inputString.setLength(0);
        }
        if (state.equals(State.operationSelected)) {
            state = State.secondArgInput;
            inputString.setLength(0);
        }

        switch (buttonId) {
            case R.id.button_bt_0:
                if (inputString.length() != 0) {
                    inputString.append("0");
                }
                break;
            case R.id.button_bt_1:
                inputString.append("1");
                break;
            case R.id.button_bt_2:
                inputString.append("2");
                break;
            case R.id.button_bt_3:
                inputString.append("3");
                break;
            case R.id.button_bt_4:
                inputString.append("4");
                break;
            case R.id.button_bt_5:
                inputString.append("5");
                break;
            case R.id.button_bt_6:
                inputString.append("6");
                break;
            case R.id.button_bt_7:
                inputString.append("7");
                break;
            case R.id.button_bt_8:
                inputString.append("8");
                break;
            case R.id.button_bt_9:
                inputString.append("9");
                break;

        }

    }

    //метод обработки нажатия кнопки действия
    public void onActionPressed(int actionId) {
        if (actionId == R.id.button_result && state.equals(State.secondArgInput)) {
            secondArg = Integer.parseInt(inputString.toString());
            state = State.resultShow;
            inputString.setLength(0);

            switch (actionSelected) {
                case R.id.button_addition:
                    inputString.append(firstArg + secondArg);
                    break;
                case R.id.button_division:
                    inputString.append(firstArg / secondArg);
                    break;
                case R.id.button_multiplication:
                    inputString.append(firstArg * secondArg);
                    break;
                case R.id.button_subtracting:
                    inputString.append(firstArg - secondArg);
                    break;
               
            }
        } else if (inputString.length() > 0 && state.equals(State.firstArgInput)) {
            firstArg = Integer.parseInt(inputString.toString());
            state = State.operationSelected;
            actionSelected = actionId;
            switch (actionId) {
                case R.id.button_addition:
                    actionSelected = R.id.button_addition;
                    break;
                case R.id.button_division:
                    actionSelected = R.id.button_division;
                    break;
                case R.id.button_multiplication:
                    actionSelected = R.id.button_multiplication;
                    break;
                case R.id.button_subtracting:
                    actionSelected = R.id.button_subtracting;
                    break;
                case R.id.button_reset:
                    actionSelected = R.id.button_reset;
                    break;
                case R.id.button_result:
                    actionSelected = R.id.button_result;
                    break;
            }
        }

    }

    //метод формирования строки для отображения в окне результата
    public String getText() {
        StringBuilder string = new StringBuilder();
        switch (state) {
            default:
                return inputString.toString();

            case resultShow:
                return string.append(firstArg).append(' ')
                        .append(getOperationChar())
                        .append(' ')
                        .append(secondArg)
                        .append(" = ")
                        .append(inputString.toString())
                        .toString();

            case secondArgInput:
                return string.append(firstArg).append(' ')
                        .append(getOperationChar())
                        .append(' ')
                        .append(inputString)
                        .toString();

            case operationSelected:
                return string.append(firstArg).append(' ')
                        .append(getOperationChar())
                        .toString();
        }
    }

    //метод получения символа действия для вывода в окно результата
    private char getOperationChar() {
        switch (actionSelected) {
            case R.id.button_addition:
                return '+';

            case R.id.button_division:
            default:
                return '/';

            case R.id.button_multiplication:
                return '*';

            case R.id.button_subtracting:
                return '-';

        }
    }
}


