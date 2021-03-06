package com.github.maicmiller;

public class ArvoreBinaria< T extends Comparable<T> >  {

    private BinNo<T> raiz;

    public ArvoreBinaria(){
        this.raiz = null;
    }

    // Método responsável por chamar a exibição recursiva...
    public void inserir(T conteudo){
        BinNo<T> novoNo = new BinNo<>(conteudo); // Criando um novo nó do tipo conteúdo.
        raiz = inserir(raiz, novoNo);
    }

    /*
    * Método de inserir, que trabalha de forma recursiva, ele chamará ele mesmo até chegar na posição
    * que ele irá inserir o novo elemento.
    * */
    private BinNo<T> inserir(BinNo<T> atualNo, BinNo<T> novoNo){
        if (atualNo == null){ // Se o atual for nulo, quer dizer que estou na raiz e irei retornar esse novo nó e inseri-lo na raiz...
            return novoNo; // Que é o novo nó que está sendo inserido...
        }else if(novoNo.getConteudo().compareTo(atualNo.getConteudo()) < 0){ // Vamos comparar se o conteúdo do novo nó é menor que o conteúdo do nó atual. Se for menor retorna -1, se for igual retorna 0 e se for maior retorna 1.
            atualNo.setNoEsquerdo(inserir(atualNo.getNoEsquerdo(), novoNo));
        }else{  // se o conteúdo do novo nó for maior ou igual ao nó atual, vamos percorrer para a direita...
            atualNo.setNoDireito(inserir(atualNo.getNoDireito(), novoNo));
        }
        return atualNo; // Depois de percorrer todos esses loops vamos retornar o nó atual...
    }

    /*
    * Exibição em ordem do menor para o maior a partir da Raiz...
    * */
    public void exibirInOrdem(){
        System.out.println("\n Exibindo InOrdem!");
        exibirInOrdem(this.raiz);
    }
    /*
    * Como essa exibição recursiva é em ordem, primeiro visitamos o elemento da esquerda, depois exibe o nó e
    * então visita o nó da direita e vai fazendo isso, a lógica é essa...
    * Enquanto o nó não estiver nulo o método recursivo continua a exibir...
    * */
    private void exibirInOrdem(BinNo<T> atualNo){
        if (atualNo != null){ // Vamos conferir se o nó atual não está nulo, se sim quer dizer que está no final da exibição...
             exibirInOrdem(atualNo.getNoEsquerdo());
            System.out.print(atualNo.getConteudo() + ", "); // Exibindo o atual...
            exibirInOrdem(atualNo.getNoDireito()); // Visitando o da direita...
        }
    }

    /*
     * Exibição em ordem do menor para o maior a partir da Raiz...
     * */
    public void exibirPosOrdem(){
        System.out.println("\n Exibindo Pós-Ordem!");
        exibirPosOrdem(this.raiz);
    }
    /*
     * Como essa exibição recursiva é em Pós-ordem, primeiro ele visita depois exibe...
     * Enquanto o nó não estiver nulo o método recursivo continua a exibir...
     * */
    private void exibirPosOrdem(BinNo<T> atualNo){
        if (atualNo != null){ // Vamos conferir se o nó atual não está nulo, se sim quer dizer que está no final da exibição...
            exibirPosOrdem(atualNo.getNoEsquerdo());
            exibirPosOrdem(atualNo.getNoDireito()); // Visitando o da direita...
            System.out.print(atualNo.getConteudo() + ", "); // Exibindo o atual...
        }
    }

    /*
     * Exibição em ordem do menor para o maior a partir da Raiz...
     * */
    public void exibirPreOrdem(){
        System.out.println("\n Exibindo Pré-Ordem!");
        exibirPreOrdem(this.raiz);
    }
    /*
     * Como essa exibição recursiva é em Pré-ordem, primeiro exibimos depois ele visita os nós...
     * Enquanto o nó não estiver nulo o método recursivo continua a exibir...
     * */
    private void exibirPreOrdem(BinNo<T> atualNo){
        if (atualNo != null){ // Vamos conferir se o nó atual não está nulo, se sim quer dizer que está no final da exibição...
            System.out.print(atualNo.getConteudo() + ", "); // Exibindo o conteúdo principal...
            exibirPreOrdem(atualNo.getNoEsquerdo());
            exibirPreOrdem(atualNo.getNoDireito()); // Visitando o da direita...
        }
    }

    /*
    * Método remove
    * */
    public void remover(T conteudo){
        try {
            BinNo<T> atual = this.raiz;
            BinNo<T> pai = null;
            BinNo<T> filho = null;
            BinNo<T> temp = null;

            // 1º Encontrar o nó que tem o conteúdo que estou a passar ("...remover(T conteúdo){...")
            // 2º Vamos fazer um loop para percorrer a árvore para encontrar esse conteúdo...
            while (atual != null && !atual.getConteudo().equals(conteudo)){ //  Enquanto o meu atual não for igual ao meu conteúdo e diferente de nulo, o ‘loop’ continua...
                pai = atual;
                /*
                * if: Caminhando dentro da árvore em busca do "conteúdo", para verificar se o "conteúdo" que passei é menor que o
                * conteúdo desse nó, para saber para qual lado deve ir, para o nó esquerdo ou nó direito.
                *
                * A partir do momento que encontrar o conteúdo, ele vai quebrar o loop e sabe que o atual é exatamente
                * o nó que eu tenho aquele conteúdo...
                * */
                if (conteudo.compareTo(atual.getConteudo()) < 0){
                   atual = atual.getNoEsquerdo(); // Se o conteúdo for menor eu vou para o nó da esquerda...
                }else{
                    atual = atual.getNoDireito(); // Se o conteúdo for maior eu vou para o nó da direita...
                }
            }
            // Após percorrer toda a árvore e não encontrar o elemento (chegou numa folha ou a raiz é nula)...
            if (atual == null){
                System.out.println("Conteúdo não encontrado. Bloco Try");
            }

             if (pai == null){ // 1.ª possibilidade:
                 if (atual.getNoDireito() == null){ // Primeira condição:
                     this.raiz = atual.getNoEsquerdo(); // Se eu tenho o nó da direita como nulo, pega o da esquerda inevitavelmente...
                 }else if (atual.getNoEsquerdo() == null){
                     this.raiz = atual.getNoDireito(); // Se eu tenho o nó da esquerda como nulo, pega o da direita inevitavelmente...
                 }else{ // caso não ocorra nenhuma das condições acima...
                     for(temp = atual, filho = atual.getNoEsquerdo();
                        filho.getNoDireito() != null; // condição de parada do for...
                        temp = filho, filho = filho.getNoEsquerdo() // para cada ciclo de execução incrementa...
                     ){
                         /*
                         * tratando do caso, dos nós descendentes do nó que está a ser retirado...
                         * */
                         if (filho != atual.getNoEsquerdo()){ // o filho diferente da referência de nó à esquerda do nó atual...
                             temp.setNoDireito(filho.getNoEsquerdo());
                             filho.setNoEsquerdo(raiz.getNoEsquerdo());
                         }
                     }
                     filho.setNoDireito(raiz.getNoDireito());
                     raiz = filho;
                 }
             /*
             * Percorrer por dentro da árvore para o caso da referência do nó atual a direita ser nulo.
             * */
             }else if (atual.getNoDireito() == null){ // 2.ª possibilidade: testa se a referencia de nó a direita, do nó atual é nulo.
                 if (pai.getNoEsquerdo() == atual){ // se a referencia de nó pai a esquerda é igual ao nó atual;
                     pai.setNoEsquerdo(atual.getNoEsquerdo()); // a referencia de nó à esquerda do nó pai, vai ser a mesma referencia de nó à esquerda do nó atual...
                 }else{ // se as possibilidades acima não for verdade;
                     pai.setNoDireito(atual.getNoEsquerdo());
                 }
             /*
              * Percorrer por dentro da árvore para o caso da referência do nó atual a esquerda ser nulo.
              * */
             }else if (atual.getNoEsquerdo() == null){ // 3.ª possibilidade: testa se a referencia de nó a esquerda, do nó atual é nulo.
                 if (pai.getNoEsquerdo() == atual){ // se a referencia de nó pai a esquerda é igual ao nó atual;
                     pai.setNoEsquerdo(atual.getNoDireito()); // a referencia de nó à direita do nó pai, vai ser a mesma referencia de nó à esquerda do nó atual...
                 }else{ // se as possibilidades acima não for verdade;
                     pai.setNoDireito(atual.getNoDireito());
                 }
             }else{ // 4.ª possibilidade:
                 for(
                     temp = atual, filho = atual.getNoEsquerdo();
                     filho.getConteudo() != null;
                     temp = filho, filho = filho.getNoDireito()
                 ){
                     if (filho != atual.getNoEsquerdo()){
                         temp.setNoDireito(filho.getNoEsquerdo());
                         filho.setNoEsquerdo(atual.getNoEsquerdo());
                     }
                     filho.setNoDireito(atual.getNoDireito());
                     if (pai.getNoEsquerdo() == atual){
                         pai.setNoEsquerdo(filho);
                     }else{
                         pai.setNoDireito(filho);
                     }
                 }
             }

        }catch (NullPointerException erro){ // Quando um conteúdo que estiver a buscar não for encontrado, o catch vê onde ele cai...
            System.out.println("Conteúdo não encontrado. Bloco Catch");
        }
    }
}
