@Echo off
javac ./src/com/stickfighter/main/*.java -d ./classes && cd classes && java com.stickfighter.main.Game && cd..
