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

        // Recupero los datos del MainActivity
        pertenenciaOriginal = (Pertenencia) getIntent().getSerializableExtra("pertenencia");
        posicion = getIntent().getIntExtra("posicion", -1);


    }
}