/* eslint-disable @typescript-eslint/no-explicit-any */
import { Children, useState, useEffect, useCallback } from "react";
import "./uno.css";
import { GameInfoState } from "interfaces";
import { iniciadorGame } from "utils/inicializadores";
import { Carta } from "modelos/Carta";
import { Jogador } from "modelos/Jogador";

function UnoGame() {
  const [state, setState] = useState<GameInfoState>(new GameInfoState());
  // const [message] = useState<{ name: string; message: string }>({
  //   name: "teste",
  //   message: "teste",
  // });
  const [indexAtual, setIndexAtual] = useState<0 | 1 | 2 | 3>(0);
  const modoDev = window.location.pathname.includes("modoDev");

  useEffect(() => {
    setState(iniciadorGame(state));
  }, []);

  const getTopoDescarte = () => state?.descarte.peek();

  const getJogadorAtual = useCallback(() => {
    return state?.jogadores.get(indexAtual);
  }, [indexAtual, state]);

  // Avança o objeto atual para o próximo
  const moveToNextObject = (): void => {
    if (indexAtual === 0) {
      setIndexAtual(1);
    } else if (indexAtual === 1) {
      setIndexAtual(2);
    } else if (indexAtual === 2) {
      setIndexAtual(3);
    } else {
      setIndexAtual(0);
    }
  };

  // Retrocede o objeto atual para o anterior
  const moveToPreviousObject = (): void => {
    if (indexAtual === 0) {
      setIndexAtual(3);
    } else if (indexAtual === 1) {
      setIndexAtual(0);
    } else if (indexAtual === 2) {
      setIndexAtual(1);
    } else {
      setIndexAtual(2);
    }
  };

  const proximoJogador = () => {
    if (!state) {
      return;
    }

    setState((prevState) => {
      const newState = { ...prevState! };
      if (newState.direcao === "DIREITA") {
        moveToNextObject();
      } else {
        moveToPreviousObject();
      }
      // Adicione código para verificar se a carta pode ser jogada de acordo com as regras do jogo.
      return newState;
    });
  };

  const comprarCarta = () => {
    if (!state || state.bloqueado) {
      return;
    }

    setState((prevState) => {
      const newState = { ...prevState! };
      const jogadorAtual = getJogadorAtual()!;

      if (jogadorAtual && newState.compraObrigatoria > 0) {
        // O jogador atual tem uma compra obrigatória, deve comprar cartas até atingir o número necessário.
        while (newState.compraObrigatoria > 0) {
          const cartaComprada = newState.monte.pop()!;
          jogadorAtual.mao.push(cartaComprada);
          newState.compraObrigatoria--;
        }
      } else {
        const cartaComprada = newState.monte.pop()!;
        jogadorAtual.mao.push(cartaComprada);
      }

      return newState;
    });
  };

  const descartarCarta = (jogador: Jogador, carta: Carta) => {
    if (
      !state ||
      state.bloqueado ||
      !jogador ||
      !carta ||
      jogador !== getJogadorAtual()
    ) {
      return;
    }

    const topo = getTopoDescarte();
    const newState = { ...state };

    if (carta.cor !== topo.cor && carta.valor !== topo.valor) {
      if (carta.cor === "wild" && carta.acao) {
        carta.acao(newState);
      } else {
        return;
      }
    }

    setState(() => {
      jogador.mao.remove(carta);
      newState.descarte.push(carta);
      // Adicione código para verificar se a carta pode ser jogada de acordo com as regras do jogo.
      return newState;
    });
    proximoJogador();
  };

  return (
    <div className="container">
      <div className="centro">
        <div onClick={() => comprarCarta()} className="monte">
          <img src="src/assets/generic/deck.png" alt="deck" />
        </div>
        <div className="descarte">
          <img src={getTopoDescarte()?.imagem} alt="deck" />
        </div>
      </div>
      {state && state.jogadores.getSize() > 0
        ? Children.toArray(
            state.jogadores.map((j) => {
              return (
                <div key={j.id} className={`area area${j.id}`}>
                  <div
                    className={`player-name ${
                      getJogadorAtual()?.id === j.id ? "turno" : ""
                    }`}
                  >
                    {j.nome}
                  </div>
                  <div className="deck">
                    <div className={`cartas cartas-${j.id}`}>
                      {Children.toArray(
                        j.mao.map((c) => {
                          return (
                            <div
                              key={c.id}
                              className="carta"
                              onClick={() => descartarCarta(j, c)}
                            >
                              {j.isBot && !modoDev ? (
                                <img
                                  src="src/assets/generic/deck.png"
                                  alt="deck"
                                />
                              ) : (
                                <img
                                  src={c.imagem}
                                  alt={`${c.valor}_${c.cor}`}
                                />
                              )}
                            </div>
                          );
                        })
                      )}
                    </div>
                  </div>
                </div>
              );
            })
          )
        : null}
    </div>
  );
}

export default UnoGame;
