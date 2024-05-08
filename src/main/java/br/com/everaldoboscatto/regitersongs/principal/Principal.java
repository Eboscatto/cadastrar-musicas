package br.com.everaldoboscatto.regitersongs.principal;

import br.com.everaldoboscatto.regitersongs.model.Artista;
import br.com.everaldoboscatto.regitersongs.model.Musica;
import br.com.everaldoboscatto.regitersongs.model.TipoArtista;
import br.com.everaldoboscatto.regitersongs.repository.ArtistaRepository;
import br.com.everaldoboscatto.regitersongs.service.ConsultaChatGPT;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private final ArtistaRepository repositorio;
    private Scanner leitura = new Scanner(System.in);

    public Principal(ArtistaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void exibeMenu() {
        var opcao = -1;

        while (opcao != 0) {
            var menu = """
                    ******* Register Songs ******* 
                    0 - Sair
                    1 - Cadastrar artistas
                    2 - Buscar artistas
                    3 - Cadastrar músicas
                    4 - Listar músicas
                    5 - Buscar músicas por artista
                    6 - Pesquisar dados sobre um artista                   
                    """;

            System.out.println("\n" + menu);
            System.out.println("Opção: ");
            opcao = leitura.nextInt();
            leitura.nextLine();
            switch (opcao) {
                case 1:
                    cadastrarArtistas();
                    break;
                case 2:
                    buscarArtista();
                    break;
                case 3:
                    cadastrarMusicas();
                    break;
                case 4:
                    buscarMusica();
                    break;
                case 5:
                    buscarMusicasPorArtista();
                    break;
                case 6:
                    pesquisarDadosArtista();
                    break;
                case 0:
                    System.out.println("Encerrando sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        }

    }
    private void cadastrarArtistas() {
        var cadastrarNovo =  "S";

        while (cadastrarNovo.equalsIgnoreCase("s")) {
            System.out.println("Informe o nome do artista: ");
            var nomeArtista = leitura.nextLine();
            System.out.println("Informe qual o tipo de carreira do artista: [solo, dupla, trio ou banda]");
            var tipoCarreira = leitura.nextLine();
            TipoArtista tipoArtista = TipoArtista.valueOf(tipoCarreira.toUpperCase());
            Artista artista = new Artista(nomeArtista, tipoArtista);
            repositorio.save(artista);
            System.out.println("Cadastrar novo artista? (S/N)");
            cadastrarNovo = leitura.nextLine();
        }
    }

    private void buscarArtista() {
        System.out.println("Qual artista deseja buscar?");
        var nomeArtista = leitura.nextLine();
        Optional<Artista> artistaBuscado = repositorio.findByNomeContainingIgnoreCase(nomeArtista);
        System.out.println("\nResultado:");
        if (artistaBuscado.isPresent()) {
            System.out.println(artistaBuscado.get().getNome());
        } else {
            System.out.println("Artista não encontrado!");
        }
    }

    private void cadastrarMusicas() {
        System.out.println("Cadastrar música de qual artista? ");
        var nomeDoArtista = leitura.nextLine();
        Optional<Artista> artista = repositorio.findByNomeContainingIgnoreCase(nomeDoArtista);
        if (artista.isPresent()) {
            System.out.println("Informe o nome da música: ");
            var nomeDaMusica = leitura.nextLine();

            // Criar um novo objeto Musica
            Musica musica = new Musica(nomeDaMusica);

            // Criar um link/setar a música no artista, música recebe artista
            musica.setArtista(artista.get());

            // Criar um link/setar o artista na música, artista recebe música
            artista.get().getMusicas().add(musica);

            // Salvar no Bando de Dados
            repositorio.save(artista.get());
        } else {
            System.out.println("Artista não encontrado!");
        }
    }

    private void buscarMusica() {
        // Fazer uma busca de músicas utilizando a Derived Queries
        List<Artista> artistas = repositorio.findAll();
        // Imprimir o artista e suas músicas
        artistas.forEach(a -> a.getMusicas().forEach(System.out::println));
    }

    private void buscarMusicasPorArtista() {
        System.out.println("Buscar músicas de qual artista?");
        var nomeArtista = leitura.nextLine();
        List<Musica> musicas = repositorio.buscarMusicasPorArtista(nomeArtista);
        musicas.forEach(System.out::println);
    }

    private void pesquisarDadosArtista() {
        System.out.println("Pesquisar dados sobre qual artista? ");
        var nomeArtista = leitura.nextLine();
        System.out.println("\nResultado da pesquisa:");
        var resposta = ConsultaChatGPT.obterInformacao(nomeArtista);
        System.out.println(resposta.trim());
    }
}
