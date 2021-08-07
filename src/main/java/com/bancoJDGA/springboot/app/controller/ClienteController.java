package com.bancoJDGA.springboot.app.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bancoJDGA.springboot.app.editors.BancoPropertyEditor;
import com.bancoJDGA.springboot.app.editors.CuentaPropertyEditor;
import com.bancoJDGA.springboot.app.errors.DataBaseBancoException;
import com.bancoJDGA.springboot.app.models.dao.IBancoDao;
import com.bancoJDGA.springboot.app.models.dao.IClienteDao;
import com.bancoJDGA.springboot.app.models.dao.ICuentaDao;
import com.bancoJDGA.springboot.app.models.entity.Banco;
import com.bancoJDGA.springboot.app.models.entity.Cliente;
import com.bancoJDGA.springboot.app.models.entity.Cuenta;

@Controller
public class ClienteController {
	
	@Autowired
	private IClienteDao clienteDao;
	
	@Autowired
	private IBancoDao bancoDao;
	
	@Autowired 
	private ICuentaDao cuentaDao;
	
	@Autowired BancoPropertyEditor bancoEditor;
	
	@Autowired CuentaPropertyEditor cuentaEditor;
	
	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Banco.class, "banco", bancoEditor);
		binder.registerCustomEditor(Cuenta.class, "cuenta", cuentaEditor);
	}
	
	
	
	@RequestMapping(value="/clientes-lista", method = RequestMethod.GET)
	public String clienteLista(Model model, Map<String, Object> modelCliente) {
		Cliente cliente = new Cliente();
		modelCliente.put("cliente",cliente);
		
		
		
		model.addAttribute("titulo", "Lista clientes");
		model.addAttribute("clientes", clienteDao.findAll());
		return "clientes-lista";
	}
	
	//Form
	
	@RequestMapping(value="/form-cliente")
	public String crear(Map<String, Object> model, Model modelList) {
		Cliente cliente = new Cliente();
		List<Banco> listaBancos = bancoDao.findAll();
		List<Cuenta> listaCuentas = cuentaDao.findAll();
		model.put("cliente",cliente);
		modelList.addAttribute("listaBancos",listaBancos);
		modelList.addAttribute("listaCuentas",listaCuentas);
		model.put("titulo","Llenar los datos del cliente");
		return "form-cliente";
	}
	
	@RequestMapping(value ="/form-cliente/{id}")
	public String editar(@PathVariable(value="id") String id, Map<String, Object> model) {
		Cliente cliente = null;
		
		if(id.length() > 0) {
			cliente = clienteDao.findOne(id);
		}else {
			return "redirect:index";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Editar cliente");
		
		return "form-cliente";
	}
	
	@RequestMapping(value ="/form-cliente", method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente , BindingResult result, Model model, SessionStatus status, RedirectAttributes flash) {
		if(result.hasErrors()) {
			model.addAttribute("titulo","Formulario");
			return "form-cliente";
		}else {
			model.addAttribute("result", false);
		}
		
		Banco banco = bancoDao.findOne(cliente.getBanco().getId());
		List<Cliente> clientes = banco.getClientes();
		
		Cuenta cuenta = cuentaDao.findOne(cliente.getCuenta().getIdCuenta());
		List<Cliente> clientesCuenta = cuenta.getClientes();
		
		model.addAttribute("titulo", "Formulario de tarjeta");
		model.addAttribute("mensaje", "Se envio la informacion correctamente");
		
		clientes.add(cliente);
		banco.setClientes(clientes);
		cuenta.setClientes(clientesCuenta);
		try {
			clienteDao.save(cliente);
			bancoDao.save(banco);
			cuentaDao.save(cuenta);
		} catch (DataBaseBancoException e) {
			e.printStackTrace();
			flash.addFlashAttribute("mensaje",e.getMessage());
		}
		
		
		
		status.setComplete();
		
		return "redirect:clientes-lista";
	}
	
	@RequestMapping(value ="/eliminarcliente/{id}")
	public String eliminar(@PathVariable(value = "id") String id) {
		if(id.length() > 0) {
			clienteDao.delete(id);
		}
		
		return "redirect:/clientes-lista";
	}
	
	
	@RequestMapping(value="/buscar-numero-tel", method = RequestMethod.POST)
	//Evidencia 12
	public String cargarClientesNumeroTelefono(Cliente cliente, RedirectAttributes flash) {
		
		if(!clienteDao.findClient(cliente.getNumeroTelefonico(), cliente.getIdUser()).isEmpty()) {
			flash.addFlashAttribute("listaClientesBusqueda", clienteDao.findClient(cliente.getNumeroTelefonico(), cliente.getIdUser()));
			flash.addFlashAttribute("mensajeSuccess","Se encontraron clientes");
		
		}else {
			flash.addFlashAttribute("mensaje","No se encontraron clientes");
		}
		
		
		return "redirect:/clientes-lista";
	}
	
	/*
	@RequestMapping(value="/buscar-por-id", method = RequestMethod.POST)
	//Evidencia 12
	public String cargarClientesId(Cliente cliente, RedirectAttributes flash) {
		
		if(!clienteDao.findById(cliente.getIdUser()).isEmpty()) {
			flash.addFlashAttribute("listaClientesNumeroTel", clienteDao.findById(cliente.getIdUser()));
			flash.addFlashAttribute("mensajeSuccess","Se encontraron clientes");
		
		}else {
			flash.addFlashAttribute("mensaje","No se encontraron clientes");
		}
		
		
		return "redirect:/clientes-lista";
	}
	*/
	
	
	
	
}
