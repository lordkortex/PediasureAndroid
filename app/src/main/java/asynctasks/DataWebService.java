package asynctasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import entities.CalculatorList;
import pediasure.app.hn.com.pediasure.ActivityEdad;
import pediasure.app.hn.com.pediasure.CalculatorConstants;

/**
 * Created by mac on 27/9/15.
 */
public class DataWebService extends AsyncTask<String , Void, String> {
    private Context context;
    private static String SOAP_ACTION1 = "http://tempuri.org/HelloWorld";
    private static String NAMESPACE = "http://tempuri.org/";
    private static String METHOD_NAME1 = "HelloWorld";
    private static String URLWS = "http://www.pediasureapp.work/pediasureService.asmx?wsdl";

    private ProgressDialog Brockerdialog;


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        Brockerdialog = new ProgressDialog(context);
        Brockerdialog.setMessage("Obteniendo Datos ...");
        Brockerdialog.setCancelable(false);
        Brockerdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        Brockerdialog.show();

    }

    public DataWebService(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {


        String xml = "";

        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME1);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER12);
            envelope.setOutputSoapObject(request);
            envelope.dotNet = true;
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URLWS,20000);
            androidHttpTransport.call(SOAP_ACTION1, envelope);
            SoapObject result = (SoapObject)envelope.bodyIn;
            xml=result.getProperty(0).toString();
        } catch (IOException e) {
            xml = "Tiempo de Espera agotado.";//e.getMessage().toString();
        } catch (XmlPullParserException e) {
            xml = "Tiempo de Espera agotado.";//e.getMessage().toString();
        } catch (Exception e) {
            xml = "Tiempo de Espera agotado.";//e.getMessage().toString();
        }


        return xml;
    }

    @Override
    protected void onPostExecute(String xml) {

        if(!xml.contains("xml")){
            Toast.makeText(context, "Fallo en obtencion de datos.", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(context, "Proceso Satisfactorio.", Toast.LENGTH_LONG).show();

            ArrayList listXml = new ArrayList<String>(Arrays.asList(xml.split(";")));
            String resultTiempoComida=listXml.get(0).toString();
            String resultTipoAlimento=listXml.get(1).toString();
            String resultRequerimiento=listXml.get(2).toString();
            String resultCatalogo=listXml.get(3).toString();


            SharedPreferences GetPrefs = PreferenceManager.getDefaultSharedPreferences(this.context);
            SharedPreferences.Editor editor = GetPrefs.edit();

            editor.putString(CalculatorConstants.XML_TIEMPO_COMIDA, resultTiempoComida);
            editor.putString(CalculatorConstants.XML_TIPO_ALIMENTO, resultTipoAlimento);
            editor.putString(CalculatorConstants.XML_REQUERIMIENTO, resultRequerimiento);
            editor.putString(CalculatorConstants.XML_CATALOGO, resultCatalogo);

            editor.commit();
        }

        Brockerdialog.setCancelable(true);
        Brockerdialog.dismiss();

        ActivityEdad.fa.finish();

       Class ourclass = null;
        try {
                ourclass = Class.forName("pediasure.app.hn.com.pediasure.ActivityEdad" );
        } catch (ClassNotFoundException e) {
        }
        Intent ourintent = new Intent(this.context,ourclass);
        this.context.startActivity(ourintent);

    }



}