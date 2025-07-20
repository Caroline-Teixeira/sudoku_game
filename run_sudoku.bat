```batch
@echo off
cd /d "%~dp0"

:: Verifica se o arquivo JAR existe
if not exist "target\SudokuGame-1.0-SNAPSHOT.jar" (
    echo Erro: Arquivo JAR não encontrado!
    echo Certifique-se de que o projeto foi compilado com: mvn clean package
    pause
    exit /b 1
)

:: Executa o JAR
java -jar target\SudokuGame-1.0-SNAPSHOT.jar
pause
```