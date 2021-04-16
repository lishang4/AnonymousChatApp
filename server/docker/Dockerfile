# ---- Base python ----
FROM python:3.7 AS base
RUN apt-get update &&\
 apt-get install -y cron &&\
 apt-get clean all
# Create app directory
WORKDIR "/usr/src/app"

# ---- Dependincies ----
FROM base AS dependencies
COPY ./requirements.txt ./
# install app dependencies
RUN pip install -r requirements.txt

# ---- Copy Files/Build ----
FROM dependencies AS build
WORKDIR "/usr/src/app"
COPY . /usr/src/app
# Build / Compile if required

# --- Release with Alpine ----
FROM python:3.7-alpine3.8 AS release
# Create app directory
WORKDIR "/usr/src/app"
COPY --from=dependencies /usr/src/app/requirements.txt ./
COPY --from=dependencies /root/.cache /root/.cache
# Install app dependencies
RUN apk --update add python py-pip openssl ca-certificates py-openssl curl vim && \
 apk --update add --virtual build-dependencies libffi-dev openssl-dev python-dev py-pip build-base && \
 pip install -r requirements.txt &&\
 apk del build-dependencies

COPY --from=build /usr/src/app/ ./
ENTRYPOINT ["python", "/usr/src/app/server.py"]
