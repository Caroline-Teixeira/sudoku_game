# 📊 Matemática por Trás do Sudoku Game em Java

Este documento explica, de forma didática e com exemplos visuais, a matemática utilizada para verificar conflitos e determinar a posição de células em blocos no **Sudoku Game em Java**.

---

## 🎯 **O que vamos ver:**
- Como o computador identifica se dois números estão no mesmo bloco 3x3
- Como converter posições do tabuleiro em coordenadas
- Como o programa percorre todas as 81 células do Sudoku
- A matemática simples por trás de tudo isso

---

## 📌 **1. Verificação de Conflito em Bloco 3x3**

No Sudoku, além de respeitar as regras de linha e coluna, os números também não podem se repetir dentro dos blocos 3x3.

### 🔍 **Como o computador identifica blocos:**

```java
(currentCell.getRow() / 3 == otherCell.getRow() / 3 &&
 currentCell.getCol() / 3 == otherCell.getCol() / 3)
```

### 💡 **Explicação simples:**

Quando dividimos a posição por 3 e **desprezamos a parte decimal** (arredondamos para baixo), transformamos as coordenadas do tabuleiro em coordenadas de bloco.

**Exemplo prático:**
- Posição 0, 1, 2 → dividido por 3 = 0 (bloco 0)
- Posição 3, 4, 5 → dividido por 3 = 1 (bloco 1)  
- Posição 6, 7, 8 → dividido por 3 = 2 (bloco 2)

---

## 📌 **2. Tabela de Conversão de Posições**

### 📖 **Como as posições viram números de bloco:**

| Posição no Tabuleiro | Dividido por 3 | Número do Bloco |
|:--------------------|:---------------|:----------------|
| 0                   | 0 ÷ 3 = 0      | 0              |
| 1                   | 1 ÷ 3 = 0      | 0              |
| 2                   | 2 ÷ 3 = 0      | 0              |
| 3                   | 3 ÷ 3 = 1      | 1              |
| 4                   | 4 ÷ 3 = 1      | 1              |
| 5                   | 5 ÷ 3 = 1      | 1              |
| 6                   | 6 ÷ 3 = 2      | 2              |
| 7                   | 7 ÷ 3 = 2      | 2              |
| 8                   | 8 ÷ 3 = 2      | 2              |

---

## 📌 **3. Mapa Visual dos Blocos 3x3**

### 🗺️ **Como o Sudoku é dividido em 9 blocos:**

```

  0 1 2 |  3 4 5 |  6 7 8     ← Colunas
0 [0,0] | [0,1]  | [0,2]      ← Linha 0
1 [0,0] | [0,1]  | [0,2]      ← Linha 1  
2 [0,0] | [0,1]  | [0,2]      ← Linha 2
  ------+--------+------
3 [1,0] | [1,1]  | [1,2]      ← Linha 3
4 [1,0] | [1,1]  | [1,2]      ← Linha 4
5 [1,0] | [1,1]  | [1,2]      ← Linha 5
  ------+--------+------
6 [2,0] | [2,1]  | [2,2]      ← Linha 6
7 [2,0] | [2,1]  | [2,2]      ← Linha 7
8 [2,0] | [2,1]  | [2,2]      ← Linha 8


Os números entre os colchetes [] represetam os blocos:
Bloco [0,0], [0,1], [0,2]...
```

---

## 📌 **4. Exemplo Prático de Verificação**

### ✅ **Teste: Duas células estão no mesmo bloco?**

**Célula A:** Linha 1, Coluna 2  
**Célula B:** Linha 0, Coluna 4

**Calculando:**
- Célula A: Linha 1÷3 = 0, Coluna 2÷3 = 0 → Bloco (0,0)
- Célula B: Linha 0÷3 = 0, Coluna 4÷3 = 1 → Bloco (0,1)

**Resultado:** 
- Linha: 0 == 0 ✅ (mesmo grupo de linhas)
- Coluna: 0 ≠ 1 ❌ (grupos de colunas diferentes)

**Conclusão:** NÃO estão no mesmo bloco, então não há conflito.

---

## 📌 **5. Convertendo Número Sequencial em Linha e Coluna**

### 🔢 **Como o programa percorre as 81 células:**

Quando o programa precisa percorrer todas as células (numeradas de 0 a 80), ele usa:

```java
int row = i / 9;  // Calcula a linha
int col = i % 9;  // Calcula a coluna
```

### 💡 **Por que dividir por 9 para achar a linha?**

**Pense na tabuada do 9:**
- 0 ÷ 9 = 0 (células 0-8: primeira linha)
- 9 ÷ 9 = 1 (células 9-17: segunda linha)  
- 18 ÷ 9 = 2 (células 18-26: terceira linha)
- 27 ÷ 9 = 3 (células 27-35: quarta linha)

**Lógica:** Como cada linha tem **9 células**, a cada 9 números, "saltamos" para a próxima linha. A divisão por 9 nos diz quantas linhas já "pulamos".

### 🎯 **Por que o resto da divisão (%) para achar a coluna?**

**Pense no ciclo que se repete:**
- Células 0, 1, 2, 3, 4, 5, 6, 7, 8 → Colunas 0, 1, 2, 3, 4, 5, 6, 7, 8
- Células 9, 10, 11, 12, 13, 14, 15, 16, 17 → Colunas 0, 1, 2, 3, 4, 5, 6, 7, 8 (repete!)
- Células 18, 19, 20, 21, 22, 23, 24, 25, 26 → Colunas 0, 1, 2, 3, 4, 5, 6, 7, 8 (repete!)

**Lógica:** As colunas sempre seguem o padrão 0→1→2→3→4→5→6→7→8→0→1→2... O resto da divisão por 9 nos dá exatamente essa posição no ciclo!

### 🧮 **Exemplo prático com a tabuada:**

**Célula número 23:**
- **Linha**: 23 ÷ 9 = 2 (sobra 5)
  - Quantas vezes 9 cabe em 23? 2 vezes (9×2=18)
  - Isso significa que já "pulamos" 2 linhas completas
  - Estamos na linha 2 (terceira linha, contando do zero)
  
- **Coluna**: 23 % 9 = 5 (resto da divisão)
  - 23 ÷ 9 = 2 sobra 5
  - O resto 5 é nossa posição na linha atual
  - Estamos na coluna 5 (sexta coluna, contando do zero)

---

## 📌 **6. Tabela de Exemplos Práticos**

### 📊 **Conversão de número sequencial para coordenadas:**

| Número (i) | Linha (i÷9) | Coluna (i%9) | Posição no Tabuleiro |
|:-----------|:------------|:-------------|:---------------------|
| 0          | 0           | 0            | Primeira célula      |
| 8          | 0           | 8            | Final da 1ª linha    |
| 9          | 1           | 0            | Início da 2ª linha   |
| 17         | 1           | 8            | Final da 2ª linha    |
| 40         | 4           | 4            | Centro do tabuleiro  |
| 80         | 8           | 8            | Última célula        |

### 🧮 **Verificação do exemplo i=40:**
- Linha: 40 ÷ 9 = 4 (sobra 4)
- Coluna: 40 % 9 = 4 (resto da divisão)
- Posição: (4,4) - exatamente o centro!

---

## 📌 **7. Resumo das Fórmulas**

### 🎯 **Tabelinha para devs:**

| **Operação** | **Fórmula** | **Quando usar** |
|:-------------|:------------|:----------------|
| Descobrir linha de uma célula | `i / 9` | Converter número sequencial em coordenada |
| Descobrir coluna de uma célula | `i % 9` | Converter número sequencial em coordenada |
| Descobrir bloco de uma célula | `linha / 3` e `coluna / 3` | Verificar se duas células estão no mesmo bloco |
| Verificar conflito em bloco | `(linha1/3 == linha2/3) && (coluna1/3 == coluna2/3)` | Validar regras do Sudoku |

---

## 📌 **8. Por que essa matemática funciona?**

### 🤔 **A lógica por trás:**

1. **Divisão por 9**: O tabuleiro tem 9 colunas, então a cada 9 números, "pulamos" para a próxima linha
2. **Resto por 9**: Como as colunas se repetem de 0 a 8, o resto nos dá a posição exata na linha
3. **Divisão por 3**: Como cada bloco tem 3x3 células, dividir por 3 nos dá o "grupo" de linhas/colunas
4. **Comparação de blocos**: Se duas células têm o mesmo grupo de linhas E o mesmo grupo de colunas, estão no mesmo bloco

### 🎯 **Exemplo visual:**

```
Número sequencial: 23
├─ Linha: 23 ÷ 9 = 2 (terceira linha)
├─ Coluna: 23 % 9 = 5 (sexta coluna)
├─ Bloco linha: 2 ÷ 3 = 0 (primeiro grupo de linhas)
└─ Bloco coluna: 5 ÷ 3 = 1 (segundo grupo de colunas)
   Resultado: Célula (2,5) no bloco (0,1)
```

---

## 📌 **9. Aplicação Real no Código**

### 💻 **Como isso aparece no programa:**

```java
// Verificação de conflito em bloco
if (currentCell.getRow() / 3 == otherCell.getRow() / 3 && 
    currentCell.getCol() / 3 == otherCell.getCol() / 3) {
    return "Número repetido no mesmo bloco 3x3.";
}

// Conversão de índice para coordenadas
int row = i / 9;
int col = i % 9;
Optional<Cell> cell = findCell(row, col);
```

---

## 🎉 **Conclusão**

Com uma matemática simples, é possível fazer um jogo de Sudoku:
- ✅ **Divisão inteira** (para descobrir linhas e grupos)
- ✅ **Resto da divisão** (para descobrir colunas)
- ✅ **Comparações simples** (para verificar conflitos)

Com isto, o programa consegue:
- 🎯 Navegar por todas as 81 células
- 🎯 Identificar qual bloco 3x3 cada célula pertence
- 🎯 Verificar se há números repetidos
- 🎯 Validar se o jogo está correto

**Moral da história:** Matemática básica pode resolver problemas complexos! 🚀