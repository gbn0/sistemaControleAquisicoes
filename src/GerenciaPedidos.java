import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;

public class GerenciaPedidos {
    private ArrayList<Pedido> pedidos;
    private int ultimoCodigo;

    public GerenciaPedidos() {
        pedidos = new ArrayList<Pedido>();
        ultimoCodigo = 1;
    }

    public void adicionaPedido(Pedido p) {
        pedidos.add(p);
        ultimoCodigo++;
    }

    public int getUltimoCodigo() {
        return ultimoCodigo;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    /**
     * Calcula a porcentagem dos pedidos aprovados.
     * @return a porcentage dos pedidos aprovados.
     */
    public String getPercentAprovados() {
        DecimalFormat d = new DecimalFormat("0.00");
        int aux = 0;
        for (Pedido pedido : pedidos) {
            if (pedido.getStatus().equals("Aprovado")) {
                aux++;
            }
        }
        return d.format((getPercent(aux, pedidos.size())));
    }

    /**
     * Calcula a porcentagem dos pedidos reprovados.
     * @return a porcentage dos pedidos reprovados.
     */
    public String getPercentReprovados() {
        DecimalFormat d = new DecimalFormat("0.00");
        int aux = 0;
        for (Pedido pedido : pedidos) {
            if (pedido.getStatus().equals("Reprovado")) {
                aux++;
            }
        }
        return d.format((getPercent(aux, pedidos.size())));
    }

    /**
     * Cacula o valor total dos pedidos reprovados nos ultimos 30 dias.
     *
     * @return the total value of all rejected orders in the last 30 days.
     */
    public String getValorReprovadosUltimos30Dias() {
        DecimalFormat d = new DecimalFormat("0.00");
        double aux = 0;
        for (Pedido pedido : pedidos) {
            LocalDate dataAux = pedido.getDataConclusao();
            if (!(dataAux == null)) {
                if (dataAux.isAfter(LocalDate.now().minusDays(30)) && pedido.getStatus().equals("Reprovado")) {
                    aux += pedido.getValorTotal();
                }
            }
        }
        return d.format(aux);
    }

    /**
     * Cacula o valor total dos pedidos aprovados nos ultimos 30 dias.
     *
     * @return o valor total dos pedidos aprovados nos ultimos 30 dias.
     */
    public String getValorAprovadosUltimos30Dias() {
        DecimalFormat d = new DecimalFormat("0.00");
        double aux = 0;
        for (Pedido pedido : pedidos) {
            LocalDate dataAux = pedido.getDataConclusao();
            if (!(dataAux == null)) {
                if (dataAux.isAfter(LocalDate.now().minusDays(30)) && pedido.getStatus().equals("Aprovado")) {
                    aux += pedido.getValorTotal();
                }
            }
        }
        return d.format(aux);
    }

    /**
     * Retorna o numero de pedidos no sistema.
     *
     * @return o numero total de pedidos no sistema.
     */
    public int getQuantidadePedidos() {
        return pedidos.size();
    }

    /**
     * Calcula a porcentagem do valor em relação ao total.
     * @param value o valor para calcular a porcentagem de.
     * @param total o valor total para calcular a porcentagem.
     * @return a porcentagem do valor em relação ao total.
     */
    private double getPercent(int value, int total) {
        return ((double)value / (double)total) * 100;
    }

    public void verDetalhesPedidoMaiorValorAberto() {
        double maior = pedidos.get(0).getValorTotal();
        Pedido pMaior = null;
        for (Pedido p : pedidos) {
            if (p.getStatus().equals("Aberto")) {
                if (p.getValorTotal() > maior) {
                    pMaior = p;
                    maior = p.getValorTotal();
                }
            }
        }

        System.out.println("Detalhes do pedido de maior valor ainda aberto:");
        System.out.println("Código: " + pMaior.getCodigo());
        System.out.println("Funcionário: " + "\nID: " + pMaior.getUsuario().getId() + "\nNome: " + pMaior.getUsuario().getNome());
        System.out.println("Departamento: " + pMaior.getDepartamento().getNome());
        System.out.println("Data abertura: " + pMaior.getDataAbertura());
        System.out.println("Valor total: " + pMaior.getValorTotal());
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

    public int totalPedidos30Dias() {
        int aux = 0;

        for (Pedido p : pedidos) {
            if (p.getDataAbertura().isAfter(ZonedDateTime.now().toLocalDate().minusDays(30))) {
                aux++;
            }
        }

        return aux;
    }

    public String valorMedioPedidos30Dias() {
        DecimalFormat d = new DecimalFormat("0.00");
        int count = 0;
        double valorTotal = 0;

        for (Pedido p : pedidos) {
            if (p.getDataAbertura().isAfter(ZonedDateTime.now().toLocalDate().minusDays(30))) {
                count++;
                valorTotal += p.getValorTotal();
            }
        }

        return d.format(valorTotal / count);
    }

    public Pedido pesquisaPedido(int codigo) {
        Pedido aux = null;
        for (Pedido p : pedidos) {
            if (p.getCodigo() == codigo) {
                aux = p;
            }
        }
        return aux;
    }

    public ArrayList<Pedido> pesquisaPedidoFuncionario(Usuario u) {
        ArrayList<Pedido> func = new ArrayList<Pedido>();
        for (Pedido p : pedidos) {
            if (p.getUsuario().equals(u)) {
                func.add(p);
            }
        }
        return func;
    }

    public ArrayList<Pedido> pesquisaPedidoItem(Item i) {
        ArrayList<Pedido> itens = new ArrayList<Pedido>();
        for (Pedido p : pedidos) {
            for (Item i1 : p.getItens()) {
                if (i1.equals(i)) {
                    itens.add(p);
                }
            }
        }
        return itens;
    }

    public boolean excluiPedido(int codigo, Usuario u) {
        Pedido p = pesquisaPedido(codigo);
        if (p != null) {
            if (p.getUsuario().equals(u)) {
                if (p.getStatus().equals("Aberto")) {
                    pedidos.remove(p);
                    System.out.println("Pedido excluido com sucesso");
                    return true;
                } else {
                    System.out.println("Apenas pedidos ainda abertos podem ser excluidos");
                }
            } else {
                System.out.println("Apenas o usuário que criou o pedido pode exclui-lo");
            }
        } else {
            System.out.println("Pedido não encontrado");
        }
        return false;
    }
}
