package com.soft.brainAnalysis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Preguntas extends AppCompatActivity {

    RadioGroup radioGroupMatematica, radioGroupTiempo,
               radioGroupAltera, radioGroupSensacion,
               radioGroupTacto, radioGroupDistingue,
               radioGroupCalculo, radioGroupTareas,
               radioGroupDibujar, radioGroupDesorientacion,
               radioGroupPerEmociones, radioGroupProPasados,
               radioGroupAlmRecuerdos, radioGroupSonido,
               radioGroupProVision, radioGroupReRecuerdos,
               radioGroupCeguera, radioGroupAlucinaciones,
               radioGroupEnfoque, radioGroupEmociones,
               radioGroupClaridad, radioGroupOlores,
               radioGroupHumor, radioGroupObsesion,
               radioGroupCapacidadIDObjetos, radioGroupAmnesia,
               radioGroupEXLenguaje, radioGroupPatrones,
               radioGroupPalabras;

    RadioButton radioButton;

    double resultParietal[] = new double[10];
    double resultTemporal[] = new double[4];
    double resultOccipital[] = new double[5];
    double resultLimbico[] = new double[6];
    double resultOtraArea[] = new double[5];

    Reglas reglas;
    private Dialog mDialog;
    private Dialog mDialogHistoria;
    TextView textoResultado ,p1,p2,p3,p4,p5,rsp;
    Button cerrar , registrar , cerrarHistoria , guardarHistoria;
    ImageView image;
    EditText edtNombres , edtApellidos , edtDni , edtResultado, edtPorcentaje;
    String diagnostico = "";
    String porcentaje  =  "";
    DatabaseReference mReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mReference              = FirebaseDatabase.getInstance().getReference();
        radioGroupDibujar = findViewById(R.id.radioGroupDibujar);
        radioGroupMatematica = findViewById(R.id.radioGroupMatematica);
        radioGroupTiempo = findViewById(R.id.radioGroupTiempo);
        radioGroupAltera = findViewById(R.id.radioGroupAltera);
        radioGroupSensacion = findViewById(R.id.radioGroupSensacion);
        radioGroupTacto = findViewById(R.id.radioGroupTacto);
        radioGroupDistingue = findViewById(R.id.radioGroupDistingue);
        radioGroupCalculo = findViewById(R.id.radioGroupCalculo);
        radioGroupTareas = findViewById(R.id.radioGroupTareas);
        radioGroupDesorientacion = findViewById(R.id.radioGroupDesorientacion);
        radioGroupPerEmociones = findViewById(R.id.radioGroupPerEmociones);
        radioGroupProPasados = findViewById(R.id.radioGroupProPasados);
        radioGroupAlmRecuerdos = findViewById(R.id.radioGroupAlmRecuerdos);
        radioGroupSonido = findViewById(R.id.radioGroupSonido);
        radioGroupProVision = findViewById(R.id.radioGroupProVision);
        radioGroupReRecuerdos = findViewById(R.id.radioGroupReRecuerdos);
        radioGroupCeguera = findViewById(R.id.radioGroupCeguera);
        radioGroupAlucinaciones = findViewById(R.id.radioGroupAlucinaciones);
        radioGroupEnfoque = findViewById(R.id.radioGroupEnfoque);
        radioGroupEmociones = findViewById(R.id.radioGroupEmociones);
        radioGroupClaridad = findViewById(R.id.radioGroupClaridad);
        radioGroupOlores = findViewById(R.id.radioGroupOlores);
        radioGroupHumor = findViewById(R.id.radioGroupHumor);
        radioGroupObsesion = findViewById(R.id.radioGroupObsesion);
        radioGroupCapacidadIDObjetos = findViewById(R.id.radioGroupCapacidadIDObjetos);
        radioGroupAmnesia = findViewById(R.id.radioGroupAmnesia);
        radioGroupEXLenguaje = findViewById(R.id.radioGroupEXLenguaje);
        radioGroupPatrones = findViewById(R.id.radioGroupPatrones);
        radioGroupPalabras = findViewById(R.id.radioGroupPalabras);



        reglas                  = new Reglas();
        mDialog                 = new Dialog(this);
        mDialog.setContentView(R.layout.dialog_analisis);
        textoResultado          = (TextView)mDialog.findViewById(R.id.txtResultado);
        p1                      = (TextView)mDialog.findViewById(R.id.txtPorcentajeParietal);
        p2                      = (TextView)mDialog.findViewById(R.id.txtPorcentajeTemporal);
        p3                      = (TextView)mDialog.findViewById(R.id.txtPorcentajeOccipital);
        p4                      = (TextView)mDialog.findViewById(R.id.txtPorcentajeFrontal);
        p5                      = (TextView)mDialog.findViewById(R.id.txtPorcentajeOtro);
        rsp                     = (TextView)mDialog.findViewById(R.id.rsp);
        cerrar                  = (Button)mDialog.findViewById(R.id.btnClose);
        registrar               = (Button)mDialog.findViewById(R.id.btnHistoria);
        image                   = (ImageView)mDialog.findViewById(R.id.imageState);
        mDialogHistoria         = new Dialog(this);
        mDialogHistoria.setContentView(R.layout.historia);
        edtNombres              = (EditText)mDialogHistoria.findViewById(R.id.edtHistoriaNombre);
        edtApellidos            = (EditText)mDialogHistoria.findViewById(R.id.edtHistoriaApellidos);
        edtDni                  = (EditText)mDialogHistoria.findViewById(R.id.edtHistoriaDni);
        edtResultado            = (EditText)mDialogHistoria.findViewById(R.id.edtHistoriaResultado);
        edtResultado.setEnabled(false);
        edtPorcentaje           = (EditText)mDialogHistoria.findViewById(R.id.edtHistoriaPorcentaje);
        edtPorcentaje.setEnabled(false);
        cerrarHistoria          = (Button)mDialogHistoria.findViewById(R.id.btnCerrarHistoria);
        guardarHistoria         = (Button)mDialogHistoria.findViewById(R.id.btnGuardar);


        Button buttonApply      = findViewById(R.id.button_apply);
        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioGroupDibujar.getCheckedRadioButtonId() != -1 && radioGroupMatematica.getCheckedRadioButtonId() != -1
                        && radioGroupTiempo.getCheckedRadioButtonId() != -1 && radioGroupAltera.getCheckedRadioButtonId() != -1
                        && radioGroupSensacion.getCheckedRadioButtonId() != -1 && radioGroupTacto.getCheckedRadioButtonId() != -1
                        && radioGroupDistingue.getCheckedRadioButtonId() != -1 && radioGroupCalculo.getCheckedRadioButtonId() != -1
                        && radioGroupTareas.getCheckedRadioButtonId() != -1 && radioGroupDesorientacion.getCheckedRadioButtonId() != -1
                        && radioGroupPerEmociones.getCheckedRadioButtonId() != -1 && radioGroupProPasados.getCheckedRadioButtonId() != -1
                        && radioGroupAlmRecuerdos.getCheckedRadioButtonId() != -1 && radioGroupSonido.getCheckedRadioButtonId() != -1
                        && radioGroupProVision.getCheckedRadioButtonId() != -1 && radioGroupReRecuerdos.getCheckedRadioButtonId() != -1
                        && radioGroupCeguera.getCheckedRadioButtonId() != -1 && radioGroupAlucinaciones.getCheckedRadioButtonId() != -1
                        && radioGroupEnfoque.getCheckedRadioButtonId() != -1 && radioGroupEmociones.getCheckedRadioButtonId() != -1
                        && radioGroupClaridad.getCheckedRadioButtonId() != -1 && radioGroupOlores.getCheckedRadioButtonId() != -1
                        && radioGroupHumor.getCheckedRadioButtonId() != -1 && radioGroupObsesion.getCheckedRadioButtonId() != -1
                        && radioGroupCapacidadIDObjetos.getCheckedRadioButtonId() != -1 && radioGroupAmnesia.getCheckedRadioButtonId() != -1
                        && radioGroupEXLenguaje.getCheckedRadioButtonId() != -1 && radioGroupPatrones.getCheckedRadioButtonId() != -1
                        && radioGroupPalabras.getCheckedRadioButtonId() != -1){

                    limpiar();

                    double totalG1 = 0.0;
                    double totalG2 = 0.0;
                    double totalG3 = 0.0;
                    double totalG4 = 0.0;
                    double totalG5 = 0.0;

                    for (int contador = 0; contador < resultParietal.length; contador++) {
                        totalG1 += resultParietal[contador];
                    }

                    for (int contador = 0; contador < resultTemporal.length; contador++) {
                        totalG2 += resultTemporal[contador];
                    }

                    for (int contador = 0; contador < resultOccipital.length; contador++) {
                        totalG3 += resultOccipital[contador];
                    }

                    for (int contador = 0; contador < resultLimbico.length; contador++) {
                        totalG4 += resultLimbico[contador];
                    }

                    for (int contador = 0; contador < resultOtraArea.length; contador++) {
                        totalG5 += resultOtraArea[contador];
                    }

                    String diagnosysParietal = String.valueOf(totalG1);
                    String diagnosysTemporal = String.valueOf(totalG2);
                    String diagnosysOccipital = String.valueOf(totalG3);
                    String diagnosysFrontal= String.valueOf(totalG4);
                    String diagnosysOtro = String.valueOf(totalG5);

                    p1.setText(diagnosysParietal+"%");
                    p2.setText(diagnosysTemporal+"%");
                    p3.setText(diagnosysOccipital+"%");
                    p4.setText(diagnosysFrontal+"%");
                    p5.setText(diagnosysOtro+"%");

                    if(totalG1 > totalG2 && totalG1 > totalG3 && totalG1 > totalG4 && totalG1 > totalG5){
                        diagnostico = "Parietal";
                        textoResultado.setText("Parietal");
                        porcentaje  = diagnosysParietal+"%";
                        rsp.setText("Daño en el lóbulo : ");
                        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        mDialog.setCancelable(false);
                        mDialog.show();
                        p5.setTextColor(Color.WHITE);
                        p2.setTextColor(Color.WHITE);
                        p3.setTextColor(Color.WHITE);
                        p4.setTextColor(Color.WHITE);
                        p1.setTextColor(Color.RED);
                        image.setImageResource(R.drawable.parietal);
                        cerrar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mDialog.dismiss();
                            }
                        });
                        registrar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showDialogHistoria();
                            }
                        });
                    }
                    else if (totalG2 > totalG1 && totalG2 > totalG3 && totalG2 > totalG4 && totalG2 > totalG5)
                    {
                        diagnostico = "Temporal";
                        textoResultado.setText("Temporal");
                        porcentaje  = diagnosysTemporal+"%";
                        rsp.setText("Daño en el lóbulo : ");
                        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        mDialog.setCancelable(false);
                        mDialog.show();
                        p1.setTextColor(Color.WHITE);
                        p5.setTextColor(Color.WHITE);
                        p3.setTextColor(Color.WHITE);
                        p4.setTextColor(Color.WHITE);
                        p2.setTextColor(Color.RED);
                        image.setImageResource(R.drawable.temporal);
                        cerrar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mDialog.dismiss();
                            }
                        });
                        registrar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showDialogHistoria();
                            }
                        });
                    }
                    else if (totalG3 > totalG1 && totalG3 > totalG2 && totalG3 > totalG4 && totalG3 > totalG5)
                    {
                        diagnostico = "Occipital";
                        textoResultado.setText("Occipital");
                        porcentaje  = diagnosysOccipital+"%";
                        rsp.setText("Daño en el lóbulo : ");
                        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        mDialog.setCancelable(false);
                        mDialog.show();
                        p1.setTextColor(Color.WHITE);
                        p2.setTextColor(Color.WHITE);
                        p5.setTextColor(Color.WHITE);
                        p4.setTextColor(Color.WHITE);
                        p3.setTextColor(Color.RED);
                        image.setImageResource(R.drawable.occipital);
                        cerrar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mDialog.dismiss();
                            }
                        });
                        registrar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showDialogHistoria();
                            }
                        });
                    }

                    else if (totalG4 > totalG1 && totalG4 > totalG2 && totalG4 > totalG3 && totalG4 > totalG5)
                    {

                        diagnostico = "Frontal";
                        textoResultado.setText("Frontal");
                        porcentaje  = diagnosysFrontal+"%";
                        rsp.setText("Daño en el lóbulo : ");
                        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        mDialog.setCancelable(false);
                        mDialog.show();
                        p1.setTextColor(Color.WHITE);
                        p2.setTextColor(Color.WHITE);
                        p3.setTextColor(Color.WHITE);
                        p5.setTextColor(Color.WHITE);
                        p4.setTextColor(Color.RED);
                        image.setImageResource(R.drawable.frontal);
                        cerrar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mDialog.dismiss();
                            }
                        });
                        registrar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showDialogHistoria();
                            }
                        });
                    }

                    else if (totalG5 > totalG1 && totalG5 > totalG2 && totalG5 > totalG3 && totalG5 > totalG4)
                    {
                        diagnostico = "Otra área";
                        rsp.setText("Daño en otra área!");
                        porcentaje  = diagnosysOtro+"%";
                        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        mDialog.setCancelable(false);
                        mDialog.show();
                        p1.setTextColor(Color.WHITE);
                        p2.setTextColor(Color.WHITE);
                        p3.setTextColor(Color.WHITE);
                        p4.setTextColor(Color.WHITE);
                        p5.setTextColor(Color.RED);
                        image.setImageResource(R.drawable.otro);
                        cerrar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mDialog.dismiss();
                            }
                        });
                        registrar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showDialogHistoria();
                            }
                        });
                    }

                    else {
                        diagnostico = "";
                        porcentaje  = "";
                        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        mDialog.setCancelable(false);
                        mDialog.show();
                        p1.setTextColor(Color.WHITE);
                        p2.setTextColor(Color.WHITE);
                        p3.setTextColor(Color.WHITE);
                        p4.setTextColor(Color.WHITE);
                        p5.setTextColor(Color.WHITE);
                        rsp.setText("Revise los resultados!");
                        image.setImageResource(R.drawable.otro);
                        cerrar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mDialog.dismiss();
                            }
                        });
                        registrar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                edtResultado.setEnabled(true);
                                edtPorcentaje.setEnabled(true);
                                showDialogHistoria();
                            }
                        });
                    }
                    
                }
                else {
                    Toast.makeText(Preguntas.this, "Complete todo el formulario!", Toast.LENGTH_SHORT).show();
                }

                
            }
        });

        cleanAllGroupQuestions();
    }

    // GRUPO 1 ------------------------------------------------------------------------------------------------


    public void checkRadioMatematica(View v) {
        int radioId = radioGroupMatematica.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        View radioButtonView = radioGroupMatematica.findViewById(radioId);
        int indice = radioGroupMatematica.indexOfChild(radioButtonView);
        resultParietal[0] = reglas.yesOrNotG1(indice);
    }

    public void checkRadioTiempo(View v) {
        int radioId = radioGroupTiempo.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        View radioButtonView = radioGroupTiempo.findViewById(radioId);
        int indice = radioGroupTiempo.indexOfChild(radioButtonView);
        resultParietal[1] = reglas.yesOrNotG1(indice);
    }


    public void checkRadioAltera(View v){
        int radioId = radioGroupAltera.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        View radioButtonView = radioGroupAltera.findViewById(radioId);
        int indice = radioGroupAltera.indexOfChild(radioButtonView);
        resultParietal[2] = reglas.yesOrNotG1(indice);
    }


    public void checkRadioSensacion(View v){
        int radioId = radioGroupSensacion.getCheckedRadioButtonId();
        View radioButtonView = radioGroupSensacion.findViewById(radioId);
        int indice = radioGroupSensacion.indexOfChild(radioButtonView);
        resultParietal[3] = reglas.yesOrNotG1(indice);
    }


    public void checkRadioTacto(View v){
        int radioId = radioGroupTacto.getCheckedRadioButtonId();
        View radioButtonView = radioGroupTacto.findViewById(radioId);
        int indice = radioGroupTacto.indexOfChild(radioButtonView);
        resultParietal[4] = reglas.yesOrNotG1(indice);
    }

    public void checkRadioDistingue(View v){
        int radioId = radioGroupDistingue.getCheckedRadioButtonId();
        View radioButtonView = radioGroupDistingue.findViewById(radioId);
        int indice = radioGroupDistingue.indexOfChild(radioButtonView);
        resultParietal[5] = reglas.yesOrNotG1(indice);
    }


    public void checkRadioCalculo(View v){
        int radioId = radioGroupCalculo.getCheckedRadioButtonId();
        View radioButtonView = radioGroupCalculo.findViewById(radioId);
        int indice = radioGroupCalculo.indexOfChild(radioButtonView);
        resultParietal[6] = reglas.yesOrNotG1(indice);
    }


    public void checkRadioTareas(View v){
        int radioId = radioGroupTareas.getCheckedRadioButtonId();
        View radioButtonView = radioGroupTareas.findViewById(radioId);
        int indice = radioGroupTareas.indexOfChild(radioButtonView);
        resultParietal[7] = reglas.yesOrNotG1(indice);
    }

    public void checkRadioDibujar(View v) {
        int radioId = radioGroupDibujar.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        View radioButtonView = radioGroupDibujar.findViewById(radioId);
        int indice = radioGroupDibujar.indexOfChild(radioButtonView);
        resultParietal[8] = reglas.yesOrNotG1(indice);
    }

    public void checkRadioDesorientacion(View v){
        int radioId = radioGroupDesorientacion.getCheckedRadioButtonId();
        View radioButtonView = radioGroupDesorientacion.findViewById(radioId);
        int indice = radioGroupDesorientacion.indexOfChild(radioButtonView);
        resultParietal[9] = reglas.yesOrNotG1(indice);
    }


    // GRUPO 2 ------------------------------------------------------------------------------------------------

        public void checkRadioPerEmociones(View v){
        int radioId = radioGroupPerEmociones.getCheckedRadioButtonId();
        View radioButtonView = radioGroupPerEmociones.findViewById(radioId);
        int indice = radioGroupPerEmociones.indexOfChild(radioButtonView);
        resultTemporal[0] = reglas.yesOrNotG2(indice);
    }

    public void checkRadioProPasados(View v){
        int radioId = radioGroupProPasados.getCheckedRadioButtonId();
        View radioButtonView = radioGroupProPasados.findViewById(radioId);
        int indice = radioGroupProPasados.indexOfChild(radioButtonView);
        resultTemporal[1] = reglas.yesOrNotG2(indice);
    }

    public void checkRadioAlmRecuerdos(View v){
        int radioId = radioGroupAlmRecuerdos.getCheckedRadioButtonId();
        View radioButtonView = radioGroupAlmRecuerdos.findViewById(radioId);
        int indice = radioGroupAlmRecuerdos.indexOfChild(radioButtonView);
        resultTemporal[2] = reglas.yesOrNotG2(indice);
        resultLimbico[5]  = reglas.yesOrNotG4(indice);
    }

    public void checkRadioSonido(View v){
        int radioId = radioGroupSonido.getCheckedRadioButtonId();
        View radioButtonView = radioGroupSonido.findViewById(radioId);
        int indice = radioGroupSonido.indexOfChild(radioButtonView);
        resultTemporal[3] = reglas.yesOrNotG2(indice);
    }

    // GRUPO 3 ------------------------------------------------------------------------------------------------------

    public void checkRadioProVision(View v){
        int radioId = radioGroupProVision.getCheckedRadioButtonId();
        View radioButtonView = radioGroupProVision.findViewById(radioId);
        int indice = radioGroupProVision.indexOfChild(radioButtonView);
        resultOccipital[0] = reglas.vision(indice);
    }

    public void checkRadioReRecuerdos(View v){
        int radioId = radioGroupReRecuerdos.getCheckedRadioButtonId();
        View radioButtonView = radioGroupReRecuerdos.findViewById(radioId);
        int indice = radioGroupReRecuerdos.indexOfChild(radioButtonView);
        resultOccipital[1] = reglas.yesOrNotG3(indice);
    }

    public void checkRadioCeguera(View v){
        int radioId = radioGroupCeguera.getCheckedRadioButtonId();
        View radioButtonView = radioGroupCeguera.findViewById(radioId);
        int indice = radioGroupCeguera.indexOfChild(radioButtonView);
        resultOccipital[2] = reglas.yesOrNotG3(indice);
    }

    public void checkRadioAlucinaciones(View v){
        int radioId = radioGroupAlucinaciones.getCheckedRadioButtonId();
        View radioButtonView = radioGroupAlucinaciones.findViewById(radioId);
        int indice = radioGroupAlucinaciones.indexOfChild(radioButtonView);
        resultOccipital[3] = reglas.yesOrNotG3(indice);
    }

    public void checkRadioEnfoque(View v){
        int radioId = radioGroupEnfoque.getCheckedRadioButtonId();
        View radioButtonView = radioGroupEnfoque.findViewById(radioId);
        int indice = radioGroupEnfoque.indexOfChild(radioButtonView);
        resultOccipital[4] = reglas.yesOrNotG3(indice);
    }

    // GRUPO 4 ------------------------------------------------------------------------------------------------------

    public void checkRadioEmociones(View v){
        int radioId = radioGroupEmociones.getCheckedRadioButtonId();
        View radioButtonView = radioGroupEmociones.findViewById(radioId);
        int indice = radioGroupEmociones.indexOfChild(radioButtonView);
        resultLimbico[0] = reglas.yesOrNotG4(indice);
    }

    public void checkRadioClaridad(View v){
        int radioId = radioGroupClaridad.getCheckedRadioButtonId();
        View radioButtonView = radioGroupClaridad.findViewById(radioId);
        int indice = radioGroupClaridad.indexOfChild(radioButtonView);
        resultLimbico[1] = reglas.yesOrNotG4(indice);
    }

    public void checkRadioOlores(View v){
        int radioId = radioGroupOlores.getCheckedRadioButtonId();
        View radioButtonView = radioGroupOlores.findViewById(radioId);
        int indice = radioGroupOlores.indexOfChild(radioButtonView);
        resultLimbico[2] = reglas.yesOrNotG4(indice);
    }

    public void checkRadioHumor(View v){
        int radioId = radioGroupHumor.getCheckedRadioButtonId();
        View radioButtonView = radioGroupHumor.findViewById(radioId);
        int indice = radioGroupHumor.indexOfChild(radioButtonView);
        resultLimbico[3] = reglas.yesOrNotG4(indice);
    }

    public void checkRadioObsesion(View v){
        int radioId = radioGroupObsesion.getCheckedRadioButtonId();
        View radioButtonView = radioGroupObsesion.findViewById(radioId);
        int indice = radioGroupObsesion.indexOfChild(radioButtonView);
        resultLimbico[4] = reglas.yesOrNotG4(indice);
    }

    // GRUPO 5  ------------------------------------------------------------------------------------------------------

    public void checkRadioIDObjetos(View v){
        int radioId = radioGroupCapacidadIDObjetos.getCheckedRadioButtonId();
        View radioButtonView = radioGroupCapacidadIDObjetos.findViewById(radioId);
        int indice = radioGroupCapacidadIDObjetos.indexOfChild(radioButtonView);
        resultOtraArea[0] = reglas.yesOrNotG5(indice);
    }

    public void checkRadioAmnesia(View v){
        int radioId = radioGroupAmnesia.getCheckedRadioButtonId();
        View radioButtonView = radioGroupAmnesia.findViewById(radioId);
        int indice = radioGroupAmnesia.indexOfChild(radioButtonView);
        resultOtraArea[1] = reglas.yesOrNotG5(indice);
    }

    public void checkRadioEXLenguaje(View v){
        int radioId = radioGroupEXLenguaje.getCheckedRadioButtonId();
        View radioButtonView = radioGroupEXLenguaje.findViewById(radioId);
        int indice = radioGroupEXLenguaje.indexOfChild(radioButtonView);
        resultOtraArea[2] = reglas.yesOrNotG5(indice);
    }

    public void checkRadioPatrones(View v){
        int radioId = radioGroupPatrones.getCheckedRadioButtonId();
        View radioButtonView = radioGroupPatrones.findViewById(radioId);
        int indice = radioGroupPatrones.indexOfChild(radioButtonView);
        resultOtraArea[3] = reglas.yesOrNotG5(indice);
    }

    public void checkRadioPalabras(View v){
        int radioId = radioGroupPalabras.getCheckedRadioButtonId();
        View radioButtonView = radioGroupPalabras.findViewById(radioId);
        int indice = radioGroupPalabras.indexOfChild(radioButtonView);
        resultOtraArea[4] = reglas.yesOrNotG5(indice);
    }

    private void cleanAllGroupQuestions(){
        radioGroupDibujar.clearCheck();
        radioGroupMatematica.clearCheck();
        radioGroupTiempo.clearCheck();
        radioGroupAltera.clearCheck();
        radioGroupSensacion.clearCheck();
        radioGroupTacto.clearCheck();
        radioGroupDistingue.clearCheck();
        radioGroupCalculo.clearCheck();
        radioGroupTareas.clearCheck();
        radioGroupDesorientacion.clearCheck();
        radioGroupPerEmociones.clearCheck();
        radioGroupProPasados.clearCheck();
        radioGroupAlmRecuerdos.clearCheck();
        radioGroupSonido.clearCheck();
        radioGroupProVision.clearCheck();
        radioGroupReRecuerdos.clearCheck();
        radioGroupCeguera.clearCheck();
        radioGroupAlucinaciones.clearCheck();
        radioGroupEnfoque.clearCheck();
        radioGroupEmociones.clearCheck();
        radioGroupClaridad.clearCheck();
        radioGroupOlores.clearCheck();
        radioGroupHumor.clearCheck();
        radioGroupObsesion.clearCheck();
        radioGroupCapacidadIDObjetos.clearCheck();
        radioGroupAmnesia.clearCheck();
        radioGroupEXLenguaje.clearCheck();
        radioGroupPatrones.clearCheck();
        radioGroupPalabras.clearCheck();
    }

    private void showDialogHistoria(){
        mDialogHistoria.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialogHistoria.setCancelable(false);
        mDialogHistoria.show();
        edtResultado.setText(diagnostico);
        edtPorcentaje.setText(porcentaje);
        cerrarHistoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialogHistoria.dismiss();
            }
        });
        guardarHistoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarHistoria();
            }
        });
    }

    private void limpiar(){
        textoResultado.setText("");
        p1.setText("");
        p2.setText("");
        p3.setText("");
        p4.setText("");
        p5.setText("");
        rsp.setText("");
    }

    private void registrarHistoria(){

        String nombres = edtNombres.getText().toString();
        String apellidos = edtApellidos.getText().toString();
        String dni = edtDni.getText().toString();
        String porcentaje = edtPorcentaje.getText().toString();
        String resultado = edtResultado.getText().toString();

        if (!nombres.isEmpty() && !apellidos.isEmpty() && !dni.isEmpty() && !porcentaje.isEmpty() && !resultado.isEmpty())

        {
            Map<String,Object> map = new HashMap<>();
            map.put("nombres",nombres);
            map.put("apellidos",apellidos);
            map.put("dni",dni);
            map.put("resultado",resultado);
            map.put("porcentaje",porcentaje);
            mReference.child("Historias").push().setValue(map);
            mDialogHistoria.dismiss();
            mDialog.dismiss();
            Toast.makeText(this, "Registrado!", Toast.LENGTH_SHORT).show();
        }

        else{
            Toast.makeText(this, "Complete los campos!", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onBackPressed() {
        NavUtils.navigateUpFromSameTask(this);
    }

}