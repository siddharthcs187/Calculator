package com.example.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {
    TextView workingsTV;
    TextView resultsTV;
    String workings = "";
    String formula ="";
    String tempFormula = "";
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DB = new DBHelper(this);
        initTextViews();
    }

    private void initTextViews() {
        workingsTV = (TextView)findViewById(R.id.workingsTextView);
        resultsTV = (TextView)findViewById(R.id.resultsTextView);
    }
    private void setWorkings(String givenValue){
        workings = workings + givenValue;
        workingsTV.setText(workings);
    }
    public void equalOnClick(View view) {
        Double result = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        checkForPowerOf();

        try {
            result = (double) engine.eval(formula);
        } catch (ScriptException e) {
            Toast.makeText(this,"Invalid Input", Toast.LENGTH_SHORT).show();
        }
        if(result!=null) {
            resultsTV.setText(String.valueOf(result.doubleValue()));
            Boolean checkhistorydata = DB.inserthistorydata(workings+"="+ result);

        }
        workings= String.valueOf(result);
    }

    private void checkForPowerOf() {
        ArrayList<Integer> indexOfPowers = new ArrayList<>();
        for(int i = 0; i<workings.length();i++){
            if(workings.charAt(i) == '^')
                indexOfPowers.add(i);
        }
            formula = workings;
            tempFormula = workings;
            for(Integer index : indexOfPowers){
                changeFormula(index);
            }
            formula=tempFormula;
        }


    private void changeFormula(Integer index) {
        String numLeft = "";
        String numRight = "";

        for(int i=index+1;i<workings.length();i++){
            if(isNumeric(workings.charAt(i)))
                numRight+=workings.charAt(i);
            else
                break;
        }
        for(int i=index-1;i>=0;i--){


            if(isNumeric(workings.charAt(i)))
                numLeft+=workings.charAt(i);
            else
                break;
        }
        Log.d("myTag", numLeft);
        Log.d("myTag", numRight);

        StringBuilder leftNumberBuilder = new StringBuilder(numLeft);
        numLeft = leftNumberBuilder.reverse().toString();

        Log.d("myTag", numLeft);
        Log.d("myTag", numRight);

        resultsTV.setText(numLeft);

        String orginal = numLeft + "^" + numRight;
        String changed  = "Math.pow("+numLeft+", "+numRight+")";
        tempFormula = tempFormula.replace(orginal, changed);
    }

    private boolean isNumeric(char c){
        return ((c >= '0' && c <= '9') || c == '.');
    }

    public void clearOnClick(View view) {
        workingsTV.setText("");
        workings = "";
        resultsTV.setText("");
        leftBracket=true;
    }

    boolean leftBracket = true;

    public void bracketsOnClick(View view) {
        if(leftBracket) {
            setWorkings("(");
            leftBracket=false;
        }
        else {
            setWorkings(")");
            leftBracket=true;
        }

    }

    public void powerOnClick(View view) {
        setWorkings("^");
    }

    public void divideOnClick(View view) {
        setWorkings("/");
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

    public void multiplyOnClick(View view) {
        setWorkings("*");
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

    public void subtractOnClick(View view) {
        setWorkings("-");
    }

    public void pointOnClick(View view) {
        setWorkings(".");
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

    public void addOnClick(View view) {
        setWorkings("+");
    }

    public void backOnClick(View view){
        int t = workings.length();
        workings = workings.substring(0, (t-1));
        workingsTV.setText(workings);
    }

    public void changeOnClick(View view){
        Intent intent =new Intent(this, Arrays.class);
        startActivity(intent);
    }

    public void historyOnClick(View view){
        Intent intent =new Intent(this, History.class);
        startActivity(intent);
    }

}