# Hajj Smart Portal Admin Portal

.......

# Prerequisites:
- docker (20.x.x )
- docker-compose ( 1.29.2) 

# Getting Started

Start it `docker-compose up -d`
Initial config 

```sh
for f in src/main/sql/1.0.0/init/*.sql;do fname=$(basename $f); docker-compose exec db sh -c '/opt/mssql-tools/bin/sqlcmd -U sa -P "$SA_PASSWORD" -i  /data/init/'"$fname"''; done;
```


# Authors

# License

LGPL v3