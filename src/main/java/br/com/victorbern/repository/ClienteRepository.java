package br.com.victorbern.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import br.com.victorbern.model.ClienteModel;

public interface ClienteRepository extends Repository<ClienteModel, Integer> {
	
	void save (ClienteModel cliente);
	
	void delete (ClienteModel cliente);
	
	List<ClienteModel> findAll();
	
	ClienteModel findByCpf(String cpf);
	
	List<ClienteModel> findByNomeContainingIgnoreCase(String nome);
	
	List<ClienteModel> findByTelefone(String telefone);
}
