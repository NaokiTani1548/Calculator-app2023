package com.websarva.wings.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    public Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button0,
            plus, minus, multiply, divide, equal,reset;
    int[] counter = new int[20];
    int count = 0;
    int total;
    int[] correctNumber = new int[50];
    int[] Orders = new int[50];
    int orderCount = 0;
    int[] plusOrder = new int[10];
    int[] minusOrder = new int[10];
    int[] multiplyOrder = new int[10];
    int[] divideOrder = new int[10];
    int plusCount = 0;
    int minusCount = 0;
    int multiplyCount = 0;
    int divideCount = 0;
    EditText editText;

    //入力された数字群を計算で使えるように変える


    public MainActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.result);
        editText.setInputType(InputType.TYPE_NUMBER_VARIATION_NORMAL);
        editText.setText("");
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(buttonNumberListener);
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(buttonNumberListener);
        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(buttonNumberListener);
        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(buttonNumberListener);
        button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(buttonNumberListener);
        button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(buttonNumberListener);
        button7 = (Button) findViewById(R.id.button7);
        button7.setOnClickListener(buttonNumberListener);
        button8 = (Button) findViewById(R.id.button8);
        button8.setOnClickListener(buttonNumberListener);
        button9 = (Button) findViewById(R.id.button9);
        button9.setOnClickListener(buttonNumberListener);
        button0 = (Button) findViewById(R.id.button0);
        button0.setOnClickListener(buttonNumberListener);
        plus = (Button) findViewById(R.id.buttonPuls);
        plus.setOnClickListener(buttonNumberListener);
        minus = (Button) findViewById(R.id.buttonMinus);
        minus.setOnClickListener(buttonNumberListener);
        multiply = (Button) findViewById(R.id.buttonMultiply);
        multiply.setOnClickListener(buttonNumberListener);
        divide = (Button) findViewById(R.id.buttonDivide);
        divide.setOnClickListener(buttonNumberListener);
        equal = (Button) findViewById(R.id.buttonEqual);
        equal.setOnClickListener(buttonNumberListener);
        reset = (Button) findViewById(R.id.buttonReset);
        reset.setOnClickListener(buttonNumberListener);

    }

    View.OnClickListener buttonNumberListener = new View.OnClickListener() {
        private void InputNumber(int number) {
            counter[count] = number;
            editText.append(String.valueOf(number));
            count++;
            ButtonVisibility();
        }
        private void ButtonInVisibility(){
            plus.setEnabled(false);
            minus.setEnabled(false);
            divide.setEnabled(false);
            multiply.setEnabled(false);
            equal.setEnabled(false);
        }
        private void ButtonVisibility(){
            plus.setEnabled(true);
            minus.setEnabled(true);
            divide.setEnabled(true);
            multiply.setEnabled(true);
            equal.setEnabled(true);
        }
        private int CreateNumberFromInput() {
            int number = 0;
            for (int i = 0; i < count; i++) {
                number = number * 10 + counter[i];
            }
            count = 0;
            ButtonInVisibility();
            return number;
        }

        @Override
        public void onClick(View view) {
            if ((Button) view == button1) {
                InputNumber(1);
            }
            if ((Button) view == button2) {
                InputNumber(2);
            }
            if ((Button) view == button3) {
                InputNumber(3);
            }
            if ((Button) view == button4) {
                InputNumber(4);
            }
            if ((Button) view == button5) {
                InputNumber(5);
            }
            if ((Button) view == button6) {
                InputNumber(6);
            }
            if ((Button) view == button7) {
                InputNumber(7);
            }
            if ((Button) view == button8) {
                InputNumber(8);
            }
            if ((Button) view == button9) {
                InputNumber(9);
            }
            if ((Button) view == button0) {
                InputNumber(0);
            }
            if ((Button) view == plus) {
                editText.append(plus.getText());
                correctNumber[orderCount] = CreateNumberFromInput();
                Orders[orderCount] = 1;
                orderCount += 1;
            }
            if ((Button) view == minus) {
                editText.append(minus.getText());
                correctNumber[orderCount] = CreateNumberFromInput();
                Orders[orderCount] = 2;
                orderCount += 1;
            }
            if ((Button) view == multiply) {
                editText.append(multiply.getText());
                correctNumber[orderCount] = CreateNumberFromInput();
                Orders[orderCount] = 3;
                orderCount += 1;
            }
            if ((Button) view == divide) {
                editText.append(divide.getText());
                correctNumber[orderCount] = CreateNumberFromInput();
                Orders[orderCount] = 4;
                orderCount += 1;
            }
            if ((Button) view == equal) {
                editText.setText(equal.getText());
                correctNumber[orderCount] = CreateNumberFromInput();
                for (int i = 0; i < orderCount; i++) {
                    if (Orders[i] == 3) {
                        total = correctNumber[i] * correctNumber[i + 1];
                    }
                    if (Orders[i] == 4) {
                        total = correctNumber[i] / correctNumber[i + 1];
                    }
                    if (Orders[i] == 3 || Orders[i] == 4) {
                        correctNumber[i] = total;
                        correctNumber[i + 1] = 0;
                        for (int j = i + 1; j < orderCount; j++) {
                            correctNumber[j] = correctNumber[j + 1];
                            correctNumber[j + 1] = 0;
                        }
                        for (int o = i; o < orderCount - 1; o++) {
                            Orders[o] = Orders[o + 1];
                            Orders[o + 1] = 0;
                        }
                        i -= 1;
                    }
                }


                for (int i = 0; i < orderCount; i++) {
                    if (Orders[i] == 1) {
                        total = correctNumber[i] + correctNumber[i + 1];
                    }
                    if (Orders[i] == 2) {
                        total = correctNumber[i] - correctNumber[i + 1];
                    }
                    if (Orders[i] == 1 || Orders[i] == 2) {
                        correctNumber[i] = total;
                        correctNumber[i + 1] = 0;
                        for (int j = i + 1; j < orderCount; j++) {
                            correctNumber[j] = correctNumber[j + 1];
                            correctNumber[j + 1] = 0;
                        }
                        for (int o = i; o < orderCount - 1; o++) {
                            Orders[o] = Orders[o + 1];
                            Orders[o + 1] = 0;
                        }
                        i -= 1;

                    }
                }
                editText.append(Integer.toString(correctNumber[0]));
            }
            if((Button)view == reset){
                for(int k = 0; k < 50 ; k++){
                    correctNumber[k] = 0;
                    Orders[k] = 0;
                    orderCount = 0;
                }
                editText.setText(">");
            }
        }

        ;
    };
}
