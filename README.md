Resumo do Projeto
Este projeto implementa uma versão básica do jogo de xadrez em Java, simulando um tabuleiro de xadrez e as peças, além de permitir o movimento das peças de acordo com as regras tradicionais do jogo. O projeto é projetado para demonstrar a lógica fundamental do xadrez, manipulação de peças e controle do jogo, servindo como uma base para futuras extensões e melhorias.

Objetivos
Simular um Tabuleiro de Xadrez:

Criar e gerenciar um tabuleiro de xadrez 8x8.
Posicionar peças de xadrez nas posições iniciais.
Implementar Peças de Xadrez:

Representar as peças clássicas: Torre, Cavalo, Bispo, Rainha, Rei e Peão.
Implementar a lógica de movimento e captura para cada tipo de peça.
Gerenciar Movimentos e Regras:

Permitir que as peças se movam de acordo com suas regras específicas.
Implementar regras básicas como captura de peças adversárias, movimentos válidos e verificação de movimentos especiais (como roque e promoção de peões).
Interface de Usuário:

Fornecer uma interface simples para visualizar o tabuleiro e as peças.
Permitir ao usuário realizar movimentos de peças através de comandos ou interações básicas.
Funcionalidades
Representação do Tabuleiro:

Classe Board para gerenciar o estado do tabuleiro.
Matriz 8x8 que armazena peças e suas posições.
Peças de Xadrez:

Classes Piece e suas subclasses (Rook, Knight, Bishop, Queen, King, Pawn).
Implementação das regras de movimento para cada tipo de peça.
Controle de cor das peças (branca e preta).
Movimentação de Peças:

Métodos para verificar movimentos válidos e capturas.
Implementação de movimentos especiais (movimento duplo de peão, en passant, roque).
Configuração Inicial:

Método para posicionar peças nas posições iniciais do jogo.
Criação de um método para configurar o tabuleiro com peças brancas e pretas nas suas posições padrão.
Interface Simples:

Visualização básica do tabuleiro e peças.
Métodos para imprimir o estado atual do tabuleiro e peças.
Arquitetura do Projeto
Classes Principais:

Board: Gerencia o tabuleiro de xadrez e a colocação das peças.
Piece: Classe abstrata que define a interface para todas as peças.
Rook, Knight, Bishop, Queen, King, Pawn: Classes específicas que implementam a lógica de movimento para cada tipo de peça.
Auxiliares:

Position: Representa a posição de uma peça no tabuleiro.
Color: Enumeração para definir a cor das peças.
Como Executar
Compilar e Executar:

Compile as classes Java com javac.
Execute o programa com java ChessGame.
Interações:

O programa inicializa um tabuleiro com peças nas posições padrão.
Permite ao usuário visualizar o estado atual do tabuleiro e fazer movimentos básicos.
