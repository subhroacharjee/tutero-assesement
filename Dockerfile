FROM alpine:latest

# # Sensible Defaults for Memory Consumption
# ARG MIN_MEM=2G
# ARG MAX_MEM=2G

# # This env var will force the JVM to terminate if more memory is allocated than specified here
# ENV _JAVA_OPTIONS="-Xms${MIN_MEM} -Xmx${MAX_MEM}"

RUN apk update && \
    apk fetch openjdk8 && \
    apk add --no-cache openjdk8;

ENV JAVA_HOME=/usr/lib/jvm/java-1.8-openjdk
ENV PATH="$JAVA_HOME/bin:${PATH}"
WORKDIR /usr/src/app


# Copying Package
COPY ./app/ .

# Copying input.txt
Copy ./input.txt .
# #Compiling source
RUN echo "Hello world"
RUN javac ./App/App.java
#Execution
CMD ["java", "App/App" ]
