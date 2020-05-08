#!/bin/bash
# Jonas Bertossa
# La variabile userlogged contiene l'utente loggato
# Resta nel while finche la variabile non contiene l'utente per cui facelock è stato installato
userlogged=$(who -H | awk '{print $1}' | sed '2q;d')
user=$(head -n 1 /etc/facelock/user)
while [ "$userlogged" != "$user" ]
do
	userlogged=$(who -H | awk '{print $1}' | sed '2q;d')
	sleep 1
done
/usr/bin/python3 /etc/facelock/faceCheck.py