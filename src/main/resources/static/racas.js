
function adicionarRaca() {
  const nomeRaca = document.getElementById("raca_name").value;
  if (nomeRaca.trim() === "") {
    alert("Por favor, insira o nome da raça.");
    return;
  }
  fetch('http://localhost:8080/racas/', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({ nome: nomeRaca }),
  })
  .then(response => response.json())
  .then(data => {
    document.getElementById("raca_name").value = "";
    listarRacas();
  })
  .catch(error => console.error('Erro ao adicionar raça:', error));
}

function listarRacas() {
  fetch('http://localhost:8080/racas/')
    .then(response => response.json())
    .then(data => exibirRacas(data))
    .catch(error => console.error('Erro ao obter raças:', error));
}

function excluirRaca(id) {
  fetch(`http://localhost:8080/racas/${id}`, {
    method: 'DELETE',
  })
  .then(response => {
    if (!response.ok) {
      throw new Error('Erro ao excluir raça.');
    }
    listarRacas();
  })
  .catch(error => console.error('Erro ao excluir raça:', error));
}

function criarBotaoExcluir(id) {
  const botaoExcluir = document.createElement('button');
  botaoExcluir.textContent = 'Excluir';
  botaoExcluir.className = 'btn btn-outline-danger';
  botaoExcluir.addEventListener('click', () => excluirRaca(id));

  return botaoExcluir;
}

function exibirRacas(racas) {
  const listaRacas = document.querySelector('.listaRacas');

  listaRacas.innerHTML = "";

  racas.forEach(raca => {
    const linha = document.createElement('tr');
    const colunaNome = document.createElement('td');
    colunaNome.textContent = raca.nome;

    const colunaAcoes = document.createElement('td');
    colunaAcoes.appendChild(criarBotaoExcluir(raca.id));

    linha.appendChild(colunaNome);
    linha.appendChild(colunaAcoes);
    listaRacas.appendChild(linha);
  });
}


document.addEventListener("DOMContentLoaded", function () {
  listarRacas();
});
