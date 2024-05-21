package br.com.everaldoboscatto.regitersongs.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artistas")
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    @Column(unique = true)
    private String nome;
    @Enumerated(EnumType.STRING)
    private TipoCarreira tipo;

    // // Relação Bidireconal com Musica - um para muitos.
    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Musica> musicas = new ArrayList<>();

    public Artista() {

    }
    public Artista(String nomeArtista, TipoCarreira tipoCarreira) {
        this.nome = nomeArtista;
        this.tipo = tipoCarreira;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoCarreira getTipo() {
        return tipo;
    }

    public void setTipo(TipoCarreira tipo) {
        this.tipo = tipo;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }
    @Override
    public String toString() {
        return "Artistae='" + nome + '\'' +
                ", tipo=" + tipo +
                ", musicas=" + musicas;

    }
}
