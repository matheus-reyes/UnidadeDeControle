import java.io.*;
import java.util.*;

public class UC{
    
    //Barramentos
    static String BarramentoInterno = "", BarramentoExterno = "";

    //Registradores especiais
    static String ULA = "", MAR = "", MBR = "", PC = "", IR = "", CAR = "", CBR = "";

    //Registradores e seus opcodes
    final static int OPCODES1 = 1, OPCODES2 = 2, OPCODES3 = 3, OPCODES4 = 4;
    
    //Instruções MIPS e seus opcodes
    final static int OPCODELI = 1, OPCODELW = 2, OPCODESW = 3, OPCODEMOVE = 4, OPCODEADD = 5, 
    OPCODESUB = 6, OPCODEBEQ = 7, OPCODEBNE = 8, OPCODEJ = 9, OPCODESLT = 10, OPCODELA = 11;

    //Lista Ligada de objetos memória (contém o conteúdo do código MIPS e seus endereços)
    static LinkedList<Memoria> memoria = new LinkedList<Memoria>();
    //Lista Ligada de objetos microprograma (contém o o conteúdo do microprograma e seus endereços)
    static LinkedList<Microprograma> microprograma = new LinkedList<Microprograma>();
    //Map que possui a chave como nome do vetor e o valor como o início do endereço desse vetor
    static Map <String, Integer> listas = new LinkedHashMap <String, Integer>();

    static String s1 = "", s2 = "", s3 = "", s4 = "";

    public static void main(String[] args) throws IOException {
        BufferedReader inputStream = null;
        Scanner entrada = new Scanner(System.in);
        try {
            
            //Recebe o arquivo de entrada
            inputStream = new BufferedReader(new FileReader("exemplo_slt_j_move.txt"));
            //Cada linha do código MIPS
            String linha;
            //String que receberá o código de montagem linha por linha
            String codigodeMontagem;
            String codigodeMaquina;
            boolean text = false;
            boolean data = false;

            //Percorre o documento com o código MIPS
            while ((linha = inputStream.readLine()) != null) {

                //se a linha for igual a .text, marca na variável text
                if(linha.equals(".text")){
                    text = true;
                    data = false;
                // Se a linha for igual a .data, marca na variável data
                }else if(linha.equals(".data")){
                    data = true;
                    text = false;
                }
                
                // Se a última linha foi text, faz as operações relacionadas a esse marcador
                if(text && !linha.equals("") && !linha.equals(".text")){
                    //Recebe o código de montagem linha por linha
                    codigodeMontagem = FuncoesAuxiliares.converterLinguagemdeMontagem(linha);
                    //Converte para linguagem de máquina linha por linha
                    codigodeMaquina = FuncoesAuxiliares.converterLinguagemdeMaquina(codigodeMontagem);
                    //Armazena o código na memória
                    FuncoesAuxiliares.armazenarNaMemoria(codigodeMaquina);
                }

                //Se a última linha foi data, faz as operações relacionadas a esse marcador
                if(data && !linha.equals("") && !linha.equals(".data")){
                    FuncoesAuxiliares.armazenarLista(linha);
                }

            }

        }finally {
            
            //Fecha o arquivo
            if (inputStream != null) {
                inputStream.close();
            }
        } 

        //Função que lê o microprograma linha a linha e armazena na lista microprograma
        FuncoesAuxiliares.lerMicroprograma();

        boolean pulou = false;
        int enderecoAtual = -1;
        // Percorre a lista de memórias
        for(int i = 0; i < 500; i++){
            System.out.println("Digite Enter para ir a proxima linha");
            String enter = entrada.nextLine();
            if(!pulou){
                enderecoAtual = FuncoesAuxiliares.encontrarPosicaoMemoria(enderecoAtual + 1);
            }

            //endereco não encontrado
            if(enderecoAtual == -1){
                break;
            }

            IR = FuncoesAuxiliares.completarBinario(Integer.toBinaryString(enderecoAtual), 12);
            MBR = FuncoesAuxiliares.completarBinario(Integer.toBinaryString(enderecoAtual), 12);

            //Ignora linhas vazias
            if(!memoria.get(enderecoAtual).getConteudo().equals("")){

                //Se o código nas 4 primeiras posições for equivalente ao opcode de li
                if(memoria.get(enderecoAtual).getConteudo().substring(0, 4).equals("0001")){
                    
                    FuncoesULA.li(memoria.get(enderecoAtual).getConteudo());
                    pulou = false;

                //Se o código nas 4 primeiras posições for equivalente ao opcode de add
                }else if(memoria.get(enderecoAtual).getConteudo().substring(0, 4).equals("0101")){
                    
                    FuncoesULA.add(memoria.get(enderecoAtual).getConteudo());
                    pulou = false;

                //Se o código nas 4 primeiras posições for equivalente ao opcode de sub
                }else if(memoria.get(enderecoAtual).getConteudo().substring(0, 4).equals("0110")){
                        
                    FuncoesULA.sub(memoria.get(enderecoAtual).getConteudo());
                    pulou = false;
        
                //Se o código nas 4 primeiras posições for equivalente ao opcode de move
                }else if(memoria.get(enderecoAtual).getConteudo().substring(0, 4).equals("0100")){
                        
                    FuncoesULA.move(memoria.get(enderecoAtual).getConteudo());
                    pulou = false;

                //Se o código nas 4 primeiras posições for equivalente ao opcode de jump
                }else if(memoria.get(enderecoAtual).getConteudo().substring(0, 4).equals("1001")){
                   
                    enderecoAtual = FuncoesAuxiliares.encontrarPosicaoMemoria(FuncoesULA.j(memoria.get(enderecoAtual).getConteudo()));
                    pulou = true;

                //Se o código nas 4 primeiras posições for equivalente ao opcode de slt
                }else if(memoria.get(enderecoAtual).getConteudo().substring(0, 4).equals("1010")){
                            
                    FuncoesULA.slt(memoria.get(enderecoAtual).getConteudo());
                    pulou = false;
                
                //Se o código nas 4 primeiras posições for equivalente ao opcode de beq
                }else if(memoria.get(enderecoAtual).getConteudo().substring(0, 4).equals("0111")){
                    
                    int enderecoAntigo = enderecoAtual;
                    enderecoAtual = FuncoesULA.beq(memoria.get(enderecoAtual).getConteudo(), enderecoAtual);
                    //Houve pulo
                    if(enderecoAtual != (enderecoAntigo + 1)){
                        enderecoAtual = FuncoesAuxiliares.encontrarPosicaoMemoria(enderecoAtual);
                    }

                    pulou = true;
                    
                //Se o código nas 4 primeiras posições for equivalente ao opcode de bne
                }else if(memoria.get(enderecoAtual).getConteudo().substring(0, 4).equals("1000")){
                    
                    int enderecoAntigo = enderecoAtual;
                    enderecoAtual = FuncoesULA.bne(memoria.get(enderecoAtual).getConteudo(), enderecoAtual);
                    //Houve pulo
                    if(enderecoAtual != (enderecoAntigo + 1)){
                        enderecoAtual = FuncoesAuxiliares.encontrarPosicaoMemoria(enderecoAtual);
                    }

                    pulou = true;
                   
                //Se o código nas 4 primeiras posições for equivalente ao opcode de la
                }else if(memoria.get(enderecoAtual).getConteudo().substring(0, 4).equals("1011")){
                                        
                    FuncoesULA.la(memoria.get(enderecoAtual).getConteudo());
                    pulou = false;
                
                //Se o código nas 4 primeiras posições for equivalente ao opcode de lw
                }else if(memoria.get(enderecoAtual).getConteudo().substring(0, 4).equals("0010")){
                                            
                    FuncoesULA.lw(memoria.get(enderecoAtual).getConteudo());
                    pulou = false;
                
                //Se o código nas 4 primeiras posições for equivalente ao opcode de sw
                }else if(memoria.get(enderecoAtual).getConteudo().substring(0, 4).equals("0011")){

                    FuncoesULA.sw(memoria.get(enderecoAtual).getConteudo());
                    pulou = false;
                    
                }

                PC = FuncoesAuxiliares.completarBinario(Integer.toBinaryString(enderecoAtual + 1), 12);

                System.out.print("S1: " + (!s1.equals("") ? Integer.parseInt(FuncoesAuxiliares.converterRegistrador(s1), 2) : s1));
                System.out.print("   S2: " + (!s2.equals("") ? Integer.parseInt(FuncoesAuxiliares.converterRegistrador(s2), 2) : s2));
                System.out.print("   S3: " + (!s3.equals("") ? Integer.parseInt(FuncoesAuxiliares.converterRegistrador(s3), 2) : s3));
                System.out.println("   S4: " + (!s4.equals("") ? Integer.parseInt(FuncoesAuxiliares.converterRegistrador(s4), 2) : s4));
                System.out.print("ULA: " + (!ULA.equals("") ? Integer.parseInt(FuncoesAuxiliares.converterRegistrador(ULA), 2) : ULA));
                System.out.print("   MAR: " + (!MAR.equals("") ? Integer.parseInt(FuncoesAuxiliares.converterRegistrador(MAR), 2) : MAR));
                System.out.print("   MBR: " + (!MBR.equals("") ? Integer.parseInt(FuncoesAuxiliares.converterRegistrador(MBR), 2) : MBR));
                System.out.print("   IR: " + (!IR.equals("") ? Integer.parseInt(FuncoesAuxiliares.converterRegistrador(IR), 2) : IR));
                System.out.println("   PC: " + (!PC.equals("") ? Integer.parseInt(FuncoesAuxiliares.converterRegistrador(PC), 2) : PC));
                System.out.println();
            }

        }
    }

}