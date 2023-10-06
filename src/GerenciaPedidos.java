
    //metodo para buscar pedidos pela descrição qnd a classe for implementada 
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

    

