import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

import javax.xml.transform.SourceLocator;

public class App {
    private static JsonParser jsonParser;

    public static void main(String[] args) throws Exception {

        // fazer uma conexao http e buscar os tops 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());      
        String body = response.body();
                       
        // pegar so os dados que interessam (titulo, poster, classiifcação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
          
        // exibir e manipular os dados 
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println( "\u001b[1mTítulo:\u001b[m " + filme.get("title"));
            System.out.println( "\u001b[1mURL:\u001b[m " + filme.get("image"));
            System.out.println( "\u001b[37m\u001b[44m " + filme.get("imDbRating") + "\u001b[m");
            double classificacao = Double.parseDouble(filme.get("imDbRating"));
            int numeroEstrelinhas = (int) classificacao;
            for (int n = 1; n <=  numeroEstrelinhas; n++  ) {
                System.out.print("⭐");
            }
            System.out.print("\n");
        }
                
    }

}
