@echo off
cd /d %~dp0

set EXE_FILE=jd-cli.exe
set PLUGIN_FILE=jd_exe.sct
set FROM_EXE_PATH=..\..\%EXE_FILE%
set TO_DIR=WinMerge
set TO_PLUGIN_DIR=%TO_DIR%\MergePlugins

echo START INSTALL WinMerge Plugin FOR EXE

IF EXIST "C:\PROGRA~1\%TO_DIR%\" (
echo INSTALL TO "C:\PROGRA~1\%TO_DIR%"
echo copy %FROM_EXE_PATH%
copy /Y "%FROM_EXE_PATH%" "C:\PROGRA~1\%TO_DIR%\%EXE_FILE%"
echo copy %PLUGIN_FILE%
copy /Y "%PLUGIN_FILE%" "C:\PROGRA~1\%TO_PLUGIN_DIR%\%PLUGIN_FILE%"
echo INSTALL Finished.
) ELSE IF EXIST "C:\PROGRA~2\%TO_DIR%\" (
echo INSTALL TO "C:\PROGRA~2\%TO_DIR%"
echo copy %FROM_EXE_PATH%
copy /Y "%FROM_EXE_PATH%" "C:\PROGRA~2\%TO_DIR%\%EXE_FILE%"
echo copy %PLUGIN_FILE%
copy /Y "%PLUGIN_FILE%" "C:\PROGRA~2\%TO_PLUGIN_DIR%\%PLUGIN_FILE%"
echo INSTALL Finished.
) ELSE (
echo No Install WinMerge.
)

pause
exit
