import java.util.ArrayList;

public class GerenciaPedidos {
    private ArrayList<Pedido> pedidos; 
    public GerenciaPedidos(){
        pedidos = new ArrayList<>(pedidos);
    }

    public ArrayList<Pedido> buscaPedidoDesc(String descricao){
        ArrayList<Pedido> pedidoDescricao = new ArrayList<Pedido>();   
        for (Pedido p : pedidos){
            ArrayList <Item> v = p.getItens();
            for (Item i : v){
                if (i.equals(descricao)){
                    pedidoDescricao.add(p);
                }
            }
        }
        return pedidoDescricao;
    }

    public ArrayList<Pedido> buscaPedidoFunc(int id){
        ArrayList<Pedido> pedidoFuncionario = new ArrayList<Pedido>();
        for (Pedido p : pedidos){
                if(p.getFuncionario().getId() == id){
                    pedidoFuncionario.add(p);
                }
            }
            return pedidoFuncionario;
        }
        
        
    }