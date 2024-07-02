# Default target
all: compile run clean

# Compile Java code
compile:
	javac Main.java Person.java SocialNetwork.java

# Run Java code
run:
	java Main

# Generate JavaDoc documentation
doc:
	javadoc -protected -splitindex -use -author -version -d ./javadoc *.java

# Clean up compiled files
clean:
	rm -f *.class

# Clean up JavaDoc documentation
cleandoc:
	rm -rf ./javadoc

# Display help message
help:
	@echo Available targets:
	@echo   all       - compile run clean
	@echo   compile   - Compile Java code
	@echo   run       - Run Java code with input file
	@echo   doc       - Generate JavaDoc documentation
	@echo   clean     - Clean up compiled files
	@echo   cleandoc  - Clean up JavaDoc documentation
	@echo   help      - Display this help message
