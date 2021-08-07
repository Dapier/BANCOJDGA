package com.bancoJDGA.springboot.app.controller;


import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.bancoJDGA.springboot.app.models.dao.ICasaInversionistaDao;

import com.bancoJDGA.springboot.app.models.entity.CasaInversionista;

@Controller
public class CasaInversionistaController {
	
	@Autowired
	private ICasaInversionistaDao casaInversionistaDao;
	
	
	@RequestMapping(value="/casa-inversionista-lista", method = RequestMethod.GET)
	public String casaInversionistaLista(Model model) {
		model.addAttribute("titulo", "Lista de casa inversionistas");
		model.addAttribute("casaInversionistas", casaInversionistaDao.findAll());
	
		return "casa-inversionista-lista";
	}
	
	
	
	//Form
	@RequestMapping(value="/form-casa-inversora")
	public String crear(Map<String, Object> model) {
		CasaInversionista casaInversionista = new CasaInversionista();
		model.put("casaInversionista",casaInversionista);
		model.put("titulo","Llenar los datos de la casa inversora");
		return "form-casa-inversora";
	}
	
	@RequestMapping(value ="/form-casa-inversora/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model) {
		CasaInversionista casaInversionista = null;
		
		if(id > 0) {
			casaInversionista = casaInversionistaDao.findOne(id);
		}else {
			return "redirect:/casa-inversionista-lista";
		}
		model.put("casaInversionista", casaInversionista);
		model.put("titulo", "Editar casa inversora");
		
		return "form-casa-inversora";
	}
	
	@RequestMapping(value ="/form-casa-inversora", method = RequestMethod.POST)
	public String guardar (@Valid CasaInversionista casaInversionista , BindingResult result, Model model, SessionStatus status) {
		if(result.hasErrors()) {
			model.addAttribute("titulo","Llene correctamente los campos");
			model.addAttribute("mensaje", "Error al enviar los datos, por favor escriba correctamente los campos");
			result.getAllErrors().forEach(System.out:: println);
			System.out.println(casaInversionista.getIdOferta());
			System.out.println(casaInversionista.getMontoMinimo());
			System.out.println(casaInversionista.getNombreOferta());
			System.out.println(casaInversionista.getPlazos());
			System.out.println(casaInversionista.getPorcentajeRetorno());
			return "form-casa-inversora";
		}else {
			model.addAttribute("result", false);
			model.addAttribute("errList", "");
		}
			
		
		model.addAttribute("titulo", "Formulario Casa inversora");
		model.addAttribute("mensaje", "se guardo la casa inversora correctamente");
		casaInversionistaDao.save(casaInversionista);
		status.setComplete();
		
		return "redirect:casa-inversionista-lista";
	}
	
	@RequestMapping(value ="/eliminarcasainversora/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {
		if(id > 0) {
			casaInversionistaDao.delete(id);
		}
		
		return "redirect:/casa-inversionista-lista";
	}
	
	
}
