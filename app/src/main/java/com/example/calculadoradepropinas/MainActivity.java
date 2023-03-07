package com.example.calculadoradepropinas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    // static - Define algo que se comparte por todos los objetos de la clase
    private static final NumberFormat formatMoneda = NumberFormat.getCurrencyInstance();
    private static final NumberFormat formatPorcentaje = NumberFormat.getPercentInstance();

    private double importe = 0.0;
    private double porcentaje = 0.0;
    private int numPersonas = 1;

    private TextView importeTextView;
    private TextView porcentaTextView;
    private TextView propinaTextView;
    private TextView totalTextView;
    private TextView numPersonasTextView;
    private TextView cuotaTextView;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        importeTextView = findViewById(R.id.importeTextView);
        porcentaTextView = findViewById(R.id.porcentajeTextView);
        propinaTextView = findViewById(R.id.propinaTextView);
        totalTextView = findViewById(R.id.totalTextView);
        numPersonasTextView = findViewById(R.id.numPersonasTextView);
        cuotaTextView = findViewById(R.id.cuotaTextView);

        // Show initial values
        porcentaTextView.setText( formatPorcentaje.format( porcentaje ) );
        propinaTextView.setText( formatMoneda.format( 0.0 ) );
        totalTextView.setText( formatMoneda.format(0.0) );
        cuotaTextView.setText( formatMoneda.format(0.0) );

        // Events
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        EditText importeEditText = findViewById(R.id.importeEditText);
        importeEditText.addTextChangedListener( listenImporteEditText );

        SeekBar porcentajeSeekBar = findViewById(R.id.porcentajeSeekBar);
        porcentajeSeekBar.setOnSeekBarChangeListener( listenPorcentajeSeekBar );

        EditText numPersonasEditText = findViewById(R.id.numPersonasEditText);
        numPersonasEditText.addTextChangedListener( listenNumPersonasEditText );


        // Focus
        importeEditText.requestFocus();

    }

    // Listeners
    private final SeekBar.OnSeekBarChangeListener listenPorcentajeSeekBar = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            porcentaje = progress / 100.0;
            porcentaTextView.setText(formatPorcentaje.format(porcentaje));
            calcular();

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private final TextWatcher listenImporteEditText = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {


        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                importe = Double.parseDouble(s.toString()) / 100.0;
                importeTextView.setText(formatMoneda.format( importe ));


            }catch ( NumberFormatException nfe ){
                importe = 0.0;
                importeTextView.setText("");
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private final TextWatcher listenNumPersonasEditText = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                numPersonas = Integer.parseInt(s.toString());
                numPersonasTextView.setText(s.toString());
            }catch ( NumberFormatException nfe ){
                numPersonasTextView.setText("1");
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void calcular(){
        double propina = porcentaje * importe;
        double total = importe + propina;
        double cuota = total / numPersonas;

        propinaTextView.setText(formatMoneda.format(propina));
        cuotaTextView.setText(formatMoneda.format(cuota));
        totalTextView.setText(formatMoneda.format(total));


    }
}