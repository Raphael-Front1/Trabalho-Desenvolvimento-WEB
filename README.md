# Trabalho Desenvolvimento WEB - Biblioteca Pessoal

Sistema de cadastro e exclusão de livros desenvolvido como atividade avaliativa.

## Tecnologias

- **Spring Framework / Spring Boot** 3.3.5
- **JPA / Hibernate** (persistência)
- **Spring Security** (autenticação)
- **AJAX** (exclusão e operações sem reload)
- **JasperReports** (geração de PDF)
- **Thymeleaf** (templates)
- **PostgreSQL** (Supabase)
- **Java 17**

## Como rodar localmente

1. Configure as variáveis de ambiente:
   - `DATABASE_URL` (ex: `jdbc:postgresql://aws-0-sa-east-1.pooler.supabase.com:6543/postgres`)
   - `DATABASE_USER`
   - `DATABASE_PASSWORD`

2. Execute:
   ```bash
   mvn spring-boot:run
   ```

3. Acesse: http://localhost:8080/livros

## Deploy

Hospedado no [Render.com](https://render.com), banco no [Supabase](https://supabase.com).
