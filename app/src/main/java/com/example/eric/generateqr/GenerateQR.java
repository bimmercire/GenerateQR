package com.example.eric.generateqr;

/***
 * ZXING QR Library
 * mysamplecode.com
 *
 *
 */



import android.graphics.Bitmap;
import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;

public class GenerateQR extends ActionBarActivity {

    private final static String TAG = "GenerateQR";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_qr);

    }

    public void onGenerateQRClick(View view){

        EditText editTextField = (EditText) findViewById(R.id.editField);
        String encodeQRString = editTextField.getText().toString();
        Log.d(TAG, "String to encode: " + encodeQRString);


        // Find screen size
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;
        int smallerDimension = width < height ? width : height;
        smallerDimension = smallerDimension * (3/4);

        //Encode QR

        QRCodeEncoder qrCodeEncoder = new QRCodeEncoder(encodeQRString,null, Contents.Type.TEXT, BarcodeFormat.QR_CODE.toString(), smallerDimension);
        try {
            Bitmap bitmap = qrCodeEncoder.encodeAsBitmap();
            ImageView myImage = (ImageView) findViewById(R.id.QRImage);
            myImage.setImageBitmap(bitmap);

        }catch (WriterException e){
            e.printStackTrace();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_generate_qr, menu);
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
