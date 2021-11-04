FROM openliberty/open-liberty:kernel-slim-java15-openj9-ubi

COPY --chown=1001:0 config/server.xml config/bootstrap.properties /config/

RUN features.sh

ARG ARTIFACT_NAME
COPY --chown=1001:0 ${ARTIFACT_NAME} /apps/${ARTIFACT_NAME}

RUN configure.sh
