package main.java.com.example.tp2suggestionbox.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fatecararas.tp2suggestionbox.model.entities.CategoriaEntity;


@Repository
public class CategoriaRepository extends JpaRepository<CategoriaEntity, Integer> {
    
}
