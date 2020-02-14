import face_recognition
import cv2
import numpy as np
from time import sleep
import os,sys
import shutil

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

    face_names = -1
    for face_encoding in face_encodings:
        matches = face_recognition.compare_faces(encodings, face_encoding)

        face_distances = face_recognition.face_distance(encodings, face_encoding)
        best_match_index = np.argmin(face_distances)
        if matches[best_match_index]:
            face_name = names[best_match_index]
            break
    return face_name

def getPicture():
    user = ""
    files = []
    isempty = True

    if len(sys.argv) == 2:
        user = sys.argv[1]
        print(user)
    else:
        user = "DEFAULT"

    if (not os.path.isdir("IMAGES/" + user + "/")):
        os.mkdir("IMAGES/" + user + "/")

    for file in os.listdir('IMAGES/' + user + "/"):
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

    shutil.move('.\\' + (str(files[0]) + '.jpg'), 'IMAGES\\' + user + '\\' +  str(files[0]) + '.jpg')

users = getUsers()
known_face_encodings = getEncodings(users)
known_face_names = getFinalUsers(users)





