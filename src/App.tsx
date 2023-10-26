import { Children, useState, useEffect } from "react";
import "./App.css";
import { GameInfoState } from "./interfaces";
import { iniciadorGame } from "./utils/inicializadores";
import { Carta } from "modelos/Carta";
import { Jogador } from "modelos/Jogador";

function App() {
  const [state, setState] = useState<GameInfoState>();
  useEffect(() => {
    setState(iniciadorGame(2, 4));
  }, []);

  const getTopoDescarte = () => state?.descarte.peek();

  const discartaCarta = (jogador: Jogador, carta: Carta) => {
    setState((prev) => {
      return {
        ...prev,
        descarte: prev!.descarte.push(carta),
      } as GameInfoState;
    });
    jogador.mao.remove(carta);
  };

  return (
    <div className="container">
      <div className="centro">
        <div className="monte">
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
                  <div className="deck">
                    {/* <div className="nome-jogador">{j.nome}</div> */}
                    <div className={`cartas cartas-${j.id}`}>
                      {j.mao.map((c) => {
                        return (
                          <div
                            className="carta"
                            onClick={() => discartaCarta(j, c)}
                          >
                            <img src={c.imagem} alt={`${c.valor}_${c.cor}`} />
                          </div>
                        );
                      })}
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

export default App;
