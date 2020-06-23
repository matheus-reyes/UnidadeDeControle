
import java.io.*;
import java.util.*;

public class UC{
    
    //Registradores e seus opcodes
    final static int OPCODES1 = 1, OPCODES2 = 2, OPCODES3 = 3, OPCODES4 = 4, OPCODES5 = 5;
    
    //Instruções MIPS e seus opcodes
    final static int OPCODELI = 1, OPCODELW = 2, OPCODESW = 3, OPCODEMOVE = 4, OPCODEADD = 5, 
    OPCODESUB = 6, OPCODEBEQ = 7, OPCODEBNE = 8, OPCODEJ = 9, OPCODESLT = 10;

    static LinkedList<Memoria> memoria = new LinkedList<Memoria>();

    static String s1 = "", s2 = "", s3 = "", s4 = "", s5 = "";

    public static void main(String[] args) throws IOException {
        BufferedReader inputStream = null;

        //Registradores
       
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
        for(int i = 0; i < memoria.size(); i++){

            //Ignora linhas vazias
            if(!memoria.get(i).getConteudo().equals("")){

                //Se o código nas 4 primeiras posições for equivalente ao opcode de li
                if(memoria.get(i).getConteudo().substring(0, 4).equals("0001")){

                    li(memoria.get(i).getConteudo());

                //Se o código nas 4 primeiras posições for equivalente ao opcode de add
                }else if(memoria.get(i).getConteudo().substring(0, 4).equals("0101")){
                    
                    add(memoria.get(i).getConteudo());
                
                //Se o código nas 4 primeiras posições for equivalente ao opcode de sub
                }else if(memoria.get(i).getConteudo().substring(0, 4).equals("0110")){
                        
                    sub(memoria.get(i).getConteudo());

                //Se o código nas 4 primeiras posições for equivalente ao opcode de move
                }else if(memoria.get(i).getConteudo().substring(0, 4).equals("0100")){
                        
                    move(memoria.get(i).getConteudo());

                //Se o código nas 4 primeiras posições for equivalente ao opcode de jump
                }else if(memoria.get(i).getConteudo().substring(0, 4).equals("1100")){
                            
                    i = j(memoria.get(i).getConteudo());

                //Se o código nas 4 primeiras posições for equivalente ao opcode de slt
                }else if(memoria.get(i).getConteudo().substring(0, 4).equals("1010")){
                            
                    slt(memoria.get(i).getConteudo());
                
                //Se o código nas 4 primeiras posições for equivalente ao opcode de beq
                }else if(memoria.get(i).getConteudo().substring(0, 4).equals("0111")){
                                
                    i = beq(memoria.get(i).getConteudo(), i);
                
                //Se o código nas 4 primeiras posições for equivalente ao opcode de bne
                }else if(memoria.get(i).getConteudo().substring(0, 4).equals("1000")){
                                    
                    i = bne(memoria.get(i).getConteudo(), i);
                
                }
            }
            // System.out.println(memoria.get(i).getConteudo());
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
        String MOVEbinario = completarBinario(Integer.toBinaryString(OPCODEMOVE), 4);
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
        linguagemdeMontagem = linguagemdeMontagem.replace("move", MOVEbinario);
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
        
        //Se o número binário possuir mais de 16 bits
        if(binario.length() > 16){
            int posicoes = binario.length() - 16;
            return binario.substring(posicoes, binario.length());
        }

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

    //Realiza o LI
    public static void li(String codigoMaquina){
        
        //Separa os opcodes de acordo com o espaço
        String opcodes[] = codigoMaquina.split(" ");

        //Compara cada um dos códigos com os registradores, se for igual, atribui o valor da constante ao registrador
        if(opcodes[1].equals("001")){
            s1 = opcodes[2];
        }else if(opcodes[1].equals("010")){
            s2 = opcodes[2];
        }else if(opcodes[1].equals("011")){
            s3 = opcodes[2];
        }else if(opcodes[1].equals("100")){
            s4 = opcodes[2];
        }else if(opcodes[1].equals("101")){
            s5 = opcodes[2];
        }

    }

    //Realiza o MOVE
    public static void move(String codigoMaquina){
        
        //Separa os opcodes de acordo com o espaço
        String opcodes[] = codigoMaquina.split(" ");

        //Compara cada um dos códigos com os registradores, se for igual, atribui o valor da constante ao registrador
        if(opcodes[1].equals("001")){
            s1 = converterRegistrador(opcodes[2]);
        }else if(opcodes[1].equals("010")){
            s2 = converterRegistrador(opcodes[2]);
        }else if(opcodes[1].equals("011")){
            s3 = converterRegistrador(opcodes[2]);
        }else if(opcodes[1].equals("100")){
            s4 = converterRegistrador(opcodes[2]);
        }else if(opcodes[1].equals("101")){
            s5 = converterRegistrador(opcodes[2]);
        }

    }

    //Realiza o J
    public static int j(String codigoMaquina){

        //Separa os opcodes de acordo com o espaço
        String opcodes[] = codigoMaquina.split(" ");

        //Retorna a linha correspondente em decimal
        return Integer.parseInt((opcodes[1]), 2);
    } 

    //Realiza o SLT
    public static int slt(String codigoMaquina){

        //Separa os opcodes de acordo com o espaço
        String opcodes[] = codigoMaquina.split(" ");

        
        return Integer.parseInt((opcodes[1]), 2);
    } 

    //Realiza o BEQ
    public static int beq(String codigoMaquina, int posicaoAtual){

        //Separa os opcodes de acordo com o espaço
        String opcodes[] = codigoMaquina.split(" ");

        //pega o valor de cada registrador ou constante passados por parâmetro
        int pam1 = Integer.parseInt(converterRegistrador(opcodes[1]), 2);
        int pam2 = Integer.parseInt(converterRegistrador(opcodes[2]), 2);

        //Se os parâmetros forem iguais, retorna a linha informada para realizar o pulo
        if(pam1 == pam2){
            return Integer.parseInt(opcodes[3], 2);
        }

        //Se os parâmetros não forem iguais, retorna a mesma posição, não realizando o pulo
        return posicaoAtual;
        
    } 

    //Realiza o BNE
    public static int bne(String codigoMaquina, int posicaoAtual){

        //Separa os opcodes de acordo com o espaço
        String opcodes[] = codigoMaquina.split(" ");

        //pega o valor de cada registrador ou constante passados por parâmetro
        int pam1 = Integer.parseInt(converterRegistrador(opcodes[1]), 2);
        int pam2 = Integer.parseInt(converterRegistrador(opcodes[2]), 2);

        //Se os parâmetros forem diferentes, retorna a linha informada para realizar o pulo
        if(pam1 != pam2){
            return Integer.parseInt(opcodes[3], 2);
        }

        //Se os parâmetros forem iguais, retorna a mesma posição, não realizando o pulo
        return posicaoAtual;
    }

    //Realiza o ADD
    public static void add(String codigoMaquina){
        
        //Separa os opcodes de acordo com o espaço
        String opcodes[] = codigoMaquina.split(" ");

        //Converte para inteiro o binário de acordo com o seu valor no registrador
        int soma1 = Integer.parseInt(converterRegistrador(opcodes[2]), 2);
        int soma2 = Integer.parseInt(converterRegistrador(opcodes[3]), 2);
        
        //Compara os opcodes com os registradores e atribui o resultado da soma no registrador equivalente
        if(opcodes[1].equals("001")){
            s1 = completarBinario(Integer.toBinaryString(soma1 + soma2), 16);
        }else if(opcodes[1].equals("010")){
            s2 = completarBinario(Integer.toBinaryString(soma1 + soma2), 16);
        }else if(opcodes[1].equals("011")){
            s3 = completarBinario(Integer.toBinaryString(soma1 + soma2), 16);
        }else if(opcodes[1].equals("100")){
            s4 = completarBinario(Integer.toBinaryString(soma1 + soma2), 16);
        }else if(opcodes[1].equals("101")){
            s5 = completarBinario(Integer.toBinaryString(soma1 + soma2), 16);
        }

    }

    //Realiza o SUB
    public static void sub(String codigoMaquina){
        
        //Separa os opcodes de acordo com o espaço
        String opcodes[] = codigoMaquina.split(" ");

        //Converte para inteiro o binário de acordo com o seu valor no registrador
        int sub1 = Integer.parseInt(converterRegistrador(opcodes[2]), 2);
        int sub2 = Integer.parseInt(converterRegistrador(opcodes[3]), 2);
        
        //Compara os opcodes com os registradores e atribui o resultado da subtração no registrador equivalente
        if(opcodes[1].equals("001")){
            s1 = completarBinario(Integer.toBinaryString(sub1 - sub2), 16);
        }else if(opcodes[1].equals("010")){
            s2 = completarBinario(Integer.toBinaryString(sub1 - sub2), 16);
        }else if(opcodes[1].equals("011")){
            s3 = completarBinario(Integer.toBinaryString(sub1 - sub2), 16);
        }else if(opcodes[1].equals("100")){
            s4 = completarBinario(Integer.toBinaryString(sub1 - sub2), 16);
        }else if(opcodes[1].equals("101")){
            s5 = completarBinario(Integer.toBinaryString(sub1 - sub2), 16);
        }

    }

    //Retorna o valor de um registrador de acordo com um opcode
    public static String converterRegistrador(String opcode){
        
        //Verifica se o opcode equivale a um registrador, se sim, retorna o valor desse registrador
        if(opcode.equals("001")){
            return s1;
        }else if(opcode.equals("010")){
            return s2;
        }else if(opcode.equals("011")){
            return s3;    
        }else if(opcode.equals("100")){
            return s4;
        }else if(opcode.equals("101")){
            return s5;
        }

        //Se o opcode não equivale a nenhum opcode, retorna o mesmo, provavelmente é uma constante
        return opcode;

    }

}