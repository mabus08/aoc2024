# Makefile für Rust Projekt

# Variablen
SRC = src/main.rs
BIN = main
BIN_PATH = $(shell pwd)/$(BIN)

# Standardziel
all: build run

# Erstelle das Executable
build:
	rustc $(SRC) -o $(BIN)

# Führe das Executable aus
run:
	$(BIN_PATH)

# Lösche das Executable
clean:
	rm -f $(BIN)

# Erstelle das Executable und führe es aus
build-run: build run

# Ziel zum Debuggen
debug:
	rustc -g $(SRC) -o $(BIN)

.PHONY: all build run clean build-run debug