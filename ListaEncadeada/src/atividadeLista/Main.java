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

class ListaEncadeada {

private Celula primeiro;
private Celula ultimo;
private int tamanho;

public ListaEncadeada() {
	
	Celula sentinela = new Celula();
	
	primeiro = sentinela;
	ultimo = sentinela;
	tamanho = 0;
}

public boolean listaVazia() {
	
	boolean resp;
	
	if (primeiro == ultimo)
		resp = true;
	else
		resp = false;
	
	return resp;
}

public void inserir(Jogador novo, int posicao) throws Exception {
	
	Celula anterior, novaCelula, proximaCelula;
	
	if ((posicao >= 0) && (posicao <= tamanho)) {
		anterior = primeiro;
		for (int i = 0; i < posicao; i++)
			anterior = anterior.getProximo();
			
		novaCelula = new Celula(novo);
		
		proximaCelula = anterior.getProximo();
		
		anterior.setProximo(novaCelula);
		novaCelula.setProximo(proximaCelula);
		
		if (posicao == tamanho)  // a inserção ocorreu na última posição da lista
			ultimo = novaCelula;
		
		tamanho++;
		
	} else
		throw new Exception("Não foi possível inserir o item na lista: a posição informada é inválida!");
}

public void inserirInicio(Jogador novo) 
	
{	
	Celula anterior;
	Celula novaCelula = new Celula(novo);
	anterior = primeiro.getProximo();
	primeiro.setProximo(novaCelula);
	novaCelula.setProximo(anterior);
	tamanho++;
	
	
		
	
}

public void inserirFinal(Jogador novo) throws Exception

{
	Celula novaCelula = new Celula(novo);
	
	
	
	ultimo.setProximo(novaCelula);
	ultimo = novaCelula;
	
	
	
	tamanho++;
	
	
	
	
	
}

public Jogador remover(int posicao) throws Exception {
	
	Celula anterior, celulaRemovida, proximaCelula;
	
	if (! listaVazia()) {
		if ((posicao >= 0) && (posicao < tamanho)) {
			anterior = primeiro;
			for (int i = 0; i < posicao; i++) {
				anterior = anterior.getProximo();
				
			}
			
			celulaRemovida = anterior.getProximo();
			
			proximaCelula = celulaRemovida.getProximo();
			
			anterior.setProximo(proximaCelula);
			celulaRemovida.setProximo(null);
			
			if (celulaRemovida == ultimo)
				ultimo = anterior;
			
			tamanho--;
			System.out.print("(R) " + celulaRemovida.getItem().getNome() + "\n");
			return (celulaRemovida.getItem());	
		} else
			throw new Exception("Não foi possível remover o item da lista: a posição informada é inválida!");
	} else
		throw new Exception("Não foi possível remover o item da lista: a lista está vazia!");
} 

public Jogador removerInicio() throws Exception
{
	Celula removido, proximo;
	if (!listaVazia())
	{
		removido = primeiro.getProximo();
		primeiro.setProximo(removido.getProximo());
		
	}
	else
		throw new Exception ("Não foi possível remover o item da lista: a lista está vazia!");
	
	tamanho--;
	System.out.print("(R) " + removido.getItem().getNome() + "\n");
	
	return removido.getItem();
}


public Jogador removerFinal() throws Exception
{
	
	Celula anterior = primeiro;
	Celula removido;
	if (! listaVazia())
	{
		for (int i = 1; i < tamanho; i++)
			
		{
			anterior = anterior.getProximo();
		}
		
		removido = anterior.getProximo();
		anterior.setProximo(null);
		tamanho--;
		
		
		
	}
	else
		throw new Exception("Não foi possível remover o item da fila: a lista está vazia! ");
	
	
		System.out.print("(R) " + removido.getItem().getNome() + "\n");
	
		return removido.getItem();
		
		
	
}

public void imprimir() throws Exception {
	
	Celula aux;
	int contador = 0;
	
	if (! listaVazia()) {
		
		aux = primeiro.getProximo();
		while (aux != null) {
			System.out.print("[" + contador + "]" + " ## ");
			aux.getItem().imprimir();
			aux = aux.getProximo();
			contador++;
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
	
	public void bubblesort(int[] array, int n) {
		for (int i = (n - 1); i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (array[j] > array[j + 1]) {
	               			
					int temp = array[j];
	      				array[j] = array[j+1];
	      				array[j+1] = temp;
				}
			}
		}
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
		ListaEncadeada lista;
		int posicao;

		lista = new ListaEncadeada();

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
		
		qtdeJogadores = Integer.parseInt(s.nextLine());
		for (i = 0; i <= qtdeJogadores; i++) {
			// String nomeJogador = pilhaJogador.consultarTopo().getNome();

			comandoLista = s.nextLine();
			listaComando = comandoLista.split(" ");

			if (listaComando[0].equals("II")) {
				id = listaComando[1];
				idLista = Integer.parseInt(id);

				lista.inserirInicio(pessoa[idLista]);

			}
			
			else if (listaComando[0].equals("I*")) {
				id = listaComando[2];
				posicao = Integer.parseInt(listaComando[1]);
				idLista = Integer.parseInt(id);

				lista.inserir(pessoa[idLista], posicao);

			}

			else if (listaComando[0].equals("IF")) {
				id = listaComando[1];
				idLista = Integer.parseInt(id);

				lista.inserirFinal(pessoa[idLista]);

			}
			
			else if (listaComando[0].equals("R*")) {
				posicao = Integer.parseInt(listaComando[1]);
				lista.remover(posicao);			
				

			}
			
			else if (listaComando[0].equals("RI")) {		
				lista.removerInicio();

			}
			
			else if (listaComando[0].equals("RF")) {
			lista.removerFinal();

			}
			
		
			

		}
		
	lista.imprimir();

	

	}

}
