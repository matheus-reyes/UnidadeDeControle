
import java.io.*;
import java.util.*;

public class UC{
    
    //Registradores e seus opcodes
    final static int OPCODES1 = 1, OPCODES2 = 2, OPCODES3 = 3, OPCODES4 = 4, OPCODES5 = 5;
    
    //Instruções MIPS e seus opcodes
    final static int OPCODELI = 1, OPCODELW = 2, OPCODESW = 3, OPCODEMOV = 4, OPCODEADD = 5, 
    OPCODESUB = 6, OPCODEBEQ = 7, OPCODEBNE = 8, OPCODEJ = 9, OPCODESLT = 10;

    static LinkedList<Memoria> memoria = new LinkedList<Memoria>();

    public static void main(String[] args) throws IOException {
        BufferedReader inputStream = null;

        //Registradores
        int s1, s2, s3, s4, s5;
        
        try {
            
            //Recebe o arquivo de entrada
            inputStream = new BufferedReader(new FileReader("exemplo.txt"));
            //Cada linha do código MIPS
            String linha;
            //String que receberá o código de montagem linha por linha
            String codigodeMontagem;
            String codigodeMaquina;
            
            //Percorre o documento com o código MIPS
            while ((linha = inputStream.readLine()) != null) {
                //Exibe na tela linha por linha do código MIPS
                // System.out.println(linha);
                //Recebe o código de montagem linha por linha
                codigodeMontagem = converterLinguagemdeMontagem(linha);
                //Converte para linguagem de máquina linha por linha
                codigodeMaquina = converterLinguagemdeMaquina(codigodeMontagem);
                //Armazena o código na memória
                armazenarNaMemoria(codigodeMaquina);
            }

        }finally {
            
            //Fecha o arquivo
            if (inputStream != null) {
                inputStream.close();
            }
        } 

        //Percorre a lista de memórias
        for(Memoria memorias: memoria){
            // System.out.println("Endereço: " + memorias.getEndereco());
            // System.out.println("Conteúdo: " + memorias.getConteudo());
            //Ignora linhas vazias
            if(!memorias.getConteudo().equals("")){

                //Se o opcode for igual ao opcode de add
                if(memorias.getConteudo().substring(0, 4).equals("0101")){
                    System.out.println(add());
                }
            }
        }
    }

    //Função que recebe como parâmetro uma linha de código MIPS e a converte para linguagem de montagem
    public static String converterLinguagemdeMontagem(String codigoMips){
        
        //String que recebe o código de máquina
        String codigoMaquina = "";
        //Inteiro que receberá a posição que deve ser ignorada
        int posicaoIgnorada = -1;
        
        //Transforma os registradores em Linguagem de Montagem
        for(int i = 0; i < codigoMips.length(); i++){
            if(codigoMips.charAt(i) == 's' && codigoMips.charAt(i + 1) == '1' && i < (codigoMips.length() - 1)){
                codigoMaquina += "1";
                posicaoIgnorada = i + 1;
            }else if(codigoMips.charAt(i) == 's' && codigoMips.charAt(i + 1) == '2' && i < (codigoMips.length() - 1)){
                codigoMaquina += "2";
                posicaoIgnorada = i + 1;
            }else if(codigoMips.charAt(i) == 's' && codigoMips.charAt(i + 1) == '3' && i < (codigoMips.length() - 1)){
                codigoMaquina += "3";
                posicaoIgnorada = i + 1;
            }else if(codigoMips.charAt(i) == 's' && codigoMips.charAt(i + 1) == '4' && i < (codigoMips.length() - 1)){
                codigoMaquina += "4";
                posicaoIgnorada = i + 1;
            }else if(codigoMips.charAt(i) == 's' && codigoMips.charAt(i + 1) == '5' && i < (codigoMips.length() - 1)){
                codigoMaquina += "5";
                posicaoIgnorada = i + 1;
            }else{
                if((i != posicaoIgnorada) && (!isInteger(String.valueOf(codigoMips.charAt(i)), 10))){
                    codigoMaquina += codigoMips.charAt(i);
                }
            }
        }

        //Converte o valor para binário
        String[] codigoMipsSeparado = codigoMips.split(" ");
        if(isInteger(codigoMipsSeparado[codigoMipsSeparado.length - 1], 10)){
            int constante = Integer.parseInt(codigoMipsSeparado[codigoMipsSeparado.length - 1]);
            String constanteConvertida = completarBinario(Integer.toBinaryString(constante), 16);
            codigoMaquina += constanteConvertida;
        }

        //Retorna o código de máquina
        return codigoMaquina;
    }

    //Função que recebe como parâmetro a linguagem de montagem e converte para linguagem de máquina
    public static String converterLinguagemdeMaquina(String linguagemdeMontagem){

        //Recebe os valores em binário das funções
        String LIbinario = completarBinario(Integer.toBinaryString(OPCODELI), 4);
        String LWbinario = completarBinario(Integer.toBinaryString(OPCODELW), 4);
        String SWbinario = completarBinario(Integer.toBinaryString(OPCODESW), 4);
        String MOVbinario = completarBinario(Integer.toBinaryString(OPCODEMOV), 4);
        String ADDbinario = completarBinario(Integer.toBinaryString(OPCODEADD), 4);
        String SUBbinario = completarBinario(Integer.toBinaryString(OPCODESUB), 4);
        String BEQbinario = completarBinario(Integer.toBinaryString(OPCODEBEQ), 4);
        String BNEbinario = completarBinario(Integer.toBinaryString(OPCODEBNE), 4);
        String Jbinario = completarBinario(Integer.toBinaryString(OPCODEJ), 4);
        String SLTbinario = completarBinario(Integer.toBinaryString(OPCODESLT), 4);

        String S1binario = completarBinario(Integer.toBinaryString(OPCODES1), 3);
        String S2binario = completarBinario(Integer.toBinaryString(OPCODES2), 3);
        String S3binario = completarBinario(Integer.toBinaryString(OPCODES3), 3);
        String S4binario = completarBinario(Integer.toBinaryString(OPCODES4), 3);
        String S5binario = completarBinario(Integer.toBinaryString(OPCODES5), 3);
        
        //Substitui na linha o valor em binário
        linguagemdeMontagem = linguagemdeMontagem.replace("li", LIbinario);
        linguagemdeMontagem = linguagemdeMontagem.replace("lw", LWbinario);
        linguagemdeMontagem = linguagemdeMontagem.replace("sw", SWbinario);
        linguagemdeMontagem = linguagemdeMontagem.replace("mov", MOVbinario);
        linguagemdeMontagem = linguagemdeMontagem.replace("add", ADDbinario);
        linguagemdeMontagem = linguagemdeMontagem.replace("sub", SUBbinario);
        linguagemdeMontagem = linguagemdeMontagem.replace("beq", BEQbinario);
        linguagemdeMontagem = linguagemdeMontagem.replace("bne", BNEbinario);
        linguagemdeMontagem = linguagemdeMontagem.replace("j", Jbinario);
        linguagemdeMontagem = linguagemdeMontagem.replace("slt", SLTbinario);

        linguagemdeMontagem = linguagemdeMontagem.replace("$1", S1binario);
        linguagemdeMontagem = linguagemdeMontagem.replace("$2", S2binario);
        linguagemdeMontagem = linguagemdeMontagem.replace("$3", S3binario);
        linguagemdeMontagem = linguagemdeMontagem.replace("$4", S4binario);
        linguagemdeMontagem = linguagemdeMontagem.replace("$5", S5binario);

        //Remove as vírgulas
        linguagemdeMontagem = linguagemdeMontagem.replace(",", "");
        
        return linguagemdeMontagem;
    }

    //Completa o número com 0 se o número ocupar menos que 4 bits
    public static String completarBinario(String binario, int numeroBits){
        
        //String que armazenará os zeros a esquerda
        String zeros = "";
        //completa o número certo de zeros a esquerda
        for(int i = binario.length(); i < numeroBits; i++){
            zeros += "0";
        }
        //retorna o número binário convertido
        return zeros + binario;
    }

    //Função que verifica se é um inteiro. Retirada de https://stackoverflow.com/questions/5439529/determine-if-a-string-is-an-integer-in-java
    public static boolean isInteger(String s, int radix) {
        if(s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return true;
    }

    //Armazena os dados na memória
    public static void armazenarNaMemoria(String linguagemdeMaquina){
        //Cria endereços sucessivos de acordo com o tamanho de linhas
        String endereco = completarBinario(Integer.toBinaryString(memoria.size()), 16);
        //Cria uma instância de memória passando o endereço e o conteúdo sendo o códugo de máquina
        Memoria novaMemoria = new Memoria(endereco, linguagemdeMaquina);
        //Adiciona a instância da memória na lista ligada
        memoria.add(novaMemoria);
    }

    public static String add(){
        return "maoe";
    }

}