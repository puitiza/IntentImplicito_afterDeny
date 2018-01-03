package pe.anthony.intentimplicito_afterdeny;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {

    private ImageButton imgBtn_Phone;
    private final static int  ALLOW_CALL = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgBtn_Phone = (ImageButton) findViewById(R.id.ImgBtn_phone);
        imgBtn_Phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:25858135"));
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.CALL_PHONE)){
                        //YA LO CANCELE Y SOLICITO NUEVAMENTE LOS PERMISOS
                         new SweetAlertDialog(MainActivity.this,SweetAlertDialog.WARNING_TYPE)
                                 .setTitleText("prro Atencion!")
                                 .setContentText("Debes otorgar los permisos de llamada si quieres llamar ")
                                 .setConfirmText("Permitir")
                                 .setCancelText("Cancelar prro")
                                 .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                     @Override
                                     public void onClick(SweetAlertDialog sweetAlertDialog) {
                                         sweetAlertDialog.cancel();
                                     }
                                 })
                                 .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                     @Override
                                     public void onClick(SweetAlertDialog sweetAlertDialog) {
                                         sweetAlertDialog.cancel();
                                         ActivityCompat.requestPermissions(MainActivity.this,
                                                 new String[]{Manifest.permission.CALL_PHONE},ALLOW_CALL);
                                     }
                                 }).show();
                    }else{//PRIMERA VEZ
                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{Manifest.permission.CALL_PHONE},ALLOW_CALL);
                    }

                }else{

                    startActivity(intent);
                }
               /* if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(MainActivity.this,"no hay permiso",Toast.LENGTH_LONG).show();
                    requestPermision();
                }else {
                    startActivity(intent);
                }*/
            }
        });
    }

    public void requestPermision(){
        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);
    }
}
