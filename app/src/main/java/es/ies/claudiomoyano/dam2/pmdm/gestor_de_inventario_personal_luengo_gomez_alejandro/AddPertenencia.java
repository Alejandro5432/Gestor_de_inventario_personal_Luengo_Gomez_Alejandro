package es.ies.claudiomoyano.dam2.pmdm.gestor_de_inventario_personal_luengo_gomez_alejandro;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.util.Calendar;

public class AddPertenencia extends AppCompatActivity {
    EditText editTextNombre, editTextCategoria, editTextDimensiones, editTextUnidades, editTextPeso, editTextValor;
    CheckBox cbFragil;
    TextView textViewFecha;
    LocalDate fechaSeleccionada;
    /**
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pertenencia);

    tvFechSeleccionada = findViewById(R.id.tvFechaSeleccionada);

    // Muestro un DatePicker cuando se haga clic en el TextView y almaceno ahí la fecha elegida
        tvFechSeleccionada.setOnClickListener(v-> mostrarDatePicker());
}

private void mostrarDatePicker(){

    final Calendar calendario = Calendar.getInstance();
    anio = calendario.get(Calendar.YEAR);
    mes = calendario.get(Calendar.MONTH);
    dia = calendario.get(Calendar.DAY_OF_MONTH);

    DatePickerDialog datePickerDialog = new DatePickerDialog(this,
            (view, year, month, dayOfMonth) -> {
                // Actualizo el TextView con la fecha elegida
                String fecha = dayOfMonth+"/"+(month+1)+"/"+year;
                tvFechSeleccionada.setText(fecha);

            /* Esto se podría usar para guardar la fecha en una variable
            anio = year;
            mes = month;
            dia = dayOfMonth;/

            }, anio, mes, dia);
**/
    // Por último lo muestro:
   // datePickerDialog.show();
}
