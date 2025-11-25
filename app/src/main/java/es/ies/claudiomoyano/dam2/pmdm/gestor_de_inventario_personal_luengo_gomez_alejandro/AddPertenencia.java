package es.ies.claudiomoyano.dam2.pmdm.gestor_de_inventario_personal_luengo_gomez_alejandro;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.util.Calendar;

public class AddPertenencia extends AppCompatActivity {
    EditText etNombre, etCategoria, etDimensiones, etUnidades, etPeso, etValor;
    CheckBox checkBoxFragil;
    Button btnFecha, btnGuardar;
    LocalDate fechaSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pertenencia);

        // Como de costumbre inicio realizando las referencias
        etNombre = findViewById(R.id.etNombre);
        etCategoria = findViewById(R.id.etCategoria);
        etUnidades = findViewById(R.id.etUnidades);
        etPeso = findViewById(R.id.etPeso);
        etDimensiones = findViewById(R.id.etDimensiones);
        etValor = findViewById(R.id.etValor);
        checkBoxFragil = findViewById(R.id.checkBoxFragil);
        btnFecha = findViewById(R.id.btnFecha); // Hacer clic en el botón abre un DatePicker
        btnGuardar = findViewById(R.id.btnGuardar);

        // Invoco la función mostrarDatePicker() al pulsar el botón para que el usuario pueda elegir la fecha
        btnFecha.setOnClickListener(v-> mostrarDatePicker());

        // Invoco la función guardarPertenencia()
        btnGuardar.setOnClickListener(v-> guardarPertenencia());
    }

    public void mostrarDatePicker(){
        Calendar c = Calendar.getInstance();
        int anio = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH);
        int dia = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {
                    fechaSeleccionada = LocalDate.of(year, month + 1, dayOfMonth);
                    btnFecha.setText("Fecha: " + fechaSeleccionada.toString());
                },
                anio, mes, dia
        );
        dialog.show();
    }

    private void guardarPertenencia(){
        // Validaciones de los datos
        if(etNombre.getText().toString().isEmpty()){
            Toast.makeText(this, "Introduzca el nombre de la pertenencia", Toast.LENGTH_SHORT).show();
        }

        if(etCategoria.getText().toString().isEmpty()){
            Toast.makeText(this, "Introduzca la categoría de la pertenencia", Toast.LENGTH_SHORT).show();
        }

        if(etUnidades.getText().toString().isEmpty()){
            Toast.makeText(this, "Introduzca las unidades que dispone de esa pertenencia", Toast.LENGTH_SHORT).show();
        }

        if(etPeso.getText().toString().isEmpty()){
            Toast.makeText(this, "Introduzca el peso de la pertenencia (por unidad y en kg o lb)", Toast.LENGTH_SHORT).show();
        }

        // Una vez comprobados los datos, los obtengo y los devuelvo al MainActivity con un Intent
        String nombre = etNombre.getText().toString();
        String categoria = etCategoria.getText().toString();
        String dimensiones = etDimensiones.getText().toString();

        int unidades = Integer.parseInt(etUnidades.getText().toString());
        double peso = Double.parseDouble(etPeso.getText().toString());
        // Valor era voluntario, por defecto le pongo un 0
        double valor = 0;
        if(!etValor.getText().toString().isEmpty()){
            valor = Double.parseDouble(etValor.getText().toString());
        }

        boolean fragil = checkBoxFragil.isChecked();

        // Instancio una pertenencia
        Pertenencia p;

        // Si no se ha seleccionado fecha y valor uso el constructor simple
        if(valor == 0 || fechaSeleccionada == null){
            p = new Pertenencia(nombre, categoria, unidades, peso, dimensiones, fragil);
        }else {
            p = new Pertenencia(nombre, categoria, unidades, peso, dimensiones, fragil, valor, fechaSeleccionada);
        }

        // Comunico con el MainActivity y le devuelvo la info
        Intent resultIntent = new Intent();
        resultIntent.putExtra("pertenencia", p);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}