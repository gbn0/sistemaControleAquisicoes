import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class GerenciaPedidos {
    private ArrayList<Pedido> pedidos;

    public GerenciaPedidos() {
        pedidos = new ArrayList<Pedido>();
    }

    public double getPercentAprovados() {
        int aux = 0;
        for (Pedido pedido : pedidos) {
            if (pedido.getStatus().equals("Aprovado")) {
                aux++;
            }
        }
        return getPercent(aux, pedidos.size());
    }

    public double getPercentReprovados() {
        int aux = 0;
        for (Pedido pedido : pedidos) {
            if (pedido.getStatus().equals("Reprovado")) {
                aux++;
            }
        }
        return getPercent(aux, pedidos.size());
    }

    public double getValorReprovadosUltimos30Dias() {
        double aux = 0;
        for (Pedido pedido : pedidos) {
            LocalDate dataAux = pedido.getDataConclusao();

            if (dataAux.isAfter(LocalDate.now().minusDays(30)) && pedido.getStatus().equals("Reprovado")) {
                aux += pedido.getValorTotal();
            }
        }
        return aux;
    }

    public double getValorAprovadosUltimos30Dias() {
        double aux = 0;
        for (Pedido pedido : pedidos) {
            LocalDate dataAux = pedido.getDataConclusao();

            if (dataAux.isAfter(LocalDate.now().minusDays(30)) && pedido.getStatus().equals("Aprovado")) {
                aux += pedido.getValorTotal();
            }
        }
        return aux;
    }

    public int getQuantidadePedidos() {
        return pedidos.size();
    }

    private double getPercent(int value, int total) {
        return (value / total) * 100;
    }

}
