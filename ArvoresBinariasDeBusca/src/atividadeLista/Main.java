
import java.io.*;
import java.util.Scanner;


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
			System.out.println("Erro no fechamento do arquivo de leitura: " + excecao);
		}
	}
}

class No {

	private Jogador item;
	private No esquerda;
	private No direita;
	
	public No() {
		
		item = new Jogador();
		esquerda = null;
		direita = null;
	}
	
	public No(Jogador registro) {
		
		item = registro;
		esquerda = null;
		direita = null;
	}
	
	public Jogador getItem() {
		return item;
	}
	public void setItem(Jogador item) {
		this.item = item;
	}
	
	public No getEsquerda() {
		return esquerda;
	}
	public void setEsquerda(No esquerda) {
		this.esquerda = esquerda;
	}
	
	public No getDireita() {
		return direita;
	}
	public void setDireita(No direita) {
		this.direita = direita;
	}
}

class ABB {

	private No raiz;
	
	public ABB() {
		
		raiz = null;
	}
	
	public Jogador pesquisar(String nome) {
		return pesquisar(this.raiz, nome);
	}
	
	private Jogador pesquisar(No raizSubarvore, String nome)
		{
	
		if (raizSubarvore == null) {
			System.out.println("NAO");
			return null;
		}
		
		else if  (nome.compareTo(raizSubarvore.getItem().getNome()) > 0) {
			System.out.print(raizSubarvore.getItem().getNome() + " ");
			return pesquisar(raizSubarvore.getDireita(), nome);
		}
		
		else if (nome.compareTo(raizSubarvore.getItem().getNome()) < 0){
			System.out.print(raizSubarvore.getItem().getNome() + " ");
			return pesquisar(raizSubarvore.getEsquerda(), nome);
		}
		
		else 
			System.out.print(raizSubarvore.getItem().getNome());
			System.out.println(" SIM");
			return raizSubarvore.getItem();
	
	}
	
	public void inserir(Jogador novo) throws Exception {
		this.raiz = inserir(this.raiz, novo);
	}
	
	private No inserir(No raizSubarvore, Jogador novo) throws Exception{
		String nomeJogador = novo.getNome();		
		
		if (raizSubarvore == null) {
			raizSubarvore = new No(novo);
		}
		else if (novo.getNome() == raizSubarvore.getItem().getNome())
			throw new Exception("Não foi possível inserir o item na árvore: chave já inseriada anteriormente!");
		else if (nomeJogador.compareTo(raizSubarvore.getItem().getNome()) < 0)
			raizSubarvore.setEsquerda(inserir(raizSubarvore.getEsquerda(), novo));
		else
			raizSubarvore.setDireita(inserir(raizSubarvore.getDireita(), novo));
			
		return raizSubarvore;
	}
	
	
	
	
}


class Jogador {

	private int id, altura, peso, anoNascimento;
	private String nome, universidade, cidadeNascimento, estadoNascimento;

	public Jogador(int id, String nome, int altura, int peso, String universidade, int anoNascimento,
			String cidadeNascimento, String estadoNascimento) {

		this.setId(id);
		this.setNome(nome);
		this.setAltura(altura);
		this.setPeso(peso);
		this.setUniversidade(universidade);
		this.setAnoNascimento(anoNascimento);
		this.setCidadeNascimento(cidadeNascimento);
		this.setEstadoNascimento(estadoNascimento);

	}

	public Jogador() {
	}

	public void imprimir() {
		System.out.print(this.id + " ## ");
		System.out.print(this.nome + " ## ");
		System.out.print(this.altura + " ## ");
		System.out.print(this.peso + " ## ");
		System.out.print(this.anoNascimento + " ## ");
		System.out.print(this.universidade + " ## ");
		System.out.print(this.cidadeNascimento + " ## ");
		System.out.print(this.estadoNascimento + " ## ");
		System.out.println();
	}

	public void lerJogador(String entrada) {

		String[] elemento = entrada.split(",", 8);
		this.id = Integer.parseInt(elemento[0]);
		this.nome = elemento[1];
		this.altura = Integer.parseInt(elemento[2]);
		this.peso = Integer.parseInt(elemento[3]);
		this.universidade = elemento[4];

		if (elemento[4].equals(",") || elemento[4].equals("")) {
			this.universidade = "nao informado";
		}

		this.anoNascimento = Integer.parseInt(elemento[5]);
		this.cidadeNascimento = elemento[6];
		if (elemento[6].equals(",") || elemento[6].equals("")) {
			this.cidadeNascimento = "nao informado";
		}

		this.estadoNascimento = elemento[7];
		if (elemento[7].equals(",") || elemento[6].equals("")) {
			this.estadoNascimento = "nao informado";
		}
	}
	


	public Jogador clone() {
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

		Scanner s = new Scanner(System.in);
		MyIO.setCharset("UTF-8");
		int contador = 0;
		int linha = 0;
		int qtdeJogadores;
		int i;
//		double mediaAlturaFilaJogador;

		Jogador[] pessoa = new Jogador[4000]; // meu vetor de jogadores
		String str;
		String diretorio;
		String id;
		int idLista;
		String comandoLista;
		String listaComando[];
		ABB arvore;
		int posicao;

		arvore = new ABB();

		diretorio = "/tmp/jogadores.txt";

		ArquivoTextoLeitura leitura = new ArquivoTextoLeitura(diretorio);

		str = leitura.ler();
		str = leitura.ler();
		// populando o vetor de Jogadores atraves do arquivo

		while (str != null) {
			
			Jogador jogador = new Jogador();

			jogador.lerJogador(str);

			pessoa[contador++] = jogador;
			str = leitura.ler();
		}

		leitura.fecharArquivo();

		id = s.nextLine();

			while(!id.equals("FIM")) {
				linha = Integer.parseInt(id);	
				arvore.inserir(pessoa[linha]);;	
				id = s.nextLine();
			}		
			
		// aqui escrever o codigo para realizar a segunda parte da questao
		
		String nomeJogador = s.nextLine();
		while (! nomeJogador.equals("FIM")) {
			// String nomeJogador = pilhaJogador.consultarTopo().getNome();

			arvore.pesquisar(nomeJogador);
			nomeJogador = s.nextLine();

			
		
			

		}
		
	//lista.imprimir();

	

	}

}
