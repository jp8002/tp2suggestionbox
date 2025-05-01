package br.com.fatecararas.tp2suggestionbox.dto;

import br.com.fatecararas.tp2suggestionbox.model.entities.CategoriaEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO {
    private Integer id;

    @NotBlank
    private String descricao;

    private list<SugestaoDTO> sugestoes = new ArrayList<Object>();

    public CategoriaDTO(CategoriaEntity entity){
        this.id = entity.getId();
        this.descricao = entity.getDescricao();
    }

}
