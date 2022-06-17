import cv2
import numpy as np

img = cv2.imread("Resources/car_on_road.jpg")

resized = cv2.resize(img, (768, 432))

while True:
    cv2.rectangle(resized, (220, 260), (335, 370), (255, 0, 0), 1)
    imgROI = resized[260:370, 220:335]
    cv2.imshow("my frame", resized)

    if cv2.waitKey(5) & 0xFF == ord('s'):
        cv2.imwrite('ROI.jpg',  imgROI)
        break


cv2.waitKey(0)
