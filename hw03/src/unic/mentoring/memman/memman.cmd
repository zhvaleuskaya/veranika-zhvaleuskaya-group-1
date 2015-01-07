javac Run.java
cd ../../..
java -XX:NewSize=24m -Xmn72m -XX:SurvivorRatio=1 -XX:PermSize=16m -XX:MaxPermSize=32m -Xms328m -Xmx328m -Xss1m unic.mentoring.memman.Run