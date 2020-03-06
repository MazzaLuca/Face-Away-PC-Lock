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
import time
from datetime import datetime
from os import path




system = "";
if platform == "darwin":
        system = "macOS"
elif platform == "win32":
        system = "Windows"
else :
        system = "Linux"

# Metodo che ritorna la lista di utenti che hanno fatto le foto per poi
# essere riconosciuti. Per sapere quest'informazione l'algoritmo si dirige 
# in Dataset e conta le directory, ritornando i loro nomi.

def getUsers():
    path = './Dataset/'
    names = []
    files = os.listdir(path)
    for name in files:
        full_path = os.path.join(path, name + "/")
        if os.path.exists(full_path):
            names.append(name)
    return names

# Legge il file settings.csv e ne ricava le impostazioni desiderate dall'utente
def getSettings(file):
    data = [
        "",
        "",
        ""
    ]
    with open(file, newline='') as File:  
        reader = csv.reader(File)
        for row in reader:
            if(row[0] == "Countdown"):
                data[0] = row[1].strip()
            elif (row[0] == "logLock"):
                data[1] = row[1].strip()
            elif (row[0] == "logStranger"):
                data[2] = row[1].strip()
    return data
# In base ai nomi dati come parametro crea gli encodings, fatto questo 
# ritorna la lista creata
def getEncodings(usernames):
    path = './Dataset/'
    encs = []
    for username in usernames:
        files = os.listdir(path + username)
        for img in files:
            directory = 'Dataset/' + username + "/" + img
            print(directory)
            data = face_recognition.load_image_file(directory)
            encs.append(face_recognition.face_encodings(data)[0])
    return encs

# Ritorna un array con i nomi degli utenti ripetuti per ogni immagine
# di esso
def getFinalUsers(names):
    path = './Dataset/'
    finalNames = []
    for name in names:
        files = len(os.listdir(path + name))
        i = 0
        while i < files:
            finalNames.append(name)
            i += 1
    return finalNames

def getFaces(frame,names,encodings):
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

def TakePhoto():
    user = ""
    files = []
    isempty = True

    if len(sys.argv) == 2:
        user = sys.argv[1]
        print(user)
    else:
        user = "DEFAULT"

    if (not os.path.isdir("Dataset/" + user + "/")):
        os.mkdir("Dataset/" + user + "/")

    for file in os.listdir('Dataset/' + user + "/"):
        files.insert(0, file.strip(".jpg"))
        isempty = False
    if isempty:
        files.insert(0, "0")
    else:
        files[0] = int(files[0]) + 1

    camera = cv2.VideoCapture(0)
    while True:
        return_value,image = camera.read()
        cv2.imshow('image',image)
        if cv2.waitKey(1)& 0xFF == ord('s'):
            cv2.imwrite('' + str(files[0]) + '.jpg',image)
            break
    camera.release()
    cv2.destroyAllWindows()

    shutil.move('.\\' + (str(files[0]) + '.jpg'), 'Dataset\\' + user + '\\' +  str(files[0]) + '.jpg')

def getFrame():
    if system == "macOS":
         video_capture = cv2.VideoCapture(0)
    else:
        video_capture = cv2.VideoCapture(0, cv2.CAP_DSHOW)
    ret, frame = video_capture.read()
    small_frame = cv2.resize(frame, (0, 0), fx=0.25, fy=0.25)
    rgb_small_frame = small_frame[:, :, ::-1]
    cv2.destroyAllWindows()
    return rgb_small_frame

def lockScreen(system):
    if(system == "Windows"):
        ctypes.windll.user32.LockWorkStation()
    elif (system == "macOS" or system == "Linux"):
        loginPF = CDLL('/System/Library/PrivateFrameworks/login.framework/Versions/Current/login')
        result = loginPF.SACLockScreenImmediate()
    else:
        print("Non so ancora bloccare questo dispositivo")


def checkIfProcessRunning(processName):
    for proc in psutil.process_iter():
        try:
            if processName.lower() in proc.name().lower():
                return True
        except (psutil.NoSuchProcess, psutil.AccessDenied, psutil.ZombieProcess):
            pass
    return False;

# Metodo che permette di loggare un azione in base all'utente e all'azione eseguita
def logAction(action, user):
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





settings = getSettings("Settings/settings.csv")
users = getUsers()
known_face_encodings = getEncodings(users)
known_face_names = getFinalUsers(users)
timer = int(settings[0])
lastUser = "nobody"

start = int(round(time.time() * 1000))
while timer >= 0:
    now = int(round(time.time() * 1000))
    if checkIfProcessRunning("Camera"):
        print("Camera is active in another process")
    else:
        face = getFaces(getFrame(), known_face_names, known_face_encodings) 
        if(face == -1):
            print(timer)
            timer = timer - 1
            if(settings[2] == "1"):
                logAction("Face not recognized", lastUser)
            if timer == -1:
                if(settings[1] == "1"):
                    logAction("Face not recognised for too long --> pc locked", lastUser)
                break
        else:
            lastUser = face
            print("Hi " + face)
            timer = int(settings[0])

    if(now - start < 1000):
        sleep(float(1000 - (now - start))/ 1000.0)
lockScreen(system)