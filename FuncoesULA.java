public class FuncoesULA {

    //Realiza o LW
    public static void lw(String codigoMaquina){
        
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
        return (Integer.parseInt((opcodes[1]), 2) - 2);
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

        //Verifica se o segundo parâmetro é uma constante ou registrador e armazena os bits correspondentes
        if(FuncoesAuxiliares.verificaRegistrador(codigoMaquina.substring(7, 19))){
            opcodes[2] = codigoMaquina.substring(7, 10);
        }else{
            opcodes[2] = codigoMaquina.substring(7, 19);
        }
        
        opcodes[3] = codigoMaquina.substring(19, 31);

        //pega o valor de cada registrador ou constante passados por parâmetro
        int pam1 = Integer.parseInt(FuncoesAuxiliares.converterRegistrador(opcodes[1]), 2);
        int pam2 = Integer.parseInt(FuncoesAuxiliares.converterRegistrador(opcodes[2]), 2);

        //Se os parâmetros forem iguais, retorna a linha informada para realizar o pulo
        if(pam1 == pam2){
            return (Integer.parseInt(opcodes[3], 2) - 2);
        }

        //Se os parâmetros não forem iguais, retorna a mesma posição, não realizando o pulo
        return posicaoAtual;
        
    } 

    //Realiza o BNE
    public static int bne(String codigoMaquina, int posicaoAtual){

        String opcodes[] = new String[4];
        //Recebe em cada posição dos opcodes o trecho da String correspondente
        opcodes[0] = codigoMaquina.substring(0, 4);
        opcodes[1] = codigoMaquina.substring(4, 7);

        //Verifica se o segundo parâmetro é uma constante ou registrador e armazena os bits correspondentes
        if(FuncoesAuxiliares.verificaRegistrador(codigoMaquina.substring(7, 19))){
            opcodes[2] = codigoMaquina.substring(7, 10);
        }else{
            opcodes[2] = codigoMaquina.substring(7, 19);
        }
        
        opcodes[3] = codigoMaquina.substring(19, 31);

        //pega o valor de cada registrador ou constante passados por parâmetro
        int pam1 = Integer.parseInt(FuncoesAuxiliares.converterRegistrador(opcodes[1]), 2);
        int pam2 = Integer.parseInt(FuncoesAuxiliares.converterRegistrador(opcodes[2]), 2);

        //Se os parâmetros forem diferentes, retorna a linha informada para realizar o pulo
        if(pam1 != pam2){
            System.out.println(Integer.parseInt(opcodes[3], 2) - 2);
            return (Integer.parseInt(opcodes[3], 2) - 2);
        }

        //Se os parâmetros forem iguais, retorna a mesma posição, não realizando o pulo
        return posicaoAtual;
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
        }else{
            opcodes[3] = codigoMaquina.substring(10, 22);
        }

        //Converte para inteiro o binário de acordo com o seu valor no registrador
        int soma1 = Integer.parseInt(FuncoesAuxiliares.converterRegistrador(opcodes[2]), 2);
        int soma2 = Integer.parseInt(FuncoesAuxiliares.converterRegistrador(opcodes[3]), 2);
        
        //Compara os opcodes com os registradores e atribui o resultado da soma no registrador equivalente
        if(opcodes[1].equals("001")){
            UC.s1 = FuncoesAuxiliares.completarBinario(Integer.toBinaryString(soma1 + soma2), 16);
        }else if(opcodes[1].equals("010")){
            UC.s2 = FuncoesAuxiliares.completarBinario(Integer.toBinaryString(soma1 + soma2), 16);
        }else if(opcodes[1].equals("011")){
            UC.s3 = FuncoesAuxiliares.completarBinario(Integer.toBinaryString(soma1 + soma2), 16);
        }else if(opcodes[1].equals("100")){
            UC.s4 = FuncoesAuxiliares.completarBinario(Integer.toBinaryString(soma1 + soma2), 16);
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
        }else{
            opcodes[3] = codigoMaquina.substring(10, 22);
        }

        //Converte para inteiro o binário de acordo com o seu valor no registrador
        int sub1 = Integer.parseInt(FuncoesAuxiliares.converterRegistrador(opcodes[2]), 2);
        int sub2 = Integer.parseInt(FuncoesAuxiliares.converterRegistrador(opcodes[3]), 2);
        
        //Compara os opcodes com os registradores e atribui o resultado da subtração no registrador equivalente
        if(opcodes[1].equals("001")){
            UC.s1 = FuncoesAuxiliares.completarBinario(Integer.toBinaryString(sub1 - sub2), 16);
        }else if(opcodes[1].equals("010")){
            UC.s2 = FuncoesAuxiliares.completarBinario(Integer.toBinaryString(sub1 - sub2), 16);
        }else if(opcodes[1].equals("011")){
            UC.s3 = FuncoesAuxiliares.completarBinario(Integer.toBinaryString(sub1 - sub2), 16);
        }else if(opcodes[1].equals("100")){
            UC.s4 = FuncoesAuxiliares.completarBinario(Integer.toBinaryString(sub1 - sub2), 16);
        }

    }
}