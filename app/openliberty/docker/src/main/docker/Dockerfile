FROM openliberty/open-liberty:kernel-slim-java15-openj9-ubi

COPY --chown=1001:0 config/* /config/

RUN bash -x features.sh

ARG ARTIFACT_NAME
COPY --chown=1001:0 ${ARTIFACT_NAME} /config/dropins/

RUN configure.sh
