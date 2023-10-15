import java.io.BufferedReader;
import java.io.File;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;

public class SistemaControle {
    
    private Scanner in = null;
    private GerenciaPedidos gerenciaPedidos;
    private GerenciaDepartamentos gerenciaDepartamentos;
    private GerenciaUsuarios gerenciaUsuarios;
    private GerenciaItens gerenciaItens;
    private Usuario usuario = null;

    public SistemaControle() {
        try {
            BufferedReader streamEntrada = new BufferedReader(new FileReader("dados.txt"));
            in = new Scanner(streamEntrada);
        }catch(Exception e) {
            System.out.println(e);
        }

        this.gerenciaDepartamentos = new GerenciaDepartamentos();
        this.gerenciaPedidos = new GerenciaPedidos();
        this.gerenciaUsuarios = new GerenciaUsuarios();
    }

    public void executa() {
        carregaDados();
        restauraEntrada();
        Usuario u = null;
        while(u == null) {
            System.out.println("Digite o nome do usuário que está utilizando o sistema");
            String nome = in.nextLine();
            u = gerenciaUsuarios.pesquisaUsuarioNome(nome);
            if(u == null) {
                System.out.println("Usuário não encontrado digite novamente");
            }
        }
        this.usuario = u;
        loop();
    }

    private void loop() {
        int opcao;
        do {
            menuPrincipal();
            System.out.print("Digite a opção desejada: ");
            opcao = in.nextInt();
            in.nextLine();
            switch(opcao) {
                case 1:
                    menuUsuário();
                    System.out.print("Digite a opção desejada: ");
                    int opcao2 = in.nextInt();
                    in.nextLine();
                    switch(opcao2) {
                        case 1:
                            listaUsuarios();
                            break;
                        case 2:
                            System.out.println("Digite o id do usuário");
                            int id = in.nextInt();
                            in.nextLine();
                            if(trocaUsuario(id)) {
                                System.out.println("Usuário trocado com sucesso");
                            }else {
                                System.out.println("Usuário não encontrado");
                            }
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("Opção inválida");
                            break;
                    }
                case 2:
                    menuPedidos();
                    System.out.print("Digite a opção desejada: ");
                    int opcao3 = in.nextInt();
                    in.nextLine();
                    switch(opcao3) {
                        case 1:
                            adicionaPedido();
                            break;
                        case 2:
                            listaPedidos();
                            break;
                        case 3:
                            aprovaPedido();
                            break;
                        case 4:
                            menuProcuraPedidos();
                            System.out.print("Digite a opção desejada: ");
                            int opcao4 = in.nextInt();
                            in.nextLine();
                            switch(opcao4) {
                                case 1:
                                    System.out.println("Digite a data inicial no formato: ano-mes-dia");
                                    LocalDate inic = LocalDate.parse(in.nextLine());
                                    System.out.println("Digite a data final no formato: ano-mes-dia");
                                    LocalDate end = LocalDate.parse(in.nextLine());
                                    ArrayList<Pedido> entreData = gerenciaPedidos.listarPedidoEntreDatas(inic,end);
                                    for(Pedido p : entreData) {
                                        System.out.println(p);
                                    }
                                    break;
                                case 2:
                                    System.out.println("Digite o id do funcionário solicitante");
                                    int id = in.nextInt();
                                    in.nextLine();
                                    Usuario u = gerenciaUsuarios.pesquisaUsuarioId(id);
                                    if(u == null) {
                                        System.out.println("Usuário não encontrado");
                                        break;
                                    }
                                    ArrayList<Pedido> func = gerenciaPedidos.pesquisaPedidoFuncionario(u);
                                    for(Pedido p : func) {
                                        System.out.println(p);
                                    }
                                    break;
                                case 3:
                                    System.out.println("Digite a descrição do item");
                                    String desc = in.nextLine();
                                    Item i = gerenciaItens.pesquisaItem(desc);
                                    if(i == null) {
                                        System.out.println("Item não encontrado");
                                        break;
                                    }
                                    ArrayList<Pedido> pItem = gerenciaPedidos.pesquisaPedidoItem(i);
                                    if(pItem.size() == 0) {
                                        System.out.println("Não existem pedidos com esse item");
                                        break;
                                    }
                                    for(Pedido p : pItem) {
                                        System.out.println(p);
                                    }
                                    break;
                                case 0:
                                    break;
                                default:
                                    System.out.println("Opcão inválida");
                                    break;
                            }
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("Opção inválida");
                            break;
                    }

                    break;
                case 3:
                    menuItens();
                    System.out.print("Digite a opção desejada: ");
                    int opcao4 = in.nextInt();
                    in.nextLine();
                    switch(opcao4) {
                        case 1:
                            gerenciaItens.listaItens();
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("Opção inválida");
                            break;
                    }
                    break;
                case 4:
                    menuDepartamentos();
                    System.out.print("Digite a opção desejada: ");
                    int opcao5 = in.nextInt();
                    in.nextLine();
                    switch(opcao5) {
                        case 1:
                            gerenciaDepartamentos.listaDepartamentos();
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("Opção inválida");
                            break; 
                    }
                case 5:
                    exibeEstatiscas();
                    break;
                case 0:
                    System.out.println("Encerrando");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    
            }
        }while(opcao !=0);
    }

    private void menuPrincipal() {
        System.out.println("[1] Usuários\n"
                            + "[2] Pedidos\n"
                            + "[3] Itens\n"
                            + "[4] Departamentos\n"
                            + "[5] Estatísticas gerais\n"
                            + "[0] Sair");
    }

    private void menuPedidos() {
        System.out.println("[1] Registrar pedido\n"
                            + "[2] Listar todos pedidos\n"
                            + "[3] Aprovar pedido\n"
                            + "[4] Procurar pedidos\n"
                            + "[0] Voltar");
    }

    private void menuProcuraPedidos() {
        System.out.println("[1] Procurar pedidos entre duas datas\n"
                            + "[2] Procurar pedidos por funcionário\n"
                            + "[3] Procurar pedidos por item\n"
                            + "[0] Voltar");
    }

    private void menuUsuário() {
        System.out.println("[1] Listar usuários\n"
                            + "[2] Trocar usuário\n"
                            + "[0] Voltar");
    }

    private void menuItens() {
        System.out.println("[1] Listar itens\n"
                            + "[0] Voltar");
    }

    private void menuDepartamentos() {
        System.out.println("[1] Listar departamentos\n"
                            + "[0] Voltar");
    }

    private void carregaDados() {

        //registra departamentos
        String nome = in.nextLine();
        do {
            
            String descricao = in.nextLine();
            double valorMaximo = in.nextDouble();
            in.nextLine();

            Departamento d = new Departamento(nome, descricao, valorMaximo);
            gerenciaDepartamentos.cadastraDepartamento(d);

            nome = in.nextLine();

        }while(!(nome.equals("-1")));

        //Registra usuários sendo funcionários ou administradores
        nome = in.nextLine();
        do {
            
            String iniciais = in.nextLine();
            String tipo = in.nextLine();
            Departamento d = gerenciaDepartamentos.pesquisaDepartamento(in.nextLine());

            Usuario u;
            if(tipo.equals("ADM")) {
                u = new Administrador(gerenciaUsuarios.getUltimoId(),nome, iniciais, d);
            }else {
                u = new Funcionario(gerenciaUsuarios.getUltimoId(),nome, iniciais, d);
            }
            
            gerenciaUsuarios.adicionaUsuario(u);

            nome = in.nextLine();

        }while(!(nome.equals("-1")));

        String descricao;
        do {
            
            descricao = in.nextLine();
            double valor = in.nextDouble();
            in.nextLine();
            
            Item item = new Item(descricao, valor);
            gerenciaItens.cadastraItem(item);

            descricao = in.nextLine();

        }while(!(descricao.equals("-1")));

        int codigo = in.nextInt();
        in.nextLine();
        do {
            
            Usuario u = gerenciaUsuarios.pesquisaUsuarioId(codigo);
            Departamento d = gerenciaDepartamentos.pesquisaDepartamento(in.nextLine());
            ArrayList<Item> itens = new ArrayList<Item>();
            descricao = in.nextLine();
            do {
                itens.add(gerenciaItens.pesquisaItem(descricao));
                descricao = in.nextLine();
            }while(!(descricao.equals("-1")));
            
            LocalDate data = LocalDate.parse(in.nextLine());

            Pedido p  = new Pedido(codigo, u, d, data, itens);
            codigo = in.nextInt();
            in.nextLine();

        }while(codigo != -1);
    }

    private void restauraEntrada() {
        in = new Scanner(System.in);
    }

    public void exibeEstatiscas() {
        if(!(usuario instanceof Administrador)) {
            System.out.println("Você não tem permissão para acessar essa função");
            return;
        }
        System.out.println("Quantidade de pedidos: " + gerenciaPedidos.getQuantidadePedidos());
        System.out.println("Percentual de pedidos aprovados: " + gerenciaPedidos.getPercentAprovados() + "%");
        System.out.println("Percentual de pedidos reprovados: " + gerenciaPedidos.getPercentReprovados() + "%");
        System.out.println("Quantidade de pedidos nos últimos 30 dias: " + gerenciaPedidos.totalPedidos30Dias());
        System.out.println("Valor médio dos pedidos nos últimos 30 dias: " + gerenciaPedidos.valorMedioPedidos30Dias());
        System.out.println("Valor total de pedidos aprovados nos últimos 30 dias: " + gerenciaPedidos.getValorAprovadosUltimos30Dias());
        System.out.println("Valor total de pedidos reprovados nos últimos 30 dias: " + gerenciaPedidos.getValorReprovadosUltimos30Dias());
        gerenciaPedidos.verDetalhesPedidoMaiorValorAberto();
    }

    public void adicionaPedido() {
        System.out.println("Digite o nome do departamento");
        Departamento d = gerenciaDepartamentos.pesquisaDepartamento(in.nextLine());
        if(d == null) {
            System.out.println("Departamento não encontrado");
            return;
        }

        System.out.println("Digite a descrição dos itens que deseja adicionar, um de cada vez, e 0 para encerrar");
        String descricao = in.nextLine();
        ArrayList<Item> itens = new ArrayList<Item>();
        do {
            Item i = gerenciaItens.pesquisaItem(descricao);
            if (i != null) {
                System.out.println("Esse item não existe");
            }else {
                itens.add(i);
                System.out.println("Item adicionado ao pedido");
            }

            System.out.println("Digite a descrição dos itens que deseja adicionar, um de cada vez, e 0 para encerrar");
            descricao = in.nextLine();
            
        }while(!(descricao.equals("0")));

        
        Pedido p = new Pedido(gerenciaPedidos.getUltimoCodigo(),this.usuario,d,LocalDate.now(), itens);

        if(p.getValorTotal() > d.getValorMaximo()) {
            System.out.println("O valor total do pedido excede o valor máximo do departamento");
            return;
        }

        gerenciaPedidos.adicionaPedido(p);
    }


    private void aprovaPedido() {
        if(!(usuario instanceof Administrador)) {
            System.out.println("Você não tem permissão para acessar essa função");
            return;
        }
        System.out.println("Digite o codigo do pedido a ser buscado");
        int c = in.nextInt();
        in.nextLine();
        Pedido p = gerenciaPedidos.pesquisaPedido(c);

        if(p == null) {
            System.out.println("Pedido não encontrado");
        }else {
            switch(p.getStatus()) {
                case "Aberto":
                    System.out.println("O pedido está aberto, detalhes do pedido:\n" + p);
                    System.out.println("--------------------------------------------------");
                    System.out.println("1- Aprovar\n2-Reprovar\n0-Sair");
                    int op = in.nextInt();
                    if(op == 1) {
                        p.setStatus("Aprovado");
                        System.out.println("Pedido aprovado");
                    }else if(op == 2) {
                        p.setStatus("Reprovado");
                        System.out.println("Pedido reprovado");
                    }else if(op == 0) {
                        loop();
                    }else {
                        System.out.println("Opção invalida, voltando");
                        loop();
                    }
                    break;
                case "Aprovado":
                    System.out.println("O pedido já foi aprovado");
                    break;
                case "Reprovado":
                    System.out.println("O pedido já foi reprovado");
                    break;
                case "Concluido":
                    System.out.println("O pedido já está concluído");
                    break;
            }
        }
    }
    
    public boolean trocaUsuario(int id){
        Usuario u = gerenciaUsuarios.pesquisaUsuarioId(id);
        if(u != null){
            usuario = u;
            return true;
        }
        return false;
    }

    public void listaUsuarios(){
        ArrayList<Usuario> usuarios = gerenciaUsuarios.getUsuarios();
        for(Usuario u : usuarios){
            if(u.equals(usuario)) {
                System.out.println(u + "(Atual)");
            }else {
                System.out.println(u);
            }
        }
    }

    public void listaPedidos() {
        ArrayList<Pedido> pedidos = gerenciaPedidos.getPedidos();
        for(Pedido p : pedidos) {
            System.out.println("==================================\n"
             + p + "\n"
             + "==================================");
        }
    }
}
