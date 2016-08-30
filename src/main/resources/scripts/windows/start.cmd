@echo off

:: BatchGotAdmin
:-------------------------------------
REM  --> Check for permissions
    IF "%PROCESSOR_ARCHITECTURE%" EQU "amd64" (
>nul 2>&1 "%SYSTEMROOT%\SysWOW64\cacls.exe" "%SYSTEMROOT%\SysWOW64\config\system"
) ELSE (
>nul 2>&1 "%SYSTEMROOT%\system32\cacls.exe" "%SYSTEMROOT%\system32\config\system"
)

REM --> If error flag set, we do not have admin.
if '%errorlevel%' NEQ '0' (
    echo Requesting administrative privileges...
    goto UACPrompt
) else ( goto gotAdmin )

:UACPrompt
    echo Set UAC = CreateObject^("Shell.Application"^) > "%temp%\getadmin.vbs"
    set params = %*:"=""
    echo UAC.ShellExecute "cmd.exe", "/c ""%~s0"" %params%", "", "runas", 1 >> "%temp%\getadmin.vbs"

    "%temp%\getadmin.vbs"
    del "%temp%\getadmin.vbs"
    exit /B

:gotAdmin
    pushd "%CD%"
    CD /D "%~dp0"
:--------------------------------------    
:    <YOUR BATCH SCRIPT HERE>
netsh int ip set address "wireless network connection" static 10.10.10.50 255.255.255.0
:--- run in background for the next 3 commands
SET outputDir=%HOMEPATH%\ept-dist
cd /d %outputDir% & start /b cmd /c java -jar renison-ept-1.0-SNAPSHOT.jar --spring.config.location=application.local-mysql.properties --spring.application.json='{"dispatchOptionsRequest":true}'
cd /d %outputDir%\admin-dist & start /b cmd /c python -m SimpleHTTPServer 8081
cd /d %outputDir%\student-dist & start /b cmd /c python -m SimpleHTTPServer 8888