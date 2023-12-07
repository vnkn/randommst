
randmst :randmst.class Tuple.class PriorityHeap.class 


randmst.class: randmst.java 
	javac -d . -classpath . randmst.java
Tuple.class: Tuple.java
	javac -d . -classpath . Tuple.java
PriorityHeap.class: PriorityHeap.java
	javac -d . -classpath . PriorityHeap.java
clean:
	rm -f *.class

