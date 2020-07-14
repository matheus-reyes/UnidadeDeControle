public class FuncoesULA {

    //Realiza o SW
    public static void sw(String codigoMaquina){
        
        String opcodes[] = new String[4];
        //Recebe em cada posição dos opcodes o trecho da String correspondente
        opcodes[0] = codigoMaquina.substring(0, 4);
        //Registrador que sobrescreverá a posição no array
        opcodes[1] = codigoMaquina.substring(4, 7);
        //Registrador que contém o endereço do vetor
        opcodes[2] = codigoMaquina.substring(7, 10);
        //Constante que será somada com o endereço do vetor
        opcodes[3] = codigoMaquina.substring(10, 22);
        //Variável para guardar o endereço do vetor
        int enderecoVetor = Integer.parseInt(FuncoesAuxiliares.converterRegistrador(opcodes[2]), 2);
        //Variável para guardar a constante
        int constante = Integer.parseInt(opcodes[3], 2);
        //Variável para guardar o valor que sobrescreverá a posição do array
        String valorSobrescreve = FuncoesAuxiliares.converterRegistrador(opcodes[1]);
        //Variável para guardar o endereço+const em binário
        String enderecoBinario = FuncoesAuxiliares.completarBinario(Integer.toBinaryString(enderecoVetor + constante), 12);
        for(int i = 0; i < UC.memoria.size(); i++){
            if((UC.memoria.get(i).getEndereco()).equals(enderecoBinario)){
                UC.memoria.get(i).setConteudo(valorSobrescreve);
            }
        }
    }

    //Realiza o LW
    public static void lw(String codigoMaquina){
        
        String opcodes[] = new String[4];
        //Recebe em cada posição dos opcodes o trecho da String correspondente
        opcodes[0] = codigoMaquina.substring(0, 4);
        //Registrador que armazenará o valor do vetor
        opcodes[1] = codigoMaquina.substring(4, 7);
        //Registrador que contém o endereço do vetor
        opcodes[2] = codigoMaquina.substring(7, 10);
        //Constante que será somada com o endereço do vetor
        opcodes[3] = codigoMaquina.substring(10, 22);
        
        //Armazena no CAR a primeira linha do ciclo de execução do ADD (linha 72)
        UC.CAR = FuncoesAuxiliares.completarBinario(Integer.toBinaryString(72), 12);
        FuncoesAuxiliares.exibirCicloExecucao(UC.CAR, codigoMaquina);
        
        //Variável que armazena o valor correspondente a posição do vetor 
        String valorVetor = "";
        //Variável para guardar o endereço do vetor
        int enderecoVetor = Integer.parseInt(FuncoesAuxiliares.converterRegistrador(opcodes[2]), 2);
        //Varável para guardar a constante
        int constante = Integer.parseInt(opcodes[3], 2);
        //Variável para guardar o endereço+const em binário
        String enderecoBinario = FuncoesAuxiliares.completarBinario(Integer.toBinaryString(enderecoVetor + constante), 12);
        for(int i = 0; i < UC.memoria.size(); i++){
            if((UC.memoria.get(i).getEndereco()).equals(enderecoBinario)){
                valorVetor = UC.memoria.get(i).getConteudo();
            }
        }

        //Compara cada um dos códigos com os registradores, se for igual, atribui o valor da constante ao registrador
        if(opcodes[1].equals("001")){
            UC.s1 = valorVetor;
        }else if(opcodes[1].equals("010")){
            UC.s2 = valorVetor;
        }else if(opcodes[1].equals("011")){
            UC.s3 = valorVetor;
        }else if(opcodes[1].equals("100")){
            UC.s4 = valorVetor;
        }
    }
    
    //Realiza o LA
    public static void la(String codigoMaquina){
        
        String opcodes[] = new String[3];
        //Recebe em cada posição dos opcodes o trecho da String correspondente
        opcodes[0] = codigoMaquina.substring(0, 4);
        opcodes[1] = codigoMaquina.substring(4, 7);
        opcodes[2] = codigoMaquina.substring(7, 19);

        //Compara cada um dos códigos com os registradores, se for igual, atribui o valor da constante ao registrador
        if(opcodes[1].equals("001")){
            UC.s1 = opcodes[2];
        }else if(opcodes[1].equals("010")){
            UC.s2 = opcodes[2];
        }else if(opcodes[1].equals("011")){
            UC.s3 = opcodes[2];
        }else if(opcodes[1].equals("100")){
            UC.s4 = opcodes[2];
        }
    }

    //Realiza o LI
    public static void li(String codigoMaquina){
        
        String opcodes[] = new String[3];
        //Recebe em cada posição dos opcodes o trecho da String correspondente
        opcodes[0] = codigoMaquina.substring(0, 4);
        opcodes[1] = codigoMaquina.substring(4, 7);
        opcodes[2] = codigoMaquina.substring(7, 19);

        //Armazena no CAR a primeira linha do ciclo de execução do ADD (linha 65)
        UC.CAR = FuncoesAuxiliares.completarBinario(Integer.toBinaryString(65), 12);
        FuncoesAuxiliares.exibirCicloExecucao(UC.CAR, codigoMaquina);

        //Compara cada um dos códigos com os registradores, se for igual, atribui o valor da constante ao registrador
        if(opcodes[1].equals("001")){
            UC.s1 = opcodes[2];
        }else if(opcodes[1].equals("010")){
            UC.s2 = opcodes[2];
        }else if(opcodes[1].equals("011")){
            UC.s3 = opcodes[2];
        }else if(opcodes[1].equals("100")){
            UC.s4 = opcodes[2];
        }
    }

    //Realiza o MOVE
    public static void move(String codigoMaquina){
        
        String opcodes[] = new String[3];
        //Recebe em cada posição dos opcodes o trecho da String correspondente
        opcodes[0] = codigoMaquina.substring(0, 4);
        opcodes[1] = codigoMaquina.substring(4, 7);
        opcodes[2] = codigoMaquina.substring(7, 10);

        //Armazena no CAR a primeira linha do ciclo de execução do ADD (linha 38)
        UC.CAR = FuncoesAuxiliares.completarBinario(Integer.toBinaryString(38), 12);
        FuncoesAuxiliares.exibirCicloExecucao(UC.CAR, codigoMaquina);

        //Compara cada um dos códigos com os registradores, se for igual, atribui o valor da constante ao registrador
        if(opcodes[1].equals("001")){
            UC.s1 = FuncoesAuxiliares.converterRegistrador(opcodes[2]);
        }else if(opcodes[1].equals("010")){
            UC.s2 = FuncoesAuxiliares.converterRegistrador(opcodes[2]);
        }else if(opcodes[1].equals("011")){
            UC.s3 = FuncoesAuxiliares.converterRegistrador(opcodes[2]);
        }else if(opcodes[1].equals("100")){
            UC.s4 = FuncoesAuxiliares.converterRegistrador(opcodes[2]);
        }

    }

    //Realiza o J
    public static int j(String codigoMaquina){

        String opcodes[] = new String[2];
        //Recebe em cada posição dos opcodes o trecho da String correspondente
        opcodes[0] = codigoMaquina.substring(0, 4);
        opcodes[1] = codigoMaquina.substring(4, 16);
 
        //Retorna a linha correspondente em decimal
        return Integer.parseInt((opcodes[1]), 2) - 1;
    } 

    //Realiza o SLT
    public static void slt(String codigoMaquina){

        String opcodes[] = new String[4];
        //Recebe em cada posição dos opcodes o trecho da String correspondente
        opcodes[0] = codigoMaquina.substring(0, 4);
        opcodes[1] = codigoMaquina.substring(4, 7);
        opcodes[2] = codigoMaquina.substring(7, 10);
        opcodes[3] = codigoMaquina.substring(10, 13);

        //pega o valor de cada registrador ou constante passados por parâmetro
        int pam1 = Integer.parseInt(FuncoesAuxiliares.converterRegistrador(opcodes[2]), 2);
        int pam2 = Integer.parseInt(FuncoesAuxiliares.converterRegistrador(opcodes[3]), 2);

        if(pam1 < pam2){
            if(opcodes[1].equals("001")){
                UC.s1 = "1";
            }else if(opcodes[1].equals("010")){
                UC.s2 = "1";
            }else if(opcodes[1].equals("011")){
                UC.s3 = "1";
            }else if(opcodes[1].equals("100")){
                UC.s4 = "1";
            }
        }else{
            if(opcodes[1].equals("001")){
                UC.s1 = "0";
            }else if(opcodes[1].equals("010")){
                UC.s2 = "0";
            }else if(opcodes[1].equals("011")){
                UC.s3 = "0";
            }else if(opcodes[1].equals("100")){
                UC.s4 = "0";
            }
        }
    
    } 

    //Realiza o BEQ
    public static int beq(String codigoMaquina, int posicaoAtual){

        String opcodes[] = new String[4];
        //Recebe em cada posição dos opcodes o trecho da String correspondente
        opcodes[0] = codigoMaquina.substring(0, 4);
        opcodes[1] = codigoMaquina.substring(4, 7);
        //Variável auxiliar para armazenar se é ou não uma constate
        boolean constante = false;

        //Verifica se o segundo parâmetro é uma constante ou registrador e armazena os bits correspondentes
        if(FuncoesAuxiliares.verificaRegistrador(codigoMaquina.substring(7, 19))){
            opcodes[2] = codigoMaquina.substring(7, 10);

            //Armazena no CAR a primeira linha do ciclo de execução do BEQ registrador com registrador (linha 41)
            UC.CAR = FuncoesAuxiliares.completarBinario(Integer.toBinaryString(41), 12);
            FuncoesAuxiliares.exibirCicloExecucao(UC.CAR, codigoMaquina);
        }else{
            opcodes[2] = codigoMaquina.substring(7, 19);
            constante = true;

            //Armazena no CAR a primeira linha do ciclo de execução do BEQ registrador com constante (linha 45)
            UC.CAR = FuncoesAuxiliares.completarBinario(Integer.toBinaryString(45), 12);
            FuncoesAuxiliares.exibirCicloExecucao(UC.CAR, codigoMaquina);
        }
        
        if(constante){
            opcodes[3] = codigoMaquina.substring(19, 31);
        }else{
            opcodes[3] = codigoMaquina.substring(10, 22);
        }

        //pega o valor de cada registrador ou constante passados por parâmetro
        int pam1 = Integer.parseInt(FuncoesAuxiliares.converterRegistrador(opcodes[1]), 2);
        int pam2 = Integer.parseInt(FuncoesAuxiliares.converterRegistrador(opcodes[2]), 2);

        //Se os parâmetros forem iguais, retorna a linha informada para realizar o pulo
        if(pam1 == pam2){
            return (Integer.parseInt(opcodes[3], 2) - 1);
        }

        //Se os parâmetros não forem iguais, retorna a mesma posição, não realizando o pulo
        return posicaoAtual + 1;
        
    } 

    //Realiza o BNE
    public static int bne(String codigoMaquina, int posicaoAtual){

        String opcodes[] = new String[4];
        //Recebe em cada posição dos opcodes o trecho da String correspondente
        opcodes[0] = codigoMaquina.substring(0, 4);
        opcodes[1] = codigoMaquina.substring(4, 7);
        //Variável auxiliar para armazenar se é ou não uma constate
        boolean constante = false;

        //Verifica se o segundo parâmetro é uma constante ou registrador e armazena os bits correspondentes
        if(FuncoesAuxiliares.verificaRegistrador(codigoMaquina.substring(7, 19))){
            opcodes[2] = codigoMaquina.substring(7, 10);

            //Armazena no CAR a primeira linha do ciclo de execução do BNE registrador com registrador (linha 50)
            UC.CAR = FuncoesAuxiliares.completarBinario(Integer.toBinaryString(50), 12);
            FuncoesAuxiliares.exibirCicloExecucao(UC.CAR, codigoMaquina);
        }else{
            opcodes[2] = codigoMaquina.substring(7, 19);
            constante = true;

            //Armazena no CAR a primeira linha do ciclo de execução do BNE registrador com constante (linha 54)
            UC.CAR = FuncoesAuxiliares.completarBinario(Integer.toBinaryString(54), 12);
            FuncoesAuxiliares.exibirCicloExecucao(UC.CAR, codigoMaquina);
        }

        if(constante){
            opcodes[3] = codigoMaquina.substring(19, 31);
        }else{
            opcodes[3] = codigoMaquina.substring(10, 22);
        }
        
        //pega o valor de cada registrador ou constante passados por parâmetro
        int pam1 = Integer.parseInt(FuncoesAuxiliares.converterRegistrador(opcodes[1]), 2);
        int pam2 = Integer.parseInt(FuncoesAuxiliares.converterRegistrador(opcodes[2]), 2);

        //Se os parâmetros forem diferentes, retorna a linha informada para realizar o pulo
        if(pam1 != pam2){
            return (Integer.parseInt(opcodes[3], 2) - 1);
        }

        //Se os parâmetros forem iguais, retorna a mesma posição, não realizando o pulo
        return posicaoAtual + 1;
    }

    //Realiza o ADD
    public static void add(String codigoMaquina){
        
        String opcodes[] = new String[4];
        //Recebe em cada posição dos opcodes o trecho da String correspondente
        opcodes[0] = codigoMaquina.substring(0, 4);
        opcodes[1] = codigoMaquina.substring(4, 7);
        opcodes[2] = codigoMaquina.substring(7, 10);

        //Verifica se o segundo parâmetro é uma constante ou registrador e armazena os bits correspondentes
        if(FuncoesAuxiliares.verificaRegistrador(codigoMaquina.substring(10, 22))){
            opcodes[3] = codigoMaquina.substring(10, 13);

            //Armazena no CAR a primeira linha do ciclo de execução do ADD registrador com registrador (linha 12)
            UC.CAR = FuncoesAuxiliares.completarBinario(Integer.toBinaryString(12), 12);
            FuncoesAuxiliares.exibirCicloExecucao(UC.CAR, codigoMaquina);
        }else{
            opcodes[3] = codigoMaquina.substring(10, 22);

            //Armazena no CAR a primeira linha do ciclo de execução do ADD registrador com constante (linha 18)
            UC.CAR = FuncoesAuxiliares.completarBinario(Integer.toBinaryString(18), 12);
            FuncoesAuxiliares.exibirCicloExecucao(UC.CAR, codigoMaquina);
        }

        //Converte para inteiro o binário de acordo com o seu valor no registrador
        int soma1 = Integer.parseInt(FuncoesAuxiliares.converterRegistrador(opcodes[2]), 2);
        int soma2 = Integer.parseInt(FuncoesAuxiliares.converterRegistrador(opcodes[3]), 2);
        
        //Compara os opcodes com os registradores e atribui o resultado da soma no registrador equivalente
        if(opcodes[1].equals("001")){
            UC.s1 = FuncoesAuxiliares.completarBinario(Integer.toBinaryString(soma1 + soma2), 12);
        }else if(opcodes[1].equals("010")){
            UC.s2 = FuncoesAuxiliares.completarBinario(Integer.toBinaryString(soma1 + soma2), 12);
        }else if(opcodes[1].equals("011")){
            UC.s3 = FuncoesAuxiliares.completarBinario(Integer.toBinaryString(soma1 + soma2), 12);
        }else if(opcodes[1].equals("100")){
            UC.s4 = FuncoesAuxiliares.completarBinario(Integer.toBinaryString(soma1 + soma2), 12);
        }

    }

    //Realiza o SUB
    public static void sub(String codigoMaquina){
        
        String opcodes[] = new String[4];
        //Recebe em cada posição dos opcodes o trecho da String correspondente
        opcodes[0] = codigoMaquina.substring(0, 4);
        opcodes[1] = codigoMaquina.substring(4, 7);
        opcodes[2] = codigoMaquina.substring(7, 10);

        //Verifica se o segundo parâmetro é uma constante ou registrador e armazena os bits correspondentes
        if(FuncoesAuxiliares.verificaRegistrador(codigoMaquina.substring(10, 22))){
            opcodes[3] = codigoMaquina.substring(10, 13);

            //Armazena no CAR a primeira linha do ciclo de execução do SUB registrador com registrador (linha 25)
            UC.CAR = FuncoesAuxiliares.completarBinario(Integer.toBinaryString(25), 12);
            FuncoesAuxiliares.exibirCicloExecucao(UC.CAR, codigoMaquina);
        }else{
            opcodes[3] = codigoMaquina.substring(10, 22);

            //Armazena no CAR a primeira linha do ciclo de execução do SUB registrador com constante (linha 31)
            UC.CAR = FuncoesAuxiliares.completarBinario(Integer.toBinaryString(31), 12);
            FuncoesAuxiliares.exibirCicloExecucao(UC.CAR, codigoMaquina);
        }

        //Converte para inteiro o binário de acordo com o seu valor no registrador
        int sub1 = Integer.parseInt(FuncoesAuxiliares.converterRegistrador(opcodes[2]), 2);
        int sub2 = Integer.parseInt(FuncoesAuxiliares.converterRegistrador(opcodes[3]), 2);
        
        //Compara os opcodes com os registradores e atribui o resultado da subtração no registrador equivalente
        if(opcodes[1].equals("001")){
            UC.s1 = FuncoesAuxiliares.completarBinario(Integer.toBinaryString(sub1 - sub2), 12);
        }else if(opcodes[1].equals("010")){
            UC.s2 = FuncoesAuxiliares.completarBinario(Integer.toBinaryString(sub1 - sub2), 12);
        }else if(opcodes[1].equals("011")){
            UC.s3 = FuncoesAuxiliares.completarBinario(Integer.toBinaryString(sub1 - sub2), 12);
        }else if(opcodes[1].equals("100")){
            UC.s4 = FuncoesAuxiliares.completarBinario(Integer.toBinaryString(sub1 - sub2), 12);
        }

    }
}