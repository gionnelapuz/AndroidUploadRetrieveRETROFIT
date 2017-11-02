package com.example.gin.androiduploadretrieveretrofit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gin.androiduploadretrieveretrofit.API.API;
import com.example.gin.androiduploadretrieveretrofit.OBJECT.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class insertActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextAge;

    private Button btnInsertText, btnInsertImage, btnChooseImage;

    private ImageView imageUpload;
    private Bitmap bitmap;

    public static String ROOT_URL = "https://harleylapuz.000webhostapp.com/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextAge = (EditText) findViewById(R.id.editTextAge);

        btnChooseImage = (Button) findViewById(R.id.btnChooseImage);
        btnInsertText = (Button) findViewById(R.id.btnInsertText);
        btnInsertImage = (Button) findViewById(R.id.btnInsertImage);

        imageUpload = (ImageView) findViewById(R.id.imageUpload);

        btnChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), 1);
            }
        });
        btnInsertText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertText();
            }
        });
        btnInsertImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imageUpload.getDrawable() == null){
                    Toast.makeText(insertActivity.this, "PLEASE SELECT AN IMAGE FIRST!", Toast.LENGTH_LONG).show();
                }
                else{
                    insertImage();
                }
            }
        });
    }

    public void insertText(){
        final ProgressDialog inserting = ProgressDialog.show(this, "INSERT TEXT", "Inserting...", false, false);
        inserting.show();

        String name = editTextName.getText().toString().trim();
        String age = editTextAge.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);

        Call<Person> call = api.insertText(name,age);

        call.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                inserting.dismiss();

                String message = response.body().getMessage();
                Toast.makeText(insertActivity.this, message, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                inserting.dismiss();
                Toast.makeText(insertActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    public void insertImage(){
        final ProgressDialog inserting = ProgressDialog.show(this, "INSERT IMAGE", "Inserting...", false, false);
        inserting.show();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        final String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);

        Call<Person> call = api.insertImage(imageString);

        call.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                inserting.dismiss();

                String message = response.body().getMessage();
                Toast.makeText(insertActivity.this, message, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                inserting.dismiss();
                Toast.makeText(insertActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageUpload.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
