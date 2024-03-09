FROM maven:3-openjdk-8-slim as build
COPY --from=iordaniskostelidis/linkedin-data-importer /root/.m2/repository/gr/ihu/ict/linkedin-data-importer /root/.m2/repository/gr/ihu/ict/linkedin-data-importer
COPY --from=iordaniskostelidis/zotero-publications-importer /root/.m2/repository/gr/ihu/ict/zotero-publications-importer /root/.m2/repository/gr/ihu/ict/zotero-publications-importer
WORKDIR /opt/src
COPY . .
RUN mvn package \
 && mv /opt/src/resumeinsync-api/target/resumeinsync-api-RELEASE.jar /opt/resumeinsync-api-RELEASE.jar \
 && mvn clean

FROM openjdk:8u312-jre-slim

RUN apt-get update \
    && apt-get install -y \
    locales \
    locales-all \
    mime-support \
    && apt-get clean

COPY --from=build /opt/resumeinsync-api-RELEASE.jar /opt/gr/ihu/ict/resumeinsync-api.jar

ENV LC_ALL="en_US.UTF-8"
ENV LANG="en_US.UTF-8"
ENV TZ="Europe/Athens"
ENV ACTIVE_PROFILE="production"
ENV DATABASE_USERNAME=""
ENV DATABASE_PASSWORD=""
ENV DATABASE_URL=""
ENV CLIENT_ID=""
ENV CLIENT_SECRET=""
ENV SIGNING_KEY=""
ENV VERIFIER_KEY=""
ENV RESOURCE_ID=""
ENV ACCESS_TOKEN_DURATION=""
ENV REFRESH_TOKEN_DURATION=""

ENTRYPOINT ["java", "-jar", "/opt/gr/ihu/ict/resumeinsync-api.jar", "--spring.profiles.active=${ACTIVE_PROFILE}"]