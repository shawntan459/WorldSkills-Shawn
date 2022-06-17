import cv2
from cv2 import bitwise_and
import numpy as np

blank = np.zeros((400, 400), dtype='uint8')

cv2.imshow('try', blank)

rect = cv2.rectangle(blank.copy(), (50, 50), (200, 350), (255), -1)
cv2.imshow('rect', rect)

circle = cv2.circle(blank.copy(), (200, 200), 100, 255, -1)
cv2.imshow('circle', circle)

bitwise_and = cv2.bitwise_and(circle, rect)
cv2.imshow('bitwise', bitwise_and)
cv2.waitKey(0)
