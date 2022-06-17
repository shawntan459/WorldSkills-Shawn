import cv2
import numpy as np
from sklearn.preprocessing import binarize

im = cv2.imread("Resources/thumbs_up_down.jpg")
cv2.imshow("tums", im)

gray = cv2.cvtColor(im, cv2.COLOR_BGR2GRAY)

_, binary = cv2.threshold(gray, 225, 255, cv2.THRESH_BINARY_INV)
cv2.imshow('binary', binary)

contours, hierarchy = cv2.findContours(
    binary, cv2.RETR_TREE, cv2.CHAIN_APPROX_SIMPLE)
image = cv2.drawContours(im, contours, -1, (0, 255, 0), 2)

cv2.imshow('result', image)

cv2.waitKey(0)
