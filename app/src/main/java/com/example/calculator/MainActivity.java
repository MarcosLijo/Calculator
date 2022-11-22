package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // Variables
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

        // Botón igual deshabilitado para no poder escribir un igual sin operandos ni operadores
        Button bEq = findViewById(R.id.buttonEqual);
        bEq.setEnabled(false);

    }

    public void escribirOperando(View view) {
        TextView tv = findViewById(R.id.TextViewOperation);
        TextView tv2 = findViewById(R.id.TextViewResult);
        Button bEq = findViewById(R.id.buttonEqual);
        Button b0 = findViewById(R.id.button0);

        // Si el resultado no es 0, la operación empieza de nuevo
        if (resultado != 0) {
            operacion = "";
            resultado = 0;
        }
        // Cogemos el tag y se lo añadimos a la operación
        operacion += (String) view.getTag();
        // Escribimos el texto en el TextView
        tv.setText(operacion);

        // Si hay un operador, calculamos la operación existente en el segundo TextView
        if (!operador.equals("")) {
            calcular(tv2);
            // Si no, cogemos el tag y se lo ponemos al segundo TextView también
        } else {
            text2 += (String) view.getTag();
            tv2.setText(text2);
        }


        // Habilitamos algunos botones que hemos deshabilitado anteriormente
        bEq.setEnabled(true);
        b0.setEnabled(true);
    }

    // Método para calcular la operación existente en el segundo TextView (Es igual a escribirResultado())
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
        text2 = "";
    }


    public void escribirOperador(View view) {
        TextView tv = findViewById(R.id.TextViewOperation);
        Button b0 = findViewById(R.id.button0);
        Button bEq = findViewById(R.id.buttonEqual);
        // No se puede pulsar un operador si no hay un operando primero
        if (operacion.equals("")) return;

        // No puede empezar con un número negativo
        if (operacion.startsWith("-")) return;

        // Ponemos el resultado a 0
        resultado = 0;

        // Si no hay operador se lo asignamos y lo escribimos en el TextView1
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
            // Hacemos un array con los valores de la operación que tenemos
            String[] values = operacion.split("[-+*/%]+");

            // Se los asignamos a sus variables correspondientes
            op1 = Integer.parseInt(values[0]);
            op2 = Integer.parseInt(values[1]);
        } catch (Exception e) {
            return;
        }
        // Switch para calcular el resultado
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
                break;
        }
        // A la operación total le añadimos el resultado y lo escribimos en el TextView1
        operacion += "=" + resultado;
        tv.setText(operacion);
        // Y la operación pasa a tener el valor del resultado obtenido
        operacion = String.valueOf(resultado);


        // Volvemos a habilitar los botones desahabilitados anteriormente
        if (operador.equals("/") || operador.equals("%")) b0.setEnabled(true);

        // Reiniciamos los operandos y el operador
        op1 = 0;
        op2 = 0;
        operador = "";

        // Y deshabilitamos el botón igual
        bEq.setEnabled(false);
    }

    public void clearAll(View view) {
        TextView tv = findViewById(R.id.TextViewOperation);
        TextView tv2 = findViewById(R.id.TextViewResult);

        // Se reinician todas las variables y los TextView correspondientes
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