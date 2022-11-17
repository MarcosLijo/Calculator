package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String operador = "";
    String aux = "";
    String resultadoTotal = "";
    String res = "";
    int r = 0;
    int op1;
    int op2;
    boolean b = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button bEq = findViewById(R.id.buttonEqual);
        bEq.setClickable(false);

    }

    public void escribirOperando(View view) {
        TextView tv = findViewById(R.id.TextViewOperation);
        Button bEq = findViewById(R.id.buttonEqual);
        if (b/*Si viene de un resultado*/) return;
//        if (r != 0) aux = r + aux;
        aux += (String) view.getTag();
        resultadoTotal += (String) view.getTag();
        tv.setText(resultadoTotal);
        bEq.setClickable(true);

        // PRUEBA
//        res += (String) view.getTag();
    }

    public void escribirOperador(View view) {
        TextView tv = findViewById(R.id.TextViewOperation);
        Button b0 = findViewById(R.id.button0);
        Button bEq = findViewById(R.id.buttonEqual);

        if (resultadoTotal.equals("")) return;

        b = false;

        if (operador.equals("")) {
            if (aux.equals("")) {
                op1 = r;
            } else {
                op1 = Integer.parseInt(aux);
            }
            aux = "";
            operador = (String) view.getTag();
            resultadoTotal += operador;
            tv.setText(resultadoTotal);
        }

//        res += (String) view.getTag();

        if (operador.equals("/")) b0.setClickable(false);
        if (operador.equals("R")) b0.setClickable(false);

        if (resultadoTotal.endsWith("+") || resultadoTotal.endsWith("-") || resultadoTotal.endsWith("*") || resultadoTotal.endsWith("/") || resultadoTotal.endsWith("R"))
            bEq.setClickable(false);

    }

    public void resultado(View view) {
        TextView tv = findViewById(R.id.TextViewOperation);
        Button b0 = findViewById(R.id.button0);
        Button bEq = findViewById(R.id.buttonEqual);

//        String[] values = res.split("^[-+*/R]");

//        op1 = Integer.parseInt(values[0]);
//        op2 = Integer.parseInt(values[1]);


        op2 = Integer.parseInt(aux);

        switch (operador) {
            case "+":
                r = op1 + op2;
                break;
            case "-":
                r = op1 - op2;
                break;
            case "*":
                r = op1 * op2;
                break;
            case "/":
                r = op1 / op2;
                break;
            case "R":
                r = op1 % op2;
                break;
            default:
                r = Integer.parseInt(aux);
                break;
        }
        resultadoTotal += " = " + r;
        tv.setText(resultadoTotal);
        if (operador.equals("/")) b0.setClickable(true);
        if (operador.equals("R")) b0.setClickable(true);
        op1 = 0;
        op2 = 0;
        operador = "";
        aux = "";
        b = true;
        bEq.setClickable(false);
    }

    public void clearAll(View view) {
        TextView tv = findViewById(R.id.TextViewOperation);
        operador = "";
        aux = "";
        resultadoTotal = "";
        op1 = 0;
        op2 = 0;
        r = 0;
        tv.setText("0");
    }
}