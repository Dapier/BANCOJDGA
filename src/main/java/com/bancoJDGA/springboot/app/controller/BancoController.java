package com.bancoJDGA.springboot.app.controller;

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

import com.bancoJDGA.springboot.app.models.dao.IBancoDao;
import com.bancoJDGA.springboot.app.models.entity.Banco;
import com.bancoJDGA.springboot.app.validator.BancoValidator;

@Controller
@SessionAttributes("banco")
public class BancoController {
	
	@Autowired
	private IBancoDao bancoDao;

	@Autowired private BancoValidator bancoValidator;
	
	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		binder.addValidators(bancoValidator);
		
	}
	
	

	@RequestMapping(value="/bancos-lista", method = RequestMethod.GET)
	public String bancoLista(Model model) {
		model.addAttribute("titulo","Lista de bancos");
		model.addAttribute("bancos", bancoDao.findAll());
		return "bancos-lista";
	}
	
	//Form
	
	
	@RequestMapping(value="/form-banco")
	public String crear(Map<String, Object> model) {
		Banco banco = new Banco();
		model.put("banco",banco);
		model.put("titulo","Llenar los datos del banco");
		return "form-banco";
	}
	
	@RequestMapping(value ="/form-banco/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model) {
		Banco banco = null;
		
		if(id > 0 ) {
			banco = bancoDao.findOne(id);
		}else {
			return "redirect:";
		}
		model.put("banco", banco);
		model.put("titulo", "Editar banco");
		
		return "form-banco";
	}
	
	@RequestMapping(value ="/form-banco", method = RequestMethod.POST)
	public String guardar (@Valid Banco banco , BindingResult result, Model model, SessionStatus status) {
		
		//bancoValidator.validate(banco, result)
		
		
		if(result.hasErrors()) {
			model.addAttribute("titulo","Error al enviar la informacion , intente de nuevo");
			return "form-banco";
		}
		
		model.addAttribute("titulo", "Formulario banco");
		model.addAttribute("mensaje", "se guardo el banco correctamente");
		bancoDao.save(banco);
		status.setComplete();
		
		return "redirect:bancos-lista";
	}
	
	@RequestMapping(value ="/eliminarbanco/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {
		if(id != null && id > 0) {
			bancoDao.delete(id);
		}
		
		return "redirect:/bancos-lista";
	}
	
	
	
	
	
}
