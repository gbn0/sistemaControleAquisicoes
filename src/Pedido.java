import java.time.ZonedDateTime;
import java.util.ArrayList;

public class Pedido {
    
    private Funcionario funcionario;
    private Departamento departamento;
    private ZonedDateTime dataAbertura;
    private ZonedDateTime dataConclusao;
    private String status;
    private ArrayList<Item> itens;
    private double valorTotal;

    public Pedido(Funcionario funcionario, Departamento departamento, ZonedDateTime dataAbertura) {
        this.funcionario = funcionario;
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

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public ZonedDateTime getDataAbertura() {
        return dataAbertura;
    }

    public ZonedDateTime getDataConclusao() {
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

    

}
