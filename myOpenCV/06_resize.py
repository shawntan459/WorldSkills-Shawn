import cv2
import numpy as np

img = cv2.imread("Resources/sg_large.jpg")

resized = cv2.resize(img, (640, 480))

cv2.imshow("hello", resized)
cv2.waitKey(0)
