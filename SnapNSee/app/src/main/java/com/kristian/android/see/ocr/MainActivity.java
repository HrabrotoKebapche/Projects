package com.kristian.android.see.ocr;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.Text;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    private static final String LOG = "Text API";
    private static final int PHOTO_REQ = 10;
    private TextView OcrScan;
    private TextView OcrScan2;
    private Uri Uri;
    private TextRecognizer TR;
    private static final int WRITE_PERM = 20;
    private static final String SAVE_URI = "uri";
    private static final String SAVE_RESULT = "result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        OcrScan = (TextView) findViewById(R.id.results);
        OcrScan2 = (TextView) findViewById(R.id.results2);
        if (savedInstanceState != null) {
            Uri = Uri.parse(savedInstanceState.getString(SAVE_URI));
            OcrScan.setText(savedInstanceState.getString(SAVE_RESULT));
            OcrScan2.setText(savedInstanceState.getString(SAVE_RESULT));
        }
        TR = new TextRecognizer.Builder(getApplicationContext()).build();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(MainActivity.this, new
                        String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_PERM);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case WRITE_PERM:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    takePicture();
                } else {
                    Toast.makeText(MainActivity.this, "Permission Denied!", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQ && resultCode == RESULT_OK) {
            ScanIntentStart();
            try {
                Bitmap bitmap = bitmapDecode(this, Uri);
                if (TR.isOperational() && bitmap != null) {
                    Frame frame = new Frame.Builder().setBitmap(bitmap).build();
                    SparseArray<TextBlock> textBlocks = TR.detect(frame);
                    String blocks = "";
                    String lines = "";
                    String words = "";
                    for (int index = 0; index < textBlocks.size(); index++) {
                        //extract scanned text blocks here
                        TextBlock tBlock = textBlocks.valueAt(index);
                        blocks = blocks + tBlock.getValue() + "\n" + "\n";
                        for (Text line : tBlock.getComponents()) {
                            //extract scanned text lines here
                            lines = lines + line.getValue() + "\n";
                            for (Text element : line.getComponents()) {
                                //extract scanned text words here
                                words = words + element.getValue() + ", ";
                            }
                        }
                    }
                    if (textBlocks.size() == 0) {
                        OcrScan.setText("Scan Error: Found nothing to scan");
                    } else {

                        OcrScan.setText(OcrScan.getText() + lines + "\n");
                        OcrScan.setText(OcrScan.getText() + "---------" + "\n");


                        String bg = Translate((String) OcrScan.getText());
                        OcrScan2.setText(OcrScan2.getText());
                        OcrScan2.setText(OcrScan2.getText() + bg);

                    }
                } else {
                    OcrScan.setText("Could not set up the recognizer!");
                }
            } catch (Exception e) {
//                Toast.makeText(this, "Failed to load Image", Toast.LENGTH_SHORT)
//                        .show();
//                Log.e(LOG, e.toString());
            }
        }
    }

    private void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photo = new File(Environment.getExternalStorageDirectory(), "picture.jpg");
        Uri = FileProvider.getUriForFile(MainActivity.this,
                BuildConfig.APPLICATION_ID + ".provider", photo);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri);
        startActivityForResult(intent, PHOTO_REQ);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (Uri != null) {
            outState.putString(SAVE_URI, Uri.toString());
            outState.putString(SAVE_RESULT, OcrScan.getText().toString());
        }
        super.onSaveInstanceState(outState);
    }

    private void ScanIntentStart() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        mediaScanIntent.setData(Uri);
        this.sendBroadcast(mediaScanIntent);
    }

    private Bitmap bitmapDecode(Context ctx, Uri uri) throws FileNotFoundException {
        int targetW = 600;
        int targetH = 600;
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(ctx.getContentResolver().openInputStream(uri), null, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;

        return BitmapFactory.decodeStream(ctx.getContentResolver()
                .openInputStream(uri), null, bmOptions);
    }

    private String Translate(String eng){
        String full = "";
        String text = "";
        try {

            Resources res = getResources();
            InputStream is = res.openRawResource(R.raw.tords);

            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(is));
            String line2;

            while ((line2 = bufferedReader.readLine()) != null) {
                full += line2 + System.getProperty("line.separator");
            }

            eng = eng.toLowerCase();
            String[] finitto = eng.split(" ");

            for(String s: finitto){

                s = s.replace(".","").replace(",","").replace("-","").replace("?","").replace("!","").replace("(","").replace(")","");

                Scanner scanner = new Scanner(full);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String EnBg[] = line.split(" ", 2);

                    if (EnBg[0].equals(s)) {

                        text += EnBg[1] + "(" + s + ")," + " ";
                    }
                }
                scanner.close();
            }

        }catch (IOException e){
            Toast.makeText(this, "Failed to Input/Output", Toast.LENGTH_SHORT)
                    .show();
            Log.e(LOG, e.toString());
        }
        return text;
    }
}
