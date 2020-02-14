import face_recognition
import cv2
import numpy as np
from time import sleep

import os,sys
 
path = './Dataset/'

if len(sys.argv) == 2:
    path = sys.argv[1]
 
 
files = os.listdir(path)

for name in files:
    full_path = os.path.join(path, name)
    if os.path.isdir(full_path):
        print('Directory: ' + name)
    else:
        print('File: ' + name)
