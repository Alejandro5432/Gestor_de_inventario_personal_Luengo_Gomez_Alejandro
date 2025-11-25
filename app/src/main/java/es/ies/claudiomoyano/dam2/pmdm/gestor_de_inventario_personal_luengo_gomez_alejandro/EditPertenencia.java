package es.ies.claudiomoyano.dam2.pmdm.gestor_de_inventario_personal_luengo_gomez_alejandro;

import android.content.Intent;
import android.os.Bundle;

public class EditPertenencia extends BasePertenencia {

    private Pertenencia pertenenciaOriginal;
    private int posicion = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pertenencia); // Reutilizo el layout, hacer otro es innecesario

        inicializarViews();
        inicializarDatePicker();

        // Recupero los datos enviados desde el MainActivity
        pertenenciaOriginal = (Pertenencia) getIntent().getSerializableExtra("pertenencia");
        posicion = getIntent().getIntExtra("posicion", -1);

        // Relleno los campos con la pertenencia original, este if está para evitar crasheos
        if(pertenenciaOriginal != null){
            rellenarCampos(pertenenciaOriginal);
        }

        btnGuardar.setText("Guardar cambios");

        btnGuardar.setOnClickListener(v-> {
            if(!validarDatos()) return;

            // Creo la pertenencia actualizada
            Pertenencia pertenenciaEditada = crearDesdeSuClase();

            // Y devuelvo los datos al MainActivity
            Intent result = new Intent();
            result.putExtra("pertenencia", pertenenciaEditada);
            result.putExtra("posicion", posicion);
            setResult(RESULT_OK, result);
            finish();
        });
    }
}