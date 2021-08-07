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
import javax.validation.constraints.NotEmpty;



@Entity
@Table(name ="tarjetas")
public class Tarjeta implements Serializable{

	
	private static final long serialVersionUID = -2297024208424531442L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String idTarjeta;
	
	@NotEmpty
	@Column(name="numero_tarjeta", nullable = false, length = 16)
	private String numeroTarjeta;
	
	
	@NotEmpty
	@Column(name="numero_de_cuenta", nullable = false)
	private String numeroDeCuenta;
	
	@NotEmpty
	@Column(name="icv", nullable = false, length = 3)
	private String icv;
	
	
	@NotEmpty
	@Column(name="nombre", nullable = false)
	private String nombre;
	
	
	//Relacion cuenta-tarjeta
	@JoinColumn(name = "cuenta", referencedColumnName = "idCuenta", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Cuenta cuenta;
	

	public String getIdTarjeta() {
		return idTarjeta;
	}

	public void setIdTarjeta(String idTarjeta) {
		this.idTarjeta = idTarjeta;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public String getNumeroDeCuenta() {
		return numeroDeCuenta;
	}

	public void setNumeroDeCuenta(String numeroDeCuenta) {
		this.numeroDeCuenta = numeroDeCuenta;
	}

	public String getIcv() {
		return icv;
	}

	public void setIcv(String icv) {
		this.icv = icv;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
