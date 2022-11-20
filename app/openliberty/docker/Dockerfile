FROM icr.io/appcafe/open-liberty:beta-instanton

ARG JKUBE_DEFAULT_ASSEMBLY=maven

COPY --chown=1001:0 $JKUBE_DEFAULT_ASSEMBLY/target/dockerfiles/config/* /config/

#RUN features.sh

COPY --chown=1001:0 $JKUBE_DEFAULT_ASSEMBLY/target/dockerfiles/*.war /config/apps/

ENV OPENJ9_SCC=true
RUN configure.sh
