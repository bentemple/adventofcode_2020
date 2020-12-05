gfind . -name "*.kt" -print | xargs kotlinc -d main.jar;kotlin -classpath main.jar MainKt
