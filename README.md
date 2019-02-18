# Avaliação Técnica para Estágio

A aplicação foi desenvolvida para resolução da atividade solicitada para dar continuidade ao processo seletivo, e deveria ler de um arquivo de texto os dados de carros que seriam alugados por uma concessionária e posteriormente obter do usuário a descrição dos serviços solicitados, a fim de encontrar de acordo com os dados lidos do arquivo um automóvel para locação pelo menor preço possível.

Como um _"plus"_, foi implementado um _"gerador"_ para o arquivo .txt, que pode ser acessado ao iniciar a aplicação, e permitindo recriar a base de dados inicial. _(Um backup do arquivo "dataloader.txt" original pode ser encontrado na pasta "data" no diretório raiz)_.
A versão do Java utilizada é a 1.8, em acordo com as normas especificadas na documentação do desafio.

# Qual a lógica?

+ O preço total do aluguel é calculado e comparado logicamente para encontrar a opção mais viável para o aluguel do veículo.

+ Foi desenvolvida uma forma de verificar a disponibilidade de um veículo para os dias em que se pretende alugá-lo, não permitindo que um carro seja alugado duas vezes no mesmo dia, o que se acontecesse causaria transtornos. Ao alugar um novo carro, o sistema procura dentre os carros se ele já está alugado, se sim, ele procura por um novo carro, até que se retorne um carro disponível, e caso o contrário exibe a mensagem _"Não possuem carros disponíveis na data indicada."_

# Como usar?

A aplicação deve ser executada através do terminal, caminhando até o diretório onde se encontra o executável _(../rent_car/RentACar.jar)_ e executando o comando _java -jar RentACar.jar_

Quando o programa for executado, o usuário receberá uma mensagem, perguntando se ele deseja executar o sistema, digitando 1 _(carregando o arquivo de dados já existente)_, ou se ele deseja cadastrar novos carros, digitando 2 _(essa ação sobrescreve o arquivo anterior, que possui um backup na pasta data)_. O usuário tem também a opção de encerrar a aplicação desde já, pressionando 0.

Ao selecionar o sistema, o usuário pode usar a opção _"a"_ para alugar um carro, ou _"s"_ para encerrar o programa.

Para alugar um carro, o usuário precisa digitar o tipo do cliente _(Normal ou Premium, caso possua ou não cartão fidelidade)_, seguido da quantidade de passageiros _(Um inteiro)_ e as datas nas quais o carro deverá ser locado. Tais informações devem ser separadas por dois pontos e um espaço _": "_ e as datas, separadas por uma vírgula e um espaço _", "_. _(A sintaxe necessária está disponível durante a execução do programa)_

*Exemplo de input:*

Premium: 6: 01set2009 (sab), 02set2009 (dom)

*Exemplo de output:*

NAVIGATOR: NorthCar

# Dúvidas?

As dúvidas sobre o funcionamento do programa podem ser sanadas através do meu [e-mail](kellysonsilva99@gmail.com).
