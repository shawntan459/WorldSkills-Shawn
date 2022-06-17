import cv2
import numpy as np

img = cv2.imread("Resources/car.jpg")
cv2.imshow("my image", img)

imgROI = img[310:360, 310:515]
cv2.imshow("ROI image", imgROI)

cv2.imwrite('SavedImage.jpg', imgROI)
cv2.waitKey(0)
