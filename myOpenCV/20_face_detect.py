import cv2
import numpy as np

face_cascade = cv2.CascadeClassifier(
    "Resources/haarcascade_frontalface_default.xml")

img = cv2.imread("Resources/np_faces.jpg")
cv2.imshow("girl", img)


def detect_face(img):
    face_img = img.copy()
    gray = cv2.cvtColor(img, cv2.COLOR_RGB2GRAY)

    faces = face_cascade.detectMultiScale(gray)

    for (x, y, w, h) in faces:
        cv2.rectangle(face_img, (x, y,), (x+w, y+h), (0, 0, 255), 3)

    return face_img


result = detect_face(img)

cv2.imshow("detection", result)

cv2.waitKey(0)
