package com.pruebatecnica.crud.CRUDCLIENTES.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.pruebatecnica.crud.CRUDCLIENTES.model.*;

import jakarta.validation.Valid;

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
	
	
	@PostMapping("/buscar/{id}")
	 public ResponseEntity<MessageResponse> buscarCliente(@PathVariable Integer id) {
		
			 Optional <Cliente> cliente=clienteRepository.findById(id);
			
					 
				
					 
					 if(cliente.isPresent()) {
						 MessageResponse message=new MessageResponse();
						 message.setStatus(HttpStatus.OK);
						 message.setErrores(null);
						 message.setResponse(cliente.get());
						 message.setMensaje("Cliente Encontrado Con Exito");
						 return new ResponseEntity<>(message,HttpStatus.OK);
						 
					 }else {
						 MessageResponse message=new MessageResponse();
						 message.setStatus(HttpStatus.NOT_FOUND);
						 message.setResponse(null);
						 message.setMensaje("No Se encontro el cliente solicitado");
						 message.setErrores(HttpStatus.NOT_FOUND.getReasonPhrase());
							
						return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
					 }
						 
					
				
			
					
			
			 
			}
			
		
	    
	
	@PostMapping("/nuevoCliente")
	public ResponseEntity<MessageResponse> guardarCliente(@Valid @RequestBody Cliente cliente) {
		
		try {
			MessageResponse message=new MessageResponse();
		
			Cliente saveCliente=clienteRepository.save(cliente);
			
			message.setResponse(saveCliente);
			message.setErrores(null);
			message.setMensaje(" Nuevo Cliente Creado Con Exito");
			message.setStatus(HttpStatus.OK);
			
			return new ResponseEntity<>(message,HttpStatus.OK);
		
			
		}catch(Exception e) {
			MessageResponse message=new MessageResponse();
			message.setResponse(null);
			message.setErrores(e.getMessage());
			message.setMensaje("Error al Guardar Los Datos del Cliente");
			message.setStatus(HttpStatus.BAD_REQUEST);
			
			return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
		}
		
	}	
		
		
	
	
	@PutMapping("/actualizarCliente/{id}")
	public ResponseEntity<MessageResponse> actualizarCliente(@PathVariable Integer id,@Valid @RequestBody Cliente cliente,BindingResult result) {
		if(clienteRepository.findById(id).isPresent()) {
			cliente.setId_cliente(id);
			cliente.setFecha_registro(clienteRepository.findById(id).get().getFecha_registro());
			Cliente saveCliente=clienteRepository.save(cliente);
			
			MessageResponse message=new MessageResponse();
			message.setResponse(saveCliente);
			message.setErrores(result.getFieldErrors().toString());
			message.setMensaje("Datos del Cliente Actualizados Correctamente");
			message.setStatus(HttpStatus.OK);
			
		 return new ResponseEntity<>(message,HttpStatus.OK);
		}else {
			MessageResponse message=new MessageResponse();
			message.setResponse(null);
			message.setErrores(result.getFieldErrors().toString());
			message.setMensaje("No Fue posible actualizar el cliente".concat("no existe el cliente con id " + id));
			message.setStatus(HttpStatus.NOT_FOUND);
			 return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	
	@PostMapping("/eliminarCliente/{id}")
	public ResponseEntity<MessageResponse> eliminarCliente(@PathVariable Integer id){
		
		
		MessageResponse message=new MessageResponse();
		
		//Comentario Añadido
		if(clienteRepository.findById(id).isPresent()) {
			message.setErrores(null);
			message.setMensaje("El Cliente Fue Eliminado Con Exito");
			message.setResponse(null);
			message.setStatus(HttpStatus.OK);
			clienteRepository.deleteById(id);
			return new ResponseEntity(message,HttpStatus.OK);
			
		}else {
			return new ResponseEntity(message,HttpStatus.NOT_FOUND);

		}
		
		
	}
	
	
}
