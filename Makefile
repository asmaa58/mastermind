MAINCLASS ?= com.example.mastermind.App

.PHONY: all compile run clean

all: compile run

compile:
	mvn --quiet compile

run:
	@java --class-path target/classes/ $(MAINCLASS)

clean:
	rm -rf target/
