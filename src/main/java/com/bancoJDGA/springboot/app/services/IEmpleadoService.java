package com.bancoJDGA.springboot.app.services;

import java.util.List;

import com.bancoJDGA.springboot.app.models.entity.Empleado;

public interface IEmpleadoService {

	public Empleado getById(String id, List<Empleado> lista);
}
