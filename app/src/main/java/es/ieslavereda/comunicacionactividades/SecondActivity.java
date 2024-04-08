package es.ieslavereda.comunicacionactividades;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    private TextView comunica;
    private Button aceptar;
    private Button cancelar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

        Bundle extras = getIntent().getExtras();
        String nombre = extras.getString("nombre");
        if (nombre==null)
            nombre = "sin nombre";

        comunica = findViewById(R.id.comunica);
        aceptar = findViewById(R.id.aceptar);
        cancelar = findViewById(R.id.cancelar);

        comunica.setText("Hola " + nombre + ". ¿Aceptas las condiciones?");

        aceptar.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("resultado", "Aceptado");
            setResult(RESULT_OK,intent);
            finish();
        });

        cancelar.setOnClickListener( v -> {
            Intent intent = new Intent();
            intent.putExtra("resultado", "Rechazado");
            setResult(RESULT_CANCELED,intent);
            finish();
        });

    }

}
