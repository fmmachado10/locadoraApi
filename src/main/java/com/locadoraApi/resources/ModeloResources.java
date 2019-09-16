package com.locadoraApi.resources;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.locadoraApi.dao.ModeloDao;
import com.locadoraApi.modelo.Modelo;

public class ModeloResources {

	@GetMapping
	public List<Modelo> listagem() {
		return ModeloDao.listagem();

	}

	@GetMapping("/{id}")
	public Modelo retornaPorId(@PathVariable int id) {
		return ModeloDao.retornaPorId(id);
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Modelo> inserir(@RequestBody Modelo modelo, HttpServletResponse response) {
		ModeloDao.inserir(modelo);
		modelo.setId(ModeloDao.retornaUltimoId());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(modelo.getId())
				.toUri();
		return ResponseEntity.created(uri).body(modelo);
	}

	@DeleteMapping("/excluir{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Modelo modelo) {
		ModeloDao.excluir(modelo);

	}

	@PutMapping("/{id}")
	public ResponseEntity<Modelo> update(@PathVariable int id, @RequestBody Modelo modelo) {
		Modelo modeloR = ModeloDao.retornaPorId(id);
		BeanUtils.copyProperties(modelo, modeloR, "id");
		ModeloDao.alterar(modeloR);
		return ResponseEntity.ok(modeloR);
	}

}
