package br.com.victorbern.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.victorbern.model.ClienteModel;
import br.com.victorbern.model.ResponseModel;
import br.com.victorbern.repository.ClienteRepository;

@RestController
@RequestMapping("/service")
public class ClienteService {
	@Autowired
	ClienteRepository clienteRepository;
	
	/**
	 * SALVAR UM NOVO REGISTRO
	 * @param cliente
	 * @return
	 */
	@RequestMapping(value="/cliente", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel salvar(@RequestBody ClienteModel cliente){
		
		try {
 
			this.clienteRepository.save(cliente);
 
			return new ResponseModel(1,"Registro salvo com sucesso!");
 
		}catch(Exception e) {
 
			return new ResponseModel(0,e.getMessage());			
		}
		
	}
	
	/**
	 * ATUALIZAR O REGISTRO DE UMA PESSOA
	 * @param cliente
	 * @return
	 */
	@RequestMapping(value="/cliente", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel atualizar(@RequestBody ClienteModel cliente) {
		
		try {
			this.clienteRepository.save(cliente);
			
			return new ResponseModel(1, "Registro atualizado com sucesso!");
			
		} catch (Exception e) {
			
			return new ResponseModel(0, e.getMessage());
		}
	}
	
	/**
	 * CONSULTAR TODOS OS CLIENTES
	 * @return
	 */
	@RequestMapping(value="/cliente", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<ClienteModel> consultar(){
		
		return this.clienteRepository.findAll();
	}
	
	/**
	 * CONSULTAR CLIENTE PELO NOME
	 * @param nome
	 * @return
	 */
	@RequestMapping(value="/cliente/nome/{nome}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ClienteModel> buscarPeloNome(@PathVariable("nome") String nome) {
		
		return this.clienteRepository.findByNomeContainingIgnoreCase(nome);
	}
	
	/**
	 * CONSULTAR CLIENTE PELO TELEFONE
	 * @param telefone
	 * @return
	 */
	@RequestMapping(value="/cliente/telefone/{telefone}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<ClienteModel> buscarPeloTelefone(@PathVariable("telefone") String telefone) {
		return this.clienteRepository.findByTelefone(telefone);
	}
	
	/***
	 * EXCLUIR UM REGISTRO PELO CÓDIGO
	 * @param cpf
	 * @return
	 */
	@RequestMapping(value="/cliente/{cpf}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel excluir(@PathVariable("cpf") String cpf) {
		ClienteModel clienteModel = clienteRepository.findByCpf(cpf);
		
		try {
			clienteRepository.delete(clienteModel);
			return new ResponseModel(1, "Registro deletado com sucesso");
		} catch (Exception e) {
			return new ResponseModel(1, e.getMessage());
		}
		
		
	}
}