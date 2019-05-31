@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  sna4so startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and SNA4SO_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\sna4so.jar;%APP_HOME%\lib\google-cloud-bigquery-1.64.0.jar;%APP_HOME%\lib\google-api-services-sheets-v4-rev516-1.23.0.jar;%APP_HOME%\lib\google-api-services-drive-v3-rev154-1.25.0.jar;%APP_HOME%\lib\google-cloud-core-http-1.64.0.jar;%APP_HOME%\lib\google-api-services-bigquery-v2-rev20181104-1.27.0.jar;%APP_HOME%\lib\google-api-client-1.27.0.jar;%APP_HOME%\lib\google-oauth-client-jetty-1.23.0.jar;%APP_HOME%\lib\google-cloud-core-1.64.0.jar;%APP_HOME%\lib\gax-httpjson-0.57.0.jar;%APP_HOME%\lib\gax-1.40.0.jar;%APP_HOME%\lib\protobuf-java-util-3.6.1.jar;%APP_HOME%\lib\google-auth-library-oauth2-http-0.13.0.jar;%APP_HOME%\lib\google-http-client-appengine-1.28.0.jar;%APP_HOME%\lib\google-http-client-jackson2-1.28.0.jar;%APP_HOME%\lib\google-oauth-client-java6-1.23.0.jar;%APP_HOME%\lib\google-oauth-client-1.27.0.jar;%APP_HOME%\lib\google-http-client-1.28.0.jar;%APP_HOME%\lib\opencensus-contrib-http-util-0.18.0.jar;%APP_HOME%\lib\proto-google-iam-v1-0.12.0.jar;%APP_HOME%\lib\api-common-1.7.0.jar;%APP_HOME%\lib\guava-26.0-android.jar;%APP_HOME%\lib\javax.annotation-api-1.3.2.jar;%APP_HOME%\lib\jetty-6.1.26.jar;%APP_HOME%\lib\jsr305-3.0.2.jar;%APP_HOME%\lib\proto-google-common-protos-1.14.0.jar;%APP_HOME%\lib\google-auth-library-credentials-0.13.0.jar;%APP_HOME%\lib\opencensus-api-0.18.0.jar;%APP_HOME%\lib\jetty-util-6.1.26.jar;%APP_HOME%\lib\servlet-api-2.5-20081211.jar;%APP_HOME%\lib\j2objc-annotations-1.1.jar;%APP_HOME%\lib\threetenbp-1.3.3.jar;%APP_HOME%\lib\protobuf-java-3.6.1.jar;%APP_HOME%\lib\gson-2.7.jar;%APP_HOME%\lib\grpc-context-1.14.0.jar;%APP_HOME%\lib\checker-compat-qual-2.5.2.jar;%APP_HOME%\lib\error_prone_annotations-2.1.3.jar;%APP_HOME%\lib\animal-sniffer-annotations-1.14.jar;%APP_HOME%\lib\jackson-core-2.9.6.jar

@rem Execute sna4so
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %SNA4SO_OPTS%  -classpath "%CLASSPATH%" it.uniba.main/AppMain %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable SNA4SO_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%SNA4SO_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
