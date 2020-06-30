
import java.io.*;
import java.util.*;

public class UC{
    
    //Registradores e seus opcodes
    final static int OPCODES1 = 1, OPCODES2 = 2, OPCODES3 = 3, OPCODES4 = 4;
    
    //Instruções MIPS e seus opcodes
    final static int OPCODELI = 1, OPCODELW = 2, OPCODESW = 3, OPCODEMOVE = 4, OPCODEADD = 5, 
    OPCODESUB = 6, OPCODEBEQ = 7, OPCODEBNE = 8, OPCODEJ = 9, OPCODESLT = 10;

    static LinkedList<Memoria> memoria = new LinkedList<Memoria>();
    static LinkedList<Microprograma> microprograma = new LinkedList<Microprograma>();

    static String s1 = "", s2 = "", s3 = "", s4 = "";

    public static void main(String[] args) throws IOException {
        BufferedReader inputStream = null;
       
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

                //Recebe o código de montagem linha por linha
                codigodeMontagem = FuncoesAuxiliares.converterLinguagemdeMontagem(linha);
                //Converte para linguagem de máquina linha por linha
                codigodeMaquina = FuncoesAuxiliares.converterLinguagemdeMaquina(codigodeMontagem);
                //Armazena o código na memória
                FuncoesAuxiliares.armazenarNaMemoria(codigodeMaquina);
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

                    FuncoesULA.li(memoria.get(i).getConteudo());

                //Se o código nas 4 primeiras posições for equivalente ao opcode de add
                }else if(memoria.get(i).getConteudo().substring(0, 4).equals("0101")){
                    
                    FuncoesULA.add(memoria.get(i).getConteudo());

                //Se o código nas 4 primeiras posições for equivalente ao opcode de sub
                }else if(memoria.get(i).getConteudo().substring(0, 4).equals("0110")){
                        
                    FuncoesULA.sub(memoria.get(i).getConteudo());

                //Se o código nas 4 primeiras posições for equivalente ao opcode de move
                }else if(memoria.get(i).getConteudo().substring(0, 4).equals("0100")){
                        
                    FuncoesULA.move(memoria.get(i).getConteudo());

                //Se o código nas 4 primeiras posições for equivalente ao opcode de jump
                }else if(memoria.get(i).getConteudo().substring(0, 4).equals("1001")){
                            
                    i = FuncoesULA.j(memoria.get(i).getConteudo());

                //Se o código nas 4 primeiras posições for equivalente ao opcode de slt
                }else if(memoria.get(i).getConteudo().substring(0, 4).equals("1010")){
                            
                    FuncoesULA.slt(memoria.get(i).getConteudo());
                
                //Se o código nas 4 primeiras posições for equivalente ao opcode de beq
                }else if(memoria.get(i).getConteudo().substring(0, 4).equals("0111")){
                                
                    i = FuncoesULA.beq(memoria.get(i).getConteudo(), i);
                
                //Se o código nas 4 primeiras posições for equivalente ao opcode de bne
                }else if(memoria.get(i).getConteudo().substring(0, 4).equals("1000")){
                                    
                    i = FuncoesULA.bne(memoria.get(i).getConteudo(), i);
                
                }
            }
            // System.out.println(memoria.get(i).getConteudo());
        }
        System.out.println("S1: " + (!s1.equals("") ? Integer.parseInt(FuncoesAuxiliares.converterRegistrador(s1), 2) : s1));
        System.out.println("S2: " + (!s2.equals("") ? Integer.parseInt(FuncoesAuxiliares.converterRegistrador(s2), 2) : s2));
        System.out.println("S3: " + (!s3.equals("") ? Integer.parseInt(FuncoesAuxiliares.converterRegistrador(s3), 2) : s3));
        System.out.println("S4: " + (!s4.equals("") ? Integer.parseInt(FuncoesAuxiliares.converterRegistrador(s4), 2) : s4));
        FuncoesAuxiliares.lerMicroprograma();
    }

}