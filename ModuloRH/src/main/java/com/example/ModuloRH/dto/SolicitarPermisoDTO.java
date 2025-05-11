package com.example.ModuloRH.dto;

import java.util.Date;

public record SolicitarPermisoDTO(int idEmpleado, int idTipoPermiso, Date fechaInicio, int duracion) {
}
