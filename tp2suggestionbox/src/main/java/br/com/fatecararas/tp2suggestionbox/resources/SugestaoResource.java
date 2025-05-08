package br.com.fatecararas.tp2suggestionbox.resources;

import java.util.List;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import br.com.fatecararas.tp2suggestionbox.dto.SugestaoDTO;
import br.com.fatecararas.tp2suggestionbox.model.entities.SugestaoEntity;
import br.com.fatecararas.tp2suggestionbox.repositories.SugestaoRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/sugestoes")
public class SugestaoResource {

    private final SugestaoRepository repository;
    private final ModelMapper mapper;

    public SugestaoResource(SugestaoRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @PostMapping
    public SugestaoDTO salvar(@Valid @RequestBody SugestaoDTO dto){
        SugestaoEntity entity = mapper.map(dto, SugestaoEntity.class);
        return mapper.map(repository.save(entity), SugestaoDTO.class);
    }

    @GetMapping
    public List<SugestaoDTO> buscarTodos(){
        return repository.findAll()
        .stream()
        .map( c -> mapper.map(c, SugestaoDTO.class))
        .toList();
    }

}
