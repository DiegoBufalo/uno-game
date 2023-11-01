import { GameInfoState } from "interfaces";
import { getRandomInt } from "utils/utils";

export class Carta {
  id: string;
  cor: string;
  valor: string;
  imagem: string;
  acao?: (state: GameInfoState) => void;

  constructor(cor: string, valor: string, acao?: (state: GameInfoState) => void) {
    this.id = `${cor}.${valor}.${getRandomInt()}`
    this.cor = cor;
    this.valor = valor;
    this.imagem = this.cardImage(cor, valor);
    this.acao = acao;
  }

  private cardImage = (cor: string, valor: string) => `src/assets/${cor}/${valor}.png`;
}
