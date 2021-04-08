package br.gov.sp.etecsebrae.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import br.gov.sp.etecsebrae.model.Login;

@Controller
public class LoginController {

	//final String urlLogin = "http://localhost:8001/login";
	final String urlLogin = "https://api-login-bpedroni.herokuapp.com/login";
	RestTemplate restTemplate = new RestTemplate();

	@GetMapping({ "/logins" })
	public ModelAndView get() {
		ModelAndView modelAndView = new ModelAndView("login/index");

		ArrayList<?> logins = restTemplate.getForObject(urlLogin, ArrayList.class);
		modelAndView.addObject("logins", logins);

		return modelAndView;
	}

	@GetMapping({ "/login/create" })
	public ModelAndView create() {
		ModelAndView modelAndView = new ModelAndView("login/create");
		modelAndView.addObject(new Login());
		return modelAndView;
	}

	@PostMapping({ "/login/create" })
	public String create(Login login) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		Map<String, Object> map = new HashMap<>();
		map.put("login", login.getLogin());
		map.put("nome", login.getNome());
		map.put("password", login.getPassword());
		map.put("tipo", login.getTipo());

		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
		restTemplate.postForEntity(urlLogin + "/create", entity, Login.class);

		return "redirect:/logins";
	}

	@GetMapping({ "/login/edit" })
	public ModelAndView edit(String id) {
		Login login = restTemplate.getForObject(urlLogin + "/get/" + id, Login.class);

		ModelAndView modelAndView = new ModelAndView("login/edit");
		modelAndView.addObject(login);
		return modelAndView;
	}

	@PostMapping({ "/login/edit" })
	public String edit(Login login) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		Map<String, Object> map = new HashMap<>();
		map.put("login", login.getLogin());
		map.put("nome", login.getNome());
		map.put("password", login.getPassword());
		map.put("tipo", login.getTipo());

		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
		restTemplate.put(urlLogin + "/edit", entity, Login.class);

		return "redirect:/logins";
	}

	@GetMapping({ "/login/delete" })
	public ModelAndView delete(String id) {
		Login login = restTemplate.getForObject(urlLogin + "/get/" + id, Login.class);

		ModelAndView modelAndView = new ModelAndView("login/delete");
		modelAndView.addObject(login);
		return modelAndView;
	}

	@PostMapping({ "/login/delete" })
	public String delete(Login login) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-HTTP-Method-Override", "DELETE");
		headers.setContentType(MediaType.APPLICATION_JSON);

		Map<String, Object> map = new HashMap<>();
		map.put("login", login.getLogin());
		map.put("nome", login.getNome());
		map.put("password", login.getPassword());
		map.put("tipo", login.getTipo());

		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
		restTemplate.exchange(urlLogin + "/delete", HttpMethod.DELETE, entity, Login.class, map);

		return "redirect:/logins";
	}
}
