package pediasure.app.hn.com.pediasure;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import adapters.ExpandableListAdapter;
import asynctasks.DataWebService;
import asynctasks.XpathUtil;
import entities.CalculatorList;


/**
 * Created by mac on 26/9/15.
 */
public class ActivityEdad extends Activity implements AdapterView.OnItemSelectedListener {


    private Button btnIniciar;
    public static Activity fa;

    private String SspSpinnerEdad;
    private Spinner spSpinnerEdad;

    private Spinner spSpinnerSexo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edad_sexo);
        getActionBar().setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        fa = this;

        loadData();
        clearDataMapCalculator();

        spSpinnerEdad = (Spinner) findViewById(R.id.spinnerEdad);
        spSpinnerSexo = (Spinner) findViewById(R.id.spinnerSexo);
        spSpinnerEdad.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        if(!CalculatorList.nodeListRequerimiento.isEmpty()){
            //spSpinnerEdad.setAdapter(createAdapter(CalculatorList.nodeListRequerimiento));
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_layout,CalculatorList.nodeListRequerimiento);
            spSpinnerEdad.setAdapter(adapter);

            List<String> sexoList = new ArrayList<String>();
            sexoList.add("Niño");
            sexoList.add("Niña");
            ArrayAdapter<String> adapterSexo = new ArrayAdapter<String>(this, R.layout.spinner_layout,sexoList);
            spSpinnerSexo.setAdapter(adapterSexo);
        }


        btnIniciar = (Button) findViewById(R.id.buttonIniciar);
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if("".equals(CalculatorList.edad)){
                    Toast.makeText(getApplicationContext(),
                            "Debe seleccionar una edad antes de continuar",
                            Toast.LENGTH_SHORT).show();
                }else{
                try {
                    CalculatorList.calculatorMap = new HashMap<String, String>();
                    Class ourclass = Class.forName("pediasure.app.hn.com.pediasure.ActivityDesayuno");
                    Intent ourintent = new Intent(ActivityEdad.this, ourclass);
                    startActivity(ourintent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                }
            }
        });



    }


    private void clearDataMapCalculator(){

        CalculatorList.calculatorMap.put(CalculatorConstants.DESAYUNO_PLATO ,"");
        CalculatorList.calculatorMap.put(CalculatorConstants.DESAYUNO_ACOMPAÑANTE ,"");
        CalculatorList.calculatorMap.put(CalculatorConstants.DESAYUNO_BEBIDA ,"");

        CalculatorList.calculatorMap.put(CalculatorConstants.ALMUERZO_PLATO ,"");
        CalculatorList.calculatorMap.put(CalculatorConstants.ALMUERZO_ACOMPAÑANTE ,"");
        CalculatorList.calculatorMap.put(CalculatorConstants.ALMUERZO_BEBIDA ,"");

        CalculatorList.calculatorMap.put(CalculatorConstants.CENA_PLATO ,"");
        CalculatorList.calculatorMap.put(CalculatorConstants.CENA_ACOMPAÑANTE ,"");
        CalculatorList.calculatorMap.put(CalculatorConstants.CENA_BEBIDA ,"");

        CalculatorList.calculatorMap.put(CalculatorConstants.MERIENDA_PLATO ,"");
        CalculatorList.calculatorMap.put(CalculatorConstants.MERIENDA_ACOMPAÑANTE ,"");
        CalculatorList.calculatorMap.put(CalculatorConstants.MERIENDA_BEBIDA ,"");

    }

    private void loadData(){

        SharedPreferences GetPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        if (GetPrefs.contains(CalculatorConstants.XML_CATALOGO) ) {

            String resultTiempoComida=GetPrefs.getString(CalculatorConstants.XML_TIEMPO_COMIDA, "");
            String resultTipoAlimento=GetPrefs.getString(CalculatorConstants.XML_TIPO_ALIMENTO, "");
            String resultRequerimiento=GetPrefs.getString(CalculatorConstants.XML_REQUERIMIENTO, "");
            String resultCatalogo=GetPrefs.getString(CalculatorConstants.XML_CATALOGO, "");

            CalculatorList.nodeListRequerimiento = XpathUtil.getListObjects(resultRequerimiento, 1,"/NewDataSet/" + CalculatorConstants.XML_REQUERIMIENTO);


            CalculatorList.nodeListDesayunoPlato = XpathUtil.getListObjects(resultCatalogo,  3,"/NewDataSet/" + CalculatorConstants.XML_CATALOGO + "[id_tiempo_comida ='1' and id_tipo_alimento = '1']");
            CalculatorList.nodeListDesayunoAcompañante = XpathUtil.getListObjects(resultCatalogo,  3,"/NewDataSet/" + CalculatorConstants.XML_CATALOGO + "[id_tiempo_comida ='1' and id_tipo_alimento = '2']");
            CalculatorList.nodeListDesayunoBebida = XpathUtil.getListObjects(resultCatalogo,  3,"/NewDataSet/" + CalculatorConstants.XML_CATALOGO + "[id_tiempo_comida ='1' and id_tipo_alimento = '3']");


            CalculatorList.nodeListAlmuerzoPlato = XpathUtil.getListObjects(resultCatalogo,  3,"/NewDataSet/" + CalculatorConstants.XML_CATALOGO + "[id_tiempo_comida ='2' and id_tipo_alimento = '1']");
            CalculatorList.nodeListAlmuerzoAcompañante = XpathUtil.getListObjects(resultCatalogo, 3,"/NewDataSet/" + CalculatorConstants.XML_CATALOGO + "[id_tiempo_comida ='2' and id_tipo_alimento = '2']");
            CalculatorList.nodeListAlmuerzoBebida = XpathUtil.getListObjects(resultCatalogo,  3,"/NewDataSet/" + CalculatorConstants.XML_CATALOGO + "[id_tiempo_comida ='2' and id_tipo_alimento = '3']");

            CalculatorList.nodeListCenaPlato = XpathUtil.getListObjects(resultCatalogo,  3,"/NewDataSet/" + CalculatorConstants.XML_CATALOGO + "[id_tiempo_comida ='3' and id_tipo_alimento = '1']");
            CalculatorList.nodeListCenaAcompañante = XpathUtil.getListObjects(resultCatalogo,  3,"/NewDataSet/" + CalculatorConstants.XML_CATALOGO + "[id_tiempo_comida ='3' and id_tipo_alimento = '2']");
            CalculatorList.nodeListCenaBebida = XpathUtil.getListObjects(resultCatalogo,  3,"/NewDataSet/" + CalculatorConstants.XML_CATALOGO + "[id_tiempo_comida ='3' and id_tipo_alimento = '3']");

            CalculatorList.nodeListMeriendaPlato = XpathUtil.getListObjects(resultCatalogo,  3,"/NewDataSet/" + CalculatorConstants.XML_CATALOGO + "[id_tiempo_comida ='4' and id_tipo_alimento = '1']");
            CalculatorList.nodeListMeriendaAcompañante = XpathUtil.getListObjects(resultCatalogo, 3,"/NewDataSet/" + CalculatorConstants.XML_CATALOGO + "[id_tiempo_comida ='4' and id_tipo_alimento = '2']");
            CalculatorList.nodeListMeriendaBebida = XpathUtil.getListObjects(resultCatalogo,  3,"/NewDataSet/" + CalculatorConstants.XML_CATALOGO + "[id_tiempo_comida ='4' and id_tipo_alimento = '3']");


            CalculatorList.mapRequerimientos = XpathUtil.getMapObjects(resultRequerimiento, 1, 2, "/NewDataSet/" + CalculatorConstants.XML_REQUERIMIENTO);
            CalculatorList.mapCatalogo = XpathUtil.getMapObjects(resultCatalogo, 3, 4, "/NewDataSet/" + CalculatorConstants.XML_CATALOGO);

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

        CalculatorList.edad = spSpinnerEdad.getItemAtPosition(arg2).toString();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private ArrayAdapter<String> createAdapter(List<String> planlistGenerico) {

        ArrayAdapter<String> planAdaptergenerico = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, planlistGenerico);
        planAdaptergenerico.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return planAdaptergenerico;

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        new DataWebService(this).execute();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
