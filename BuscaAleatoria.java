import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BuscaAleatoria<T> implements IBuscador<T> {

    private long comparacoes;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private T[] dados;

    public BuscaAleatoria(T[] dados) {
        this.dados = dados;
    }

    @Override
    public long getComparacoes() {
        return comparacoes;
    }

    @Override
    public double getTempo() {
        if (inicio == null)
            throw new IllegalStateException("Não foi feita nenhuma busca.");

        return Duration.between(inicio, fim).toNanos();
    }

    @Override
    public T buscar(T dado) {
        comparacoes = 0;
        T encontrado = null;

        List<T> lista = new ArrayList<>(Arrays.asList(dados));
        Collections.shuffle(lista);

        inicio = LocalDateTime.now();
        for (T item : lista) {
            comparacoes++;
            if (item.equals(dado)) {
                encontrado = item;
                break;
            }
        }
        fim = LocalDateTime.now();

        return encontrado;
    }
}
