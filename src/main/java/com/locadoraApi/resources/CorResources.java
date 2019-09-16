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

import com.locadoraApi.dao.CorDao;
import com.locadoraApi.modelo.Cor;

public class CorResources {

	@GetMapping
	public List<Cor> listagem() {
		return CorDao.listagem();

	}

	@GetMapping("/{id}")
	public Cor retornaPorId(@PathVariable int id) {
		return CorDao.retornaPorId(id);
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Cor> inserir(@RequestBody Cor cor, HttpServletResponse response) {
		CorDao.inserir(cor);
		cor.setId(CorDao.retornaUltimoId());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(cor.getId()).toUri();
		return ResponseEntity.created(uri).body(cor);
	}

	@DeleteMapping("/excluir/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		CorDao.excluir(id);

	}

	@PutMapping("/alterar/{id}")
	public ResponseEntity<Cor> update(@PathVariable int id, @RequestBody Cor modelo) {
		Cor modeloR = CorDao.retornaPorId(id);
		BeanUtils.copyProperties(modelo, modeloR, "id");
		CorDao.alterar(modeloR);
		return ResponseEntity.ok(modeloR);
	}

}
