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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bancoJDGA.springboot.app.editors.CasaInversoraPropertyEditor;
import com.bancoJDGA.springboot.app.errors.DataBaseBancoException;
import com.bancoJDGA.springboot.app.models.dao.ICasaInversionistaDao;
import com.bancoJDGA.springboot.app.models.dao.ICuentaDao;
import com.bancoJDGA.springboot.app.models.entity.CasaInversionista;
import com.bancoJDGA.springboot.app.models.entity.Cuenta;


@Controller
@SessionAttributes("cuenta")
public class CuentaController {
	
	@Autowired
	private ICuentaDao cuentaDao;
	
	@Autowired
	private ICasaInversionistaDao casaInversionistaDao;

	
	@Autowired
	private CasaInversoraPropertyEditor casaInversoraEditor;
	
	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(CasaInversionista.class, "casaInversionista", casaInversoraEditor);
		
		
	}
	

	@RequestMapping(value="/cuentas-lista", method = RequestMethod.GET)
	public String cuentaLista(Model model) {
		model.addAttribute("titulo","Lista de cuenta");
		model.addAttribute("cuentas", cuentaDao.findAll());
		
		return "cuentas-lista";
	}
	//Forms
	
	@RequestMapping(value="/form-cuenta")
	public String crear(Map<String, Object> model, Model modelList) {
		Cuenta cuenta = new Cuenta();
		List<CasaInversionista> listaCasaInversionistas = casaInversionistaDao.findAll();
		model.put("cuenta",cuenta);
		modelList.addAttribute("listaCasaInversionistas",listaCasaInversionistas);
		model.put("titulo","Llenar los datos de la cuenta");
		return "form-cuenta";
	}
	
	@RequestMapping(value ="/form-cuenta/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Cuenta cuenta = null;
		
		if(id > 0 && id > 0 ) {
			cuenta = cuentaDao.findOne(id);
		}else {
			return "redirect:/";
		}
		flash.addFlashAttribute("mensaje","La cuenta fue actualizada");
		model.put("cuenta", cuenta);
		model.put("titulo", "Editar cuenta");
		
		return "form-cuenta";
	}
	
	@RequestMapping(value ="/form-cuenta", method = RequestMethod.POST)
	public String guardar (@Valid Cuenta cuenta , BindingResult result, Model model, SessionStatus status, RedirectAttributes flash) {
		
		
		
		if(result.hasErrors()) {
			model.addAttribute("titulo","Formulario cuenta");
			model.addAttribute("result", result.hasErrors());
			model.addAttribute("mensaje", "Error al registrar la cuenta");
			result.getAllErrors().forEach(System.out:: println);
			System.out.println(cuenta.getIdCuenta());
			System.out.println(cuenta.getMontoMinimo());
			System.out.println(cuenta.getSaldoActual());
			System.out.println(cuenta.getPorcentaje());
			System.out.println(cuenta.getCasaInversionista().getNombreOferta());
			return "form-cuenta";
		}else {
			model.addAttribute("result", false);
		}
		
		
		CasaInversionista casaInversora = casaInversionistaDao.findOne(cuenta.getCasaInversionista().getIdOferta());
		
		
		try {
			cuentaDao.save(cuenta);
			casaInversionistaDao.save(casaInversora);
		} catch(DataBaseBancoException e) {
			e.printStackTrace();
			flash.addFlashAttribute("mensaje", e.getMessage());
		}
		
		
		status.setComplete();
		
		
		return "redirect:cuentas-lista";
	}
	
	@RequestMapping(value ="/eliminarcuenta/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		
		
		if(id != null && id >0) {
			Cuenta cuenta = cuentaDao.findOne(id);
			if(cuenta.getTarjetas().isEmpty()) {
				cuentaDao.delete(id);
			}else {
				flash.addFlashAttribute("mensaje","La cuenta tiene tarjetas pendientes por eliminar");
			}
		}
		
		return "redirect:/cuentas-lista";
	}
	
}
