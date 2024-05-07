package br.com.everaldoboscatto.regitersongs.principal;

import java.util.Scanner;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    public void exibeMenu() {
        var opcao = -1;

        while (opcao != 0) {
            var menu = """
                    ******* Register Songs ******* 
                    0 - Sair
                    1 - Cadastrar artistas
                    2 - Listar artistas
                    3 - Cadastrar músicas
                    4 - Listar músicas
                    5 - Buscar músicas por artista
                    6 - Pesquisar dados sobre um artista
                    
                    
                    """;
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
                    buscarMusicaPorArtista();
                    break;
                case 6:
                    pesquisarDadosArtista();
                    break;
                case 0:
                    System.out.println("Encerrando sistema...");
                    break;
                default:
                    break;
            }

        }

    }
    private void cadastrarArtistas() {
    }

    private void buscarArtista() {
    }

    private void cadastrarMusicas() {
    }

    private void buscarMusica() {
    }

    private void buscarMusicaPorArtista() {
    }

    private void pesquisarDadosArtista() {
    }


}
