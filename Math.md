# 📊 Mathematics Behind the Sudoku Game in Java
<a href="https://github.com/Caroline-Teixeira/sudoku_game/blob/main/Matematica.md">
<img src="https://raw.githubusercontent.com/yammadev/flag-icons/refs/heads/master/png/BR%402x.png" alt="Portuguese" ></a>

This document explains, in a didactic way with visual examples, the mathematics used to verify conflicts and determine cell positions in blocks in the **Sudoku Game in Java**.

---

## 🎯 **What we'll see:**
- How the computer identifies if two numbers are in the same 3x3 block
- How to convert board positions into coordinates
- How the program traverses all 81 cells of the Sudoku
- The simple mathematics behind all of this

---

## 📌 **1. Conflict Verification in 3x3 Block**

In Sudoku, besides respecting row and column rules, numbers also cannot be repeated within 3x3 blocks.

### 🔍 **How the computer identifies blocks:**

```java
(currentCell.getRow() / 3 == otherCell.getRow() / 3 &&
 currentCell.getCol() / 3 == otherCell.getCol() / 3)
```

### 💡 **Simple explanation:**

When we divide the position by 3 and **ignore the decimal part** (round down), we transform board coordinates into block coordinates.

**Practical example:**
- Position 0, 1, 2 → divided by 3 = 0 (block 0)
- Position 3, 4, 5 → divided by 3 = 1 (block 1)  
- Position 6, 7, 8 → divided by 3 = 2 (block 2)

---

## 📌 **2. Position Conversion Table**

### 📖 **How positions become block numbers:**

| Board Position | Divided by 3 | Block Number |
|:---------------|:-------------|:-------------|
| 0              | 0 ÷ 3 = 0    | 0            |
| 1              | 1 ÷ 3 = 0    | 0            |
| 2              | 2 ÷ 3 = 0    | 0            |
| 3              | 3 ÷ 3 = 1    | 1            |
| 4              | 4 ÷ 3 = 1    | 1            |
| 5              | 5 ÷ 3 = 1    | 1            |
| 6              | 6 ÷ 3 = 2    | 2            |
| 7              | 7 ÷ 3 = 2    | 2            |
| 8              | 8 ÷ 3 = 2    | 2            |

---

## 📌 **3. Visual Map of 3x3 Blocks**

### 🗺️ **How Sudoku is divided into 9 blocks:**

```

  0 1 2 |  3 4 5 |  6 7 8     ← Columns
0 [0,0] | [0,1]  | [0,2]      ← Row 0
1 [0,0] | [0,1]  | [0,2]      ← Row 1  
2 [0,0] | [0,1]  | [0,2]      ← Row 2
  ------+--------+------
3 [1,0] | [1,1]  | [1,2]      ← Row 3
4 [1,0] | [1,1]  | [1,2]      ← Row 4
5 [1,0] | [1,1]  | [1,2]      ← Row 5
  ------+--------+------
6 [2,0] | [2,1]  | [2,2]      ← Row 6
7 [2,0] | [2,1]  | [2,2]      ← Row 7
8 [2,0] | [2,1]  | [2,2]      ← Row 8


The numbers in brackets [] represent the blocks:
Block [0,0], [0,1], [0,2]...
```

---

## 📌 **4. Practical Verification Example**

### ✅ **Test: Are two cells in the same block?**

**Cell A:** Row 1, Column 2  
**Cell B:** Row 0, Column 4

**Calculating:**
- Cell A: Row 1÷3 = 0, Column 2÷3 = 0 → Block (0,0)
- Cell B: Row 0÷3 = 0, Column 4÷3 = 1 → Block (0,1)

**Result:** 
- Row: 0 == 0 ✅ (same row group)
- Column: 0 ≠ 1 ❌ (different column groups)

**Conclusion:** They are NOT in the same block, so there's no conflict.

---

## 📌 **5. Converting Sequential Number to Row and Column**

### 🔢 **How the program traverses all 81 cells:**

When the program needs to traverse all cells (numbered from 0 to 80), it uses:

```java
int row = i / 9;  // Calculates the row
int col = i % 9;  // Calculates the column
```

### 💡 **Why divide by 9 to find the row?**

**Think of the 9 times table:**
- 0 ÷ 9 = 0 (cells 0-8: first row)
- 9 ÷ 9 = 1 (cells 9-17: second row)  
- 18 ÷ 9 = 2 (cells 18-26: third row)
- 27 ÷ 9 = 3 (cells 27-35: fourth row)

**Logic:** Since each row has **9 cells**, every 9 numbers, we "jump" to the next row. Division by 9 tells us how many rows we've already "jumped".

### 🎯 **Why the remainder (%) to find the column?**

**Think of the repeating cycle:**
- Cells 0, 1, 2, 3, 4, 5, 6, 7, 8 → Columns 0, 1, 2, 3, 4, 5, 6, 7, 8
- Cells 9, 10, 11, 12, 13, 14, 15, 16, 17 → Columns 0, 1, 2, 3, 4, 5, 6, 7, 8 (repeats!)
- Cells 18, 19, 20, 21, 22, 23, 24, 25, 26 → Columns 0, 1, 2, 3, 4, 5, 6, 7, 8 (repeats!)

**Logic:** Columns always follow the pattern 0→1→2→3→4→5→6→7→8→0→1→2... The remainder of division by 9 gives us exactly this position in the cycle!

### 🧮 **Practical example with multiplication table:**

**Cell number 23:**
- **Row**: 23 ÷ 9 = 2 (remainder 5)
  - How many times does 9 fit into 23? 2 times (9×2=18)
  - This means we've already "jumped" 2 complete rows
  - We're on row 2 (third row, counting from zero)
  
- **Column**: 23 % 9 = 5 (remainder of division)
  - 23 ÷ 9 = 2 remainder 5
  - The remainder 5 is our position in the current row
  - We're on column 5 (sixth column, counting from zero)

---

## 📌 **6. Practical Examples Table**

### 📊 **Converting sequential number to coordinates:**

| Number (i) | Row (i÷9) | Column (i%9) | Position on Board |
|:-----------|:----------|:-------------|:------------------|
| 0          | 0         | 0            | First cell        |
| 8          | 0         | 8            | End of 1st row    |
| 9          | 1         | 0            | Start of 2nd row  |
| 17         | 1         | 8            | End of 2nd row    |
| 40         | 4         | 4            | Center of board   |
| 80         | 8         | 8            | Last cell         |

### 🧮 **Verification of example i=40:**
- Row: 40 ÷ 9 = 4 (remainder 4)
- Column: 40 % 9 = 4 (remainder of division)
- Position: (4,4) - exactly the center!

---

## 📌 **7. Formula Summary**

### 🎯 **Cheat sheet for developers:**

| **Operation** | **Formula** | **When to use** |
|:-------------|:------------|:----------------|
| Find row of a cell | `i / 9` | Convert sequential number to coordinate |
| Find column of a cell | `i % 9` | Convert sequential number to coordinate |
| Find block of a cell | `row / 3` and `column / 3` | Check if two cells are in the same block |
| Check block conflict | `(row1/3 == row2/3) && (column1/3 == column2/3)` | Validate Sudoku rules |

---

## 📌 **8. Why does this mathematics work?**

### 🤔 **The logic behind:**

1. **Division by 9**: The board has 9 columns, so every 9 numbers, we "jump" to the next row
2. **Remainder by 9**: Since columns repeat from 0 to 8, the remainder gives us the exact position in the row
3. **Division by 3**: Since each block has 3x3 cells, dividing by 3 gives us the "group" of rows/columns
4. **Block comparison**: If two cells have the same row group AND the same column group, they're in the same block

### 🎯 **Visual example:**

```
Sequential number: 23
├─ Row: 23 ÷ 9 = 2 (third row)
├─ Column: 23 % 9 = 5 (sixth column)
├─ Block row: 2 ÷ 3 = 0 (first group of rows)
└─ Block column: 5 ÷ 3 = 1 (second group of columns)
   Result: Cell (2,5) in block (0,1)
```

---

## 📌 **9. Real Application in Code**

### 💻 **How this appears in the program:**

```java
// Block conflict verification
if (currentCell.getRow() / 3 == otherCell.getRow() / 3 && 
    currentCell.getCol() / 3 == otherCell.getCol() / 3) {
    return "Number repeated in the same 3x3 block.";
}

// Index to coordinates conversion
int row = i / 9;
int col = i % 9;
Optional<Cell> cell = findCell(row, col);
```

---

## 🎉 **Conclusion**

With simple mathematics, it's possible to create a Sudoku game:
- ✅ **Integer division** (to discover rows and groups)
- ✅ **Remainder of division** (to discover columns)
- ✅ **Simple comparisons** (to verify conflicts)

With this, the program can:
- 🎯 Navigate through all 81 cells
- 🎯 Identify which 3x3 block each cell belongs to
- 🎯 Check if there are repeated numbers
- 🎯 Validate if the game is correct

**Moral of the story:** Basic mathematics can solve complex problems! 🚀
