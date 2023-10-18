import java.text.DecimalFormat;
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

    public Pedido(int codigo, Usuario usuario, Departamento departamento, LocalDate dataAbertura, ArrayList<Item> itens) {
        this.codigo = codigo;
        this.usuario = usuario;
        this.departamento = departamento;
        this.dataAbertura = dataAbertura;
        this.dataConclusao = null;
        this.status = "Aberto";
        this.itens = itens;
        this.valorTotal = calculaValorTotal();
    }

    private double calculaValorTotal() {
        DecimalFormat d = new DecimalFormat("0.00");
        double valor = 0;
        for(Item i : itens) {
            valor += i.getValor();
        }
        
        return Double.parseDouble(d.format(valor));
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
        this.dataConclusao = LocalDate.now();
    }

    @Override
    public String toString() {
        return ("==================================\n" + "Pedido " + codigo + "\nStatus: " + status + "\nValor: " + valorTotal + "\nData de abertura: " + dataAbertura + "\nFuncionario: \n" + "ID: " + this.getUsuario().getId() + "\nNome: " + this.getUsuario().getNome() +"\n==================================");
    }

    

}
