FROM eclipse-temurin:25-jdk-jammy

# Install a standalone Maven binary to avoid pulling default-jre (which would replace Temurin)
ENV MAVEN_VERSION=3.9.5
RUN apt-get update \
	&& apt-get install -y --no-install-recommends wget ca-certificates tar \
	&& wget -q https://archive.apache.org/dist/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz -O /tmp/maven.tar.gz \
	&& mkdir -p /opt/maven \
	&& tar -xzf /tmp/maven.tar.gz -C /opt/maven --strip-components=1 \
	&& ln -s /opt/maven/bin/mvn /usr/bin/mvn \
	&& rm -rf /var/lib/apt/lists/* /tmp/maven.tar.gz

ARG TEST_EMAIL=
ARG TEST_PASSWORD=

ENV TEST_EMAIL=${TEST_EMAIL}
ENV TEST_PASSWORD=${TEST_PASSWORD}

WORKDIR /workspace

COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline

COPY . .

CMD ["mvn", "test"]