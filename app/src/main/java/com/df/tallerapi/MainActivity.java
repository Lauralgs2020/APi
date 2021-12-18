package com.df.tallerapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.df.tallerapi.API.PersonajeService;
import com.df.tallerapi.Models.PersonajeRespuesta;
import com.df.tallerapi.Models.Personajes;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "CHARACTER";

    private Retrofit retrofit;

    private RecyclerView recyclerView;
    private AdapterPersonajes listaPersonajesAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerCharacter);
        listaPersonajesAdapter = new AdapterPersonajes(this);
        recyclerView.setAdapter(listaPersonajesAdapter);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 1   );
        recyclerView.setLayoutManager(layoutManager);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        obtenerDatos();
    }

    private void obtenerDatos(){
        PersonajeService service = retrofit.create(PersonajeService.class);
        Call<PersonajeRespuesta> PersonsRespuestaCall = service.obtenerPersonajes();


        PersonsRespuestaCall.enqueue(new Callback<PersonajeRespuesta>() {
            @Override
            public void onResponse(Call<PersonajeRespuesta> call, Response<PersonajeRespuesta> response) {
                if(response.isSuccessful()){

                    PersonajeRespuesta PersonsRespuesta = response.body();
                    ArrayList<Personajes> listaPersonajes = PersonsRespuesta.getResults();

                    listaPersonajesAdapter.adicionarListaPersonajes(listaPersonajes);

                } else {
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PersonajeRespuesta> call, Throwable t) {
                Log.e(TAG, " onFailure: " + t.getMessage());
            }
        });
    }
}