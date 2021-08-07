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

import com.bancoJDGA.springboot.app.editors.BancoPropertyEditor;
import com.bancoJDGA.springboot.app.errors.DataBaseBancoException;
import com.bancoJDGA.springboot.app.models.dao.IBancoDao;
import com.bancoJDGA.springboot.app.models.dao.IEmpleadoDao;
import com.bancoJDGA.springboot.app.models.entity.Banco;
import com.bancoJDGA.springboot.app.models.entity.Empleado;




@Controller
@SessionAttributes("empleado")
public class EmpleadoController {
	
	@Autowired
	private IBancoDao bancoDao;
	
	@Autowired
	private IEmpleadoDao empleadoDao;
	
	@Autowired
	private BancoPropertyEditor bancoEditor;
	
	
	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Banco.class, "banco", bancoEditor);
		
		
		
		
	}
	
	@RequestMapping(value="/empleados-lista", method = RequestMethod.GET)
	public String empleadoLista(Model model, Map<String, Object> modelEmpleado) {
		Empleado empleado = new Empleado();
		modelEmpleado.put("empleado", empleado);
		
		
		model.addAttribute("titulo", "Lista de empleados");
		model.addAttribute("empleados", empleadoDao.findAll());
		
		return "empleados-lista";
	}
	//Form
	
		@RequestMapping(value="/form-empleado")
		public String crear(Map<String, Object> model, Model modelList) {
			Empleado empleado = new Empleado();
			List<Banco> listaBancos = bancoDao.findAll();
			model.put("empleado",empleado);
			modelList.addAttribute("listaBancos",listaBancos);
			model.put("titulo", "Llene los datos del empleado");
			return "form-empleado";
		}
		
		@RequestMapping(value ="/form-empleado/{id}")
		public String editar(@PathVariable(value="id") String id, Map<String, Object> model) {
			Empleado empleado = null;
			
			if(id != null && id.length() > 0) {
				empleado = empleadoDao.findOne(id);
			}else {
				return "redirect:";
			}
			model.put("empleado", empleado);
			model.put("titulo", "Editar empleado");
			
			return "form-empleado";
		}
		
		@RequestMapping(value ="/form-empleado", method = RequestMethod.POST)
		public String guardar(@Valid Empleado empleado , BindingResult result, Model model, SessionStatus status, RedirectAttributes flash) {
			
			System.out.println("Entrando en metodo guardar");
			
			if(result.hasErrors()) {
				model.addAttribute("titulo", "Llene correctamente los campos");
				model.addAttribute("result", result.hasErrors());
				model.addAttribute("mensaje", "Error al enviar los datos, por favor escriba correctamente los campos");
				result.getAllErrors().forEach(System.out:: println);
				System.out.println(empleado.getNombre());
				System.out.println(empleado.getApellido());
				System.out.println(empleado.getPuesto());
				System.out.println(empleado.getAntiguedad());
				System.out.println(empleado.getBanco());
				return "form-empleado";
			} else {
				model.addAttribute("result", false);
			}
			
			
			Banco banco = bancoDao.findOne(empleado.getBanco().getId());
			List<Empleado> empleados = banco.getEmpleados();
			
			empleados.add(empleado);
			banco.setEmpleados(empleados);
			
			model.addAttribute("titulo", "Formulario de tarjeta");
			model.addAttribute("mensaje", "Se envio la informacion correctamente");
			try {
				empleadoDao.save(empleado);
				bancoDao.save(banco);
			}catch (DataBaseBancoException e) {
				e.printStackTrace();
				flash.addFlashAttribute("mensaje",e.getMessage());
				
			}
			status.setComplete();
			result.getAllErrors();
			return "redirect:empleados-lista";
		}
		
		@RequestMapping(value ="/eliminarempleado/{id}")
		public String eliminar(@PathVariable(value = "id") String id) {
			if(id.length() > 0 && id !=null) {
				empleadoDao.delete(id);
			}
			
			return "redirect:/empleados-lista";
		}

		
		// #Evidencia 12
		@RequestMapping(value="/buscar-empleado", method = RequestMethod.POST)
		public String loadEmpleados(Empleado empleado, RedirectAttributes flash) {
			
			if(!empleadoDao.findEmpleado(empleado.getIdEmpleado(), empleado.getPuesto()).isEmpty()) {
				flash.addFlashAttribute("listaEmpleadosBusqueda", empleadoDao.findEmpleado(empleado.getIdEmpleado(), empleado.getPuesto()));
				flash.addFlashAttribute("mensajeSuccess","Se encontraron empleados");
			
			}else {
				flash.addFlashAttribute("mensaje","No se encontraron empleados con los datos solicitados");
			}
			
			
			return "redirect:/empleados-lista";
			
			
		}
		
		
		
		
		
		
}
