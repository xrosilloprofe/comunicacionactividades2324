package es.ieslavereda.comunicacionactividades;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText nombre;
    private Button verificarButton;
    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        nombre = findViewById(R.id.nombre);
        verificarButton = findViewById(R.id.verificar);
        resultado = findViewById(R.id.resultado);


        ActivityResultLauncher activityResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode()== Activity.RESULT_OK || result.getResultCode()==Activity.RESULT_CANCELED){
                        Intent data = result.getData();
                        resultado.setText("Resultado " + data.getExtras().getString("resultado"));
                    }
                }
        );

        verificarButton.setOnClickListener(v -> {
            if(nombre.getText().toString().equals(""))
                Toast.makeText(this,"No puede estar vacío el nombre",Toast.LENGTH_SHORT).show();
            else{
                Intent intent = new Intent(this,SecondActivity.class);
                intent.putExtra("nombre",nombre.getText().toString());
                activityResult.launch(intent);
            }
        });
    }
}