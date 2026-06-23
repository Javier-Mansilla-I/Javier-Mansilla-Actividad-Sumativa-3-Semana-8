# build_and_run.ps1
# Script para compilar la librería personalizada Llanquihue Tour y ejecutar la aplicación.

# Limpiar carpetas anteriores
if (Test-Path "out") { Remove-Item -Recurse -Force out }
if (Test-Path "dist") { Remove-Item -Recurse -Force dist }
if (Test-Path "bin_lib") { Remove-Item -Recurse -Force bin_lib }

# Crear directorios
New-Item -ItemType Directory -Force -Path "bin_lib" | Out-Null
New-Item -ItemType Directory -Force -Path "dist/lib" | Out-Null
New-Item -ItemType Directory -Force -Path "out" | Out-Null

Write-Host "=========================================================" -ForegroundColor Cyan
Write-Host "1. Compilando clases del modelo y utilidades (Librería)..." -ForegroundColor Cyan
Write-Host "=========================================================" -ForegroundColor Cyan

# Compilar clases de la librería (model y util)
javac -encoding UTF-8 -d bin_lib src/model/*.java src/util/*.java
if ($LASTEXITCODE -ne 0) {
    Write-Error "Fallo en la compilación de la librería."
    exit $LASTEXITCODE
}

# Localizar el ejecutable jar.exe
$jarPath = "jar"
if (Test-Path "C:\Program Files\Java\jdk-23\bin\jar.exe") {
    $jarPath = "C:\Program Files\Java\jdk-23\bin\jar.exe"
} elseif ($env:JAVA_HOME -and (Test-Path "$env:JAVA_HOME\bin\jar.exe")) {
    $jarPath = "$env:JAVA_HOME\bin\jar.exe"
} else {
    $jdkDirs = Get-ChildItem -Path "C:\Program Files\Java" -Filter "jdk-*" -ErrorAction SilentlyContinue
    if ($jdkDirs) {
        $firstJdk = $jdkDirs[0].FullName
        if (Test-Path "$firstJdk\bin\jar.exe") {
            $jarPath = "$firstJdk\bin\jar.exe"
        }
    }
}

Write-Host "Empaquetando en dist/lib/llanquihue-tour-lib.jar utilizando: $jarPath" -ForegroundColor Green
& $jarPath -cf dist/lib/llanquihue-tour-lib.jar -C bin_lib .
if ($LASTEXITCODE -ne 0) {
    Write-Error "Fallo al crear el archivo JAR de la librería."
    exit $LASTEXITCODE
}

Remove-Item -Recurse -Force bin_lib

Write-Host "=========================================================" -ForegroundColor Cyan
Write-Host "2. Compilando aplicación principal (Service y App)..." -ForegroundColor Cyan
Write-Host "=========================================================" -ForegroundColor Cyan

# Compilar el resto utilizando la librería jar
javac -encoding UTF-8 -cp "dist/lib/llanquihue-tour-lib.jar" -d out src/service/*.java src/app/*.java
if ($LASTEXITCODE -ne 0) {
    Write-Error "Fallo en la compilación de la aplicación."
    exit $LASTEXITCODE
}

Write-Host "Compilación exitosa." -ForegroundColor Green
Write-Host "=========================================================" -ForegroundColor Cyan
Write-Host "3. Iniciando Aplicación Llanquihue Tour v2.0..." -ForegroundColor Cyan
Write-Host "=========================================================" -ForegroundColor Cyan

# Ejecutar la aplicación enlazando la librería
java "-Dfile.encoding=UTF-8" -cp "out;dist/lib/llanquihue-tour-lib.jar" app.Main
