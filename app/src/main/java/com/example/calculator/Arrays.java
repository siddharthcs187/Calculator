package com.example.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Arrays extends AppCompatActivity {
    TextView workingsTV;
    TextView entriesTv;
    TextView resultsTV;
    String workings = "";
    String formula1 ="";
    String formula2 = "";
    double[] a1 = {0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,};
    double[] a2 = {0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,};
    double[] a3 = {0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,};
    int c1=0;
    int c2=0;
    boolean a1case=true;
    boolean a2case=false;
    Button button;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrays);
        DB = new DBHelper(this);
        initTextViews();
    }
    private void initTextViews() {
        button = (Button)findViewById(R.id.changearray);
        workingsTV = (TextView)findViewById(R.id.workingsTextView);
        resultsTV = (TextView)findViewById(R.id.resultsTextView);
        entriesTv = (TextView) findViewById(R.id.entryTextView);

    }
    private void setWorkings(String givenValue){
        workings = workings + givenValue;
        resultsTV.setText(workings);
    }
    public void sevenOnClick(View view) {
        setWorkings("7");
    }

    public void eightOnClick(View view) {
        setWorkings("8");
    }

    public void nineOnClick(View view) {
        setWorkings("9");
    }

    public void fourOnClick(View view) {
        setWorkings("4");
    }

    public void fiveOnClick(View view) {
        setWorkings("5");
    }

    public void sixOnClick(View view) {
        setWorkings("6");
    }

    public void zeroOnClick(View view) {
        setWorkings("0");
    }

    public void oneOnClick(View view) {
        setWorkings("1");
    }

    public void twoOnClick(View view) {
        setWorkings("2");
    }

    public void threeOnClick(View view) {setWorkings("3");}

    public void pointOnClick(View view) {
        setWorkings(".");
    }

    public void backOnClick(View view){
        int t = workings.length();
        workings = workings.substring(0, (t-1));
        resultsTV.setText(workings);
    }

    public void enterOnClick(View view){
        if(workings=="")
            Toast.makeText(this,"Enter a valid input", Toast.LENGTH_SHORT).show();
        else{
            if (a1case == true) {
                a1[c1] = Double.parseDouble(workings);
                c1++;
                formula1 = formula1 + workings + ", ";
                workingsTV.setText(formula1);
            }
            if (a2case == true) {
                a2[c2] = Double.parseDouble(workings);
                c2++;
                formula2 = formula2 + workings + ", ";
                workingsTV.setText(formula2);
            }
            workings = "";
            resultsTV.setText(workings);
        }
    }

    public void changeOnClick(View view){
        Intent intent =new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void computeOnClick(View view){
        if(c1!=c2){
            Toast.makeText(this,"Length of arrays don't match", Toast.LENGTH_SHORT).show();
            workings="";
            entriesTv.setText("Enter Array 1:");
            workingsTV.setText("");
            a1case=true;
            a2case=false;
            formula1="";
            formula2="";
        }
        else {
            entriesTv.setText("Result:");
            workings="[";
            for(int i=0;i<c1;i++){
                a3[i]=a1[i]*a2[i];
            }
            for(int j = 0; j<c1;j++){
                workings=workings+ Double.toString(a3[j]) +", ";
            }
            workings+="]";
            workingsTV.setText(workings);

            Boolean checkhistorydata = DB.inserthistorydata("["+formula1+"]"+"*["+formula2+"]="+workings );

        }
    }

    public void changearrayOnClick(View view){
            workings="";
            entriesTv.setText("Enter Array 2:");
            workingsTV.setText("");
            a1case=false;
            a2case=true;


    }

    public void clearOnClick(View view){
        workings="";
        entriesTv.setText("Enter Array 1:");
        workingsTV.setText("");
        a1case=true;
        a2case=false;
        formula1="";
        formula2="";

    }



}