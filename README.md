# 🌐 **Sparse Matrix Compression**

## 📖 **Project Description**
In this project, you will work with compressing sparse matrices. A sparse matrix is a matrix that contains a large number of zero elements. The goal of this project is to design and implement a data structure for efficiently storing sparse matrices using linked lists and arrays to optimize memory usage. 🧑‍💻💾

The project includes various operations such as insertion, deletion, search, update, and matrix display. The matrix should be displayed using both a two-dimensional array format and a compressed format, and the data should be stored in CSV format. 📊

## 🎯 **Project Goals**
- 🧠 Work with linked list and array data structures
- 🔧 Implement common operations on linked lists
- 📚 Learn about sparse matrices and compression techniques
- 📂 Work with CSV data format
- ⚡ Improve efficiency and optimize memory usage

## 📝 **Project Steps**

1. **Define Data Structures**:
   - 🟢 **Node (`node`)**: Each node will store the row index, column index, value, and pointers to the next non-zero elements in the row and column.
   - 🟠 **Matrix (`matrix`)**: The matrix will be an array of pointers to the first non-zero element in each row or column.

2. **Implement Events**:
   - ➕ **Insert Node**: Change the value of a matrix element from zero to a non-zero value.
   - ➖ **Delete Node**: Change the value of a non-zero element in the matrix to zero.
   - 🔍 **Search**: Check if a given value exists in the matrix.
   - ✏️ **Update**: Change the value of a matrix element to a new value.
   - 🖼️ **Print Matrix**: Display the matrix in both two-dimensional array and compressed formats.
   - 💾 **Save**: Save the matrix data in CSV format.

## 📝 **Input and Output Format**

### 📥 **Input**
The input to the program should be a CSV file containing matrix data. Each row of the CSV file will include the row index, column index, and non-zero value.

### 📤 **Output**
After performing the operations, the program should display the matrix in two formats:
- 🧮 **Two-Dimensional Array Format**: The complete matrix including zeros.
- 📊 **Compressed Format**: Only non-zero elements displayed in the following format: `[row] [col] [value]`.

#### Example:
```
1 1 96
1 2 10
2 1 18
2 2 33
```

### 💾 **Saving Data**
The matrix data should be saved in a CSV file, so it can be reloaded for future use. 🗂️
