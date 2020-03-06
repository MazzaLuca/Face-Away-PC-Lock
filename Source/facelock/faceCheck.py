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

users = getUsers()
known_face_encodings = getEncodings(users)
known_face_names = getFinalUsers(users)
timer = 5
while timer >= 0:
    if checkIfProcessRunning("Camera"):
        print("Camera is active in another process")
    else:
        face = getFaces(getFrame(), known_face_names, known_face_encodings) 
        if(face == -1):
            print(timer)
            timer = timer - 1
            if timer == -1:
                print("Face not recognised for too long")
                break
        else:
            print("Hi " + face)
            timer = 5

    sleep(0.5)
lockScreen(system)



        





