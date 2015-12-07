package com.antonsazonov.prj_01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button0, buttonRavno, buttonClear, buttonP, buttonM, buttonU, buttonD;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        textView = (TextView) findViewById(R.id.textView);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        buttonRavno = (Button) findViewById(R.id.buttonRavno);
        buttonClear = (Button) findViewById(R.id.buttonClear);
        buttonP = (Button) findViewById(R.id.buttonP);
        buttonM = (Button) findViewById(R.id.buttonM);
        buttonU = (Button) findViewById(R.id.buttonU);
        buttonD = (Button) findViewById(R.id.buttonD);
        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        buttonP.setOnClickListener(this);
        buttonM.setOnClickListener(this);
        buttonU.setOnClickListener(this);
        buttonD.setOnClickListener(this);
        buttonClear.setOnClickListener(this);
        buttonRavno.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String strTV;
        switch (v.getId()) {
            case R.id.buttonRavno:
                execute();
                break;
            case R.id.buttonClear:
                textView.setText("");
                if (false) {
                    strTV = textView.getText().toString();
                    if (strTV.length() <= 1) {
                        textView.setText("");
                    } else {
                        if (!(strTV.charAt(strTV.length() - 2) >= '0' && strTV.charAt(strTV.length() - 2) <= '9')) {
                            textView.setText(strTV.substring(0, strTV.length() - 2));
                        } else {
                            textView.setText(strTV.substring(0, strTV.length() - 1));
                        }
                    }
                }
                break;
            case R.id.buttonP:
                strTV = textView.getText().toString();
                if (strTV.length() >= 1) {
                    if ((strTV.charAt(strTV.length() - 1) >= '0' && strTV.charAt(strTV.length() - 1) <= '9')) {
                        strTV += "+";
                        textView.setText(strTV);
                    }
                }
                break;
            case R.id.buttonM:
                strTV = textView.getText().toString();
                if (strTV.length() >= 0) {
                    if (strTV.length() >= 1) {
                        if ((strTV.charAt(strTV.length() - 1) >= '0' && strTV.charAt(strTV.length() - 1) <= '9')
                                || strTV.charAt(strTV.length() - 1) == '*'
                                || strTV.charAt(strTV.length() - 1) == '/'
                                || strTV.charAt(strTV.length() - 1) <= '+') {
                            strTV += "-";
                            textView.setText(strTV);
                        }
                    } else {
                        textView.setText("-");
                    }
                }
                break;
            case R.id.buttonU:
                strTV = textView.getText().toString();
                if (strTV.length() >= 1) {
                    if ((strTV.charAt(strTV.length() - 1) >= '0' && strTV.charAt(strTV.length() - 1) <= '9')) {
                        strTV += "*";
                        textView.setText(strTV);
                    }
                }
                break;
            case R.id.buttonD:
                strTV = textView.getText().toString();
                if (strTV.length() >= 1) {
                    if ((strTV.charAt(strTV.length() - 1) >= '0' && strTV.charAt(strTV.length() - 1) <= '9')) {
                        strTV += "/";
                        textView.setText(strTV);
                    }
                }
                break;
            case R.id.button0:
                strTV = textView.getText().toString();
                textView.setText(strTV + "0");
                break;
            case R.id.button1:
                strTV = textView.getText().toString();
                textView.setText(strTV + "1");
                break;
            case R.id.button2:
                strTV = textView.getText().toString();
                textView.setText(strTV + "2");
                break;
            case R.id.button3:
                strTV = textView.getText().toString();
                textView.setText(strTV + "3");
                break;
            case R.id.button4:
                strTV = textView.getText().toString();
                textView.setText(strTV + "4");
                break;
            case R.id.button5:
                strTV = textView.getText().toString();
                textView.setText(strTV + "5");
                break;
            case R.id.button6:
                strTV = textView.getText().toString();
                textView.setText(strTV + "6");
                break;
            case R.id.button7:
                strTV = textView.getText().toString();
                textView.setText(strTV + "7");
                break;
            case R.id.button8:
                strTV = textView.getText().toString();
                textView.setText(strTV + "8");
                break;
            case R.id.button9:
                strTV = textView.getText().toString();
                textView.setText(strTV + "9");
                break;
        }
    }

    void execute() {
        String str = textView.getText().toString();
        while (!(str.charAt(str.length() - 1) >= '0' && str.charAt(str.length() - 1) <= '9')) {
            str = str.substring(0, str.length() - 1);
        }
        while (str.indexOf("*") != -1 || str.indexOf("/") != -1 || str.indexOf("+") != -1 || str.indexOf("-", 1) != -1) {

            int indexOperation = 0;
            if (str.indexOf("*") != -1) {
                indexOperation = 1;
            } else {
                if (str.indexOf("/") != -1) {
                    indexOperation = 2;
                } else {
                    if (str.indexOf("+") != -1) {
                        indexOperation = 3;
                    } else {
                        if (str.indexOf("-", 1) != -1) {
                            indexOperation = 4;
                        } else {
                            indexOperation = 0;

                        }
                    }
                }
            }
            if (indexOperation != 0) {
                int operation = -1;
                switch (indexOperation) {
                    case 1:
                        operation = str.indexOf("*");
                        break;
                    case 2:
                        operation = str.indexOf("/");
                        break;
                    case 3:
                        operation = str.indexOf("+");
                        break;
                    case 4:
                        operation = str.indexOf("-", 1);
                        break;
                }
                int pos = -1;
                String leftFullV = "";
                String rightFullV = "";
                String leftV = "";
                String rightV = "";
                for (int i = 0; i < operation; i++) {
                    leftFullV += (str.charAt(i) + "");
                }
                for (int i = leftFullV.length() - 1; i >= 0; i--) {
                    if (leftFullV.charAt(i) >= '0' && leftFullV.charAt(i) <= '9') {
                        leftV += (leftFullV.charAt(i) + "");
                    } else {
                        pos = i;
                        i = -1;
                    }
                }
                if (pos != -1 && leftFullV.charAt(pos) == '-') {
                    if (pos - 1 >= 0) {
                        if (leftFullV.charAt(pos - 1) >= '0' && leftFullV.charAt(pos - 1) <= '9') {
                            pos++;
                        } else {
                            leftV += leftFullV.charAt(pos) + "";
                        }
                    } else {
                        leftV += leftFullV.charAt(pos) + "";
                    }
                } else {
                    pos++;
                }
                leftV = new StringBuilder(leftV).reverse().toString();
                leftFullV = leftFullV.substring(0, Math.max(0, pos));
                for (int i = operation + 1; i < str.length(); i++) {
                    if ((str.charAt(i) == '-' && operation + 1 == i) || str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                        rightV += (str.charAt(i) + "");
                    } else {
                        for (int j = i; j < str.length(); j++) {
                            rightFullV += (str.charAt(j) + "");
                        }
                        i = str.length();
                    }
                }
                long lValue = Long.parseLong(leftV);
                long rValue = Long.parseLong(rightV);
                long result = -1;
                switch (indexOperation) {
                    case 1:
                        result = lValue * rValue;
                        break;
                    case 2:
                        result = lValue / rValue;
                        break;
                    case 3:
                        result = lValue + rValue;
                        break;
                    case 4:
                        result = lValue - rValue;
                        break;
                }
                str = leftFullV + result + rightFullV;
                while (str.indexOf("--") != -1) {
                    str = str.replaceFirst("--", "");
                }
                textView.setText(str);
            }
        }
    }
}
