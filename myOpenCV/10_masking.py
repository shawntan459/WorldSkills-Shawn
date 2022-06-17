import cv2
from cv2 import bitwise_and
import numpy as np

img = cv2.imread("Resources/lena.png", 0)

cv2.imshow('lena', img)

mask = np.zeros_like(img)
cv2.imshow('mask', mask)

mask_circle = cv2.circle(mask, (250, 250), (140), 255, -1)
cv2.imshow("circle", mask_circle)

masked = cv2.bitwise_and(mask_circle, img)
cv2.imshow("masked", masked)
cv2.waitKey(0)
