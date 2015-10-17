package pediasure.app.hn.com.pediasure;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import entities.CalculatorList;

/**
 * Created by mac on 26/9/15.
 */
public class ActivityResultado extends Activity {


    private Button btnFinalizar;
    private TextView txtViewResultado;
    private TextView txtViewResultadoTomas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        getActionBar().setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        btnFinalizar = (Button) findViewById(R.id.buttonFinalizarr);
        txtViewResultado = (TextView) findViewById(R.id.textViewResultado);
        txtViewResultadoTomas = (TextView) findViewById(R.id.textViewResultadoTomas);



        double valueDesayunoAcompañante = 0;
        double valueDesayunoBebida = 0;
        double valueAlmuerzoPlato = 0;
        double valueAlmuerzoAcompañante = 0;
        double valueAlmuerzoBebida = 0;
        double valueCenaPlato = 0;
        double valueCenaAcompañante = 0;
        double valueCenaBebida = 0;
        double valueMeriendaPlato = 0;
        double valueMeriendaAcompañante = 0;
        double valueMeriendaBebida = 0;
        double valueDesayunoPlato = 0;


        final String desayunoPlato = CalculatorList.calculatorMap.get(CalculatorConstants.DESAYUNO_PLATO) ;
        final String desayunoAcompañante = CalculatorList.calculatorMap.get(CalculatorConstants.DESAYUNO_ACOMPAÑANTE);
        final String desayunoBebida = CalculatorList.calculatorMap.get(CalculatorConstants.DESAYUNO_BEBIDA);

        final String almuerzoPlato = CalculatorList.calculatorMap.get(CalculatorConstants.ALMUERZO_PLATO);
        final String almuerzoAcompañante = CalculatorList.calculatorMap.get(CalculatorConstants.ALMUERZO_ACOMPAÑANTE);
        final String almuerzoBebida = CalculatorList.calculatorMap.get(CalculatorConstants.ALMUERZO_BEBIDA);

        final String cenaPlato = CalculatorList.calculatorMap.get(CalculatorConstants.CENA_PLATO);
        final String cenaAcompañante = CalculatorList.calculatorMap.get(CalculatorConstants.CENA_ACOMPAÑANTE);
        final String cenaBebida = CalculatorList.calculatorMap.get(CalculatorConstants.CENA_BEBIDA);

        final String meriendaPlato = CalculatorList.calculatorMap.get(CalculatorConstants.MERIENDA_PLATO);
        final String meriendaAcompañante = CalculatorList.calculatorMap.get(CalculatorConstants.MERIENDA_ACOMPAÑANTE);
        final String meriendaBebida = CalculatorList.calculatorMap.get(CalculatorConstants.MERIENDA_BEBIDA);

        /*Log.i("RESULTADOS", "desayunoPlato".concat(desayunoPlato));
        Log.i("RESULTADOS", "desayunoAcompañante".concat(desayunoAcompañante));
        Log.i("RESULTADOS", "desayunoBebida".concat(desayunoBebida));
        Log.i("RESULTADOS", "almuerzoPlato".concat(almuerzoPlato));
        Log.i("RESULTADOS", "almuerzoAcompañante".concat(almuerzoAcompañante));
        Log.i("RESULTADOS", "almuerzoBebida".concat(almuerzoBebida));
        Log.i("RESULTADOS", "cenaPlato".concat(cenaPlato));
        Log.i("RESULTADOS", "cenaAcompañante".concat(cenaAcompañante));
        Log.i("RESULTADOS", "cenaBebida".concat(cenaBebida));
        Log.i("RESULTADOS", "meriendaPlato".concat(meriendaPlato));
        Log.i("RESULTADOS", "meriendaAcompañante".concat(meriendaAcompañante));
        Log.i("RESULTADOS", "meriendaBebida".concat(meriendaBebida));*/



        if( desayunoPlato != null && ! "".equals(desayunoPlato) ){
             valueDesayunoPlato = Double.parseDouble(CalculatorList.mapCatalogo.get(desayunoPlato));
        }
        if( desayunoAcompañante != null && ! "".equals(desayunoAcompañante)){
            valueDesayunoAcompañante = Double.parseDouble(CalculatorList.mapCatalogo.get(desayunoAcompañante));
        }
        if( desayunoBebida != null && ! "".equals(desayunoBebida)){
            valueDesayunoBebida = Double.parseDouble(CalculatorList.mapCatalogo.get(desayunoBebida));
        }
        if( almuerzoPlato != null && ! "".equals(almuerzoPlato)){
          valueAlmuerzoPlato = Double.parseDouble(CalculatorList.mapCatalogo.get(almuerzoPlato));
        }
        if( almuerzoAcompañante != null && ! "".equals(almuerzoAcompañante)){
             valueAlmuerzoAcompañante = Double.parseDouble(CalculatorList.mapCatalogo.get(almuerzoAcompañante));
        }
        if( almuerzoBebida != null && ! "".equals(almuerzoBebida)){
             valueAlmuerzoBebida = Double.parseDouble(CalculatorList.mapCatalogo.get(almuerzoBebida));
        }
        if( cenaPlato != null && ! "".equals(cenaPlato)){
             valueCenaPlato = Double.parseDouble(CalculatorList.mapCatalogo.get(cenaPlato));
        }
        if( cenaAcompañante != null && ! "".equals(cenaAcompañante)){
             valueCenaAcompañante = Double.parseDouble(CalculatorList.mapCatalogo.get(cenaAcompañante));
        }
        if( cenaBebida != null && ! "".equals(cenaBebida)){
              valueCenaBebida = Double.parseDouble(CalculatorList.mapCatalogo.get(cenaBebida));
        }
        if( meriendaPlato != null && ! "".equals(meriendaPlato)){
             valueMeriendaPlato = Double.parseDouble(CalculatorList.mapCatalogo.get(meriendaPlato));
      }
        if( meriendaAcompañante != null && ! "".equals(meriendaAcompañante)){
             valueMeriendaAcompañante = Double.parseDouble(CalculatorList.mapCatalogo.get(CalculatorList.calculatorMap.get(meriendaAcompañante)));
        }
        if( meriendaBebida != null && ! "".equals(meriendaBebida)){
            valueMeriendaAcompañante = Double.parseDouble(CalculatorList.mapCatalogo.get(meriendaBebida));
        }


        double total = valueDesayunoPlato + valueDesayunoAcompañante + valueDesayunoBebida +
                       valueAlmuerzoPlato +valueAlmuerzoAcompañante +valueAlmuerzoBebida +
                       valueCenaPlato + valueCenaAcompañante + valueCenaBebida +
                       valueMeriendaPlato + valueMeriendaAcompañante + valueMeriendaBebida;


        final String requerimientoCalorias = CalculatorList.mapRequerimientos.get(CalculatorList.edad);

        if(requerimientoCalorias != null){

            final double requerimientoCaloriasNumber = Double.parseDouble(requerimientoCalorias);
            final double diferenecia = requerimientoCaloriasNumber - total;
            final double pediasureMeasure = 4.63;
            double difereneciaPediasure = diferenecia / pediasureMeasure;

            if(difereneciaPediasure < 100){
                difereneciaPediasure = 100.00;
            }

            double  difereneciaPediasurePer = Math.round(difereneciaPediasure * 100.0)/100.0;
            txtViewResultado.setText("Equivalente a: " + String.valueOf(difereneciaPediasurePer).concat(" gr."));

            double resultadoTomas = 0;
            int contadorTomas = 0;

            do {
                contadorTomas += 1;
                difereneciaPediasurePer = difereneciaPediasurePer - 50;

            } while(difereneciaPediasurePer >= 50);

            if(difereneciaPediasurePer > 25){
                contadorTomas += 1;
            }

            txtViewResultadoTomas.setText(String.valueOf(contadorTomas).concat(" Tomas."));

        }



        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ActivityDesayuno.fa.finish();
                ActivityAlmuerzo.fa.finish();
                ActivityCena.fa.finish();
                ActivityMerienda.fa.finish();
                ActivityEdad.fa.finish();

                try {
                    Class ourclass = Class.forName("pediasure.app.hn.com.pediasure.ActivityEdad");
                    Intent ourintent = new Intent(ActivityResultado.this, ourclass);
                    startActivity(ourintent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
