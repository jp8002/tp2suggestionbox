package br.com.fatecararas.tp2suggestionbox.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fatecararas.tp2suggestionbox.dto.CategoriaDTO;
import br.com.fatecararas.tp2suggestionbox.model.entities.CategoriaEntity;
import br.com.fatecararas.tp2suggestionbox.repositories.CategoriaRepository;

import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository repository;

    public void salvar(CategoriaDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Categoria não pode ser nula");
        }
        var entity = new CategoriaEntity(null, dto.getDescricao());
        repository.save(entity);
    }

    public void exluir(Integer id){
        repository.deleteById(id);
    }

    public List<CategoriaDTO> buscarTodas(){
        return repository.findAll()
                .stream()
                .map(CategoriaDTO::new)
                .toList();
    }

}
