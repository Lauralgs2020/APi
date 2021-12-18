package com.df.tallerapi.API;

import com.df.tallerapi.Models.PersonajeRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PersonajeService {
    @GET("character")
    Call<PersonajeRespuesta> obtenerPersonajes();
}
