package br.com.everaldoboscatto.regitersongs.repository;

import br.com.everaldoboscatto.regitersongs.model.Artista;
import br.com.everaldoboscatto.regitersongs.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    Optional<Artista> findByNomeContainingIgnoreCase(String nomeDoArtista);

    // Buscar músicas de um determinado artista utilizando a JPQL
    @Query("SELECT m FROM Artista a JOIN a.musicas m WHERE a.nome ILIKE %:nome")
    List<Musica> buscarMusicasPorArtista(String nome);
}
