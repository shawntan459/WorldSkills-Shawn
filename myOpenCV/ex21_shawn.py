import cv2
cap = cv2.VideoCapture(0)

while True:
    r, frame = cap.read()
    imgGray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
    imgBlur = cv2.GaussianBlur(imgGray, (7, 7), 0)
    imgCanny = cv2.Canny(imgBlur, 20, 100)
    cv2.rectangle(frame, (8, 430), (250, 350), (0, 0, 255), 1)
    cv2.putText(frame, "Shawn", (10, 400),
                cv2.FONT_HERSHEY_SIMPLEX, 0.6, (200, 200, 200), 2)
    cv2.imshow("my frame", imgCanny)
    if cv2.waitKey(1) & 0xFF == ord('q'):
        break

cap.release()
cv2.destroyAllWindows
