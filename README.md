# Authenticator

Api para gerar o token de acesso a nível de cliente para consumir recursos dos endpoints das aplicações no frontent.

Essa API tem fins de estudo e de apresentar partes do meu conhecimento como desenvolvedor.

### Ref. name: *bvss-au1-api-authenticator*.

- bvss: Abrevição da marca.
- au1: Sigla aleatória que será usada como referência para um grupo de aplicações que tem a mesma finalidade de uso ou grupo.
- api: Indica que essa aplicação é uma API.
- authenticator: Informa que essa aplicação da sigla (au1) é relacionada ao serviço de negócio de autenticação de usuários.

### Config Server

Essa API consome dados do serviço de config server para ler as configuraçoes/propriedades a serem carregadas.

Ela não utiliza o application.yml, caso queira usar um arquivo local, veja configuraçoes na doc do spring e para executa-lá será
nessário subir também a aplicação [BVSS Config Server](https://github.com/brunovschettini/bvss-config-server/) localmente.

### Referencias do projeto

URI: https://medium.com/cwi-software/oauth2-com-spring-boot-2-e-spring-5-e7bfb7c58d4a