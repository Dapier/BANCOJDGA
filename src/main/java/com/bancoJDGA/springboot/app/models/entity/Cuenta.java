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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="cuentas")
public class Cuenta implements Serializable {

	private static final long serialVersionUID = 8296695844280917078L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCuenta;
	
	
	@Column(name="monto_minimo", nullable =false)
	private double montoMinimo;
	
	
	@Column(name="saldo_actual", nullable =false)
	
	private double saldoActual;
	
	
	@Column(name="porcentaje", nullable =false)
	private float porcentaje;
	
	
	
	
	//Una cuenta puede tener varias tarjetas
	//Relacion cuenta-tarjeta
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cuenta",cascade = CascadeType.MERGE)
	private List<Tarjeta> tarjetas;
	
	
	
	/*
	//una cuenta debe pertenecer a un cliente, 1, 1
	@OneToOne(mappedBy = "cuenta")
	private Cliente cliente;
	*/
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cuenta",cascade = CascadeType.MERGE)
	private List<Cliente> clientes;
	
	
	//Relacion cuenta-casa inversora 1,1
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "casaInversionista", referencedColumnName = "idOferta", nullable = true)
	private CasaInversionista casaInversionista;
	
	
	
	
	
	public Long getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(Long idCuenta) {
		this.idCuenta = idCuenta;
	}

	public double getMontoMinimo() {
		return montoMinimo;
	}

	public void setMontoMinimo(double montoMinimo) {
		this.montoMinimo = montoMinimo;
	}
	
	
	//Relacion OneToOne numero de cuenta entre cuenta

	

	public double getSaldoActual() {
		return saldoActual;
	}

	public void setSaldoActual(double saldoActual) {
		this.saldoActual = saldoActual;
	}

	public float getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(float porcentaje) {
		this.porcentaje = porcentaje;
	}

	public List<Tarjeta> getTarjetas() {
		return tarjetas;
	}

	public void setTarjetas(List<Tarjeta> tarjetas) {
		this.tarjetas = tarjetas;
	}

	
	
	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public CasaInversionista getCasaInversionista() {
		return casaInversionista;
	}

	public void setCasaInversionista(CasaInversionista casaInversionista) {
		this.casaInversionista = casaInversionista;
	}

	
	
	
	
	
	
	


}
