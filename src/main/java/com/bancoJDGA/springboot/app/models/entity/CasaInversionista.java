package com.bancoJDGA.springboot.app.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;

import javax.persistence.Table;


@Entity
@Table(name ="casaInversionistas")
public class CasaInversionista implements Serializable{

	private static final long serialVersionUID = -4494662770169006410L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idOferta;
	
	
	@Column(name="nombre_oferta", nullable = false)
	private String nombreOferta;
	
	
	@Column(name="plazos", nullable = false)
	private float plazos;
	
	
	@Column(name="porcentaje_retorno", nullable = false)
	private float porcentajeRetorno;

	
	@Column(name="monto_minimo", nullable =false)
	private double montoMinimo;
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "casaInversionista", cascade = CascadeType.MERGE)
	private List<Cuenta> cuentas;
	

	

	public Long getIdOferta() {
		return idOferta;
	}

	public void setIdOferta(Long idOferta) {
		this.idOferta = idOferta;
	}

	public String getNombreOferta() {
		return nombreOferta;
	}

	public void setNombreOferta(String nombreOferta) {
		this.nombreOferta = nombreOferta;
	}

	public float getPlazos() {
		return plazos;
	}

	public void setPlazos(float plazos) {
		this.plazos = plazos;
	}

	public float getPorcentajeRetorno() {
		return porcentajeRetorno;
	}

	public void setPorcentajeRetorno(float porcentajeRetorno) {
		this.porcentajeRetorno = porcentajeRetorno;
	}

	public double getMontoMinimo() {
		return montoMinimo;
	}

	public void setMontoMinimo(double montoMinimo) {
		this.montoMinimo = montoMinimo;
	}
	
	
	

	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
