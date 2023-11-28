# Projeto UNO: Estrutura de dados Uno

Grupo: 8

Projeto escolhido: Uno

Motivo:

- nos foi dado um desafio: implementar um projeto utilizando estruturas de dados em memória principal.
  nos foi dadas algumas opções de projeto escolhemos criar um jogo de UNO, onde um jogador
  poderá entrar em um uma partida com outros 3 bots que jogam automaticamente.
  O jogo neste projeto tem como objetivo a visualização das estruturas de dados utilizadas.

Estruturas de dados utilizadas no projeto:

- Lista circular duplamente encadeada: essa lista teve o objetivo de armazenar a ordem dos jogadores e tornar possível,
  seguir para o proximo ou para o jogador anterior, dependendo da carta lançada.
- Pilhas: as pilhas vieram com o objetivo de armazenar as cartas disponíveis para compra e armazenar as cartas descartadas durante a partida.
- Lista singularmente encadeada: essa lista serve para armazenar a "mão" de cartas de cada jogador. foi utilizada uma lista encadeada pois as
  buscas necessárias no projeto não possuem muita complexidade.

Modelos:

- Carta
- Jogador
- Partida

Utilitários:

- Gerador de nomes
- Configurador

## Tecnologias

- Java para as estruturas de dados e as regras do jogo
- TypeScript e react para a visualização grafica do jogo
- Vite para realizar o build da aplicação

### Iniciar

- uno-views/npm install
- uno-views/npm run dev
- uno-server/ spring-boot:run

### requisitos

- Node 16.x ou maiores
- Java 11.0.x ou maiores
