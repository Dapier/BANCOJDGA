package com.bancoJDGA.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;




@Entity
@Table(name="clientes")
public class Cliente implements Serializable {

	
	private static final long serialVersionUID = -7805521811118786919L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String idUser;
	
	@NotEmpty
	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	@NotEmpty
	@Column(name = "apellido", nullable = false)
	private String apellido;
	
	@NotEmpty
	@Column(name="numero_telefonico", nullable = false)
	private String numeroTelefonico;
	
	@NotEmpty
	@Column(name="email", nullable = false)
	private String email;
	
	
	@NotEmpty
	@Column(name="numero_de_cuenta",nullable = false)
	private String numeroDeCuenta;

	
	/*
	//Todo cliente debe tener solo un banco
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "banco", referencedColumnName = "id", nullable = false)
	private Banco banco;
	*/
	
	@JoinColumn(name = "banco", referencedColumnName = "id", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Banco banco;

/*
	//Todo cliente debe tener solo una cuenta a la vez
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cuenta", referencedColumnName = "idCuenta")
	private Cuenta cuenta;
	*/
	
	@JoinColumn(name = "cuenta", referencedColumnName = "idCuenta", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Cuenta cuenta;
	
	
	

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
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

	public String getNumeroTelefonico() {
		return numeroTelefonico;
	}

	public void setNumeroTelefonico(String numeroTelefonico) {
		this.numeroTelefonico = numeroTelefonico;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumeroDeCuenta() {
		return numeroDeCuenta;
	}

	public void setNumeroDeCuenta(String numeroDeCuenta) {
		this.numeroDeCuenta = numeroDeCuenta;
	}

	
	
	
	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
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
