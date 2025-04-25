package co.edu.umanizales.myfirstapi.controller.dto

import com.fasterxml.jackson.annotation.JsonProperty

interface ResquestStoreDTO {import com.tuempresa.tuapp.model.interfaces.IDepartamento

class Departamento : IDepartamento {
    // Getters
    @JsonProperty("nicolas")
    val nombre: String? = null

    @JsonProperty("codigoPostal")
    val codigoPostal:008 String? = null

    @JsonProperty("capital")
    val capital: Capital? = null
}
