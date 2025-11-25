package es.ies.claudiomoyano.dam2.pmdm.gestor_de_inventario_personal_luengo_gomez_alejandro;

import android.content.Intent;
import android.os.Bundle;

public class AddPertenencia extends BasePertenencia {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pertenencia);

        inicializarViews();
        inicializarDatePicker();

        btnGuardar.setOnClickListener(v-> {
            if(!validarDatos()) return;

            Pertenencia nuevaPertenencia = crearDesdeSuClase();

            Intent result = new Intent();
            result.putExtra("pertenencia", nuevaPertenencia);
            setResult(RESULT_OK, result);
            finish();
        });
    }
}