@echo off
cls

rem javac -Xlint:unchecked tileSearch.java
rem if errorlevel 1 goto end

java -cp "build\classes" -enableassertions tileSearch BFS tileSearch.in
pause
java -cp "build\classes" -enableassertions tileSearch DFS tileSearch.in
pause
java -cp "build\classes" -enableassertions tileSearch UCS tileSearch.in
pause
java -cp "build\classes" -enableassertions tileSearch GS tileSearch.in
pause
java -cp "build\classes" -enableassertions tileSearch A-star tileSearch.in
pause

java -cp "build\classes" -enableassertions tileSearch -cost BFS tileSearch.in
pause
java -cp "build\classes" -enableassertions tileSearch -cost DFS tileSearch.in
pause
java -cp "build\classes" -enableassertions tileSearch -cost UCS tileSearch.in
pause
java -cp "build\classes" -enableassertions tileSearch -cost GS tileSearch.in
pause
java -cp "build\classes" -enableassertions tileSearch -cost A-star tileSearch.in

:end
