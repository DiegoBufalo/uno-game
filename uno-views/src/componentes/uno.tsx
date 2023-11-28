import { Children, useState, useEffect, useCallback } from "react";
import "./uno.css";
import { GameInfoState, Jogador, Carta } from "interfaces";
import { api } from "utils/api";
import { Modal, Box } from "@mui/material";
import { ChooseColor } from "./color";
import { InitGame } from "./init";

const styleModal = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 220,
  bgcolor: "#270a50",
  border: "1px solid #270a50",
  boxShadow: 24,
  p: 4,
};

function UnoGame() {
  const handleOpen = () => setOpen(true);
  const handleClose = () => { setOpen(false); setModalType('ChooseColor')};

  const [modalType, setModalType] = useState<'ChooseName' | 'ChooseColor'>('ChooseName');
  const [open, setOpen] = useState(true);
  const [state, setState] = useState<GameInfoState>({
    jogadores: [],
    monte: [],
    descarte: [],
    direcao: "DIREITA",
    bloqueado: false,
    comprasObrigatorias: 0,
    idJogadorAtual: 0,
    jogadorAtualIsBot: false,
    jogoFinalizado: false,
    jogadorVencedor : ''
  });

  const modoDev = window.location.pathname.includes("modoDev");
  const getTopoDescarte: Carta | undefined =
    state.descarte.length > 0 ? state.descarte[0] : undefined;
  const jogadorAtual: Jogador | undefined =
    state.jogadores[
      state.jogadores.findIndex((j) => j.id === state.idJogadorAtual)
    ];


  const sentidoPartida = useCallback(() => {
    return `Sentido atual: ${state.direcao === 'ESQUERDA' ? '↺' :'↻' }`
  },[state.direcao])

  const corAtual = useCallback(() => {
    switch (state.corAtual) {
      case 'red':
        return "Vermelho";
      case 'yellow':
      return "Amarelo";
      case 'blue':
      return "Azul";
      case 'green':
      return "Verde";
      default:
        return 'Desconhecido';
    }
  },[state.corAtual])


    const reiniciaPartida = () => {
      api
        .put("/reinicia-partida")
        .then((resp) => {
          setState(resp.data);
        })
        .catch((err) => {
          alert(err.message);
        });
    };

  const verificaVencedor = (jogoFinalizado: boolean, nomeVencedor: string) => {
    if (jogoFinalizado) {
      alert('Jogador vencedor é: ' + nomeVencedor)
      reiniciaPartida();
      location.reload();
    }
  };

  const comprarCarta = () => {
    api
      .put("/compra-carta")
      .then((resp) => {
        setState(resp.data);
      })
      .catch((err) => {
        alert(err.message);
      });
  };

  const descartarCarta = (cartaId: string, cartaCor: string) => {
    if (cartaCor === 'wild') {
      handleOpen();
    }

    api
      .put(`/descarta-carta/${cartaId}`)
      .then((resp) => {
        setState(resp.data);
        verificaVencedor(resp.data.jogoFinalizado, resp.data.jogadorVencedor);
      })
      .catch((err) => {
        alert(err.message);
      });
  };


  const jogadaBot = () => {
    api
      .put("/jogada-bot")
      .then((resp) => {
        setState(resp.data);
        verificaVencedor(resp.data.jogoFinalizado, resp.data.jogadorVencedor);
      })
      .catch((err) => {
        alert(err.message);
      });
  };

  useEffect(() => {
    if (state.jogadorAtualIsBot && state.idJogadorAtual != 0 && !open) {
      setTimeout(() => jogadaBot(), 3000);
    }

  }, [state.jogadorAtualIsBot, state.idJogadorAtual, open]);

  return (
    <>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box className="box" sx={styleModal}>
          {modalType === 'ChooseName' ? (<InitGame setState={setState} close={handleClose} />):null}
          {modalType === 'ChooseColor' ? (<ChooseColor setState={setState} close={handleClose} />):null}
        </Box>
      </Modal>
      <div className="container">
      <div className="game-info">
        <div className="actual-color">
        {`Cor Atual: `}
          <span className={state.corAtual}>
            {corAtual()}
          </span>
        </div>
        <div className="actual-player">{`Jogador Atual: ${jogadorAtual ? jogadorAtual.nome : 'Desconhecido'}`}</div>
        <div className="actual-direction">
          {sentidoPartida()}
        </div>
      </div>
        <div className="centro">
          <div onClick={() => comprarCarta()} className="monte">
            <img src="src/assets/generic/deck.png" alt="deck" />
          </div>
          <div className="descarte">
            <img src={getTopoDescarte?.imagem} alt="deck" />
          </div>
        </div>
        {state && state.jogadores.length > 0
          ? Children.toArray(
              state.jogadores.map((j) => {
                return (
                  <div key={j.id} className={`area area${j.id}`}>
                    <div
                      className={`player-name ${
                        jogadorAtual.id === j.id ? "turno" : ""
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
                                onClick={() => {
                                  if (!state.jogadorAtualIsBot) {
                                    descartarCarta(c.id, c.cor)
                                  }
                                }}
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
    </>
  );
}

export default UnoGame;
