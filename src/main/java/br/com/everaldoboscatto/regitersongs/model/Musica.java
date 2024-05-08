package br.com.everaldoboscatto.regitersongs.model;

import jakarta.persistence.*;

@Entity
@Table(name = "musicas")
public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeMusica;
    @ManyToOne // Relação Bidireconal com Artista - muitos para um.
    private Artista artista;

    public Musica() {

    }

    // Musica recebe nomeDaMusica
    public Musica(String nomeDaMusica) {
        this.nomeMusica = nomeDaMusica;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nomeMusica;
    }

    public void setNome(String nome) {
        this.nomeMusica = nome;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    @Override
    public String toString() {
        return "Musica= '" + nomeMusica + '\'' +
                ", artista=" + artista.getNome();
    }
}