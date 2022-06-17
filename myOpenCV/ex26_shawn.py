import cv2
cap = cv2.VideoCapture(0)

face_cascade = cv2.CascadeClassifier(
    "Resources/haarcascade_frontalface_default.xml")


def detect_face(img):
    face_img = img.copy()
    gray = cv2.cvtColor(img, cv2.COLOR_RGB2GRAY)

    faces = face_cascade.detectMultiScale(gray)

    for (x, y, w, h) in faces:
        cv2.rectangle(face_img, (x, y), (x+w, y+h), (0, 0, 255), 3)

    return face_img


while True:
    r, frame = cap.read()
    result = detect_face(frame)

    cv2.imshow("detection", result)

    if cv2.waitKey(1) & 0xFF == ord('q'):
        break


# cap.release()
# cv2.destroyAllWindows
