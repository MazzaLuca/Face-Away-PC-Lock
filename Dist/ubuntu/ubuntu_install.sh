#!/bin/bash
#Jonas Bertossa
#Script di installazione
VER=$(awk -F= '/^NAME/{print $2}' /etc/os-release)
printf "\n[Installer] Controllo sistema..."
if [[ "$VER" = "\"Ubuntu\"" ]]; then
	echo "ubuntu"
else
	echo "$VER"
	echo "Errore: Questo script funziona solo su Ubuntu, questa macchina ha \"$VER\""
	exit 0
fi
printf "[Installer] Controllo utente..."
if [ "$EUID" = 0 ]; then
        echo "root"
else
        echo "$USER"
        echo "Errore: Questo script deve essere eseguito dall'utente root, al momento sei con l'utente \"$USER\""
        exit 0
fi
printf "[Installer] Prendo utente corrente..."
userlogged=$(who -H | awk '{print $1}' | sed '2q;d')
if [ -z "$userlogged" ]; then
	echo "errore"
    echo "Non sono riuscito a prendere l'utente corrente loggato."
	exit 1
else
	echo "$userlogged"
fi
printf "[Installer] Installo programmi..."
#echo "QT_X11_NO_MITSHM=1" >> /etc/environment
apt --fix-broken install > /dev/null 2>&1
apt-get update -y > /dev/null 2>&1
apt-get install libssl-dev > /dev/null 2>&1
wget "https://www.python.org/ftp/python/3.6.6/Python-3.6.6.tgz" > /dev/null 2>&1
tar -xvf Python-3.6.6.tgz  > /dev/null 2>&1
cd Python-3.6.6
./configure  > /dev/null 2>&1
make  > /dev/null 2>&1
make install > /dev/null 2>&1
cd ..
rm "Python-3.6.6.tgz"
rm -rf "Python-3.6.6"
apt-get install python3-pip software-properties-common gnome-screensaver openjdk-13-jdk cmake debconf-utils -y > /dev/null 2>&1
sudo -H pip3 install --upgrade pip > /dev/null 2>&1
echo "fatto"
printf "[Installer] Controllo programmi..."
if !(which "python3" > /dev/null ) ; then
    echo "errore"
    echo "Non è riuscita l'installazione di Python3."
	exit 1
fi
if !(which "pip3" > /dev/null ) ; then
    echo "errore"
    echo "Non è riuscita l'installazione di Python3-Pip."
	exit 1
fi
if !(which "cmake" > /dev/null ) ; then
    echo "errore"
    echo "Non è riuscita l'installazione di cmake"
	exit 1
fi
jversion=$(java -version 2>&1 >/dev/null | grep 'openjdk' | awk '{print $3}' | sed 's/"//g' | cut -d "_" -f1)
if [[ "$jversion" < "13" ]] ; then
    echo "errore"
    echo "Non è riuscita l'installazione di Java 13"
	exit 1
fi
echo "ok"
printf "[Installer] Installo plugin python..."
pip3 install dlib > /dev/null 2>&1
pip3 install face-recognition > /dev/null 2>&1
pip3 install opencv-python > /dev/null 2>&1
pip3 install psutil > /dev/null 2>&1
echo "fatto"
printf "[Installer] Configuro FaceLock..."
echo "
[Unit]
Description=FaceLock process

[Service]
User=${userlogged}
Group=${userlogged}
Type=forking
ExecStart=/bin/bash /etc/facelock/flstart_service.sh
WorkingDirectory=/etc/facelock

[Install]

WantedBy=multi-user.target

" > /etc/systemd/system/facelock.service
systemctl enable facelock
mkdir /etc/facelock
cp -r etc/* /etc/facelock/
chmod +x bin/*
cp -r bin/* /bin/
echo "$userlogged" > /etc/facelock/user
echo "fatto"
echo "FaceLock è stato impostato per essere avviato in automatico al login dell'utente $userlogged"
echo "Per disabilitare l'avvio automatico eseguire il comando 'sudo systemctl disable facelock'"
echo "Maggiori informazioni nella guida"
echo "Installazione completata"

