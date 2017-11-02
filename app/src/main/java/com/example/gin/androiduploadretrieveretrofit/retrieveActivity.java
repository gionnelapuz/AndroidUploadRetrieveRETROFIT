package com.example.gin.androiduploadretrieveretrofit;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gin.androiduploadretrieveretrofit.ADAPTER.retrieveImageAdapter;
import com.example.gin.androiduploadretrieveretrofit.ADAPTER.retrieveTextAdapter;
import com.example.gin.androiduploadretrieveretrofit.API.API;
import com.example.gin.androiduploadretrieveretrofit.OBJECT.Person;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class retrieveActivity extends AppCompatActivity {

    public static String ROOT_URL = "https://YOUR-URL/";

    private Button btnRetrieveText, btnRetrieveImage;
    private ListView listViewRetrieveText;
    private GridView gridViewRetrieveImage;
    private retrieveTextAdapter textAdapter;
    private retrieveImageAdapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);

        btnRetrieveText = (Button) findViewById(R.id.btnRetrieveText);
        btnRetrieveImage = (Button) findViewById(R.id.btnRetrieveImage);

        listViewRetrieveText = (ListView) findViewById(R.id.listViewRetrieveText);
        gridViewRetrieveImage = (GridView) findViewById(R.id.gridViewRetrieveImage);

        btnRetrieveText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrieveText();
                listViewRetrieveText.setVisibility(View.VISIBLE);
                gridViewRetrieveImage.setVisibility(View.GONE);
            }
        });
        btnRetrieveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrieveImage();
                listViewRetrieveText.setVisibility(View.GONE);
                gridViewRetrieveImage.setVisibility(View.VISIBLE);
            }
        });
    }


    public void retrieveText() {
        final ProgressDialog loading = ProgressDialog.show(this, "Retrieve Text", "Retrieving data...", false, false);
        loading.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);

        Call<List<Person>> call = api.getUsers();

        call.enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                loading.dismiss();

                List<Person> personList = response.body();

                textAdapter = new retrieveTextAdapter(retrieveActivity.this, personList);
                listViewRetrieveText.setAdapter(textAdapter);
            }

            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(retrieveActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    public void retrieveImage(){
        final ProgressDialog loading = ProgressDialog.show(this, "Retrieve Image", "Retrieving data...", false, false);
        loading.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);

        Call<List<Person>> call = api.getImage();

        call.enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                loading.dismiss();

                List<Person> image = response.body();

                imageAdapter = new retrieveImageAdapter(retrieveActivity.this, image);
                gridViewRetrieveImage.setAdapter(imageAdapter);
            }

            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(retrieveActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
