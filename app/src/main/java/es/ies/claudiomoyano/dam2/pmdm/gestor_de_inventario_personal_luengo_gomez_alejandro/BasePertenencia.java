package es.ies.claudiomoyano.dam2.pmdm.gestor_de_inventario_personal_luengo_gomez_alejandro;

// Es abstracta porque no se usa directamente, se hereda de ella. Inicialmente el código que aquí existe aparecía tanto en
// EditPertenencia como en AddPertenencia, de manera que busqué una forma de dejar un código más limpio y esta clase es el resultado.

import android.app.DatePickerDialog;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.util.Calendar;

public abstract class BasePertenencia extends AppCompatActivity {

    protected EditText etNombre, etCategoria, etUnidades, etPeso, etDimensiones, etValor;
    protected CheckBox cbFragil;
    protected Button btnFecha, btnGuardar;
    protected LocalDate fechaSeleccionada;

    // Las funciones son protected porque están pensadas para ser usadas en otra clase
    protected void inicializarViews(){
        etNombre = findViewById(R.id.etNombre);
        etCategoria = findViewById(R.id.etCategoria);
        etUnidades = findViewById(R.id.etUnidades);
        etPeso = findViewById(R.id.etPeso);
        etDimensiones = findViewById(R.id.etDimensiones);
        etValor = findViewById(R.id.etValor);
        cbFragil = findViewById(R.id.cbFragil);
        btnFecha = findViewById(R.id.btnFecha);
        btnGuardar = findViewById(R.id.btnGuardar);
    }

    protected void inicializarDatePicker(){
        if (btnFecha == null) return;

        btnFecha.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            int anio = c.get(Calendar.YEAR);
            int mes = c.get(Calendar.MONTH);
            int dia = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(
                    this,
                    (view, year, month, dayOfMonth) -> {
                        fechaSeleccionada = LocalDate.of(year, month + 1, dayOfMonth);
                        btnFecha.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    },
                    anio, mes, dia
            );
            dialog.show();
        });
    }

    protected boolean validarDatos(){
        // Validaciones de los datos. Estos son obligatorios, si falla algo devuelve false.
        if(etNombre.getText().toString().isEmpty()){
            Toast.makeText(this, "Introduzca el nombre de la pertenencia", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(etCategoria.getText().toString().isEmpty()){
            Toast.makeText(this, "Introduzca la categoría de la pertenencia.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(etUnidades.getText().toString().isEmpty()){
            Toast.makeText(this, "Introduzca las unidades que dispone de esa pertenencia.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(etPeso.getText().toString().isEmpty()){
            Toast.makeText(this, "Introduzca el peso de la pertenencia (por unidad y en kg o lb).", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(etDimensiones.getText().toString().isEmpty()){
            Toast.makeText(this, "Debe introducir las dimensiones de la pertenencia (largo x ancho x alto).", Toast.LENGTH_SHORT).show();
        }
        // NO hace falta validar si es frágil o no puesto que es siempre está marcada alguna de las opciones

        double valor = 0;
        if(!etValor.getText().toString().isEmpty()){
            valor = Double.parseDouble(etValor.getText().toString());
        }
        // NO hace falta validar la fecha o se pone una correcta, o no se pone, por lo que no hay que comprobar lo que elija el usuario.
        // Si está bien, retorna true
        return true;
    }

    // Creo una instancia de la clase Pertenencia. De esta forma puedo ahorrar código cuando vaya a hacer los Intents
    protected Pertenencia crearDesdeSuClase(){

        String nombre = etNombre.getText().toString();
        String categoria = etCategoria.getText().toString();
        int unidades = Integer.parseInt(etUnidades.getText().toString());
        double peso = Double.parseDouble(etPeso.getText().toString());
        String dimensiones = etDimensiones.getText().toString();
        boolean fragil = cbFragil.isChecked();
        double valor = Double.parseDouble(etValor.getText().toString());

        if(valor == 0 && fechaSeleccionada == null){
            return new Pertenencia(nombre, categoria, unidades, peso, dimensiones, fragil);
        } else if(valor == 0){
            return new Pertenencia(nombre, categoria, unidades, peso, dimensiones, fragil, fechaSeleccionada);
        } else if(fechaSeleccionada == null){
            return new Pertenencia(nombre, categoria, unidades, peso, dimensiones, fragil, valor);
        } else{
            return new Pertenencia(nombre, categoria, unidades, peso, dimensiones, fragil, valor, fechaSeleccionada);
        }
    }

    protected void rellenarCampos(Pertenencia p){
        if(p == null) return;
        etNombre.setText(p.getNombrePertencia());
        etCategoria.setText(p.getCategoria());
        etUnidades.setText(String.valueOf(p.getUnidades()));
        etPeso.setText(String.valueOf(p.getPesoUnidad()));
        etDimensiones.setText(p.getDimensiones());
        cbFragil.setChecked(p.isFragil());

        if (p.getValorUnidad() > 0) {
            etValor.setText(String.valueOf(p.getValorUnidad()));
        } else {
            etValor.setText("");
        }

        if (p.getFechaCompra() != null) {
            fechaSeleccionada = p.getFechaCompra();
            if (btnFecha != null) {
                btnFecha.setText(String.format("Fecha: %02d/%02d/%04d",
                        fechaSeleccionada.getDayOfMonth(),
                        fechaSeleccionada.getMonthValue(),
                        fechaSeleccionada.getYear()));
            }
        } else {
            if (btnFecha != null) btnFecha.setText("Seleccionar fecha");
            fechaSeleccionada = null;
        }
    }
}