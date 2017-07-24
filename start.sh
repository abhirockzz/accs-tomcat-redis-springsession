#!/bin/bash
if [ -z "$PORT" ];
then
    PORT="8080"
fi
echo "PORT set to $PORT"
# Update server.xml with env vars
sed "s/__PORT__/${PORT}/g" server.template.xml > tom-accs/conf/server.xml
sh tom-accs/bin/catalina.sh run