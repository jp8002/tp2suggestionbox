package br.com.fatecararas.tp2suggestionbox.resources;

import br.com.fatecararas.tp2suggestionbox.model.entities.CategoriaEntity;
import jakarta.validation.Valid;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fatecararas.tp2suggestionbox.dto.CategoriaDTO;
import br.com.fatecararas.tp2suggestionbox.services.CategoriaService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriaResource {

    private final CategoriaService service;

    public CategoriaResource(CategoriaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> salvar(@Valid @RequestBody CategoriaDTO dto) {
        try {
            CategoriaDTO categoria = service.salvar(dto);

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(categoria.getId())
                    .toUri();

            return ResponseEntity.created(uri).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/todas")
    public ResponseEntity<?> buscarTodas() {
        List<CategoriaDTO> categorias = service.buscarTodas();
        if (categorias.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma categoria encontrada");
        }
        return ResponseEntity.status(HttpStatus.OK).body(categorias);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Integer id) {
        service.excluir(id);
    }

    @GetMapping("/{id}")
    public CategoriaDTO buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/editar")
    public void editar( @Valid @RequestBody CategoriaDTO dto ){

        service.editar(dto);
    }


    @GetMapping("/descricao/{descricao}")

    public CategoriaDTO buscarDescricao( @PathVariable String descricao ){

        return service.buscarDescricao(descricao);
    }
}
