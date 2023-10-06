import java.time.LocalDate;
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

    public void verDetalhesPedidoMaiorValorAberto () {
        double maior = pedidos.get(0).getValorTotal();
        for (Pedido p : pedidos) {
            if (p.getStatus().equals("Aberto")) {
                if (p.getValorTotal() > maior) {
                    System.out.println("Detalhes do pedido de maior valor ainda aberto:");
                    System.out.println("Código: "+p.getCodigo());
                    System.out.println("Funcionário: "+p.getFuncionario());
                    System.out.println("Departamento: "+p.getDepartamento());
                    System.out.println("Data abertura: "+p.getDataAbertura());
                    System.out.println("Data conclusão: "+p.getDataConclusao());
                    System.out.println("Status: "+p.getStatus());
                    System.out.println("Itens: "+p.getItens());
                    System.out.println("Valor total: "+p.getValorTotal());
                }
            }
        }
    }

    public ArrayList<Pedido> listarPedidoEntreDatas(LocalDate dataInicio, LocalDate dataFinal) {
        ArrayList<Pedido> aux = new ArrayList<>();

        for (Pedido p : pedidos) {
            if (p.getDataAbertura().isAfter(dataInicio) && p.getDataAbertura().isBefore(dataFinal)) {
                aux.add(p);
            }
        }

        return aux;

    }

}
