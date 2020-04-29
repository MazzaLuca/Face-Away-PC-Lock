import face_recognition
import logging
import cv2
import numpy as np
from time import sleep
import os
import sys
import shutil
import psutil
from sys import platform
import ctypes
from ctypes import CDLL
import csv
import threading
import time
from datetime import datetime
from os import path
import Quartz

class faceCheck(object):

    # Variabile che verifica quale è il sistema operativo utilizzato
    system = ""
    if platform == "darwin":
        system = "macOS"
    elif platform == "win32":
        system = "Windows"
    else: 
        system = "Linux"

    # Variabile ottiene il nome della faccia che vede attualmente
    face = ""

    # Dictionary che tiene traccia dei valori prensenti sul file di Settings
    settings = {
        "Countdown": "0",
        "logLock": "0",
        "logStranger": "0"
    }

    # Array di utenti riconoscibili dal programma
    users = []

    # Array delle encodings delle facce conosciute
    known_face_encodings = []

    # Array dei nomi delle facce conosciute
    known_face_names = []

    # Variabile che imposta il numeor massimo che il timer può arrivare
    maxTimer = 0

    # Variabile che salva il nome dell'ultimo utente trovato dalla camera
    lastUser = "nobody"


    # Metodo costruttore
    def __init__(self):
        print("Loading images...")


    # Metodo che ritorna la lista di utenti che hanno fatto le foto per poi
    # essere riconosciuti. Per sapere quest'informazione l'algoritmo si dirige 
    # in Dataset e conta le directory, ritornando i loro nomi.
    def getUsers(self):
        path = './Dataset/'
        files = os.listdir(path)
        for name in files:
            full_path = os.path.join(path, name + "/")
            if os.path.exists(full_path):
                self.users.append(name)


    # Legge il file settings.csv e ne ricava le impostazioni desiderate dall'utente
    def getSettings(self, file):
        with open(file, newline='') as File:  
            reader = csv.reader(File)
            for row in reader:
                if(row[0] == "Countdown"):
                    self.settings["Countdown"] = row[1].strip()
                elif (row[0] == "logLock"):
                    self.settings["logLock"] = row[1].strip()
                elif (row[0] == "logStranger"):
                    self.settings["logStranger"] = row[1].strip()


    # In base ai nomi dati come parametro crea gli encodings, fatto questo 
    # ritorna la lista creata
    def getEncodings(self, usernames):
        path = './Dataset/'
        for username in usernames:
            files = os.listdir(path + username)
            for img in files:
                directory = 'Dataset/' + username + "/" + img
                data = face_recognition.load_image_file(directory)
                self.known_face_encodings.append(face_recognition.face_encodings(data)[0])


    # Ritorna un array con i nomi degli utenti ripetuti per ogni immagine
    # di esso
    def getFinalUsers(self, names ):
        path = './Dataset/'
        for name in names:
            files = len(os.listdir(path + name))
            i = 0
            while i < files:
                self.known_face_names.append(name)
                i += 1


    # Metodo che prende gli encodings delle facce degli utenti
    # e utilizza la libreria face_recognition e machine learning
    # per trovare tutte le facce
    def getFaces(self, frame, names, encodings):
        face_locations = []
        face_encodings = []
        face_locations = face_recognition.face_locations(frame)
        face_encodings = face_recognition.face_encodings(frame, face_locations)

        face_name = -1
        for face_encoding in face_encodings:
            matches = face_recognition.compare_faces(encodings, face_encoding)

            face_distances = face_recognition.face_distance(encodings, face_encoding)
            best_match_index = np.argmin(face_distances)
            if matches[best_match_index]:
                face_name = names[best_match_index]
                break
        return face_name


    # Metodo che prende l'immagine creata dalla fotocamera
    def getFrame(self):
        if self.system == "macOS":
            video_capture = cv2.VideoCapture(0)
        elif self.system == "Linux":
            video_capture = cv2.VideoCapture(-1)
        else:
            video_capture = cv2.VideoCapture(0, cv2.CAP_DSHOW)

        ret, frame = video_capture.read()
        small_frame = cv2.resize(frame, (0, 0), fx=0.25, fy=0.25)
        rgb_small_frame = small_frame[:, :, ::-1]
        cv2.destroyAllWindows()
        return rgb_small_frame


    # Metodo che blocca lo schermo
    def lockScreen(self, system):
        if(self.system == "Windows"):
            ctypes.windll.user32.LockWorkStation()
        elif (system == "macOS"):
            loginPF = CDLL('/System/Library/PrivateFrameworks/login.framework/Versions/Current/login')
            result = loginPF.SACLockScreenImmediate()
        elif (system == "Linux"):
            cmd = '/bin/bash /etc/facelock/ubuntulock.sh'
            os.system(cmd)
        else:
            print("Non posso ancora bloccare questo dispositivo")


    # Metodo che verifica se existe gia un processo che stia utilizzando la fotocamera
    def checkIfProcessRunning(self, processName):
        for proc in psutil.process_iter():
            try:
                if processName.lower() in proc.name().lower():
                    return True
            except (psutil.NoSuchProcess, psutil.AccessDenied, psutil.ZombieProcess):
                pass
        return False


    # Metodo che permette di loggare un azione in base all'utente e all'azione eseguita
    def logAction(self, action, user):
        now = datetime.now()
        file = "./Logs/log_" + now.strftime("%Y-%m-%d") + ".log"
        f = open(file, "a")
        logging.getLogger(action)
        logging.basicConfig(filename=file,level=logging.DEBUG)
        logging.info(" "+action + " at " + now.strftime("%H:%M:%S")+ ", last user:" + user)
        # if(path.exists(file)):
        #     fileExists = True

        # f = open(file, "a")

        # with f:
            
        #     fnames = ['time', 'action', 'lastUser']
        #     writer = csv.DictWriter(f, fieldnames=fnames)    
        #     if(not fileExists):
        #         writer.writeheader()
        #     writer.writerow({'time' : now.strftime("%H:%M:%S"), 'action': action, 'lastUser': user})

        # f.close()


    # Metodo che aggiorna i valori nel Dictionary
    # in base ai valori presenti nel file Settings
    def updateSettings(self):
        while True:
            self.getSettings("Settings/settings.csv")
            self.getUsers()
            self.getEncodings(self.users)
            self.getFinalUsers(self.users)
            self.maxTimer = int(self.settings["Countdown"])
            sleep(5)


    # Metodo che verifica se la faccia che vede nella fotocamera è conosciuta
    def checkFace(self):
        while True:
            if self.checkIfProcessRunning("Camera") and self.system == "Windows":
                print("Camera is active in another process")
            else:
                self.face = self.getFaces(self.getFrame(), self.known_face_names, self.known_face_encodings)
                if self.face != -1:
                    print(self.face)
 
            sleep(1)


    # Metodo che verifica il stato del timer
    # e blocca il pc se non trova una faccia conosciuta
    def lock(self):
        sleep(5)
        timer = self.maxTimer
        while True:
            if(self.face == -1):
                print(timer)
                timer = timer - 1
                if timer == -1:
                    if(self.settings["logStranger"] == "1"):
                        self.logAction("Face not recognized", self.lastUser)
                    self.lockScreen(self.system)
                    if(self.settings["logLock"] == "1"):
                        self.logAction("Face not recognised for too long --> pc locked", self.lastUser)
                    timer = self.maxTimer
            else:
                self.lastUser = self.face
                timer = self.maxTimer
            sleep(1)
    

    # Metodo richiamato per creare e fare partire le Threads del programma
    def main(self):
        updateSettingsThread = threading.Thread(target=self.updateSettings)
        faceCheckThread = threading.Thread(target=self.checkFace)
        lockThread = threading.Thread(target=self.lock)
        updateSettingsThread.start()
        faceCheckThread.start()
        lockThread.start()

# Creazione e esecuzione dell'oggetto
obj = faceCheck()        
obj.main()