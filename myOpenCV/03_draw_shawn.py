import cv2
import numpy as np

img = np.zeros((512, 512, 3), dtype=np.uint8)
#cv2.imshow("blank image", img)

# img[:] = (255, 255, 255)
# cv2.imshow("Green here", img)


# cv2.line(img, (0, 0), (512, 512), (200, 50, 150), 3)

# cv2.rectangle(img, (0, 0), (250, 350), (0, 0, 255), 1)
# cv2.rectangle(img, (200, 100), (300, 50), (0, 255, 0), -1)
# cv2.circle(img, (400, 400), 200, (255, 255, 0), 3)

cv2.putText(img, "Hellow from Me!", (10, 450),
            cv2.FONT_HERSHEY_SIMPLEX, 0.6, (200, 200, 200), 2)

pts = np.array([[100, 50], [200, 300], [350, 490], [500, 100]], np.int32)
pts = pts.reshape((-1, 1, 2))
cv2.polylines(img, [pts], True, (0, 255, 255))
cv2.imshow("drawn", img)

#img[100:300, 400:500] = (0, 255, 0)
cv2.waitKey(0)
