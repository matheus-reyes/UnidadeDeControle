
import java.io.*;

public class UC{
    
    //Registradores e seus opcodes
    final static int S1 = 1, S2 = 2, S3 = 3, S4 = 4, S5 = 5;
    
    //Instruções MIPS e seus opcodes
    final static int LI = 1, LW = 2, SW = 3, MOV = 4, ADD = 5, SUB = 6, BEQ = 7, BNE = 8, J = 9, SLT = 10;

    public static void main(String[] args) throws IOException {
        BufferedReader inputStream = null;
        
        try {
            
            //Recebe o arquivo de entrada
            inputStream = new BufferedReader(new FileReader("exemplo.txt"));
            //Cada linha do código MIPS
            String linha;
            //String que receberá o código de montagem linha por linha
            String codigodeMontagem;
            
            //Percorre o documento com o código MIPS
            while ((linha = inputStream.readLine()) != null) {
                //Exibe na tela linha por linha do código MIPS
                System.out.println(linha);
                //Recebe o código de montagem linha por linha
                codigodeMontagem = converterLinguagemdeMontagem(linha);
                //Converte para linguagem de máquina linha por linha
                converterLinguagemdeMaquina(codigodeMontagem);
            }

        }finally {
            
            //Fecha o arquivo
            if (inputStream != null) {
                inputStream.close();
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
                if(i != posicaoIgnorada){
                    codigoMaquina += codigoMips.charAt(i);
                }
            }
        }

        //Retorna o código de máquina
        return codigoMaquina;
    }

    //Função que recebe como parâmetro a linguagem de montagem e converte para linguagem de máquina
    public static void converterLinguagemdeMaquina(String linguagemdeMontagem){

        //Recebe os valores em binário das funções
        String LIbinario = completarBinario(Integer.toBinaryString(LI), 4);
        String LWbinario = completarBinario(Integer.toBinaryString(LW), 4);
        String SWbinario = completarBinario(Integer.toBinaryString(SW), 4);
        String MOVbinario = completarBinario(Integer.toBinaryString(MOV), 4);
        String ADDbinario = completarBinario(Integer.toBinaryString(ADD), 4);
        String SUBbinario = completarBinario(Integer.toBinaryString(SUB), 4);
        String BEQbinario = completarBinario(Integer.toBinaryString(BEQ), 4);
        String BNEbinario = completarBinario(Integer.toBinaryString(BNE), 4);
        String Jbinario = completarBinario(Integer.toBinaryString(J), 4);
        String SLTbinario = completarBinario(Integer.toBinaryString(SLT), 4);

        String S1binario = completarBinario(Integer.toBinaryString(S1), 3);
        String S2binario = completarBinario(Integer.toBinaryString(S2), 3);
        String S3binario = completarBinario(Integer.toBinaryString(S3), 3);
        String S4binario = completarBinario(Integer.toBinaryString(S4), 3);
        String S5binario = completarBinario(Integer.toBinaryString(S5), 3);
        
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
        
        System.out.println(linguagemdeMontagem);
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
}