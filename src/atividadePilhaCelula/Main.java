import java.util.Scanner;
import java.io.*;

class ArquivoTextoLeitura {

	private BufferedReader entrada;
	
	ArquivoTextoLeitura(String nomeArquivo) {	
		
		try {
			entrada = new BufferedReader(new InputStreamReader(new FileInputStream(nomeArquivo), "UTF-8"));
		} catch (UnsupportedEncodingException excecao) {
			System.out.println(excecao.getMessage());
		} catch (FileNotFoundException excecao) {
			System.out.println("Arquivo nao encontrado");
		}
	}
	
	public void fecharArquivo() {
		
		try {
			entrada.close();
		}
		catch (IOException excecao) {
			System.out.println("Erro no fechamento do arquivo de leitura: " + excecao);	
		}
	}
	
	@SuppressWarnings("finally")
	public String ler() {
		
		String textoEntrada = null;
		
		try {
			textoEntrada = entrada.readLine();
		}
		catch (EOFException excecao) { //Excecao de final de arquivo.
			textoEntrada = null;
		}
		catch (IOException excecao) {
			System.out.println("Erro de leitura: " + excecao);
			textoEntrada = null;
		}
		finally {
			return textoEntrada;
		}
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
	
	class Celula {

		private Jogador item;
		private Celula proximo;
		
		public Celula(Jogador novo) {
			item = novo;
			proximo = null;
		}
		
		public Celula() {
			
			item = new Jogador();
			proximo = null;
		}
		
		public Jogador getItem() {
			return item;
		}
		public void setItem(Jogador item) {
			this.item = item;
		}
		
		public Celula getProximo() {
			return proximo;
		}
		public void setProximo(Celula proximo) {
			this.proximo = proximo;
		}
	}

	class Pilha {

		public Celula getTopo() {
			return topo;
		}

		public void setTopo(Celula topo) {
			this.topo = topo;
		}

		private Celula fundo;
		private Celula topo;
		
		public Pilha() {
			
			Celula sentinela;
			
			sentinela = new Celula();
			fundo = sentinela;
			topo = sentinela;
		}
		
		public boolean pilhaVazia() {
		
			boolean resp;
			
			if (topo == fundo)
				resp = true;
			else
				resp = false;
			
			return resp;
		}
		
		public void empilhar(Jogador novo) {
			
			Celula novaCelula;
			
			novaCelula = new Celula(novo);
			novaCelula.setProximo(topo);
			topo = novaCelula;
		}
		
		public Jogador desempilhar() throws Exception {
			
			Celula desempilhado;
			
			if (! pilhaVazia()) {
				desempilhado = topo;
				topo = topo.getProximo();
				desempilhado.setProximo(null);
				return (desempilhado.getItem());
			} else
				throw new Exception("Não foi possível desempilhar: a pilha está vazia!");
		}
		
		// Método que imprime a pilha 
		public void mostrar() throws Exception{
			
			Jogador imprimirPilha[];
			imprimirPilha = new Jogador[160];
			int i = 0;
			imprimirPilha[i] = topo.getItem();
			
			// transfere a Pilha para o vetor de Jogadores
			while (topo.getProximo()!= null)
			{	
				i++;
				imprimirPilha[i] = topo.getProximo().getItem();
				topo = topo.getProximo();
				
			}
				i -= 1;
				int posicao = 0;
				
				// imprime a pilha de forma invertida
			for (int j = i; j>=0 ; j-- )
			{	System.out.print("[" + posicao + "] ");
				imprimirPilha[i].imprimir();
				i--;
				posicao++;
				
			}
			
//

	}
	}

	


public class Main {

	public static void main(String[] args) throws Exception {
		
		// MyIO.setCharset("UTF-8");
		Scanner s = new Scanner(System.in);

		int contador = 0;		
		int linha;
		int qtdeJogadores;
		int i;
		
		Jogador[] pessoa = new Jogador[4000]; //meu vetor de jogadores
		String str;
		String diretorio;
		String id;
		int idPilha;
		String comandoPilha;
		String pilhaComando[];
		Pilha pilhaJogador;
		
		
		pilhaJogador = new Pilha();
	
		diretorio = "/tmp/jogadores.txt";
		
		ArquivoTextoLeitura leitura;
		leitura = new ArquivoTextoLeitura(diretorio);
		
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
		
			
			id = s.nextLine();
			
			while(!id.equals("FIM")) {
				linha = Integer.parseInt(id);						
				try {
				pilhaJogador.empilhar(pessoa[linha]);;	
				}catch(java.lang.Exception e) {
					pilhaJogador.desempilhar();					
					pilhaJogador.empilhar(pessoa[linha]);
					
				}				
				id = s.nextLine();			
			}		
			
	//aqui escrever o codigo para realizar a segunda parte da questao 	 
			
			qtdeJogadores = s.nextInt();					
			for(i = 0; i < qtdeJogadores; i++) {
				String nomeJogador = pilhaJogador.getTopo().getItem().getNome();			
				comandoPilha = s.nextLine();
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

	


