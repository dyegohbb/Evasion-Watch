# Descrição da situação atual do EvasionWatch:
---
##### Recursos:
* ###### Api de cadastro e login publicadas utilizando Spring Security
* ###### Criptografia de senhas utilizando BCryptPasswordEncoder com 256 bits
* ###### Autenticação utilizando JwtToken com apenas login e cadastro pública
* ###### Api de população de dados publicada:
 * ###### Recebe um csv (.txt ou .csv)
 * ###### Valida se há conteúdo e também a extensão do arquivo
 * ###### Cria um histórico de importação e relaciona com todos os dados de alunos importados, para sabermos em qual data e qual arquivo se originou aquele dado salvo no banco
 * ###### Importa os dados dos usuários presentes no csv, buscando as colunas padronizadas em código

 # Descrição dos próximos passos:
 ---
* ###### Implementação do apache Kafka para comunicação entre serviços
* ###### Implementação do microsserviço em python, responsável pela previsão
  * ###### Leitura da mensagem responsável pelo inicio da previsão/treino
  * ###### Processamento de acordo com oque é necessário para a fila
  * ###### Disponibilização dos dados da predição (salvamento de dados no banco)
* ###### Implementação do front-end completo
* ###### Implementação dos agendamentos usando @Cron
* ###### Implementação de predição personalizada
* ###### Implementação do microsserviço de e-mail
