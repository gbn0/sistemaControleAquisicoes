import java.util.ArrayList;

public class GerenciaItens { //athos
    private ArrayList<Item> itens;
    public GerenciaItens(){
        itens = new ArrayList<Item>();
    }

    public boolean cadastraItem(Item novoItem){
        return itens.add(novoItem);
    }

    public Item pesquisaItem(String descricao){
        for (Item i : itens){
            if(i.getDescricao().equals(descricao)){
                return i;
            }
        }
        return null;
    }
}
