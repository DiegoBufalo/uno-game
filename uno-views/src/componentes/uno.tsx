import { Children, useState, useEffect } from "react";
import "./uno.css";
import { GameInfoState, Jogador, Carta } from "interfaces";
import { api } from "utils/api";
import { Modal, Box } from "@mui/material";
import ChooseColor from "./color";

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
  const [open, setOpen] = useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);
  const [state, setState] = useState<GameInfoState>({
    jogadores: [],
    monte: [],
    descarte: [],
    direcao: "DIREITA",
    bloqueado: false,
    comprasObrigatorias: 0,
    escolheCor: false,
    idJogadorAtual: 0,
  });

  const modoDev = window.location.pathname.includes("modoDev");
  const getTopoDescarte: Carta | undefined =
    state.descarte.length > 0 ? state.descarte[0] : undefined;
  const jogadorAtual: Jogador | undefined =
    state.jogadores[
      state.jogadores.findIndex((j) => j.id === state.idJogadorAtual)
    ];

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

  const descartarCarta = (cartaId: string) => {
    handleOpen();
    api
      .put(`/descarta-carta/${cartaId}`)
      .then((resp) => {
        setState(resp.data);
      })
      .catch((err) => {
        alert(err.message);
      });
  };

  useEffect(() => {
    api
      .post("/init?nomeJogador=Messi")
      .then((resp) => {
        setState(resp.data);
      })
      .catch((err) => {
        alert(err.message);
      });
  }, []);

  useEffect(() => {
    if (state.escolheCor) {
      handleOpen();
    }
  }, [state.escolheCor]);

  return (
    <>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box className="box" sx={styleModal}>
          <ChooseColor />
        </Box>
      </Modal>
      <div className="container">
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
                                onClick={() => descartarCarta(c.id)}
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
