import { Grid, Paper } from "@mui/material";

function ChooseColor() {
  return (
    <Grid sx={{ flexGrow: 1 }} container spacing={2}>
      <Grid item xs={12}>
        <Grid container justifyContent="center" spacing={2}>
          <Grid key={"red"} item>
            <Paper
              sx={{
                height: 140,
                width: 100,
                backgroundColor: "#E43d3d",
              }}
            />
          </Grid>
          <Grid key={"blue"} item>
            <Paper
              sx={{
                height: 140,
                width: 100,
                backgroundColor: "#1ab7e6",
              }}
            />
          </Grid>
          <Grid key={"green"} item>
            <Paper
              sx={{
                height: 140,
                width: 100,
                backgroundColor: "#34c922",
              }}
            />
          </Grid>
          <Grid key={"yellow"} item>
            <Paper
              className="color-card"
              sx={{
                height: 140,
                width: 100,
                backgroundColor: "#F7ea0e",
              }}
            />
          </Grid>
        </Grid>
      </Grid>
    </Grid>
  );
}

export default ChooseColor;
