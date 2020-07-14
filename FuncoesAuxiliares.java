import java.io.*;
public class FuncoesAuxiliares {
    
    //Variável que armazena a primeira posição disponível para memória da lista
    static int primeiraPosicaoDisponivelLista = 500;
    static int primeiraPosicaoDisponivel = 0;

    //Recebe como parâmetro um endereço na memória e retorna a posição da lista da memória correspondente
    public static int encontrarPosicaoMemoria(int endereco){
        String enderecoBinario = completarBinario(Integer.toBinaryString(endereco), 12);
        int posicao = 0;
        for(int i = 0; i < UC.memoria.size(); i++){
            if((UC.memoria.get(i).getEndereco()).equals(enderecoBinario)){
                posicao = i;
            }
        }
        return posicao;
    }

    //Função que exibe o ciclo de execução, recebe como parâmetro a linha de início dele e o código de máquina da operação
    public static void exibirCicloExecucao(String enderecoInicio, String codigoMaquina){
        //Converte o endereço de início para decimal
        int enderecoComeco = Integer.parseInt(enderecoInicio, 2);

        //Percorre o microprograma a partir do endereço de início
        for(int i = enderecoComeco; i < UC.microprograma.size(); i++){
            
            //Se chegar uma linha vazia, para de exibir o microprograma
            if(UC.microprograma.get(i).getConteudo().equals("")){
                break;
            }
            
            //Divide as partes das linhas do microprograma
            String[] partesMicroprograma = UC.microprograma.get(i).getConteudo().split("<-");

            //LI
            if(codigoMaquina.substring(0, 4).equals("0001")){
                //Registrador
                String registrador = codigoMaquina.substring(4, 7);
                //Se há "R1" na porta de entrada
                if(partesMicroprograma[0].indexOf("R1") != -1){
                    //Atualiza o microprograma com as portas alteradas
                    String portasAlteradas = alterarPortas(UC.microprograma.get(i).getConteudo(), retornaPortaRegistrador(registrador)[0]);
                    UC.microprograma.get(i).setConteudo(portasAlteradas);
                }
            
            //LW
            } else if(codigoMaquina.substring(0, 4).equals("0010")){
                //Registradores
                String registrador1 = codigoMaquina.substring(4, 7);
                String registrador2 = codigoMaquina.substring(7, 10);
                //Se há "R1" na porta de entrada
                if(partesMicroprograma[0].indexOf("R1") != -1){
                    //Atualiza o microprograma com as portas alteradas
                    String portasAlteradas = alterarPortas(UC.microprograma.get(i).getConteudo(), retornaPortaRegistrador(registrador1)[0]);
                    UC.microprograma.get(i).setConteudo(portasAlteradas);
                }
                //Se há "R1" na porta de saída
                if(partesMicroprograma[1].indexOf("R1") != -1){
                    //Atualiza o microprograma com as portas alteradas
                    String portasAlteradas = alterarPortas(UC.microprograma.get(i).getConteudo(), retornaPortaRegistrador(registrador1)[1]);
                    UC.microprograma.get(i).setConteudo(portasAlteradas);
                }
                //Se há "R2" na porta de saída
                if(partesMicroprograma[1].indexOf("R2") != -1){
                    //Atualiza o microprograma com as portas alteradas
                    String portasAlteradas = alterarPortas(UC.microprograma.get(i).getConteudo(), retornaPortaRegistrador(registrador2)[1]);
                    UC.microprograma.get(i).setConteudo(portasAlteradas);
                }
            
            //MOVE
            }else if(codigoMaquina.substring(0, 4).equals("0100")){
                //Registradores
                String registrador1 = codigoMaquina.substring(4, 7);
                String registrador2 = codigoMaquina.substring(7, 10);
                //Se há "R1" na porta de entrada
                if(partesMicroprograma[0].indexOf("R1") != -1){
                    //Atualiza o microprograma com as portas alteradas
                    String portasAlteradas = alterarPortas(UC.microprograma.get(i).getConteudo(), retornaPortaRegistrador(registrador1)[0]);
                    UC.microprograma.get(i).setConteudo(portasAlteradas);
                }
                //Se há "R2" na porta de saída
                if(partesMicroprograma[1].indexOf("R2") != -1){
                    //Atualiza o microprograma com as portas alteradas
                    String portasAlteradas = alterarPortas(UC.microprograma.get(i).getConteudo(), retornaPortaRegistrador(registrador2)[1]);
                    UC.microprograma.get(i).setConteudo(portasAlteradas);
                }
            
            //ADD
            }else if(codigoMaquina.substring(0, 4).equals("0101")){
                //Registradores
                String registrador1 = codigoMaquina.substring(4, 7);
                String registrador2 = codigoMaquina.substring(7, 10);
                String registrador3 = "";
                //Verifica se o segundo parâmetro é uma constante ou registrador e armazena os bits correspondentes
                if(verificaRegistrador(codigoMaquina.substring(10, 22))){
                    registrador3 = codigoMaquina.substring(10, 13);

                }
                
                //Se há "R1" na porta de entrada
                if(partesMicroprograma[0].indexOf("R1") != -1){
                    //Atualiza o microprograma com as portas alteradas
                    String portasAlteradas = alterarPortas(UC.microprograma.get(i).getConteudo(), retornaPortaRegistrador(registrador1)[0]);
                    UC.microprograma.get(i).setConteudo(portasAlteradas);
                }
                //Se há "R1" na porta de saída
                if(partesMicroprograma[1].indexOf("R1") != -1){
                    //Atualiza o microprograma com as portas alteradas
                    String portasAlteradas = alterarPortas(UC.microprograma.get(i).getConteudo(), retornaPortaRegistrador(registrador1)[1]);
                    UC.microprograma.get(i).setConteudo(portasAlteradas);
                }
                //Se há "R2" na porta de saída
                if(partesMicroprograma[1].indexOf("R2") != -1){
                    //Atualiza o microprograma com as portas alteradas
                    String portasAlteradas = alterarPortas(UC.microprograma.get(i).getConteudo(), retornaPortaRegistrador(registrador2)[1]);
                    UC.microprograma.get(i).setConteudo(portasAlteradas);
                }
                //Se há "R3" na porta de saída
                if(partesMicroprograma[1].indexOf("R3") != -1){
                    //Atualiza o microprograma com as portas alteradas
                    String portasAlteradas = alterarPortas(UC.microprograma.get(i).getConteudo(), retornaPortaRegistrador(registrador3)[1]);
                    UC.microprograma.get(i).setConteudo(portasAlteradas);
                }
            
            //SUB
            }else if(codigoMaquina.substring(0, 4).equals("0110")){
                //Registradores
                String registrador1 = codigoMaquina.substring(4, 7);
                String registrador2 = codigoMaquina.substring(7, 10);
                String registrador3 = "";
                //Verifica se o segundo parâmetro é uma constante ou registrador e armazena os bits correspondentes
                if(verificaRegistrador(codigoMaquina.substring(10, 22))){
                    registrador3 = codigoMaquina.substring(10, 13);
                }
                
                //Se há "R1" na porta de entrada
                if(partesMicroprograma[0].indexOf("R1") != -1){
                    //Atualiza o microprograma com as portas alteradas
                    String portasAlteradas = alterarPortas(UC.microprograma.get(i).getConteudo(), retornaPortaRegistrador(registrador1)[0]);
                    UC.microprograma.get(i).setConteudo(portasAlteradas);
                }
                //Se há "R1" na porta de saída
                if(partesMicroprograma[1].indexOf("R1") != -1){
                    //Atualiza o microprograma com as portas alteradas
                    String portasAlteradas = alterarPortas(UC.microprograma.get(i).getConteudo(), retornaPortaRegistrador(registrador1)[1]);
                    UC.microprograma.get(i).setConteudo(portasAlteradas);
                }
                //Se há "R2" na porta de saída
                if(partesMicroprograma[1].indexOf("R2") != -1){
                    //Atualiza o microprograma com as portas alteradas
                    String portasAlteradas = alterarPortas(UC.microprograma.get(i).getConteudo(), retornaPortaRegistrador(registrador2)[1]);
                    UC.microprograma.get(i).setConteudo(portasAlteradas);
                }
                //Se há "R3" na porta de saída
                if(partesMicroprograma[1].indexOf("R3") != -1){
                    //Atualiza o microprograma com as portas alteradas
                    String portasAlteradas = alterarPortas(UC.microprograma.get(i).getConteudo(), retornaPortaRegistrador(registrador3)[1]);
                    UC.microprograma.get(i).setConteudo(portasAlteradas);
                }
            
            //beq
            }else if(codigoMaquina.substring(0, 4).equals("0111")){
                //Registradores
                String registrador1 = codigoMaquina.substring(4, 7);
                String registrador2 = "";

                //Verifica se o segundo parâmetro é uma constante ou registrador e armazena os bits correspondentes
                if(verificaRegistrador(codigoMaquina.substring(7, 19))){
                    registrador2 = codigoMaquina.substring(7, 10);
                }
                
                //Se há "R1" na porta de saída
                if(partesMicroprograma[1].indexOf("R1") != -1){
                    //Atualiza o microprograma com as portas alteradas
                    String portasAlteradas = alterarPortas(UC.microprograma.get(i).getConteudo(), retornaPortaRegistrador(registrador1)[1]);
                    UC.microprograma.get(i).setConteudo(portasAlteradas);
                }
                //Se há "R2" na porta de saída
                if(partesMicroprograma[1].indexOf("R2") != -1){
                    //Atualiza o microprograma com as portas alteradas
                    String portasAlteradas = alterarPortas(UC.microprograma.get(i).getConteudo(), retornaPortaRegistrador(registrador2)[1]);
                    UC.microprograma.get(i).setConteudo(portasAlteradas);
                }
            
            //bne
            }else if(codigoMaquina.substring(0, 4).equals("1000")){
                //Registradores
                String registrador1 = codigoMaquina.substring(4, 7);
                String registrador2 = "";

                //Verifica se o segundo parâmetro é uma constante ou registrador e armazena os bits correspondentes
                if(verificaRegistrador(codigoMaquina.substring(7, 19))){
                    registrador2 = codigoMaquina.substring(7, 10);
                }
                
                //Se há "R1" na porta de saída
                if(partesMicroprograma[1].indexOf("R1") != -1){
                    //Atualiza o microprograma com as portas alteradas
                    String portasAlteradas = alterarPortas(UC.microprograma.get(i).getConteudo(), retornaPortaRegistrador(registrador1)[1]);
                    UC.microprograma.get(i).setConteudo(portasAlteradas);
                }
                //Se há "R2" na porta de saída
                if(partesMicroprograma[1].indexOf("R2") != -1){
                    //Atualiza o microprograma com as portas alteradas
                    String portasAlteradas = alterarPortas(UC.microprograma.get(i).getConteudo(), retornaPortaRegistrador(registrador2)[1]);
                    UC.microprograma.get(i).setConteudo(portasAlteradas);
                }
            }
            
            System.out.println(UC.microprograma.get(i).getConteudo());
        }
       
    }

    //Função que recebe um registrador e retorna a porta de entrada e saída do mesmo
    public static int[] retornaPortaRegistrador(String registrador){
        //Inicializa o vetor de portas
        int portas[] = {0 , 0};

        //Se o registrador for S1, adiciona as portas certas
        if(registrador.equals("001")){
            portas[0] = 6;
            portas[1] = 7;
        //Se o registrador for S2, adiciona as portas certas
        }else if(registrador.equals("010")){
            portas[0] = 8;
            portas[1] = 9;
        //Se o registrador for S3, adiciona as portas certas
        }else if(registrador.equals("011")){
            portas[0] = 10;
            portas[1] = 11;
        //Se o registrador for S4, adiciona as portas certas
        }else if(registrador.equals("100")){
            portas[0] = 12;
            portas[1] = 13;
        }

        return portas;
    }

    //Função que altera as portas do microprograma, recebe como parâmetro as portas sem alteração e a posição que deve ser ligada
    public static String alterarPortas(String linhaMicroprograma, int posicao){

        //Microprograma com as alterações na porta
        String microprogramaAlterado = "";
        posicao = 29 + posicao;

        //Percorre a linha do microprograma
        for(int i = 0; i < linhaMicroprograma.length(); i++){

            //Se for a porta que queremos alterar, então ligamos o bit
            if(i == posicao){
                microprogramaAlterado += "1";
            }else{
                microprogramaAlterado += linhaMicroprograma.charAt(i);
            }
        }

        return microprogramaAlterado;

    }

    //Função responsável por ler a linha de declação do array e armazenar na memória
    public static void armazenarLista(String linha){

        //Separa a String com o caractere dois pontos
        String[] lista = linha.split(":");
        //Captura o nome da lista informado no código MIPS
        String nomeLista = lista[0];
        //Insere no Map a chave sendo o nome da lista e valor sendo a primeira posição
        UC.listas.put(nomeLista, primeiraPosicaoDisponivelLista);
        //Separa a String com o caractere espaço
        String[] valoresLista = lista[1].split(" ");
        for(int i = 2; i < valoresLista.length; i++){
            //transforma virgula em espaço
            valoresLista[i] = valoresLista[i].replace(",", " ");
            //tira os espaços
            valoresLista[i] = valoresLista[i].replace(" ", "");
            //Cria endereços sucessivos de acordo com o tamanho de linhas
            String endereco = completarBinario(Integer.toBinaryString(primeiraPosicaoDisponivelLista), 12);
            //Cria uma instância de memória passando o endereço e o conteúdo sendo o códugo de máquina
            Memoria novaMemoria = new Memoria(endereco, completarBinario(Integer.toBinaryString(Integer.valueOf(valoresLista[i])), 12));
            //Adiciona a instância da memória na lista ligada
            UC.memoria.add(novaMemoria);
            //Atualiza a primeira posição disponível
            primeiraPosicaoDisponivelLista++;
        }

    }

    //função responsável por ler o microprograma e armazenar os dados
    public static void lerMicroprograma() throws IOException{
        
        BufferedReader inputStream = null;
       
        try {
            
            //Recebe o arquivo de entrada
            inputStream = new BufferedReader(new FileReader("microprograma.txt"));
            //Cada linha do código MIPS
            String linha;
            
            //Percorre o documento com o código MIPS
            while ((linha = inputStream.readLine()) != null) {
                //Cria endereços sucessivos de acordo com o tamanho de linhas
                String endereco = completarBinario(Integer.toBinaryString(UC.microprograma.size()), 12);
                //Cria uma instância de memória passando o endereço e o conteúdo da linha
                Microprograma linhaMicroprograma = new Microprograma (endereco, linha);
                //Adiciona a instância da memória na lista ligada
                UC.microprograma.add(linhaMicroprograma);
            }

        }finally {
            
            //Fecha o arquivo
            if (inputStream != null) {
                inputStream.close();
            }
        } 
    }

    //Verifica se há um registrador de acordo com um opcode
    public static boolean verificaRegistrador(String opcode){
        
        //Variável que verifica se o opcode é um registrador
        boolean ehRegistrador = false;
        boolean todosZerosDireita = true;
        String zerosDireita = "";

        //Verifica se o opcode pode ser uma constante ou um endereço, e analisa os bits correspondentes
        zerosDireita = opcode.substring(3, 12);

        //Verifica os 0s a direita, se for tudo 0, é um registrador
        for(int i = 0; i < zerosDireita.length(); i++){
            if(zerosDireita.charAt(i) != '0'){
                todosZerosDireita = false;
            }
        }

        //Verifica se o opcode equivale a um registrador, se sim, retorna o valor desse registrador
        if(opcode.substring(0, 3).equals("001") && todosZerosDireita){
            ehRegistrador = true;
        }else if(opcode.substring(0, 3).equals("010") && todosZerosDireita){
            ehRegistrador = true;
        }else if(opcode.substring(0, 3).equals("011") && todosZerosDireita){
            ehRegistrador = true;
        }else if(opcode.substring(0, 3).equals("100") && todosZerosDireita){
            ehRegistrador = true;
        }
        
        //Se o opcode não equivale a nenhum opcode, retorna o mesmo, provavelmente é uma constante
        return ehRegistrador;

    }

    //Retorna o valor de um registrador de acordo com um opcode
    public static String converterRegistrador(String opcode){
        
        //Verifica se o opcode equivale a um registrador, se sim, retorna o valor desse registrador
        if(opcode.equals("001")){
            return UC.s1;
        }else if(opcode.equals("010")){
            return UC.s2;
        }else if(opcode.equals("011")){
            return UC.s3;    
        }else if(opcode.equals("100")){
            return UC.s4;
        }

        //Se o opcode não equivale a nenhum opcode, retorna o mesmo, provavelmente é uma constante
        return opcode;

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
                }else{
                    if(codigoMips.substring(0, 2).equals("la")){
                        if((i != posicaoIgnorada) && (!isInteger(String.valueOf(codigoMips.charAt(i)), 10)) && i < 8){
                            codigoMaquina += codigoMips.charAt(i);
                        }

                    }else if((i != posicaoIgnorada) && (!isInteger(String.valueOf(codigoMips.charAt(i)), 10))){
                        codigoMaquina += codigoMips.charAt(i);
                    }
                }
            }
            
            //Remove os parênteses
            codigoMaquina = codigoMaquina.replace("(", "");
            codigoMaquina = codigoMaquina.replace(")", "");

            //Converte o valor para binário
            String[] codigoMipsSeparado = codigoMips.split(" ");
            
       
            for(int i = 0; i < codigoMipsSeparado.length; i++){
                if(isInteger(codigoMipsSeparado[i].replace(",", ""), 10)){
                    int constante = Integer.parseInt(codigoMipsSeparado[i].replace(",", ""));
                    String constanteConvertida = completarBinario(Integer.toBinaryString(constante), 12);
                    codigoMaquina += constanteConvertida;
                }
            }

            try{
                if(!isInteger(codigoMipsSeparado[codigoMipsSeparado.length - 1], 10)){
                    String enderecoLista = completarBinario(Integer.toBinaryString(UC.listas.get(codigoMipsSeparado[codigoMipsSeparado.length - 1])), 12);
                    codigoMaquina += enderecoLista;
                }
            }catch(Exception ignore){

            }            

            //Retorna o código de máquina
            return codigoMaquina;
        }
    
        //Função que recebe como parâmetro a linguagem de montagem e converte para linguagem de máquina
        public static String converterLinguagemdeMaquina(String linguagemdeMontagem){
    
            //Recebe os valores em binário das funções
            String LIbinario = completarBinario(Integer.toBinaryString(UC.OPCODELI), 4);
            String LWbinario = completarBinario(Integer.toBinaryString(UC.OPCODELW), 4);
            String SWbinario = completarBinario(Integer.toBinaryString(UC.OPCODESW), 4);
            String MOVEbinario = completarBinario(Integer.toBinaryString(UC.OPCODEMOVE), 4);
            String ADDbinario = completarBinario(Integer.toBinaryString(UC.OPCODEADD), 4);
            String SUBbinario = completarBinario(Integer.toBinaryString(UC.OPCODESUB), 4);
            String BEQbinario = completarBinario(Integer.toBinaryString(UC.OPCODEBEQ), 4);
            String BNEbinario = completarBinario(Integer.toBinaryString(UC.OPCODEBNE), 4);
            String Jbinario = completarBinario(Integer.toBinaryString(UC.OPCODEJ), 4);
            String SLTbinario = completarBinario(Integer.toBinaryString(UC.OPCODESLT), 4);
            String LAbinario = completarBinario(Integer.toBinaryString(UC.OPCODELA), 4);
    
            String S1binario = completarBinario(Integer.toBinaryString(UC.OPCODES1), 3);
            String S2binario = completarBinario(Integer.toBinaryString(UC.OPCODES2), 3);
            String S3binario = completarBinario(Integer.toBinaryString(UC.OPCODES3), 3);
            String S4binario = completarBinario(Integer.toBinaryString(UC.OPCODES4), 3);
                   
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
            linguagemdeMontagem = linguagemdeMontagem.replace("la", LAbinario);
    
            linguagemdeMontagem = linguagemdeMontagem.replace("$1", S1binario);
            linguagemdeMontagem = linguagemdeMontagem.replace("$2", S2binario);
            linguagemdeMontagem = linguagemdeMontagem.replace("$3", S3binario);
            linguagemdeMontagem = linguagemdeMontagem.replace("$4", S4binario);
    
            //Remove as vírgulas
            linguagemdeMontagem = linguagemdeMontagem.replace(",", "");
            String linguagemdeMontagemComEspaco = linguagemdeMontagem;
            //Remove os espaços
            linguagemdeMontagem = linguagemdeMontagem.replace(" ", "");
            String espacos[] = linguagemdeMontagemComEspaco.split(" ");
            int numeroespacos = (espacos.length - 1);
    
            //Se a liguagem de montagem possuir mais de 5 caracteres (ignora espaços em brancos e alguns comandos)
            if(linguagemdeMontagem.length() > 5){
                //Completa com bits a direita
                linguagemdeMontagem = completarBinarioDireita(linguagemdeMontagem, (32));
                linguagemdeMontagemComEspaco = completarBinarioDireita(linguagemdeMontagemComEspaco, (32 + numeroespacos));
            }
            
            System.out.println(linguagemdeMontagemComEspaco);
            System.out.println(linguagemdeMontagem);
            return linguagemdeMontagem;
        }
    
        //Completa o número com 0 se o número ocupar menos que 4 bits
        public static String completarBinario(String binario, int numeroBits){
            
            //Se o número binário possuir mais de 12 bits
            if(binario.length() > 12){
                int posicoes = binario.length() - 12;
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
    
        //Completa o número com 0 a direita se o número ocupar menos que 4 bits
        public static String completarBinarioDireita(String binario, int numeroBits){
            
            //String que armazenará os zeros a direita
            String zeros = "";
            //completa o número certo de zeros a direita
            for(int i = binario.length(); i < numeroBits; i++){
                zeros += "0";
            }
            //retorna o número binário convertido
            return binario + zeros;
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
            String endereco = completarBinario(Integer.toBinaryString(primeiraPosicaoDisponivel), 12);
            primeiraPosicaoDisponivel++;
            //Cria uma instância de memória passando o endereço e o conteúdo sendo o códugo de máquina
            Memoria novaMemoria = new Memoria(endereco, linguagemdeMaquina);
            //Adiciona a instância da memória na lista ligada
            UC.memoria.add(novaMemoria);
        }

}