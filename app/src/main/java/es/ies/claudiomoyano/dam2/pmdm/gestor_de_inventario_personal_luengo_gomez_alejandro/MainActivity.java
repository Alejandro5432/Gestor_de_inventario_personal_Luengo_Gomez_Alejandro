package es.ies.claudiomoyano.dam2.pmdm.gestor_de_inventario_personal_luengo_gomez_alejandro;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton fabAniadirPertenencia;
    private PertenenciaAdapter adapter;
    private List<Pertenencia> listaPertenencias = new ArrayList<>();

    private int posicionEditando = -1;

    // Este es el lanzador del Intent de AddPertenencia
    private final ActivityResultLauncher<Intent> launcherAniadir =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    (ActivityResult result) -> {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            Pertenencia nueva = (Pertenencia) result.getData().getSerializableExtra("pertenencia");
                            if (nueva != null) {
                                listaPertenencias.add(nueva);
                                adapter.notifyItemInserted(listaPertenencias.size() - 1);
                            }
                        }
                    });
    // Y este es el lanzador del Intent de EditPertenencia
    private final ActivityResultLauncher<Intent> launcherEditar =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    (ActivityResult result) -> {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {

                            Pertenencia editada =
                                    (Pertenencia) result.getData().getSerializableExtra("pertenencia");
                            int posicion =
                                    result.getData().getIntExtra("posicion", -1);

                            if (editada != null && posicion != -1) {
                                listaPertenencias.set(posicion, editada);
                                adapter.notifyItemChanged(posicion);
                            }
                        }
                    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        fabAniadirPertenencia = findViewById(R.id.fabAgregar);
        recyclerView = findViewById(R.id.pertenencias_card);
        adapter = new PertenenciaAdapter(this, listaPertenencias);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Al hacer clic en el floatingActionButton se abre AddPertenencia
        fabAniadirPertenencia.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddPertenencia.class);
            launcherAniadir.launch(intent);
        });

        // Eventos al clicar de editar y eliminar, funcionan a partir de la interfaz
        adapter.setOnItemClickListener(new PertenenciaAdapter.OnItemClickListener() {
            @Override
            public void onEditar(Pertenencia p, int position) {
                posicionEditando = position;

                Intent intent = new Intent(MainActivity.this, EditPertenencia.class);
                intent.putExtra("pertenencia", p);
                intent.putExtra("posicion", position); // Importante decirle la posición
                launcherEditar.launch(intent);
            }

            @Override
            public void onEliminar(Pertenencia p, int position) {
                confirmarEliminacion(p, position);
            }
        });
    }

    // Saca un mensaje por pantalla preguntando al usuario si quiere borrar la pertenencia
    // y si escoge que sí, lo borra.
    private void confirmarEliminacion(Pertenencia pertenencia, int posicion) {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar")
                .setMessage("¿Quieres eliminar \"" + pertenencia.getNombrePertencia() + "\"?")
                .setPositiveButton("Sí", (dialog, which) -> {
                    listaPertenencias.remove(posicion);
                    adapter.notifyItemRemoved(posicion);
                })
                .setNegativeButton("No", null)
                .show();
    }
}