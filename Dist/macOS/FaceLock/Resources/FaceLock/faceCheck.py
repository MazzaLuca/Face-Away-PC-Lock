import face_recognition
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

class faceCheck(object):

    #
    system = ""
    if platform == "darwin":
        system = "macOS"
    elif platform == "win32":
        system = "Windows"
    else: 
        system = "Linux"

    #
    face = ""

    #
    settings = {
        "Countdown": "0",
        "logLock": "0",
        "logStranger": "0"
    }

    #
    users = []

    #
    known_face_encodings = []

    #
    known_face_names = []

    #
    maxTimer = 0

    #
    lastUser = "nobody"

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
    #
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

    #
    #
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

    # 
    #
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

    #
    #
    def lockScreen(self, system):
        if(self.system == "Windows"):
            ctypes.windll.user32.LockWorkStation()
        elif (system == "macOS" or system == "Linux"):
            loginPF = CDLL('/System/Library/PrivateFrameworks/login.framework/Versions/Current/login')
            result = loginPF.SACLockScreenImmediate()
        else:
            print("Non posso ancora bloccare questo dispositivo")

    # 
    #
    def checkIfProcessRunning(self, processName):
        for proc in psutil.process_iter():
            try:
                if processName.lower() in proc.name().lower():
                    return True
            except (psutil.NoSuchProcess, psutil.AccessDenied, psutil.ZombieProcess):
                pass
        return False

    # Metodo che permette di loggare un azione in base all'utente e all'azione eseguita
    #
    def logAction(self, action, user):
        now = datetime.now()
        file = "Logs/log_" + now.strftime("%Y-%m-%d") + ".csv"
        fileExists = False

        if(path.exists(file)):
            fileExists = True

        f = open(file, "a")

        with f:
            
            fnames = ['time', 'action', 'lastUser']
            writer = csv.DictWriter(f, fieldnames=fnames)    
            if(not fileExists):
                writer.writeheader()
            writer.writerow({'time' : now.strftime("%H:%M:%S"), 'action': action, 'lastUser': user})

        f.close()

    #
    #
    def updateSettings(self):
        while True:
            self.getSettings("Settings/settings.csv")
            self.getUsers()
            self.getEncodings(self.users)
            self.getFinalUsers(self.users)
            self.maxTimer = int(self.settings["Countdown"])
            sleep(5)

    #
    #
    def checkFace(self):
        while True:
            if self.checkIfProcessRunning("Camera") and self.system == "Windows":
                print("Camera is active in another process")
            else:
                self.face = self.getFaces(self.getFrame(), self.known_face_names, self.known_face_encodings)
                if self.face != -1:
                    print(self.face)
 
            sleep(1)

    #
    #
    def lock(self):
        if self.system == "Windows":
            sleep(5)

        timer = self.maxTimer
        while timer >= -1:
            if(self.face == -1):
                print(timer)
                timer = timer - 1
                if(self.settings["logStranger"] == "1"):
                    self.logAction("Face not recognized", self.lastUser)
                if timer == -1:
                    self.lockScreen(self.system)
                    if(self.settings["logLock"] == "1"):
                        self.logAction("Face not recognised for too long --> pc locked", self.lastUser)
            else:
                self.lastUser = self.face
                timer = self.maxTimer
            sleep(1)
    
    #
    #
    def main(self):
        updateSettingsThread = threading.Thread(target=self.updateSettings)
        faceCheckThread = threading.Thread(target=self.checkFace)
        lockThread = threading.Thread(target=self.lock)
        updateSettingsThread.start()
        faceCheckThread.start()
        lockThread.start()

    
obj = faceCheck()        
obj.main()