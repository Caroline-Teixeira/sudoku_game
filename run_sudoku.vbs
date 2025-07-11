Set objShell = CreateObject("WScript.Shell")
Set objFSO = CreateObject("Scripting.FileSystemObject")

' Obtém o diretório do script
scriptPath = objFSO.GetParentFolderName(WScript.ScriptFullName)

' Muda para o diretório do script
objShell.CurrentDirectory = scriptPath

' Verifica se o arquivo JAR existe
jarPath = scriptPath & "\target\SudokuGame-1.0-SNAPSHOT.jar"
If Not objFSO.FileExists(jarPath) Then
    MsgBox "Erro: Arquivo JAR não encontrado!" & vbCrLf & vbCrLf & _
           "Certifique-se de que o projeto foi compilado com:" & vbCrLf & _
           "mvn clean package", vbCritical, "Sudoku Game"
    WScript.Quit
End If

' Executa o JAR sem mostrar janela do console
' 0 = janela oculta, False = não espera terminar
objShell.Run "java -jar target\SudokuGame-1.0-SNAPSHOT.jar", 0, False