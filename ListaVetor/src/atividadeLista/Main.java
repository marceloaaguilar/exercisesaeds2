package atividadeLista;
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

class ListaLinear {

	private Jogador lista[];
	private int primeiro;
	private int ultimo;
	private int tamanho;

	public ListaLinear(int M) {

		lista = new Jogador[M];
		tamanho = 0;
		primeiro = 0;
		ultimo = 0;
	}

	public ListaLinear() {

		lista = new Jogador[10];
		tamanho = 0;
		primeiro = 0;
		ultimo = 0;
	}

	public int getPrimeiro() {
		return primeiro;
	}

	public void setPrimeiro(int primeiro) {
		this.primeiro = primeiro;
	}

	public int getUltimo() {
		return ultimo;
	}

	public void setUltimo(int ultimo) {
		this.ultimo = ultimo;
	}

	public boolean listaVazia() {

		boolean resp;

		if (primeiro == ultimo)
			resp = true;
		else
			resp = false;

		return resp;
	}

	public boolean listaCheia() {

		boolean resp;

		if (ultimo == lista.length)
			resp = true;
		else
			resp = false;

		return resp;
	}

	public void inserir(int posicao, Jogador novo) throws Exception {

		if (!listaCheia()) {
			if ((posicao >= 0) && (posicao <= tamanho)) {
				for (int i = ultimo; i > posicao; i--)
					lista[i] = lista[i - 1];

				lista[posicao] = novo;

				ultimo++;
				tamanho++;
			} else
				throw new Exception("Não foi possível inserir o item na lista: posição informada é inválida!");
		} else
			throw new Exception("Não foi possível inserir o item na lista: a lista está cheia!");
	}

	public void inserirInicio(Jogador novo) throws Exception {

		if (!listaCheia())
			for (int i = ultimo; i > 0; i--) {
				lista[i] = lista[i - 1];
				
				
			
			}
		

		else
			throw new Exception("Não foi possível inserir o item na lista: a lista está cheia!");
		tamanho++;
		ultimo++;
		lista[0] = novo;

	}

	public void inserirFinal(Jogador novo) throws Exception {
		
		if (!listaCheia()) {
		for (int i = ultimo; i > ultimo; i--) 
			lista[i] = lista[i - 1];

		
		}
		
			else
			throw new Exception("Não foi possível inserir o item na lista: a lista está cheia!");
		lista[ultimo] = novo;
		tamanho++;
		ultimo++;
	}

	public Jogador remover(int posicao) throws Exception {

		Jogador removido;

		if (!listaVazia()) {
			if ((posicao >= 0) && (posicao < tamanho)) {

				removido = lista[posicao];
				tamanho--;

				for (int i = posicao; i < tamanho; i++) {
					lista[i] = lista[i + 1];
				}

				ultimo--;
				System.out.print("(R) " + removido.getNome() + "\n");
				return removido;
			} else
				throw new Exception("Não foi possível remover o item da lista: posição informada é inválida!");
		} else
			throw new Exception("Não foi possível remover o item da lista: a lista está vazia!");
	}
	
	public Jogador removerInicio() throws Exception {
		Jogador removido = lista[0];
		if (!listaVazia())
		{
		
			for (int i = 0; i<ultimo; i++)
			{
				lista[i] = lista[i+1];
				
			}
			ultimo--;
			tamanho--;
			
		}
		System.out.print("(R) " + removido.getNome() + "\n");
		return removido;
	}
	
	public Jogador removerFinal() throws Exception {
		Jogador removido = null;
		tamanho--;
		if (!listaVazia())
		{
		removido = lista[ultimo - 1];
		System.out.print("(R) " + removido.getNome() + "\n");		
		ultimo--;
		
			
		}
		return removido;
	}

	public void mostrar() throws Exception {

		if (!listaVazia()) {

			for (int i = primeiro; i < ultimo; i++) {
				System.out.print("[" + i + "]" + " ## ");
				lista[i].imprimir();
			}
		} else
			throw new Exception("Não foi possível imprimir o conteúdo da lista: a lista está vazia!");
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
		ListaLinear lista;
		int posicao;

		lista = new ListaLinear(150);

		diretorio = "jogadores.txt";

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
			
				lista.inserirFinal(pessoa[linha]);;	
				id = s.nextLine();			
			}		

		// aqui escrever o codigo para realizar a segunda parte da questao

		qtdeJogadores = s.nextInt();
		for (i = 0; i <= qtdeJogadores; i++) {
			// String nomeJogador = pilhaJogador.consultarTopo().getNome();

			comandoLista = s.nextLine();
			listaComando = comandoLista.split(" ");

			if (listaComando[0].equals("II")) {
				id = listaComando[1];
				idLista = Integer.parseInt(id);

				lista.inserirInicio(pessoa[idLista]);

			}
			
			if (listaComando[0].equals("I*")) {
				id = listaComando[2];
				posicao = Integer.parseInt(listaComando[1]);
				idLista = Integer.parseInt(id);

				lista.inserir(posicao, pessoa[idLista]);

			}

			if (listaComando[0].equals("IF")) {
				id = listaComando[1];
				idLista = Integer.parseInt(id);

				lista.inserirFinal(pessoa[idLista]);

			}
			
			if (listaComando[0].equals("R*")) {
				posicao = Integer.parseInt(listaComando[1]);
				lista.remover(posicao);
				

			}
			
			if (listaComando[0].equals("RI")) {
				lista.removerInicio();

			}
			
			if (listaComando[0].equals("RF")) {
				lista.removerFinal();

			}
			
			
			

		}
		
		lista.mostrar();

	

	}

}
