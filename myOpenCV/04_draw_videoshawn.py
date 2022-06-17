import cv2
cap = cv2.VideoCapture(0)

while True:
    r, frame = cap.read()

    cv2.rectangle(frame, (8, 430), (250, 350), (0, 0, 255), 1)
    cv2.putText(frame, "Shawn", (10, 400),
                cv2.FONT_HERSHEY_SIMPLEX, 0.6, (200, 200, 200), 2)
    cv2.imshow("my frame", frame)

    if cv2.waitKey(1) & 0xFF == ord('q'):
        break

cap.release()
cv2.destroyAllWindows
