package com.pruebatecnica.crud.CRUDCLIENTES.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.pruebatecnica.crud.CRUDCLIENTES.model.*;

@RestController
@RequestMapping("/clientes")
public class ClienteRestController {
	
	private ClienteJpaRepository clienteRepository ;
	
	@Autowired
	public ClienteRestController(ClienteJpaRepository cliente) {
		this.clienteRepository=cliente;
	}
	
	@GetMapping
	 public List<Cliente> getAllClientes() {
	        return clienteRepository.findAll();
	    }
	
	
	@PostMapping("/buscar")
	 public ResponseEntity<Cliente> buscarCliente(@RequestBody Integer id) {
		
			 Optional <Cliente> cliente=clienteRepository.findById(id);
			 return ResponseEntity.ok(cliente.get());
		
	    }
	
	@PostMapping("/nuevoCliente")
	public ResponseEntity<Cliente> guardarCliente(@RequestBody Cliente cliente) {
		Cliente saveCliente=clienteRepository.save(cliente);
		return ResponseEntity.ok(saveCliente);
	}
	
	
	@PutMapping("/actualizarCliente/{id}")
	public ResponseEntity<Cliente> actualizarCliente(@PathVariable Integer id,@RequestBody Cliente cliente) {
		if(clienteRepository.findById(id).isPresent()) {
			cliente.setId_cliente(id);
			Cliente saveCliente=clienteRepository.save(cliente);
			return ResponseEntity.ok(saveCliente);	
		}else {
			return ResponseEntity.notFound().build();
		}
		
		
	}
	
	
	@PostMapping("/eliminarCliente")
	public ResponseEntity<String> eliminarCliente(@RequestBody Integer id){
		
		if(clienteRepository.findById(id).isPresent()) {
			clienteRepository.deleteById(id);
			return ResponseEntity.ok().body("Registro De Cliente Eliminado Correctamente");
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro Cliente con este id");
		}
		
		
	}
	
	
}
