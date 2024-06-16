package com.mario.proyect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.entity.Usuario;
import com.mario.proyect.service.EmailService;
import com.mario.proyect.service.UsuarioService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UsuarioController {
	
    @Autowired
	private UsuarioService usuarioService;
    @Autowired
    private EmailService emailService;

	@GetMapping(value = {"/usuarios/{hayError}","/usuarios"})
	public ModelAndView getUsuarios(@PathVariable(required = false) boolean hayError) {
		return usuarioService.getUsuarios(hayError);
	}

    @GetMapping(value = {"/usuario/{nombre}/{hayError}","/usuario/{nombre}"})
    public ModelAndView getUsuario(@PathVariable String nombre, @PathVariable(required = false) boolean hayError) {
        return usuarioService.getUsuario(nombre, hayError);
    }

    @GetMapping("/usuario/del/{nombre}")
    public ModelAndView deleteUsuario(@PathVariable String nombre) {
        return usuarioService.deleteUsuario(nombre);
    }

    @GetMapping("/usuario/add")
    public ModelAndView addUsuario() {
        return usuarioService.addUsuario();
    }

    @PostMapping("/usuario/save")
    public ModelAndView saveUsuario(@ModelAttribute("usuarioNuevo") @Valid Usuario usuarioNuevo,
            BindingResult bindingResult,HttpServletRequest request) {
        return usuarioService.saveUsuario(usuarioNuevo, bindingResult, request);
    }

    @GetMapping("/usuario/edit/{email}")
    public ModelAndView editUsuario(@PathVariable String email, HttpServletRequest request) {
        return usuarioService.editUsuario(email,request);
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return usuarioService.login();
    }

    @PostMapping("/login")
    public ModelAndView loginUser(@ModelAttribute("user") Usuario user, HttpServletRequest request) {
        return usuarioService.loginUser(user,request);
    }

    @GetMapping("/register")
    public ModelAndView register() {
        return usuarioService.registrer();
    }

    @PostMapping("/register")
    public ModelAndView registerUser(@ModelAttribute("user") @Valid Usuario user, BindingResult bindingResult) {
        return usuarioService.registrerUser(user,bindingResult);
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request) {
        return usuarioService.logout(request);
    }

    @GetMapping("/home")
    public ModelAndView home(HttpServletRequest request) {
        return usuarioService.home(request);
    }
    @GetMapping("/cambioContrasenia")
    public ModelAndView cambioContrasenia() {
        return usuarioService.cambioContrasenia();
    }
    @GetMapping("/formularioCambioContraseña")
    public ModelAndView formularioCambioContrasenia() {
        return usuarioService.formCambioContrasenia();
    }

    @PostMapping("/guardarModificacionUser")
    public ModelAndView guardarCambioContrasenia(@ModelAttribute @Valid Usuario user, BindingResult bindingResult ,HttpServletRequest request) {

        emailService.sendConfirmacionCambioContrasenia();
        
        return usuarioService.guardarCambioContrasenia(user,bindingResult,request);
    }
    
}
