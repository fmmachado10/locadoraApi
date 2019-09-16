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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.locadoraApi.dao.MarcaDao;
import com.locadoraApi.modelo.Marca;

@RestController
@RequestMapping("marca")
public class MarcaResources {
	
	@GetMapping
	public List<Marca> listagem() {
		return MarcaDao.listagem();
	}
	
	@GetMapping("/{id}")
	public Marca retonarPorId(@PathVariable int id) {
		return MarcaDao.retornaPorId(id);
	}
	
	@PostMapping
	public ResponseEntity<Marca> inserir(@RequestBody Marca marca, HttpServletResponse response) {
		
		MarcaDao.inserir(marca);

		marca.setId(MarcaDao.retornaUltimoId());
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(marca.getId()).toUri();
		//response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(marca);
	}
		
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		MarcaDao.excluir(id);
		// Desafio - Retornar corretamente o Status
	}

	@PutMapping("/{id}")
	public ResponseEntity<Marca> update(@PathVariable int id, @RequestBody Marca marca) {
		
		Marca marcaBanco = MarcaDao.retornaPorId(id);
		
		BeanUtils.copyProperties(marca, marcaBanco, "id");
		
		MarcaDao.alterar(marcaBanco);
		
		return ResponseEntity.ok(marcaBanco);
		
	}

}
