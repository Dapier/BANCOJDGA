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

import com.bancoJDGA.springboot.app.editors.CuentaPropertyEditor;
import com.bancoJDGA.springboot.app.errors.DataBaseBancoException;
import com.bancoJDGA.springboot.app.models.dao.ICuentaDao;
import com.bancoJDGA.springboot.app.models.dao.ITarjetaDao;
import com.bancoJDGA.springboot.app.models.entity.Cuenta;
import com.bancoJDGA.springboot.app.models.entity.Tarjeta;

@Controller
@SessionAttributes("tarjeta")
public class TarjetaController {

	
	@Autowired
	private ITarjetaDao tarjetaDao;
	
	@Autowired
	private ICuentaDao cuentaDao;
	
	
	@Autowired
	private CuentaPropertyEditor cuentaEditor;
	
	
	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Cuenta.class, "cuenta", cuentaEditor);
	}
	
	
	@RequestMapping(value="/tarjetas-lista",method = RequestMethod.GET)
	public String tarjetaLista(Model model, Map<String, Object> modelTarjeta) {
		Tarjeta tarjeta = new Tarjeta();
		modelTarjeta.put("tarjeta",tarjeta);
		
		
		model.addAttribute("titulo", "Lista de tarjetas");
		model.addAttribute("tarjetas", tarjetaDao.findAll());
		return "tarjetas-lista";
	}
	
	//Form
	
		@RequestMapping(value="/form-tarjeta")
		public String crear(Map<String, Object> model,Model modelList) {
			Tarjeta tarjeta = new Tarjeta();
			List<Cuenta> listaCuentas = cuentaDao.findAll();
			model.put("tarjeta",tarjeta);
			modelList.addAttribute("listaCuentas",listaCuentas);
			model.put("titulo","Llenar los datos de la tarjeta");
			return "form-tarjeta";
		}
		
		@RequestMapping(value ="/form-tarjeta/{id}")
		public String editar(@PathVariable(value="id") String id, Map<String, Object> model) {
			Tarjeta tarjeta = null;
			
			if(id.length() > 0) {
				tarjeta = tarjetaDao.findOne(id);
			}else {
				return "redirect:index";
			}
			model.put("tarjeta", tarjeta);
			model.put("titulo", "Editar cliente");
			
			return "form-tarjeta";
		}
		
		@RequestMapping(value ="/form-tarjeta", method = RequestMethod.POST)
		public String guardar (@Valid Tarjeta tarjeta , BindingResult result, Model model, SessionStatus status,RedirectAttributes flash ) {
			if(result.hasErrors()) {
				model.addAttribute("titulo","Formulario");
				return "form-tarjeta";
			}
			
			Cuenta cuenta = cuentaDao.findOne(tarjeta.getCuenta().getIdCuenta());
			List<Tarjeta> tarjetas = cuenta.getTarjetas();
			if(tarjetas.size() < 2) {
				tarjetas.add(tarjeta);
				cuenta.setTarjetas(tarjetas);
			}else {
				flash.addFlashAttribute("mensaje", "La cuenta no puede tener mas de 2 tarjetas");
				return "redirect:form-tarjeta";
			}
			
			model.addAttribute("titulo", "Formulario de tarjeta");
			model.addAttribute("mensaje", "Se envio la informacion correctamente");
			
			try {
				tarjetaDao.save(tarjeta);
				cuentaDao.save(cuenta);	
			} catch (DataBaseBancoException e) {
				e.printStackTrace();
				flash.addFlashAttribute("mensaje", e.getMessage());
			}
			
			status.setComplete();
			
			return "redirect:/tarjetas-lista";
		}
		
		@RequestMapping(value ="/eliminartarjeta/{id}")
		public String eliminar(@PathVariable(value = "id") String id) {
			if(id.length() > 0) {
				tarjetaDao.delete(id);
			}
			
			return "redirect:/tarjetas-lista";
		}
	
		@RequestMapping(value="/buscar-tarjeta", method = RequestMethod.POST)
		public String loadCard(Tarjeta tarjeta, RedirectAttributes flash) {
			if(!tarjetaDao.findTarjeta(tarjeta.getIdTarjeta(), tarjeta.getNumeroDeCuenta()).isEmpty()) {
				flash.addFlashAttribute("listaTarjetasBusqueda", tarjetaDao.findTarjeta(tarjeta.getIdTarjeta(), tarjeta.getNumeroDeCuenta()));
				flash.addFlashAttribute("mensajeSuccess","Se encontraron clientes");
			}else {
				flash.addFlashAttribute("mensaje","No se encontraron tarjetas");
			}
			return "redirect:/tarjetas-lista";
		}
}
