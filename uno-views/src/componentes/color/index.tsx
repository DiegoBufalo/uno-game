import { Grid, Paper } from "@mui/material";
import React, { Dispatch, SetStateAction } from "react";
import { api } from "utils/api";

interface ChooseColorInterface {
  close: Dispatch<SetStateAction<void>>;
}

export const ChooseColor:React.FC<ChooseColorInterface> = ({ close }) => {
  const defineCor = (cor: string) => {
    api
      .put(`/definir-cor/${cor}`)
      .catch((err) => {
        alert(err.message);
      }).finally(() => close());
  };

  return (
    <Grid sx={{ flexGrow: 1 }} container spacing={2}>
      <Grid item xs={12}>
        <Grid container justifyContent="center" spacing={2}>
          <Grid key={"red"} item>
            <Paper
              onClick={() => defineCor('red')}
              sx={{
                height: 140,
                width: 100,
                backgroundColor: "#E43d3d",
                cursor: "pointer"
              }}
            />
          </Grid>
          <Grid key={"blue"} item>
            <Paper
              onClick={() => defineCor('blue')}
              sx={{
                height: 140,
                width: 100,
                backgroundColor: "#1ab7e6",
                cursor: "pointer"
              }}
            />
          </Grid>
          <Grid key={"green"} item>
            <Paper
              onClick={() => defineCor('green')}
              sx={{
                height: 140,
                width: 100,
                backgroundColor: "#34c922",
                cursor: "pointer"
              }}
            />
          </Grid>
          <Grid key={"yellow"} item>
            <Paper
              onClick={() => defineCor('yellow')}
              sx={{
                height: 140,
                width: 100,
                backgroundColor: "#F7ea0e",
                cursor: "pointer"
              }}
            />
          </Grid>
        </Grid>
      </Grid>
    </Grid>
  );
}

