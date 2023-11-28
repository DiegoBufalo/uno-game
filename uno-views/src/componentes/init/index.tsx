import { Box, TextField } from "@mui/material";
import { GameInfoState } from "interfaces";
import React, { Dispatch, SetStateAction, useState } from "react";
import { api } from "utils/api";
import { BootstrapButton } from "./style";

interface ChooseColorInterface {
  setState: Dispatch<SetStateAction<GameInfoState>>;
  close: Dispatch<SetStateAction<void>>;
}

export const InitGame: React.FC<ChooseColorInterface> = ({
  setState,
  close,
}) => {
  const [playerName, setPlayerName] = useState<string>("Jogador");

  const iniciaPartida = () => {
    api
      .post(`/init?nomeJogador=${playerName}`)
      .then((resp) => {
        setState(resp.data);
      })
      .catch((err) => {
        alert(err.message);
      })
      .finally(() => close());
  };

  return (
    <Box
      component="form"
      sx={{
        "& > :not(style)": { m: 1, width: "90%" },
      }}
      noValidate
      autoComplete="off"
    >
      <TextField
        id="standard-basic"
        label="Escolha seu nome"
        variant="standard"
        onChange={(e) => {
          setPlayerName(e.currentTarget.value);
        }}
      />

      <BootstrapButton onClick={() => iniciaPartida()} variant="contained">
        Iniciar Partida
      </BootstrapButton>
    </Box>
  );
};
