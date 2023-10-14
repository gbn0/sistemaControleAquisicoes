import java.time.LocalDate;
import java.util.ArrayList;

public class Pedido {
    
    private int codigo;
    private Usuario usuario;
    private Departamento departamento;
    private LocalDate dataAbertura;
    private LocalDate dataConclusao;
    private String status;
    private ArrayList<Item> itens;
    private double valorTotal;

    public Pedido(int codigo, Usuario usuario, Departamento departamento, LocalDate dataAbertura) {
        this.codigo = codigo;
        this.usuario = usuario;
        this.departamento = departamento;
        this.dataAbertura = dataAbertura;
        this.dataConclusao = null;
        this.status = "Aberto";
        this.itens = new ArrayList<Item>();
        this.valorTotal = calculaValorTotal();
    }

    private double calculaValorTotal() {    
        double valor = 0;
        for(Item i : itens) {
            valor += i.getValor();
        }
        return valor;
    }

    public int getCodigo() {
        return codigo;
    }
    public Usuario getUsuario() {
        return usuario;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public LocalDate getDataConclusao() {
        return dataConclusao;
    }

    public String getStatus() {
        return status;
    }

    public ArrayList<Item> getItens() {
        return itens;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Pedido " + codigo + "\nStatus: " + status + "\nValor: " + valorTotal + "\nData de abertura: " + dataAbertura;
    }

    

}
