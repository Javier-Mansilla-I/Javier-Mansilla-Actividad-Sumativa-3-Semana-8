# LlanquihueTourApp V2.0 Build and Run Script

Write-Host "====================================================" -ForegroundColor Cyan
Write-Host " Iniciando proceso de compilación y empaquetado      " -ForegroundColor Cyan
Write-Host "====================================================" -ForegroundColor Cyan

# Intentar resolver las rutas de las herramientas de Java (JDK) de manera robusta
$javaBin = ""

if (Test-Path "$env:JAVA_HOME\bin") {
    $javaBin = "$env:JAVA_HOME\bin"
} else {
    if (Test-Path "C:\Program Files\Java") {
        $jdkPaths = Get-ChildItem -Path "C:\Program Files\Java" -Filter "jdk*" -Directory | Sort-Object Name -Descending
        if ($jdkPaths.Count -gt 0) {
            $javaBin = Join-Path $jdkPaths[0].FullName "bin"
        }
    }
}

# Definir comandos
$javacCmd = "javac"
$jarCmd = "jar"
$javaCmd = "java"

if ($javaBin) {
    $resolvedJavac = Join-Path $javaBin "javac.exe"
    $resolvedJar = Join-Path $javaBin "jar.exe"
    $resolvedJava = Join-Path $javaBin "java.exe"
    
    if (Test-Path $resolvedJavac) { $javacCmd = $resolvedJavac }
    if (Test-Path $resolvedJar) { $jarCmd = $resolvedJar }
    if (Test-Path $resolvedJava) { $javaCmd = $resolvedJava }
}

Write-Host "Usando comandos de Java de: $javaBin" -ForegroundColor Gray

# 1. Limpieza de directorios previos
if (Test-Path bin) {
    Remove-Item -Path bin -Recurse -Force | Out-Null
}
if (Test-Path dist) {
    Remove-Item -Path dist -Recurse -Force | Out-Null
}

# 2. Creación de directorios
New-Item -ItemType Directory -Path bin -Force | Out-Null
New-Item -ItemType Directory -Path dist/lib -Force | Out-Null

# 3. Obtener todos los archivos Java
$javaFiles = Get-ChildItem -Path src -Filter *.java -Recurse | ForEach-Object { $_.FullName }

if ($javaFiles.Count -eq 0) {
    Write-Error "No se encontraron archivos fuentes Java en 'src/'"
    exit 1
}

# 4. Compilar clases
Write-Host "[Compilación] Compilando clases..." -ForegroundColor Yellow
& $javacCmd -d bin $javaFiles

if ($LASTEXITCODE -ne 0) {
    Write-Error "Error de compilación de Java."
    exit $LASTEXITCODE
}
Write-Host "[Compilación] Compilación exitosa." -ForegroundColor Green

# 5. Empaquetar modelo en JAR
Write-Host "[Empaquetado] Creando archivo dist/lib/model.jar..." -ForegroundColor Yellow
& $jarCmd cf dist/lib/model.jar -C bin model

if ($LASTEXITCODE -ne 0) {
    Write-Error "Error al crear el archivo JAR."
    exit $LASTEXITCODE
}
Write-Host "[Empaquetado] JAR creado exitosamente." -ForegroundColor Green

# 6. Eliminar carpeta model de bin para asegurar el uso del JAR
# (Esto fuerza a la JVM a cargar las clases de 'model' desde el JAR empaquetado en dist/lib/)
if (Test-Path bin/model) {
    Remove-Item -Path bin/model -Recurse -Force | Out-Null
}

# 7. Ejecutar Main
Write-Host "[Ejecución] Iniciando la aplicación LlanquihueTourApp..." -ForegroundColor Yellow
Write-Host "----------------------------------------------------"
& $javaCmd -cp "bin;dist/lib/model.jar" ui.Main
