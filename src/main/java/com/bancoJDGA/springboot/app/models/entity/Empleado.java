package com.bancoJDGA.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "empleados")
public class Empleado implements Serializable{

	
	private static final long serialVersionUID = -4259431977485425999L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String idEmpleado;
	
	
	@Column
	@NotNull
	private String nombre;
	
	@Column
	@NotNull
	private String apellido;
	
	@Column
	@NotNull
	private String puesto;
	
	@Column
	@NotNull(message = "Esta vacio")
	private Integer antiguedad;
	
	//Relacion banco-empleado
	@JoinColumn(name = "banco", referencedColumnName = "id", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Banco banco;
	

	public String getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(String idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	
	public Integer getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(Integer antiguedad) {
		this.antiguedad = antiguedad;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
