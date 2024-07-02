JC = javac
JFLAGS = -g
TARGET = Main
SOURCES = Main.java AVLTree.java GUIVisualization.java Stock.java StockDataManager.java InputGenerator.java

default: generate run clean

$(TARGET): $(SOURCES)
	$(JC) $(JFLAGS) $^

generate: $(TARGET)
	java InputGenerator random_input.txt

run: $(TARGET)
	java -Xint $(TARGET) random_input.txt

clean:
	rm -f *.class random_input.txt

.PHONY: clean default generate run

doc:
	javadoc -d doc $(SOURCES)
