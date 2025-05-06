package com.example.ModuloRH.service;

import com.example.ModuloRH.controller.exceptions.EmpleadoNoEncontradoException;
import com.example.ModuloRH.model.Empleado;
import com.example.ModuloRH.model.Permiso;
import com.example.ModuloRH.repository.EmpleadoRepository;
import com.example.ModuloRH.repository.PermisoRepository;
import com.example.ModuloRH.repository.TipoPermisoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Service
public class PermisoServiceImpl implements PermisoService{

    @Autowired
    private PermisoRepository permisoRepository;
    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private TipoPermisoRepository tipoPermisoRepository;
    @Autowired
    private EmpleadoService empleadoService;

    @Override
    public Permiso crearPermiso(Permiso permiso) {
        //validar que el empleado y tipo de permiso existen
        if(permiso.getTipoPermiso() == null || permiso.getTipoPermiso().getIdTipo() == 0){
            throw new IllegalArgumentException("Tipo de permiso requerido");
        }

        //fecha fin = fecha de inicio + duracion

        if(permiso.getFechaFin() == null && permiso.getDuracion() > 0){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(permiso.getFechaInicio());
            calendar.add(Calendar.DAY_OF_YEAR, permiso.getDuracion());
            permiso.setFechaFin((Date) calendar.getTime());
        }
        permiso.setAprobado(false);//aprobado por defecto
        return permisoRepository.save(permiso);
    }

    @Override
    public List<Permiso> obtenerPermisosPorEmpleado(int idEmpleado) {
        Empleado empleado = empleadoService.getById(idEmpleado);

        return permisoRepository.findByEmpleado(empleado);
    }

    @Override
    public Permiso aprobarPermiso(int idPermiso, int idJefe) {
        Permiso permiso = permisoRepository.findById(idPermiso)
                .orElseThrow(() -> new RuntimeException("Permiso no encontrado"));

        Empleado empleado = permiso.getEmpleado();
        if (empleado.getJefe() == null || empleado.getJefe().getIdEmpleado() != idJefe) {
            throw new SecurityException("No tienes permiso para aprobar esta solicitud");
        }

        permiso.setAprobado(true);
        return permisoRepository.save(permiso);
    }

    @Override
    public void rechazarPermiso(int idPermiso, int idJefe) {
        Permiso permiso = permisoRepository.findById(idPermiso)
                .orElseThrow(() -> new RuntimeException("Permiso no encontrado"));

        Empleado empleado = permiso.getEmpleado();
        if (empleado.getJefe() == null || empleado.getJefe().getIdEmpleado() != idJefe) {
            throw new SecurityException("No tienes permiso para rechazar esta solicitud");
        }

        permisoRepository.delete(permiso);

    }
}
