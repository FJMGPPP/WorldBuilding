package com.fjmg.worldbuilding.data.model;

import java.io.Serializable;

public class TipoSuccesAndFails implements Serializable
{
    public static final String INICIO_SESION = "inicio Sesion";
    public static final String PERDIDA_CONEXION = "No se pudo acceder a la conexion";
    public static final String EMAIL_EXISTENTE = "Ya existe una cuenta registrada con este email";
    public static final String CUENTA_CREADA = "Se registro la cuenta con exito" ;
    public static final String USUARIO_EXISTENTE = "Ya existe un usuario con estos datos";
    public static final String INICIO_SESION_INVALIDO = "No se pudo inciar sesion";
}
