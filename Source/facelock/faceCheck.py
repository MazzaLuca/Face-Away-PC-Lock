import face_recognition
import cv2
import numpy as np
from time import sleep

import os,sys






def getUsers():
    path = './Dataset/'
    users = []
    files = os.listdir(path)
    for name in files:
        full_path = os.path.join(path, name + "/")
        if os.path.exists(full_path):
            print('Directory: ' + name)
            users.append(name)
        else:
            print('File: ' + name)
    return users

