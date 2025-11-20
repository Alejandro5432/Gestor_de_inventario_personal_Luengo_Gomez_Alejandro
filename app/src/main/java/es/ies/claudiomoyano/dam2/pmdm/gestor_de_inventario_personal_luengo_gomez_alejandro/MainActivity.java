package es.ies.claudiomoyano.dam2.pmdm.gestor_de_inventario_personal_luengo_gomez_alejandro;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    PertenenciaAdapter adapter;
    List<Pertenencia> listaPertenencias = new ArrayList<>();

    private ActivityResultLauncher<Intent> agregarPertenenciaLauncher;

    TextView tvFechSeleccionada;
    int dia, mes, anio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.pertenencias_card);
        adapter = new PertenenciaAdapter(this, listaPertenencias);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        Button btnAniadir = findViewById(R.id.btnAniadirPertenencia);

        // Registrar el ActivityResultLauncher
        // todo Revisar este código es posible que no esté permitido por el profesor
        agregarPertenenciaLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == RESULT_OK){
                        Intent datos = result.getData();
                        if(datos != null){
                            Pertenencia p = (Pertenencia) datos.getSerializableExtra("pertenencia");

                            listaPertenencias.add(p);
                            adapter.notifyItemInserted(listaPertenencias.size() - 1);
                        }
                    }
                }
        );

        btnAniadir.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, AddPertenencia.class);
            agregarPertenenciaLauncher.launch(intent);
        });
    }
}