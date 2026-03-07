# 🩺 Voll.med API

* Projeto desenvolvido para estudo e portfólio utilizando **Java + Spring Boot + Cloud Deploy**.


![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/SpringBoot-API-brightgreen)
![Docker](https://img.shields.io/badge/Docker-Container-blue)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?logo=mysql&logoColor=white)
![Render](https://img.shields.io/badge/Deploy-Render-purple)
![Tests](https://img.shields.io/badge/tests-automated-success)
![Swagger](https://img.shields.io/badge/API-documented-brightgreen)
![Status](https://img.shields.io/badge/Status-Active-success)

API REST desenvolvida em **Java + Spring Boot** para gerenciamento de uma clínica médica.

O sistema permite gerenciar **médicos, pacientes e consultas**, com autenticação segura baseada em **JWT (JSON Web Token)**.

Este projeto foi construído com foco em:

- boas práticas de desenvolvimento backend
- arquitetura limpa
- segurança com autenticação JWT
- deploy em ambiente cloud

---

# 📌 Overview

A **Voll.med API** fornece serviços para gerenciamento de uma clínica médica, permitindo:

- autenticação de usuários
- cadastro e gerenciamento de pacientes
- cadastro e gerenciamento de médicos
- agendamento de consultas
- cancelamento de consultas
- consulta de histórico de consultas por paciente

A API segue o padrão **RESTful** e utiliza **JWT (JSON Web Token)** para autenticação e segurança das requisições.

---

# 🏗️ Arquitetura do Projeto

A aplicação segue uma arquitetura baseada em camadas.

### Controller
Responsável por expor os **endpoints REST** da aplicação.

### Service / Domain
Camada responsável pelas **regras de negócio**.

### Repository
Responsável pela comunicação com o banco de dados utilizando **Spring Data JPA**.

### DTOs
Objetos utilizados para **transferência de dados** entre cliente e API.

### Security
Camada responsável pela **autenticação e autorização utilizando JWT**.

---

# 🧰 Tecnologias Utilizadas

## Backend

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- Hibernate
- JWT Authentication
- Maven

---

## Banco de Dados

- MySQL
- Hospedado na plataforma **Aiven**

---

## Infraestrutura

- Docker
- GitHub
- Render (Cloud Deploy)
- Aiven (Database MySQL)

---

# ☁️ Infraestrutura e Deploy

## Banco de Dados

O banco **MySQL** está hospedado na plataforma **Aiven**.

Benefícios:

- alta disponibilidade
- backups automáticos
- segurança
- escalabilidade

---

## Deploy da API

A API está hospedada no:

**Render**

O deploy utiliza:

- **Dockerfile**

### Fluxo de deploy

1. Desenvolvedor realiza commit no GitHub
2. Render detecta alteração no repositório
3. Render executa build da imagem Docker
4. Nova versão da aplicação é publicada automaticamente

Esse processo funciona como um **CI/CD automático**.

---

# 🔐 Autenticação

A API utiliza autenticação baseada em **JWT**.

### Fluxo de autenticação

1. Usuário realiza login
2. API retorna um **token JWT**
3. O token deve ser enviado no header das requisições

Header de autenticação:

Authorization: Bearer TOKEN

---

# 📡 Endpoints

## 🔑 Autenticação

| Método | Endpoint | Descrição |
|------|------|------|
POST | /login | Realiza autenticação e retorna token JWT |

---

## 👤 Pacientes

| Método | Endpoint | Descrição |
|------|------|------|
POST | /pacientes | Cadastro de paciente |
PUT | /pacientes | Atualizar paciente |
DELETE | /pacientes/{id} | Excluir paciente |
GET | /pacientes | Listagem de pacientes |
GET | /pacientes/{id} | Detalhe de paciente |

---

## 👨‍⚕️ Médicos

| Método | Endpoint | Descrição |
|------|------|------|
POST | /medicos | Cadastro de médico |
PUT | /medicos | Atualizar médico |
DELETE | /medicos/{id} | Excluir médico |
GET | /medicos | Listagem de médicos |
GET | /medicos/{id} | Detalhe de médico |

---

## 📅 Consultas

| Método | Endpoint | Descrição |
|------|------|------|
POST | /consultas | Agendar consulta |
DELETE | /consultas | Cancelar consulta |
GET | /consultas/paciente/{id} | Listar consultas por paciente |

---

# 🧪 Testes da API

Os endpoints podem ser testados utilizando ferramentas como:

- Insomnia
- Postman

As coleções estão organizadas em:

- Autenticação
- Paciente
- Médico
- Consultas

---

# 🧪 Testes Automatizados

O projeto possui **testes automatizados** garantindo qualidade e confiabilidade da aplicação.

Os testes incluem:

- testes de controllers
- testes de regras de negócio
- validação de endpoints
- cenários de erro e sucesso

Para executar os testes:

mvn test

---

# 📚 Documentação da API

A API possui documentação automática utilizando **Swagger / OpenAPI**.

A documentação permite:

- visualizar todos os endpoints
- testar requisições diretamente no navegador
- verificar schemas de request e response
- entender rapidamente o funcionamento da API

Após iniciar a aplicação, a documentação pode ser acessada em:

http://{endpoint}/swagger-ui.html

ou

http://{endpoint}/swagger-ui/index.html

---

# 🚀 Executando o Projeto Localmente

### 1 - Clonar repositório

git clone https://github.com/TiagoeSouza/api-voll.med

### 2 - Acessar pasta do projeto

cd api-voll.med

### 3 - Executar aplicação

./mvnw spring-boot:run

ou

mvn spring-boot:run

---

# 🐳 Executando com Docker

### Build da imagem

docker build -t vollmed-api .

### Executar container

docker run -p 3000:3000 vollmed-api

---

# 📂 Estrutura do Projeto

src/main/java

controller   → endpoints REST  
domain       → entidades de negócio  
repository   → interfaces JPA  
service      → regras de negócio  
security     → autenticação JWT  
dto          → objetos de transferência de dados  

---

# 👨‍💻 Autor

**Tiago Esteves de Souza**

GitHub  
https://github.com/TiagoeSouza

---
