@echo off
cd %~dp0
call venv\Scripts\activate
python src\main.py
pause

