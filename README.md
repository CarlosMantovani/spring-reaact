# Aplicação Spring React

## Descrição
Este projeto integra um backend Spring Boot com um frontend React, criando uma aplicação web full-stack com uma API RESTful e uma interface dinâmica.

## Funcionalidades
- Backend em Spring Boot com APIs REST
- Frontend em React
- Docker para fácil implantação

## Como Clonar o Repositório
1. Abra o terminal e rode o comando:
   ```bash
   git clone https://github.com/CarlosMantovani/spring-reaact.git
   ```
2. Entre no diretório do projeto:
   ```bash
   cd spring-reaact
   ```

## Instruções para Rodar a Aplicação

### Pré-requisitos
- Docker
- Java 11 ou superior
- Node.js e npm

### Executando a Aplicação com Docker
1. Certifique-se de ter o Docker instalado.
2. Execute o comando abaixo para construir e iniciar o backend e o frontend:
   ```bash
   docker-compose up
   ```

### Executando Separadamente no Modo de Desenvolvimento
#### Backend (Spring Boot)
1. Navegue até a pasta do backend:
   ```bash
   cd backend
   ```
2. Execute o comando para rodar o backend:
   ```bash
   ./mvnw spring-boot:run
   ```

#### Frontend (React)
1. Navegue até a pasta do frontend:
   ```bash
   cd frontend
   ```
2. Instale as dependências do React:
   ```bash
   npm install
   ```
3. Execute o comando para iniciar o frontend:
   ```bash
   npm start
   ```
