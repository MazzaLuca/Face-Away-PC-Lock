#!/bin/bash
#Jonas Bertossa
#Esegue lo script in background in modo da non far finire l'avvio del servizio, altrimenti systemctl lo interrompe per timeout
/bin/bash /etc/facelock/flstart.sh &