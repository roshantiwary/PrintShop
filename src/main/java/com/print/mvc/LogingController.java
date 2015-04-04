package com.print.mvc;

import java.io.IOException;
import java.util.Iterator;

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

import com.print.domain.ChangePasswordForm;
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
	
	@ModelAttribute("changePasswordForm")
	public ChangePasswordForm constructChngPwdForm() {
		return new ChangePasswordForm();
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
		
		if(encoder.matches(loginForm.getPassword(), dbUserPwd) && user.getEnabled()) {
			System.out.println("Login Successful");
		} else if(!user.getEnabled()){
			result.reject("login.fail", "User has been locked");
		} else {
			result.reject("login.fail", "Bad username or password");
		}
		
		if(result.hasErrors()) {
			return "login";
		}
		
		Role role = null;
		Iterator<Role> roleItr = user.getRoles().iterator();
		while(roleItr.hasNext()) {
			Role nxtRole = roleItr.next();
			if(nxtRole.getId().equals("ROLE_ADMINISTRATOR")) {
				role = nxtRole;
				break;
			}
			if(nxtRole.getId().equals("ROLE_ADMIN")) {
				role = nxtRole;
			}
		}		
		
		if(role == null) {
			role = new Role();
			role.setId("ROLE_USER");
		}
		
		//authenticate user
		Authentication authentication = new UsernamePasswordAuthenticationToken(j_username, j_password, AuthorityUtils.createAuthorityList(role.getId()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		successHandler.onAuthenticationSuccess(request, response, authentication);
        return null;
    }
	
	@RequestMapping(value="changepassword", method=RequestMethod.POST)
	public String changePassword(@Valid @ModelAttribute("changePasswordForm")ChangePasswordForm changePwdForm, BindingResult result,
    		HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String dbUserPwd = null;
		UserAccount user = printShopDao.getUser(changePwdForm.getUsernamepwdid());
		
		if(changePwdForm.getConfirmpassword().equals(changePwdForm.getNewpassword())) {
			if(user != null) {
				dbUserPwd =  user.getPassword();
			}
			if(encoder.matches(changePwdForm.getCurrentpassword(), dbUserPwd)) {
				user.setPassword(encoder.encode(changePwdForm.getNewpassword()));
				user = printShopDao.changePassword(user);
			} else {
				result.reject("Change password failed", "Invalid old password");
			}
			
		} else {
			result.reject("Change password failed", "Both old and new passwords should match");
		}
		
		user = printShopDao.getUser(changePwdForm.getUsernamepwdid());
		if(!encoder.matches(changePwdForm.getNewpassword(), user.getPassword())) {
			result.reject("Change password failed", "Oops something went wrong!!");
		}
		
		if(result.hasErrors()) {
			return "change_pwd";
		}
		
		Role role = null;
		Iterator<Role> roleItr = user.getRoles().iterator();
		while(roleItr.hasNext()) {
			Role nxtRole = roleItr.next();
			if(nxtRole.getId().equals("ROLE_ADMINISTRATOR")) {
				role = nxtRole;
				break;
			}
			if(nxtRole.getId().equals("ROLE_ADMIN")) {
				role = nxtRole;
			}
		}		
		
		if(role == null) {
			role = new Role();
			role.setId("ROLE_USER");
		}
		
		//authenticate user
		Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList(role.getId()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		successHandler.onAuthenticationSuccess(request, response, authentication);
        return "index";
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
