package com.print.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.print.domain.LoginForm;
import com.print.domain.RegisterForm;
import com.print.domain.Role;
import com.print.domain.UserAccount;
import com.print.repo.PrintShopDao;

@Controller
public class LogingController {

	@Autowired
	private AuthenticationSuccessHandler successHandler;
	
	@Autowired
    private PrintShopDao printShopDao;
	
	@Autowired
	PasswordEncoder encoder;
		
	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager;
	
	@RequestMapping(value="login", method=RequestMethod.GET)
    public ModelAndView displayLoginPage(Model model, HttpServletRequest request, HttpServletResponse response) {  
		ModelAndView modView = new ModelAndView();
		modView.setViewName("login");
        return modView;
    }
	
	@ModelAttribute("loginForm")
	public LoginForm construct() {
		return new LoginForm();
	}
	
	@ModelAttribute("registerForm")
	public RegisterForm constructRegisterForm() {
		return new RegisterForm();
	}
	
//	@RequestMapping(value="/", method=RequestMethod.GET)
//	public ModelAndView homePage(HttpServletRequest request, HttpServletResponse response){
//		ModelAndView modView = new ModelAndView();
//		modView.setViewName("index");
//		
//		List<CustomerType> customers =  printShopDao.getAllCustomerTypes();
//		modView.addObject("customers", customers);
//		
//		List<Variety> varietyList = printShopDao.getAllVarietys();
//		modView.addObject("varietys", varietyList);
//		
//		List<ExtraType> extraList = printShopDao.getAllExtraTypes();
//		modView.addObject("extraList", extraList);
//		
//        return modView;
//	}
	
	
	@RequestMapping(value="login", method=RequestMethod.POST)
    public String customLoginPage(@Valid @ModelAttribute("loginForm")LoginForm loginForm, BindingResult result,
    		HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {   
		String j_username = loginForm.getUsername();
		String j_password = loginForm.getPassword();
		
		printShopDao.createUserRoles();
		
		String dbUserPwd = null;			
		
		UserAccount user = printShopDao.getUser(loginForm.getUsername());
		
		if(user != null) {
			dbUserPwd =  user.getPassword();
		}
		
//		if(encoder.matches(loginForm.getPassword(), dbUserPwd) && user.getEnabled())
		if(encoder.matches(loginForm.getPassword(), dbUserPwd)) {
			System.out.println("Login Successful");
			
		} else {
			result.reject("login.fail", "Bad username or password");
		}
		
		if(result.hasErrors()) {
			return "login";
		}
		
		Role role = user.getRoles().get(0);
		
		//authenticate user
		Authentication authentication = new UsernamePasswordAuthenticationToken(j_username, j_password, AuthorityUtils.createAuthorityList(role.getId()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		successHandler.onAuthenticationSuccess(request, response, authentication);
        return null;
    }
	
	@RequestMapping(value="changepassword", method=RequestMethod.POST)
	public String changePassword(String pwd) {
		
		BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
		pwdEncoder.encode(pwd);
		return null;
	}
	
//	@RequestMapping(value="register", method=RequestMethod.GET)
//	public String registerPage() {
//		return "register";
//	}
	
	@RequestMapping(value="register", method=RequestMethod.POST)
	public String createUser(@Valid @ModelAttribute("registerForm")RegisterForm registerForm, BindingResult result,
    		HttpServletRequest request, HttpServletResponse response) {
		
		String encryptedPwd = encoder.encode(registerForm.getPasswordid());
		registerForm.setPasswordid(encryptedPwd);
		
		boolean success = printShopDao.registerUser(registerForm);
		
		if(success) {
			return "admin";
		}
		
		return "login";
	}
}
