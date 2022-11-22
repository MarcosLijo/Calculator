package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String operacion = "";
    String operador = "";
    int op1;
    int op2;
    int resultado;
    String text2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bEq = findViewById(R.id.buttonEqual);
        bEq.setEnabled(false);

    }

    public void escribirOperando(View view) {
        TextView tv = findViewById(R.id.TextViewOperation);
        TextView tv2 = findViewById(R.id.TextViewResult);
        Button bEq = findViewById(R.id.buttonEqual);
        Button b0 = findViewById(R.id.button0);

        if (resultado != 0) operacion = "";
        operacion += (String) view.getTag();
        tv.setText(operacion);

        if (!operador.equals("")) {
            calcular(tv2);
        } else {
            text2 += (String) view.getTag();
            tv2.setText(text2);
        }


        bEq.setEnabled(true);
        b0.setEnabled(true);
    }

    private void calcular(TextView tv2) {

        String[] values = operacion.split("[-+*/%]+");

        op1 = Integer.parseInt(values[0]);
        op2 = Integer.parseInt(values[1]);

        switch (operador) {
            case "+":
                text2 = String.valueOf(op1 + op2);
                break;
            case "-":
                text2 = String.valueOf(op1 - op2);
                break;
            case "*":
                text2 = String.valueOf(op1 * op2);
                break;
            case "/":
                text2 = String.valueOf(op1 / op2);
                break;
            case "%":
                text2 = String.valueOf(op1 % op2);
                break;
            default:
                break;
        }
        tv2.setText(text2);
    }


    public void escribirOperador(View view) {
        TextView tv = findViewById(R.id.TextViewOperation);
        Button b0 = findViewById(R.id.button0);
        Button bEq = findViewById(R.id.buttonEqual);
        // No se puede pulsar un operador si no hay un operando primero
        if (operacion.equals("")) return;
        if (operacion.startsWith("-")) return;

        resultado = 0;

        if (operador.equals("")) {
            operador = (String) view.getTag();
            operacion += operador;
            tv.setText(operacion);
        }

        // No se puede dividir entre 0
        if (operador.equals("/") || operador.equals("%")) b0.setEnabled(false);
        // No pulsar igual si no se ha completado la operación
        if (operacion.endsWith("+") || operacion.endsWith("-") || operacion.endsWith("*") || operacion.endsWith("/") || operacion.endsWith("%"))
            bEq.setEnabled(false);

    }

    public void escribirResultado(View view) {
        TextView tv = findViewById(R.id.TextViewOperation);
        Button b0 = findViewById(R.id.button0);
        Button bEq = findViewById(R.id.buttonEqual);

        try {
            String[] values = operacion.split("[-+*/%]+");

            op1 = Integer.parseInt(values[0]);
            op2 = Integer.parseInt(values[1]);
        } catch (Exception e) {
            return;
        }


        switch (operador) {
            case "+":
                resultado = op1 + op2;
                break;
            case "-":
                resultado = op1 - op2;
                break;
            case "*":
                resultado = op1 * op2;
                break;
            case "/":
                resultado = op1 / op2;
                break;
            case "%":
                resultado = op1 % op2;
                break;
            default:
//                resultado = Integer.parseInt(aux);
                break;
        }
        operacion += "=" + resultado;

        tv.setText(operacion);
        operacion = String.valueOf(resultado);


        if (operador.equals("/") || operador.equals("%")) b0.setEnabled(true);
        op1 = 0;
        op2 = 0;
        operador = "";
        bEq.setEnabled(false);
    }

    public void clearAll(View view) {
        TextView tv = findViewById(R.id.TextViewOperation);
        TextView tv2 = findViewById(R.id.TextViewResult);
        operacion = "";
        operador = "";
        op1 = 0;
        op2 = 0;
        resultado = 0;
        text2 = "";
        tv.setText("0");
        tv2.setText("0");
    }
}