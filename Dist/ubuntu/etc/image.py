import cv2
import os
import shutil
import sys
from sys import platform


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
	files.insert(int(files[0]) + 1)

camera = cv2.VideoCapture(0)
while True:
    return_value,image = camera.read()
    cv2.imshow('image',image)
    if cv2.waitKey(1)& 0xFF == ord(' '):
        cv2.imwrite('' + str(files[0]) + '.jpg',image)
        break
camera.release()
cv2.destroyAllWindows()

if platform == 'win32':
    shutil.move('.\\' + (str(files[0]) + '.jpg'), 'Dataset\\' + user + '\\' +  str(files[0]) + '.jpg')
else:
    shutil.move('./' + (str(files[0]) + '.jpg'), 'Dataset/' + user + '/' +  str(files[0]) + '.jpg')