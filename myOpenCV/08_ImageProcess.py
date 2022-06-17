import cv2
import numpy as np

img = cv2.imread("Resources/lena.png")
# cv2.imshow("image here", img)

imgGray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
# cv2.imshow("gray here", imgGray)

# 7,7 is the blur level, higher = more blue only odd numbers
imgBlur = cv2.GaussianBlur(imgGray, (7, 7), 0)
# cv2.imshow("blurred", imgBlur)

# (pixels that are smaller than 120 --> 0, more than 120 --> 255)
ret, thres1 = cv2.threshold(imgGray, 120, 255, cv2.THRESH_BINARY)
# cv2.imshow('binary', thres1)

imgCanny = cv2.Canny(imgBlur, 50, 100)
# cv2.imshow("Canny Image", imgCanny)

imgDialation = cv2.dilate(imgCanny, (3, 3), iterations=1)
cv2.imshow("imgD", imgDialation)

imgEroded = cv2.erode(imgDialation, (3, 3), iterations=1)
cv2.imshow("imgE", imgEroded)
cv2.waitKey(0)
