package ca.ciccc.calc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private double valOne = Double.NaN;
    private double valTwo;

    // textview 1
    public TextView displayView;

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.num0:
                    System.out.println("0");
                    setNum(0);
                    break;
                case R.id.num1:
                    System.out.println("1");
                    setNum(1);
                    break;
                case R.id.num2:
                    System.out.println("2");
                    break;
                case R.id.num3:
                    System.out.println("3");
                    break;
                case R.id.num4:
                    System.out.println("4");
                    break;
                case R.id.num5:
                    System.out.println("5");
                    break;
                case R.id.num6:
                    System.out.println("6");
                    break;
                case R.id.num7:
                    System.out.println("7");
                    break;
                case R.id.num8:
                    System.out.println("8");
                    break;
                case R.id.num9:
                    System.out.println("9");
                    break;
                case R.id.clear:
                    clearNum();
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

    }

    public void setNum(double num) {
        String temp = Double.toString(getNum());
        displayView.setText(temp + num);
    }

    public double getNum() {
        double displayNum = Double.parseDouble(displayView.getText().toString());
        return displayNum;
    }

    public void clearNum() {
        displayView.setText("0");
    }

}
