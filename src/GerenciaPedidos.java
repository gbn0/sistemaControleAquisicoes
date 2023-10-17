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

    public void adicionaPedido(Pedido p ) {
        pedidos.add(p);
        ultimoCodigo++;
    }

    public int getUltimoCodigo() {
        return ultimoCodigo;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
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
            if(!(dataAux == null)) {
                if (dataAux.isAfter(LocalDate.now().minusDays(30)) && pedido.getStatus().equals("Reprovado")) {
                    aux += pedido.getValorTotal();
                }
            }
        }
        return aux;
    }

    public double getValorAprovadosUltimos30Dias() {
        double aux = 0;
        for (Pedido pedido : pedidos) {
            LocalDate dataAux = pedido.getDataConclusao();
            if(!(dataAux == null))  {
                if (dataAux.isAfter(LocalDate.now().minusDays(30)) && pedido.getStatus().equals("Aprovado")) {
                    aux += pedido.getValorTotal();
                }
            }
        }
        return aux;
    }

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
        return (value / total) * 100;
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
        System.out.println("Funcionário: " + pMaior.getUsuario());
        System.out.println("Departamento: " + pMaior.getDepartamento());
        System.out.println("Data abertura: " + pMaior.getDataAbertura());
        System.out.println("Data conclusão: " + pMaior.getDataConclusao());
        System.out.println("Status: " + pMaior.getStatus());
        System.out.println("Itens: " + pMaior.getItens());
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

    public double valorMedioPedidos30Dias() {

        int count = 0;
        double valorTotal = 0;

        for (Pedido p : pedidos) {
            if (p.getDataAbertura().isAfter(ZonedDateTime.now().toLocalDate().minusDays(30))) {
                count++;
                valorTotal += p.getValorTotal();
            }
        }

        return valorTotal / count;
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
        for(Pedido p : pedidos) {
            if(p.getUsuario().equals(u)) {
                func.add(p);
            }
        }
        return func;
    }

    public ArrayList<Pedido> pesquisaPedidoItem(Item i) {
        ArrayList<Pedido> itens = new ArrayList<Pedido>();
        for(Pedido p : pedidos) {
            for(Item i1 : p.getItens()) {
                if(i1.equals(i)) {
                    itens.add(p);
                }
            }
        }
        return itens;
    }
    public boolean excluiPedido(int codigo, Usuario u){
        Pedido p = pesquisaPedido(codigo);
        if(p != null){
            if(p.getUsuario().equals(u)) {
                if(p.getStatus().equals("Aberto")) {
                    pedidos.remove(p);
                    return true;
                }else {
                    System.out.println("Apenas pedidos ainda abertos podem ser excluidos");
                }
            }else {
                System.out.println("Apenas o usuário que criou o pedido pode exclui-lo");
            }
        }else {
            System.out.println("Pedido não encontrado");
        }
        return false;
    }
}
