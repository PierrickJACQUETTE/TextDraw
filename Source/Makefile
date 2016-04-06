JAVAC=javac
sources = $(wildcard *.java)
classes = $(sources:.java=.class)

all: myProgram

myProgram: $(classes)

clean :
	rm -f *.class

%.class : %.java
	$(JAVAC) $<
