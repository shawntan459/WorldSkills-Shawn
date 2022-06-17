from ftplib import MAXLINE
import cv2
from cv2 import minEnclosingCircle
import numpy as np

im = cv2.imread('Resources/lanes.jpg')

gray = cv2.cvtColor(im, cv2.COLOR_BGR2GRAY)
cv2.imshow('gray', gray)

edges = cv2.Canny(gray, 50, 200)
cv2.imshow("EI", edges)

lines = cv2.HoughLinesP(edges, rho=1, theta=np.pi/180, threshold=80,
                        lines=np.array([]), minLineLength=10, maxLineGap=250)

for line in lines:
    x1, y1, x2, y2 = line[0]
    cv2.line(im, (x1, y1), (x2, y2), (0, 0, 255), 2)


cv2.imshow('results', im)
cv2.waitKey(0)
cv2.destroyAllWindows()
