# Guia: Criação de Projeto no Android Studio para Acesso Web usando `JSON`

Nesse projeto utilizaremos a Activity para permitir que o usuário possa trabalhar com acesso de informações via internet (via web). A intenção é ilustrar os mecanismos utilizados por muitas aplicações Android que fazem uso do acesso web para recuperar ou transmitir informações à um servidor de dados. Isso pode ser realizado de várias maneiras, sendo que dois processos são bastante comuns: o uso de `JSON` e `XML`.

Para exemplificar a representação de arquivos do tipo `JSON` e `XML` serão apresentados abaixo dois exemplos, contendo a definição de dados de empregados de uma empresa, com dados de nome e sobrenome de três empregados:

### Exemplo de `JSON`
```yaml
{
    "employees": [
        {
            "firstName": "John",
            "lastName": "Doe"
        },
        {
            "firstName": "Anna",
            "lastName": "Smith"
        },
        {
            "firstName": "Peter",
            "lastName": "Jones"
        }
  ]
}
```

### Exemplo de `XML`
```xml
<employees>
  <employee>
    <firstName>John</firstName> <lastName>Doe</lastName>
  </employee>
  <employee>
    <firstName>Anna</firstName> <lastName>Smith</lastName>
  </employee>
  <employee>
    <firstName>Peter</firstName> <lastName>Jones</lastName>
  </employee>
</employees>
```


Para a criação do projeto foi usado um endereço web com informações que podem ser recuperadas em `JSON`. A partir do endereço http://jsonplaceholder.typicode.com é possível recuperar uma série de informações como `Posts` e `Comments`.

Foi usado o acesso aos `Posts` do site, onde cada `Post` contém os atributos: `userId`, `id`, `title` e `body`.

A aplicação de exemplo que iremos apresentar vai utilizar duas Activitys: uma para iniciar o acesso ao link http://jsonplaceholder.typicode.com/posts e listar todos os 100 itens de Posts disponibilizados; outra para visualizar os dados de cada Post com detalhe, após escolher qual Post deve ser detalhado.

Para construir de maneira organizada o projeto foram criadas as seguintes classes:

- `Post` - contendo a definição dos quatro atributos referentes ao `Post`, além da sobrescrita do método `toString()`;

- `JsonConsumer` - classe que estende a classe `AsynkTask`, que representa uma tarefa assíncrona que deve ser executada. A classe `JsonConsumer` deve ser uma tarefa assíncrona pois ao solicitar o acesso de informações via web, o sistema Android irá recuperar essas informações sem paralisar a interação da APP com o usuário. Após recuperar as informações (caso seja possível), essas informações podem ser tratadas e exibidas.
