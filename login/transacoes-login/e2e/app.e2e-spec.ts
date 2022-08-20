import { TransacoesLoginPage } from './app.po';

describe('transacoes-login App', function() {
  let page: TransacoesLoginPage;

  beforeEach(() => {
    page = new TransacoesLoginPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
