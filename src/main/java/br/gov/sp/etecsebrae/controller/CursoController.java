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

import br.gov.sp.etecsebrae.model.Curso;

@Controller
public class CursoController {

	//final String urlCurso = "http://localhost:8002/curso";
	final String urlCurso = "https://api-curso-bpedroni.herokuapp.com/curso";
	RestTemplate restTemplate = new RestTemplate();

	@GetMapping({ "/cursos" })
	public ModelAndView get() {
		ModelAndView modelAndView = new ModelAndView("curso/index");

		ArrayList<?> cursos = restTemplate.getForObject(urlCurso, ArrayList.class);
		modelAndView.addObject("cursos", cursos);

		return modelAndView;
	}

	@GetMapping({ "/curso/create" })
	public ModelAndView create() {
		ModelAndView modelAndView = new ModelAndView("curso/create");
		modelAndView.addObject(new Curso());
		return modelAndView;
	}

	@PostMapping({ "/curso/create" })
	public String create(Curso curso) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		Map<String, Object> map = new HashMap<>();
		map.put("sigla", curso.getSigla());
		map.put("nome", curso.getNome());

		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
		restTemplate.postForEntity(urlCurso + "/create", entity, Curso.class);

		return "redirect:/cursos";
	}

	@GetMapping({ "/curso/edit" })
	public ModelAndView edit(String id) {
		Curso curso = restTemplate.getForObject(urlCurso + "/get/" + id, Curso.class);

		ModelAndView modelAndView = new ModelAndView("curso/edit");
		modelAndView.addObject(curso);
		return modelAndView;
	}

	@PostMapping({ "/curso/edit" })
	public String edit(Curso curso) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		Map<String, Object> map = new HashMap<>();
		map.put("sigla", curso.getSigla());
		map.put("nome", curso.getNome());

		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
		restTemplate.put(urlCurso + "/edit", entity, Curso.class);

		return "redirect:/cursos";
	}

	@GetMapping({ "/curso/delete" })
	public ModelAndView delete(String id) {
		Curso curso = restTemplate.getForObject(urlCurso + "/get/" + id, Curso.class);

		ModelAndView modelAndView = new ModelAndView("curso/delete");
		modelAndView.addObject(curso);
		return modelAndView;
	}

	@PostMapping({ "/curso/delete" })
	public String delete(Curso curso) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-HTTP-Method-Override", "DELETE");
		headers.setContentType(MediaType.APPLICATION_JSON);

		Map<String, Object> map = new HashMap<>();
		map.put("sigla", curso.getSigla());
		map.put("nome", curso.getNome());

		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
		restTemplate.exchange(urlCurso + "/delete", HttpMethod.DELETE, entity, Curso.class, map);

		return "redirect:/cursos";
	}
}
