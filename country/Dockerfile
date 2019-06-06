FROM alpine:edge

MAINTAINER Roshni Prince "roshnikprince@gmail.com"
VOLUME ["/tmp"]
COPY scripts/tgt-certs.sh /tgt-certs.sh
RUN bash -c 'chmod a+x /tgt-certs.sh'
RUN /tgt-certs.sh

RUN apk update && apk add curl unzip bash bash-doc bash-completion && \
    rm /bin/sh && ln -sf /bin/bash /bin/sh

RUN addgroup -g 2000 inbgroup && \
   adduser -D -u 1000 -G inbgroup localuser
USER localuser
WORKDIR /home/localuser

ENV STORE_EDI856_DOCUMENT_DETAILS_OPTS='-Dspring.profiles.active=prod -Djava.security.egd=file:/dev/./urandom -Dserver.port=8080 $JAVA_JVM_ARG_HEAP_SIZE_MIN $JAVA_JVM_ARG_HEAP_SIZE_MAX'
ADD build/distributions/*.tar /
EXPOSE 8080

ENTRYPOINT ["/store-edi856-document-details/bin/store-edi856-document-details"]