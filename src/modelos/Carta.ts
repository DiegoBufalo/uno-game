export class Carta {
  cor: string;
  valor: number;
  acao?: () => void;

  constructor(cor: string, valor: number, acao?: () => void) {
    this.cor = cor;
    this.valor = valor;
    this.acao = acao;
  }
}
