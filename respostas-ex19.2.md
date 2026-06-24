# Command X Strategy
---
Os padrões de projeto Command e Strategy são muito parecidos por ambos ser utilizados para parametrizar métodos de objetos, mas as aplicações deles são em contextos diferentes:

- Command deve ser utilizado para converter qualquer operação em um objeto. Os parâmetros da operação se tornam atributos do objeto. O padrão permite armazenar os comandos e enviá-los a servidores remotos sem alterações. O invocador não precisa saber o que o comando faz, apenas que tal pode ser executado.
- Strategy tem uma finalidade parecida, mas seu foco está em apenas permitir a troca de algoritmos em um determinado método no contexto de uma única classe. Encapsulando a maneira como uma tarefa é realizada, é necessário saber qual a atividade do método para delegar à classe Strategy como será feito.

# Command X State
---
Esses padrões possuem propósitos estruturais distintos, embora ambos utilizem encapsulamento de comportamentos em objetos para limpar o código. O padrão Command está focado em encapsular a ação a ser realizada enquanto State encapsula condição:

- Command "empacota" uma ação, um pedido, permitindo seu registro em log ou até mesmo que a ação seja desfeita.
- State transforma estado em objeto, pois seu foco é fazer com que um objeto mude a forma como responde aos mesmos métodos a depender de como se encontra seu estado interno