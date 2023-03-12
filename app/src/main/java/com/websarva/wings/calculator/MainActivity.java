package com.websarva.wings.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    private Button button1,button2,button3,button4,button5,button6,button7,button8,button9,button0,
            plus,minus,multiply,divide,equal;
    int[] counter = new int[20];
    int count = 0;     //入力桁数を数える。。
    int[] correctNumber = new int[50];
    int[] plusOrder = new int[10];
    int[] minusOrder = new int[10];
    int[] multiplyOrder = new int[10];
    int[] divideOrder = new int[10];
    int[] Orders = new int[10];
    int orderCount = 0;
    int plusCount = 0;
    int minusCount = 0;
    int multiplyCount = 0;
    int divideCount = 0;
    int moved = 0;
    EditText editText;

    public int NumberCorrect(){ //入力された数字群を計算で使えるように変える
        int correctNumber  = 0;
        int roundCount = count;
        while(count > 0){
            count -= 1;
            int squared = 1;
            for (int i = 0; i < roundCount-count-1;i ++){
                squared = squared * 10;
            }
            correctNumber += counter[count]*squared;

        }
        return correctNumber;
    }

    public MainActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) { //最初の画面
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.result);
        editText.setInputType(InputType.TYPE_NUMBER_VARIATION_NORMAL);
        editText.setText("");
        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(buttonNumberListener);
        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(buttonNumberListener);
        button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(buttonNumberListener);
        button4 = (Button)findViewById(R.id.button4);
        button4.setOnClickListener(buttonNumberListener);
        button5 = (Button)findViewById(R.id.button5);
        button5.setOnClickListener(buttonNumberListener);
        button6 = (Button)findViewById(R.id.button6);
        button6.setOnClickListener(buttonNumberListener);
        button7 = (Button)findViewById(R.id.button7);
        button7.setOnClickListener(buttonNumberListener);
        button8 = (Button)findViewById(R.id.button8);
        button8.setOnClickListener(buttonNumberListener);
        button9 = (Button)findViewById(R.id.button9);
        button9.setOnClickListener(buttonNumberListener);
        button0 = (Button)findViewById(R.id.button0);
        button0.setOnClickListener(buttonNumberListener);
        plus = (Button)findViewById(R.id.buttonPuls);
        plus.setOnClickListener(buttonNumberListener);
        minus = (Button)findViewById(R.id.buttonMinus);
        minus.setOnClickListener(buttonNumberListener);
        multiply = (Button)findViewById(R.id.buttonMultiply);
        multiply.setOnClickListener(buttonNumberListener);
        divide = (Button)findViewById(R.id.buttonDivide);
        divide.setOnClickListener(buttonNumberListener);
        equal = (Button)findViewById(R.id.buttonEqual);
        equal.setOnClickListener(buttonNumberListener);

    }
    View.OnClickListener buttonNumberListener = new View.OnClickListener(){
        @Override
        public void onClick(View view){  //イベントハンドラ
            if((Button)view == button1){
               counter[count]  =  1;
               editText.append(button1.getText());
                count += 1;
            }if((Button)view == button2){
                counter[count]  =  2;
                editText.append(button2.getText());
                count += 1;
            }if((Button)view == button3){
                counter[count]  =  3;
                editText.append(button3.getText());
                count += 1;
            }if((Button)view == button4){
                counter[count]  =  4;
                editText.append(button4.getText());
                count += 1;
            }if((Button)view == button5){
                counter[count]  =  5;
                editText.append(button5.getText());
                count += 1;
            }if((Button)view == button6){
                counter[count]  =  6;
                editText.append(button6.getText());
                count += 1;
            }if((Button)view == button7){
                counter[count]  =  7;
                editText.append(button7.getText());
                count += 1;
            }if((Button)view == button8){
                counter[count]  =  8;
                editText.append(button8.getText());
                count += 1;
            }if((Button)view == button9){
                counter[count]  =  9;
                editText.append(button9.getText());
                count += 1;
            }if((Button)view == button0){
                counter[count]  =  0;
                editText.append(button0.getText());
                count += 1;
            }if((Button)view == plus){
                editText.append(plus.getText());
                correctNumber[orderCount] = NumberCorrect();
                Orders[orderCount] = 1;
                orderCount += 1;
                plusOrder[plusCount] = orderCount;
                plusCount += 1;
            }if((Button)view == minus){
                editText.append(minus.getText());
                correctNumber[orderCount] = NumberCorrect();
                Orders[orderCount] = 2;
                orderCount += 1;
                minusOrder[minusCount] = orderCount;
                minusCount += 1;
            }if((Button)view == multiply){
                editText.append(multiply.getText());
                correctNumber[orderCount] = NumberCorrect();
                Orders[orderCount] = 3;
                orderCount += 1;
                multiplyOrder[multiplyCount] = orderCount;
                multiplyCount += 1;
            }if((Button)view == divide){
                editText.append(divide.getText());
                correctNumber[orderCount] = NumberCorrect();
                Orders[orderCount] = 4;
                orderCount += 1;
                divideOrder[divideCount] = orderCount;
                divideCount += 1;
            }if((Button)view == equal){
                editText.setText(equal.getText());
                correctNumber[orderCount] = NumberCorrect();
                 if(multiplyCount != 0) {
                    int i = 0;
                    while(multiplyCount != 0) {
                        int total = correctNumber[multiplyOrder[i] - 1] * correctNumber[multiplyOrder[i]];
                        correctNumber[multiplyOrder[i] - 1 - moved] = total;
                        correctNumber[multiplyOrder[i] - moved] = 0;
                        for (int j = multiplyOrder[i] - moved; j < orderCount - multiplyOrder[i] + 1; j++) {
                            correctNumber[j] = correctNumber[j + 1];
                            correctNumber[j + 1] = 0;
                        }
                        moved += 1;
                        //editText.append(Integer.toString(correctNumber[0]));
                        //editText.append(Integer.toString(correctNumber[1]));
                        //editText.append(Integer.toString(correctNumber[2]));
                        //editText.append(Integer.toString(correctNumber[3]));
                        multiplyCount -= 1;
                        i += 1;
                    }
                }
                if(divideCount != 0) {
                    int i = 0;
                    while(divideCount != 0) {
                        int total = correctNumber[divideOrder[i] - 1] / correctNumber[divideOrder[i]];
                        correctNumber[divideOrder[i] - 1 - moved] = total;
                        correctNumber[divideOrder[i] - moved] = 0;
                        for (int j = divideOrder[i] - moved; j < orderCount - divideOrder[i] + 1; j++) {
                            correctNumber[j] = correctNumber[j + 1];
                            correctNumber[j + 1] = 0;
                        }
                        moved += 1;
                        divideCount -= 1;
                        i += 1;
                    }
                }
               if(plusCount != 0) {
                    int i = 0;
                    while(plusCount != 0) {
                        int total = correctNumber[0] + correctNumber[1];
                        correctNumber[plusOrder[i] - 1 - moved] = total;
                        correctNumber[plusOrder[i] - moved] = 0;
                       for (int j = plusOrder[i] - moved; j < orderCount - plusOrder[i] + 1; j++) {
                            correctNumber[j] = correctNumber[j + 1];
                            correctNumber[j + 1] = 0;
                        }
                        moved += 1;
                       // editText.append(Integer.toString(correctNumber[0]));
                       // editText.append(Integer.toString(correctNumber[1]));
                       // editText.append(Integer.toString(correctNumber[2]));
                       // editText.append(Integer.toString(correctNumber[3]));
                        plusCount -= 1;
                        i += 1;
                    }
                }
                if(minusCount != 0) {
                    int i = 0;
                    while(minusCount != 0) {
                        int total = correctNumber[0] - correctNumber[1];
                        correctNumber[minusOrder[i] - 1 - moved] = total;
                        correctNumber[minusOrder[i] - moved] = 0;
                        for (int j = minusOrder[i] - moved; j < orderCount - minusOrder[i]; j++) {
                             correctNumber[j] = correctNumber[j + 1];
                            correctNumber[j + 1] = 0;
                        }
                        moved += 1;
                        minusCount -= 1;
                        i += 1;
                    }
                }
                editText.append(Integer.toString(correctNumber[0]));
            }
        }
    };

}