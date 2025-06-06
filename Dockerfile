FROM ubuntu:latest
LABEL authors="virtue"

ENTRYPOINT ["top", "-b"]