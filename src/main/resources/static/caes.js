
function carregarListaRacas() {
  const selectRaca = document.getElementById('selectRaca');
  fetch('http://localhost:8080/racas/')
    .then(response => response.json())
    .then(data => {
      selectRaca.innerHTML = '';
      const defaultOption = document.createElement('option');
      defaultOption.text = 'selecionar raça';
      selectRaca.add(defaultOption);
      data.forEach(raca => {
        const option = document.createElement('option');
        option.value = raca.id;
        option.text = raca.nome;
        selectRaca.add(option);
      });
    })
    .catch(error => {
      console.error('Erro ao obter lista de raças:', error);
    });
}

function atualizarListaCaes() {
  fetch('http://localhost:8080/caninos')
    .then(response => response.json())
    .then(data => {
      const listaCaes = document.querySelector('.listaCaes');
      listaCaes.innerHTML = '';

      data.forEach(cao => {
        const row = `<tr>
                       <td>${cao.nome}</td>
                       <td>${cao.raca.nome}</td>
                       <td>
                         <button class="btn btn-outline-danger" onclick="excluirCao(${cao.id})">Excluir</button>
                       </td>
                     </tr>`;
        listaCaes.innerHTML += row;
      });
    })
    .catch(error => {
      console.error('Erro ao obter lista de cães:', error);
    });
}

atualizarListaCaes();

function adicionarCao() {
  const nomeCao = document.getElementById('cao_name').value;
  const racaCao = document.getElementById('selectRaca').value;

  if (racaCao === 'selecionar raça') {
    alert('Por favor, selecione uma raça válida.');
    return;
  }

  fetch(`http://localhost:8080/racas/${racaCao}/caninos`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      nome: nomeCao,
    }),
  })
    .then(response => response.json())
    .then(data => {
      console.log('Cão adicionado com sucesso:', data);
      atualizarListaCaes();
    })
    .catch(error => {
      console.error('Erro ao adicionar cão:', error);
    });
}

function excluirCao(caoId) {
  fetch(`http://localhost:8080/caninos/${caoId}`, {
    method: 'DELETE',
  })
    .then(response => {
    if (!response.ok) {
      throw new Error('Erro ao excluir cão.');
    }
    atualizarListaCaes();
  })
  .catch(error => console.error('Erro ao excluir cão:', error));
}


document.addEventListener('DOMContentLoaded', carregarListaRacas);
