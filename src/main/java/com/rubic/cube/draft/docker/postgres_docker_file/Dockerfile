FROM postgres:alpine3.17
ENV       POSTGRES_USER=postgres \
          POSTGRES_PASSWORD=123 \
          POSTGRES_DB=product_db \
          PGUSER=postgres

COPY custom_entry.sh /docker-entrypoint-initdb.d/custom_entry.sh

