package ca.ciccc.calc;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {


    private double valOne = Double.NaN;
    private double valTwo;
    private boolean doubleFlag = false;
    private char operator;
    private String prefixNum = "";
    private String lastFunction;

    // Define the variable and gets textview 1
    public TextView displayView;
    public TextView answerView;

    private DecimalFormat decimalFormat;

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.num0:
                    setNum(0);
                    break;
                case R.id.num1:
                    setNum(1);
                    break;
                case R.id.num2:
                    setNum(2);
                    break;
                case R.id.num3:
                    setNum(3);
                    break;
                case R.id.num4:
                    setNum(4);
                    break;
                case R.id.num5:
                    setNum(5);
                    break;
                case R.id.num6:
                    setNum(6);
                    break;
                case R.id.num7:
                    setNum(7);
                    break;
                case R.id.num8:
                    setNum(8);
                    break;
                case R.id.num9:
                    setNum(9);
                    break;
                case R.id.clear:
                    clearNum();
                    break;
                case R.id.point:
                    setDouble();
                    break;
                case R.id.plus:
                    setOperator('+');
                    break;
                case R.id.minus:
                    setOperator('-');
                    break;
                case R.id.multiple:
                    setOperator('*');
                    break;
                case R.id.divide:
                    setOperator('/');
                    break;
                case R.id.equal:
                    calc();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.num0).setOnClickListener(listener);
        findViewById(R.id.num1).setOnClickListener(listener);
        findViewById(R.id.num2).setOnClickListener(listener);
        findViewById(R.id.num3).setOnClickListener(listener);
        findViewById(R.id.num4).setOnClickListener(listener);
        findViewById(R.id.num5).setOnClickListener(listener);
        findViewById(R.id.num6).setOnClickListener(listener);
        findViewById(R.id.num7).setOnClickListener(listener);
        findViewById(R.id.num8).setOnClickListener(listener);
        findViewById(R.id.num9).setOnClickListener(listener);
        findViewById(R.id.plus).setOnClickListener(listener);
        findViewById(R.id.minus).setOnClickListener(listener);
        findViewById(R.id.multiple).setOnClickListener(listener);
        findViewById(R.id.divide).setOnClickListener(listener);
        findViewById(R.id.equal).setOnClickListener(listener);
        findViewById(R.id.point).setOnClickListener(listener);
        findViewById(R.id.percent).setOnClickListener(listener);
        findViewById(R.id.clear).setOnClickListener(listener);

        // 2
        displayView = (TextView) findViewById(R.id.display);
        answerView = (TextView) findViewById(R.id.answer);
        decimalFormat = new DecimalFormat("#.##########");

    }

    public void setNum(int num) {
        String temp = getNum();
        if(temp.equals("0") && num != 0 || lastFunction.equals("setOperator")) {
            displayView.setText(prefixNum + Integer.toString(num));

        }else if(temp.equals("0") && num == 0) {
            displayView.setText(prefixNum + "0");
        }else {
            displayView.setText(prefixNum + temp + num);
        }

        lastFunction = "setNum";
    }

    public String getNum() {
        String displayNum = displayView.getText().toString();
        System.out.println("displayView:"+displayNum);
        if(!prefixNum.equals("")) {
            //displayNum = displayNum.substring(displayNum.indexOf(prefixNum), displayNum.length());
            System.out.println(displayNum.indexOf(prefixNum));
        }
        System.out.println("replaced: "+displayNum);
        return displayNum;
    }

    public void setDouble() {
        if(!doubleFlag) {
            doubleFlag = true;
            String temp = getNum();
            displayView.setText(temp + ".");
        }

        lastFunction = "setDouble";
    }

    public void clearNum() {
        displayView.setText("0");
        answerView.setText("0");
        doubleFlag = false;
        valOne = Double.NaN;
        valTwo = Double.NaN;
        prefixNum = "";

        lastFunction = "clearNum";
    }

    public void setOperator(char operator) {
        boolean sameOperator = false;

        if(this.operator == operator) {
            sameOperator = true;
        }else {
            this.operator = operator;
        }

        //String temp = getNum();
        //displayView.setText(temp +" "+ Character.toString(this.operator) +" ");
        System.out.println("setOp Works");
        System.out.println("valOne: "+valOne);
        if(Double.isNaN(valOne)) {
            valOne = Double.parseDouble(getNum());
            prefixNum = getNum() +" "+ Character.toString(this.operator)+ " ";
        }else if(sameOperator) {
            calc();
        }else {

        }
        doubleFlag = false;
        //displayView.setText("0");
        //System.out.println(valOne);

        lastFunction = "setOperator";
    }

    public void calc() {
        if(valOne != Double.NaN) {
            valTwo = Double.parseDouble(getNum());
            Double answer = 0.0;
            if(operator == '+') {
                answer = valOne + valTwo;
            }else if(operator == '-') {
                answer = valOne - valTwo;
            }else if(operator == '*') {
                answer = valOne * valTwo;
            }else if(operator == '/') {
                answer = valOne / valTwo;
            }
            System.out.println("Answer:"+answer);
            answerView.setText(decimalFormat.format(answer));
            valOne = answer;
            prefixNum = prefixNum + getNum();
        }

        lastFunction = "calc";
    }

    public String lastAction() {
        return lastFunction;
    }

//    public void testClick(View view) {
//        Button number = (Button)view;
//        switch (number.getText().toString()) {
//            case "+":
//                valOne = Double.parseDouble(displayView.getText().toString());
//        }
//    }

}
