import cv2
import numpy as np

img = cv2.imread("Resources/tsy01.jpg")

face_cascade = cv2.CascadeClassifier(
    "Resources/haarcascade_frontalface_default.xml")
eye_cascade = cv2.CascadeClassifier("Resources/haarcascade_eye.xml")

cv2.imshow("OI", img)


def detect_face(img):
    face_img = img.copy()
    gray = cv2.cvtColor(img, cv2.COLOR_RGB2GRAY)

    faces = face_cascade.detectMultiScale(gray)

    for (x, y, w, h) in faces:
        cv2.rectangle(face_img, (x, y), (x+w, y+h), (0, 0, 255), 3)
        roi_gray = gray[y:y+h, x:x+w]
        roi_colour = face_img[y:y+h, x:x+w]

        eyes = eye_cascade.detectMultiScale(roi_gray, 1.2, 15)

        for (ex, ey, ew, eh) in eyes:
            cv2.rectangle(roi_colour, (ex, ey), (ex+ew, ey+eh), (0, 255, 0), 3)

    return face_img


result = detect_face(img)

cv2.imshow("detection", result)

cv2.waitKey(0)
