import cv2
import numpy as np

img = cv2.imread("Resources/lena.png", 0)
cv2.imshow('img', img)

mask = np.zeros_like(img)
# cv2.imshow('mask', mask)

mask_circle = cv2.circle(mask, (250, 250), (140), 255, -1)
cv2.imshow("circle", mask_circle)

blended_img = cv2.addWeighted(
    src1=img, alpha=0.5, src2=mask_circle, beta=0.5, gamma=0.0)
# src = source
cv2.imshow('blended', blended_img)
cv2.waitKey(0)
