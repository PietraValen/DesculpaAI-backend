#!/bin/bash

# 🚀 QUICK START - DesculpaAI Backend

echo "╔════════════════════════════════════════════╗"
echo "║     🎭 DesculpaAI Backend - Quick Start    ║"
echo "╚════════════════════════════════════════════╝"
echo ""

# Cores para output
GREEN='\033[0;32m'
BLUE='\033[0;34m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Verificar se Maven está instalado
echo -e "${BLUE}✓ Verificando Maven...${NC}"
if ! command -v mvn &> /dev/null; then
    echo "Maven não encontrado. Instalando..."
    sudo apt-get update && sudo apt-get install -y maven
else
    echo -e "${GREEN}✓ Maven encontrado${NC}"
fi

# Verificar se PostgreSQL está rodando
echo ""
echo -e "${BLUE}✓ Verificando PostgreSQL...${NC}"
if psql -U postgres -d desculpaai -c "SELECT 1;" 2>/dev/null; then
    echo -e "${GREEN}✓ PostgreSQL conectado${NC}"
else
    echo -e "${YELLOW}⚠ PostgreSQL não encontrado ou banco 'desculpaai' não existe${NC}"
    echo "  Para criar o banco, execute:"
    echo "  $ psql -U postgres -c 'CREATE DATABASE desculpaai;'"
fi

# Compilar o projeto
echo ""
echo -e "${BLUE}✓ Compilando projeto...${NC}"
mvn clean compile -DskipTests

# Verificar resultado
if [ $? -eq 0 ]; then
    echo -e "${GREEN}✓ Compilação bem-sucedida!${NC}"
else
    echo -e "${RED}✗ Erro na compilação${NC}"
    exit 1
fi

# Iniciar a aplicação
echo ""
echo -e "${BLUE}✓ Iniciando DesculpaAI Backend...${NC}"
echo ""
echo -e "${YELLOW}═══════════════════════════════════════════════════════════${NC}"
echo -e "${GREEN}🎉 Backend iniciando em http://localhost:8080${NC}"
echo ""
echo -e "${YELLOW}URLS IMPORTANTES:${NC}"
echo "  📚 Swagger UI:       http://localhost:8080/swagger-ui.html"
echo "  📊 API Docs JSON:    http://localhost:8080/v3/api-docs"
echo "  🔗 API Base URL:     http://localhost:8080/api"
echo ""
echo -e "${YELLOW}ARQUIVO DE CONFIGURAÇÃO:${NC}"
echo "  📝 application.properties"
echo ""
echo -e "${YELLOW}PARA PARAR O SERVIDOR:${NC}"
echo "  Press Ctrl+C"
echo ""
echo -e "${YELLOW}═══════════════════════════════════════════════════════════${NC}"
echo ""

# Iniciar a aplicação
mvn spring-boot:run
