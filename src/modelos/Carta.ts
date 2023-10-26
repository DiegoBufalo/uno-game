export class Carta {
  cor: string;
  valor: string;
  imagem: string;
  acao?: () => void;

  constructor(cor: string, valor: string, acao?: () => void) {
    this.cor = cor;
    this.valor = valor;
    this.imagem = this.cardImage(cor, valor);
    this.acao = acao;
  }

  private cardImage = (cor: string, valor: string) => `src/assets/${cor}/${valor}.png`;
}
