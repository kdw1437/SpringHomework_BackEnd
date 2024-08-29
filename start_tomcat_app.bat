@echo off
setlocal

rem 변수 세팅 (경로에 공백이 있으므로 따옴표로 묶습니다)
set "TOMCAT_HOME=C:\Users\JURO_NEW\Desktop\SpringLecture_2408\Servers\Tomcat v9.0 Server at localhost-config\apache-tomcat-9.0.71"
set "CATALINA_HOME=%TOMCAT_HOME%"
set "CATALINA_BASE=%TOMCAT_HOME%"
set "APP_URL=http://localhost:8080"

rem Tomcat 환경 변수 설정 확인
echo CATALINA_HOME : %CATALINA_HOME%
echo CATALINA_BASE : %CATALINA_BASE%

rem Tomcat 시작
echo Tomcat 시작...
call "%TOMCAT_HOME%\bin\startup.bat"

rem Tomcat 시작까지 기다리기 (sleep time 조정 가능)
echo Tomcat 시작 기다리기...
timeout /t 45 /nobreak

rem application URL로 브라우저에서 열기
echo application 열기...
start "" "%APP_URL%"

echo Tomcat이 시작되었고, application 접근 가능

endlocal