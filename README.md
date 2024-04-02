# GRU - Gerenciador do RU

O GRU (Gerenciador do RU) é um sistema que visa auxiliar a administração do Restaurante Universitário, com ferramentas para otimizar a acessibilidade e coleta de dados de uso do restaurante e de seus usuários.

## Configuração de Banco de dados:
Inicialmente crie um banco chamado gru.
Rode a aplicação do projeto para fazer as criações das tabelas.

### Scripts SQL

#### Criação de usuários:
```sql
INSERT INTO usuario (id, nome, email, senha, tipo, telefone, data_nascimento, login, ativo)
VALUES
-- Usuário Consumidor
(1, 'Usuário', 'usuario@gmail.com', 'usuario', 'CONSUMIDOR', 123456789, '1990-01-01', 'login', true),
-- Usuário Nutricionista
(2, 'Nutricionista', 'nutricionista@gmail.com', 'nutricionista', 'NUTRICIONISTA', 987654321, '1985-05-05', 'nutri', true);


```
#### Criação de cardápios:
```sql
INSERT INTO cardapio (id, acompanhamento, data, prato_principal, tipo_cardapio, tipo_refeicao) 
VALUES 
(1, 'Salada', '2024-03-31', 'Filé de Frango Grelhado', 'PRINCIPAL', 'CAFE'),
(2, 'Arroz', '2024-03-31', 'Bife à Parmegiana', 'PRINCIPAL', 'ALMOCO'),
(3, 'Feijão', '2024-03-31', 'Lasanha de Berinjela', 'VEGETARIANO', 'JANTAR');

```
#### Criação de avisos:
```sql
INSERT INTO avisos (titulo, descricao, data, usuario_id)
VALUES 
    ('Restaurante universitário sem opções vegetarianas', 'Por motivo de falta de ingredientes, as opções vegetarianas no restaurante universitário estarão limitadas até a reposição do estoque.', '2024-04-03', 1),
    ('Falta de suco', 'Pedimos desculpas pela falta de suco. O problema já está sendo resolvido e esperamos restabelecer o estoque em breve.', '2024-04-04', 1);


```
