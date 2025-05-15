package br.com.fatecararas.tp2suggestionbox.resources;

import br.com.fatecararas.tp2suggestionbox.dto.SugestaoDTO;
import br.com.fatecararas.tp2suggestionbox.model.entities.SugestaoEntity;
import br.com.fatecararas.tp2suggestionbox.repositories.SugestaoRepository;
import br.com.fatecararas.tp2suggestionbox.services.SugestaoService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/sugestoes")
public class SugestaoResource {

    private final SugestaoRepository repository;
    private final ModelMapper mapper;

    @PostMapping
    public SugestaoDTO salvar(@Valid @RequestBody SugestaoDTO dto) {
        SugestaoEntity entity = mapper.map(dto, SugestaoEntity.class);
        return mapper.map(repository.save(entity), SugestaoDTO.class);
    }

    @GetMapping
    public List<SugestaoDTO> buscarTodas() {
        return repository.findAll()
                .stream()
                .map( c -> mapper.map(c, SugestaoDTO.class))
                .toList();
    }


    @GetMapping("/{id}")
    public SugestaoDTO buscarId(@PathVariable int id){
        Optional<SugestaoEntity> entity = repository.findById(id);

        return mapper.map(entity, SugestaoDTO.class);

    }

    @DeleteMapping("/deletar/{id}")
    public void ecxluir(@PathVariable int id){
        repository.deleteById(id);
    }

    @PutMapping("/editar")
    public void editar (@Valid @RequestBody SugestaoDTO dto){
        SugestaoEntity entity = mapper.map(dto, SugestaoEntity.class);
        repository.save(entity);
    }
}
