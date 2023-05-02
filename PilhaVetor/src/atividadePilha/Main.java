package atividadePilha;
import java.io.*;

class ArquivoTextoLeitura {
    private BufferedReader entrada;

    ArquivoTextoLeitura(String nomeArquivo) {
        try {
            entrada = new BufferedReader(new InputStreamReader(new
                    FileInputStream(nomeArquivo), "UTF-8"));
        } catch (UnsupportedEncodingException excecao) {
            System.out.println(excecao.getMessage());
        } catch (FileNotFoundException excecao) {
            System.out.println("Arquivo nao encontrado");
        }
    }

    public String ler() {
        String textoEntrada = null;
        try {
            textoEntrada = entrada.readLine();
        } catch (EOFException excecao) {
            textoEntrada = null;
        } catch (IOException excecao) {
            System.out.println("Erro de leitura: " + excecao);
            textoEntrada = null;
        } finally {
            return textoEntrada;
        }
    }

    public void fecharArquivo() {
        try {
            entrada.close();
        } catch (IOException excecao) {
            System.out.println("Erro no fechamento do arquivo de leitura: " +
                    excecao);
        }
    }
}

class Pilha {

	private Jogador pilha[];
	private int topo, fundo;
	
	public Pilha(int tamanho) {
		
		
		pilha = new Jogador[tamanho];
		topo = 0;
	}
	
	
	public boolean pilhaCheia() {
	
		boolean resp;
		
		if (topo == pilha.length)
			resp = true;
		else
			resp = false;
		
		return resp;
	}
	
	public boolean pilhaVazia() {
		
		boolean resp;
		
		if (topo == 0)
			resp = true;
		else
			resp = false;
		
		return resp;
	}

	public Jogador desempilhar() throws Exception {
	
		Jogador desempilhado;
		
		if (! pilhaVazia()) {
			topo--;
			desempilhado = pilha[topo];
			return desempilhado;
		} else
			throw new Exception("Não foi possível desempilhar: a pilha está vazia!");
	}
	
	
	public void empilhar(Jogador novo) throws Exception {
		
		if (! pilhaCheia()) {
			pilha[topo] = novo;
			topo++;
		} else
			throw new Exception("Não foi possível empilhar: a pilha está cheia!");
	}
	
	public Jogador consultarTopo() throws Exception {
		
		if (! pilhaVazia()) {
			return (pilha[topo-1]);
		} else
			throw new Exception("Não foi possível consultar o elemento do topo da pilha: a pilha está vazia!");
	}
	
public void mostrar() throws Exception{
		
		int posicao;
		if (! pilhaVazia()) {
			for (int i = fundo; i < topo; i++) {
				posicao = i % pilha.length;
				System.out.print("[" + posicao + "] ");
				pilha[posicao].imprimir();
			}
		} else
			throw new Exception ("Não foi possível mostrar o conteúdo da fila: a fila está vazia!");
	}
}


   


class Jogador{
	
	private int id, altura, peso, anoNascimento;
	private String nome, universidade, cidadeNascimento, estadoNascimento;
	
	
	
	public Jogador(int id, String nome, int altura, int peso, String universidade, int anoNascimento, String cidadeNascimento, String estadoNascimento) {
	
		this.setId(id);
		this.setNome(nome);
		this.setAltura(altura);
		this.setPeso(peso);
		this.setUniversidade(universidade);
		this.setAnoNascimento(anoNascimento);
		this.setCidadeNascimento(cidadeNascimento);
		this.setEstadoNascimento(estadoNascimento);
		
	}
	
	public Jogador() {}
	
	public void imprimir(){
        System.out.print("[" + this.id + " ## ");
        System.out.print(this.nome + " ## ");
        System.out.print(this.altura + " ## ");
        System.out.print(this.peso +" ## ");
        System.out.print(this.anoNascimento + " ## ");
        System.out.print(this.universidade +" ## ");
        System.out.print(this.cidadeNascimento +" ## ");
        System.out.print(this.estadoNascimento + "]");
        System.out.println();
    }
	
	public void lerJogador(String entrada){
       
		String [] elemento = entrada.split(",", 8);
        this.id = Integer.parseInt(elemento[0]);
        this.nome = elemento[1];
        this.altura = Integer.parseInt(elemento[2]);
        this.peso = Integer.parseInt(elemento[3]);
        this.universidade = elemento[4];
        
        if (elemento[4].equals(",") || elemento[4].equals("")){
    			this.universidade = "nao informado";
    	}
        
        this.anoNascimento = Integer.parseInt(elemento[5]);
        this.cidadeNascimento = elemento[6];
        if (elemento[6].equals(",") || elemento[6].equals("")){
    			this.cidadeNascimento = "nao informado";
    	}
        
        
        this.estadoNascimento = elemento[7];
        if (elemento[7].equals(",") || elemento[6].equals("")){
        			this.estadoNascimento = "nao informado";
        	}
    }

	
	public Jogador clone(){
        Jogador jogador2 = new Jogador();
        return jogador2;
    }

	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAltura() {
		return altura;
	}
	public void setAltura(int altura) {
		this.altura = altura;
	}
	public int getAnoNascimento() {
		return anoNascimento;
	}
	public void setAnoNascimento(int anoNascimento) {
		this.anoNascimento = anoNascimento;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCidadeNascimento() {
		return cidadeNascimento;
	}
	public void setCidadeNascimento(String cidadeNascimento) {
		this.cidadeNascimento = cidadeNascimento;
	}
	public String getEstadoNascimento() {
		return estadoNascimento;
	}
	public void setEstadoNascimento(String estadoNascimento) {
		this.estadoNascimento = estadoNascimento;
	}
	public String getUniversidade() {
		return universidade;
	}
	public void setUniversidade(String universidade) {
		this.universidade = universidade;
	}
	

	
}

public class Main {

	public static void main(String[] args) throws Exception {
		
		MyIO.setCharset("UTF-8");

		int contador = 0;		
		int linha;
		int qtdeJogadores;
		int i;
//		double mediaAlturaFilaJogador;
		
		Jogador[] pessoa = new Jogador[4000]; //meu vetor de jogadores
		String str;
		String diretorio;
		String id;
		int idPilha;
		String comandoPilha;
		String pilhaComando[];
		Pilha pilhaJogador;
		
		
		pilhaJogador = new Pilha(150);
	
		diretorio = "jogadores.txt";
		
		ArquivoTextoLeitura leitura = new ArquivoTextoLeitura(diretorio);
		
		str = leitura.ler();
		str = leitura.ler();
		// populando o vetor de Jogadores atraves do arquivo
		
			while(str != null) {		
				
				Jogador jogador = new Jogador();
			
				jogador.lerJogador(str);				
				
				pessoa[contador++] = jogador;
				str = leitura.ler();
			}
			
			leitura.fecharArquivo();
	//aqui deve ser inserido o codigo que vai enfileirar	
		
			
			id = MyIO.readLine();
			
			while(!id.equals("FIM")) {
				linha = Integer.parseInt(id);						
				try {
				pilhaJogador.empilhar(pessoa[linha]);;	
				}catch(java.lang.Exception e) {
					pilhaJogador.desempilhar();					
					pilhaJogador.empilhar(pessoa[linha]);
					
				}				
				id = MyIO.readLine();				
			}		
			
	//aqui escrever o codigo para realizar a segunda parte da questao 	 
			
			qtdeJogadores = MyIO.readInt(); 						
			for(i = 0; i < qtdeJogadores; i++) {
				String nomeJogador = pilhaJogador.consultarTopo().getNome();
				
				
				comandoPilha = MyIO.readLine();
				pilhaComando = comandoPilha.split(" ");
				
				if(pilhaComando[0].equals("I")) {
					id = pilhaComando[1];
					idPilha = Integer.parseInt(id);
					
					pilhaJogador.empilhar(pessoa[idPilha]);
							
						}				
					
				
				if(comandoPilha.equals("R")) {
					idPilha = Integer.parseInt(id);
					pilhaJogador.desempilhar();
					System.out.println("(R) " + nomeJogador);
					
				}
				
				
			}
			
			// Imprimindo a Pilha
			
			pilhaJogador.mostrar();
	}
			
			
			
	}


