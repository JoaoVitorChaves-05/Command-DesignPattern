# Command - Design Pattern

Este repositório contém uma implementação do Padrão de Projeto "Command".

Visão geral do padrão
- O padrão Command encapsula uma solicitação como um objeto (um comando), permitindo parametrizar e enfileirar operações, além de desacoplar o remetente (invoker) do receptor (receiver).
- Cada comando implementa uma interface comum com um método `execute`.

O que este projeto faz
- `src/Database.java`: banco de dados simples (singleton) que guarda usuários em um HashMap.
- `src/User.java`: modelo de usuário (id, nome, email).
- `src/interfaces/ICommand.java`: interface do comando (método `execute(Object)`).
- `src/commands/`: comandos concretos (CreateUser, DeleteUser, GetUser, GetAllUsers, DeleteAllUsers).
- `src/Invoker.java`: invocador que mapeia nomes de comando para objetos de comando e os executa.
- `src/CommandLineClient.java`: cliente REPL que aceita entrada pela linha de comando e despacha para o `Invoker` (ou chama diretamente os comandos como fallback).

Comandos suportados (exemplos):
- `create <id> <name> <email>` — cria um usuário
- `delete <id>` — deleta um usuário
- `get <id>` — exibe um usuário
- `all` — lista todos os usuários
- `delete_all` — deleta todos os usuários
- `exit` — encerra o cliente

Executando (PowerShell):
```
javac -d out src\**\*.java
java -cp out CommandLineClient
```

Observações
- O arquivo `CommandLineClient` tenta usar `Invoker` se possível; caso o `Invoker` não consiga ser instanciado (por exemplo devido a inconsistências de pacotes), o cliente faz fallback e chama os comandos diretamente.

Importante
- O arquivo .md `respostas-ex19.2` contém as respostas à segunda parte da atividade de sala.
